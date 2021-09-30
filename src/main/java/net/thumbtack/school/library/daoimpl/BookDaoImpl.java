package net.thumbtack.school.library.daoimpl;

import net.thumbtack.school.library.dao.BookDao;
import net.thumbtack.school.library.database.Database;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.User;
import net.thumbtack.school.library.service.ServiceException;

import java.util.List;
import java.util.Map;

public class BookDaoImpl implements BookDao {
    private Database database = Database.getInstance();

    public BookDaoImpl() {
    }

    @Override
    public String addBook(Book book) {
        return database.insertBook(book);
    }

    @Override
    public List<String> addBooks(List<Book> books) {
        return database.insertBooks(books);
    }

    @Override
    public String deleteBook(String idBook, User user) throws ServiceException {
        database.deleteBook(idBook, user);
        return idBook;
    }

    @Override
    public List<Book> getBooks() {
        return database.getAllBooks();
    }

    @Override
    public String orderBook(String idBook, String date, User user) throws ServiceException {
        return database.orderBook(idBook, date, user);
    }

    @Override
    public User getUserByToken(String token) {
        return database.getUserByToken(token);
    }

    @Override
    public void startServer(Map<String, Book> map) {

        database.setMapBook(map);
    }

    @Override
    public List<Book> getBooksByAuthors(List<String> authorsBook) {
        return database.getBooksByAuthors(authorsBook);
    }
    @Override
    public List<Book> getBooksBySections(List<String> sectionsOfBook) {
        return database.getBooksBySection(sectionsOfBook);
    }

    @Override
    public List<Book> getBooksByName(String nameBook) {
        return database.getBooksByName(nameBook);
    }

    @Override
    public Map<String, Book> stopServer() {
        return database.getMapBook();
    }
}

