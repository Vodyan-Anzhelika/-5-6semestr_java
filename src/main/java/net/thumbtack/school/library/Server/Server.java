package net.thumbtack.school.library.Server;

import com.google.gson.Gson;
import net.thumbtack.school.library.dto.response.StopServerVariables;
import net.thumbtack.school.library.service.BookService;
import net.thumbtack.school.library.service.UserService;

import java.io.IOException;

//класс, принимающий запросы (вызовы методов данного класса). В данном классе определены все сервисы.
// Запросы приходят в методы класса Server в виде json- строки. Сервер возвращает ответ также в виде json-строки.
public class Server {
    private BookService bookService = new BookService();
    private UserService userService = new UserService();
    private Gson gson;

    public Server() {
        gson = new Gson();
    }

    public void startServer(String savedDataFileName) throws IOException {
        userService.startServer(savedDataFileName);
        bookService.startServer(savedDataFileName);
    }

    public void stopServer(String savedDataFileName) throws IOException {
        StopServerVariables stopServerVariables = new StopServerVariables();
        stopServerVariables = userService.stopServer(stopServerVariables);
        stopServerVariables = bookService.stopServer(stopServerVariables);
        Utils.stopServer(savedDataFileName, stopServerVariables);
    }

    public String getBooks(String json) {
        return bookService.getBooks(json);
    }

    public String addBook(String json) {
        return bookService.addBook(json);
    }

    public String addBooks(String json) {
        return bookService.addBooks(json);
    }

    public String deleteBook(String json) {
        return bookService.deleteBook(json);
    }

    public String orderBook(String json) {
        return bookService.orderBook(json);
    }

    public String registerUser(String json) {
        return userService.registerUser(json);
    }

    public String login(String json) {
        return userService.loginUser(json);
    }

    public String logout(String json) {
        return userService.logoutUser(json);
    }

    public String deleteAccount(String json) {
        return userService.deleteUser(json);
    }

    public String getAllBooks(String json) {
        return bookService.getBooks(json);
    }

    public String getBooksByName(String json) {
        return bookService.getBooksByName(json);
    }

    public String getBooksByAuthor(String json) {
        return bookService.getBooksByAuthors(json);
    }

    public String getBooksBySections(String json) {
        return bookService.getBooksBySection(json);
    }
}