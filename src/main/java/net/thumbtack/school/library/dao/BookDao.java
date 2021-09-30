package net.thumbtack.school.library.dao;

import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.User;
import net.thumbtack.school.library.service.ServiceException;
import java.util.List;
import java.util.Map;
public interface BookDao {
        String addBook(Book book) throws ServiceException;

        List<String> addBooks(List<Book> book) throws ServiceException;

        String deleteBook(String idBook, User user) throws ServiceException;

        List<Book> getBooks();

        String orderBook(String idBook, String date, User user) throws ServiceException;

        User getUserByToken(String token);

        void startServer(Map<String, Book> map);

        List<Book> getBooksByAuthors(List<String> authorsBook);

        List<Book> getBooksBySections(List<String> sectionsOfBook);

        List<Book> getBooksByName(String nameBook);

        Map<String, Book> stopServer();
    }
