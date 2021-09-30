package net.thumbtack.school.library.database;

import net.thumbtack.school.library.Server.Utils;
import net.thumbtack.school.library.exceptions.ErrorCode;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.User;
import net.thumbtack.school.library.service.ServiceException;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.*;

// класс, содержит в себе все необходимые структуры данных , то есть различные коллекции (Map, Set, List и т.д.)
// классов моделей. Для реализации данного класса следует использовать паттерн Singleton
public class Database {
    private static Database instance;
    private Map<String, User> mapUser = new HashMap<>();
    private Map<String, Book> mapBook = new HashMap<>();
    private BidiMap<String, User> mapToken = new DualHashBidiMap<>();
    private MultiValuedMap<String, Book> mapBooksAuthors = new HashSetValuedHashMap<>();
    private MultiValuedMap<String, Book> mapBooksSections = new HashSetValuedHashMap<>();
    private MultiValuedMap<String, Book> mapBooksName = new HashSetValuedHashMap<>();
    private MultiValuedMap<User, Book> mapUserBooks = new HashSetValuedHashMap<>();

    public Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public String insertUser(String login, User user) throws ServiceException {
        if (mapUser.putIfAbsent(login, user) != null) {
            throw new ServiceException(ErrorCode.ALREADY_REGISTER);
        }
        String token = UUID.randomUUID().toString();
        mapToken.put(token, user);
        return token;
    }

    public void deleteUser(String token) throws ServiceException {
        User user = mapToken.remove(token);
        if (user == null) {
            throw new ServiceException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        mapUser.remove(user.getLogin());

    }

    public String logout(String token) throws ServiceException {
        User user = mapToken.remove(token);
        if (user == null) {
            throw new ServiceException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        return user.getLogin();
    }

    public String login(String login, String password) throws ServiceException {
        User user = mapUser.get(login);
        if (user == null) {
            throw new ServiceException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        if (!user.getPassword().equals(password)) {
            throw new ServiceException(ErrorCode.INCORRECT_PASSWORD);
        }
        String token = UUID.randomUUID().toString();
        mapToken.put(token, mapUser.get(login));
        return token;
    }

    public String insertBook(Book book) {
        String idBook = UUID.randomUUID().toString();
        book.setBookId(idBook);
        mapBook.put(idBook, book);
        insertBookInCollections(book);
        return idBook;
    }

    public List<String> insertBooks(List<Book> books) {
        List<String> idBooks = new ArrayList<>();
        books.forEach(book -> {
            String idBook = UUID.randomUUID().toString();
            idBooks.add(idBook);
            book.setBookId(idBook);
            mapBook.put(idBook, book);
            insertBookInCollections(book);
        });
        return idBooks;
    }

    public void deleteBook(String idBook, User user) throws ServiceException {
        Book book = mapBook.get(idBook);
        if (book == null) {
            throw new ServiceException(ErrorCode.BOOK_DOES_NOT_EXIST);
        }
        if (!book.getOwner().getLogin().equals(user.getLogin())) {
            throw new ServiceException(ErrorCode.INCORRECT_USER);
        }
        mapBook.remove(idBook);
        deleteBookFromCollections(book);
    }

    public void insertBookInCollections(Book book) {
        for (String authorsBook : book.getAuthor()) {
            mapBooksAuthors.put(authorsBook, book);
        }
        for (String sectionsOfBook : book.getSections()) {
            mapBooksSections.put(sectionsOfBook, book);
        }
        mapBooksName.put(book.getName(), book);

    }

    public void deleteBookFromCollections(Book book) {
        for (String authorsBook : book.getAuthor()) {
            mapBooksAuthors.removeMapping(authorsBook, book);
        }
        for (String sectionsOfBook : book.getSections()) {
            mapBooksSections.removeMapping(sectionsOfBook, book);
        }
        mapBooksName.removeMapping(book.getAuthor(), book);

    }

    public String orderBook(String idBook, String date, User renter) throws ServiceException {
        Book book = mapBook.get(idBook);
        if (book == null) {
            throw new ServiceException(ErrorCode.BOOK_DOES_NOT_EXIST);
        }
        if (!Utils.checkDate(book.getFinishOrderDay())) {
            throw new ServiceException(ErrorCode.ALREADY_ORDER_BOOK);
        }
        book.setFinishOrderDay(Utils.calculateDate(date));
        book.setRenter(renter);
        mapBook.put(idBook, book);
        mapUserBooks.put(renter, book);
        return idBook;
    }

    public List<Book> getBooksByAuthors(List<String> authorsBook) {
        Set<Book> authorsSet = new HashSet<>();
        for (String author : authorsBook) {
            authorsSet.addAll(mapBooksAuthors.get(author));
        }
        return new ArrayList<>(authorsSet);
    }

    public List<Book> getBooksBySection(List<String> sections) {
        Set<Book> sectionsSet = new HashSet<>();
        boolean firstSet = false;
        for (String section : sections) {
            if (!firstSet) {
                sectionsSet = (Set) mapBooksSections.get(section);
                firstSet = true;
            }
            sectionsSet.retainAll(mapBooksSections.get(section));
        }
        return new ArrayList<>(sectionsSet);
    }

    public List<Book> getBooksByName(String nameBook) {
        return new ArrayList<>(mapBooksName.get(nameBook));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(mapBook.values());
    }

    public User getUserByToken(String token) {
        return mapToken.get(token);
    }

    public Map<String, Book> getMapBook() {
        instance = null;
        return mapBook;
    }

    public void setMapBook(Map<String, Book> map) {
        this.mapBook = map;
    }

    public Map<String, User> getMapUser() {
        return mapUser;
    }

    public void setMapUser(Map<String, User> map) {
        this.mapUser = map;
    }
}



//Теперь давайте обсудим возврат результата. Отметим, что возвращаемый результат может быть как очень простым,
// так и очень сложным. Например, при регистрации участника возвращается json с единственным полем - “token”,
// а при подведении итогов возвращается достаточно сложный ответ, содержащий в себе один или несколько списков.
//
//Последовательность действий для возврата результата та же, что и при обработке запроса, только в обратном направлении.
// После того, как метод класса DataBase произвел операцию с базой данных, он возвращает какой-то результат классу DAO,
// тот, в свою очередь, передает его классу сервиса, а класс сервиса на основании этого результата создает класс DTO,
// но теперь уже не DTO запроса, а DTO ответа. В этом DTO ответа должны быть те поля, которые и предполагается вернуть
// в качестве результата метода. Например, в классе RegisterUserDtoResponse вполне может быть одно лишь поле “token”,
// а в классе GetBooksDtoResponse должен быть список из заявок и иная требуемая информация.
//
//Вышеупомянутые классы и интерфейсы следует разместить в своих пакетах.
// Например, для DAO - интерфейсов нужно создать пакет net.thumbtack.school.library.dao,
// для их имплементаций - net.thumbtack.school.library.daoimpl, для сервисов - net.thumbtack.school.library.service,
// для DTO - net.thumbtack.school.library.dto.request и net.thumbtack.school.library.dto.response и т.д.