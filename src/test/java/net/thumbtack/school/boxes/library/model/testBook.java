package net.thumbtack.school.boxes.library.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.school.library.Server.Server;
import net.thumbtack.school.library.dto.request.*;
import net.thumbtack.school.library.dto.response.ErrorResponse;
import net.thumbtack.school.library.exceptions.ErrorCode;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testBook {
    @Test
    public void testValidateBook() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();

        server.startServer(null);
        String error = gson.toJson(new ErrorResponse(ErrorCode.NOT_VALIDATE_BOOK.getErrorString()));
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        String book1 = server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));

        Assert.assertEquals(error, book1);
        server.stopServer(null);
    }

    @Test
    public void testBookGetBookBySection() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("Js")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        String books = server.getBooksBySections(gson.toJson(new GetBooksBySectionsDtoRequest(new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        int result = gson.fromJson(books, JsonObject.class).getAsJsonArray("books").size();
        Assert.assertEquals(2, result);
        server.stopServer(null);
    }

    @Test
    public void testBookGetBookByAuthor() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);

        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("EvilKing",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("Js")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                Arrays.asList("Eldred", "Orlag"),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));

        String books = server.getBooksByAuthor(gson.toJson(
                new GetBooksByAuthorsDtoRequest(Arrays.asList("Lokk", "Orlag"),
                        jsonObjectToken.get("token").getAsString())));
        int result = gson.fromJson(books, JsonObject.class).getAsJsonArray("books").size();
        Assert.assertEquals(3, result);
        server.stopServer(null);
    }

    @Test
    public void testBookGetBookByName() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);

        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("EvilKing",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("Js")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                Arrays.asList("Eldred", "Orlag"),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        String books = server.getBooksByName(gson.toJson(
                new GetBooksByNameDtoRequest("EvilKing", jsonObjectToken.get("token").getAsString())));

        int result = gson.fromJson(books, JsonObject.class).getAsJsonArray("books").size();
        Assert.assertEquals(1, result);
        server.stopServer(null);

    }

    @Test
    public void testBookGetBookBySections() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("EvilKing",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("Js")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                Arrays.asList("Eldred", "Orlag"),
                Arrays.asList("UiUx", "Data"),
                jsonObjectToken.get("token").getAsString())));

        String books = server.getBooksBySections(gson.toJson(
                new GetBooksBySectionsDtoRequest(Arrays.asList("UiUx", "Data"), jsonObjectToken.get("token").getAsString())));
        int result = gson.fromJson(books, JsonObject.class).getAsJsonArray("books").size();
        Assert.assertEquals(1, result);
        server.stopServer(null);
    }

    @Test
    public void testBookOrder() throws IOException {
        Server server = new Server();
        server.startServer(null);
        Gson gson = new Gson();
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));
        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);

        String book1 = server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        JsonObject jsonObject = gson.fromJson(book1, JsonObject.class);
        String bookOrder = server.orderBook(gson.toJson(new OrderBookDtoRequest(jsonObject.get("idBook").getAsString(), "5", jsonObjectToken.get("token").getAsString())));
        Assert.assertEquals(book1, bookOrder);
        server.stopServer(null);
    }

    @Test
    public void testOrderAlreadyOrderedBook() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);


        String error = gson.toJson(new ErrorResponse(ErrorCode.ALREADY_ORDER_BOOK.getErrorString()));
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        String book1 = server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        JsonObject jsonObject = gson.fromJson(book1, JsonObject.class);
        server.orderBook(gson.toJson(new OrderBookDtoRequest(jsonObject.get("idBook").getAsString(), "5", jsonObjectToken.get("token").getAsString())));
        String bookOrder2 = server.orderBook(gson.toJson(new OrderBookDtoRequest(jsonObject.get("idBook").getAsString(), "5", jsonObjectToken.get("token").getAsString())));
        Assert.assertEquals(error, bookOrder2);
        server.stopServer(null);
    }

    @Test
    public void testBookDelete() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        String book1 = server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("Js")),
                jsonObjectToken.get("token").getAsString())));
        JsonObject jsonObject = gson.fromJson(book1, JsonObject.class);
        String bookDelete = server.deleteBook(gson.toJson(
                new DeleteBookDtoRequest(jsonObject.get("idBook").getAsString(), jsonObjectToken.get("token").getAsString())));
        Assert.assertEquals(book1, bookDelete);
        server.stopServer(null);
    }

    @Test
    public void testDeleteAlreadyDeletedBook() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);

        String error = gson.toJson(new ErrorResponse(ErrorCode.BOOK_DOES_NOT_EXIST.getErrorString()));
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        String book1 = server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("Js")),
                jsonObjectToken.get("token").getAsString())));
        JsonObject jsonObject = gson.fromJson(book1, JsonObject.class);
        server.deleteBook(gson.toJson(new DeleteBookDtoRequest(jsonObject.get("idBook").getAsString(), jsonObjectToken.get("token").getAsString())));
        String bookDelete = server.deleteBook(gson.toJson(new DeleteBookDtoRequest(jsonObject.get("idBook").getAsString(), jsonObjectToken.get("token").getAsString())));
        Assert.assertEquals(error, bookDelete);
        server.stopServer(null);
    }

    @Test
    public void testAddAllBooks() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));

        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        AddBookDtoRequest request1 = new AddBookDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")));
        AddBookDtoRequest request3 = new AddBookDtoRequest("CruelPrince",
                new ArrayList<>(Collections.singleton("Duarte")),
                new ArrayList<>(Collections.singleton("Js")));

        server.addBooks(gson.toJson(new AddBooksDtoRequest(Arrays.asList(request1, request3), jsonObjectToken.get("token").getAsString())));
        String booksReturn = server.getAllBooks(gson.toJson(new GetBooksDtoRequest(jsonObjectToken.get("token").getAsString())));

        int result = gson.fromJson(booksReturn, JsonObject.class).getAsJsonArray("books").size();
        Assert.assertEquals(2, result);
        server.stopServer(null);
    }

    @Test
    public void testWrongToken() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String errorCode = gson.toJson(new ErrorResponse(ErrorCode.INCORRECT_TOKEN.getErrorString()));
        String token = server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));
        JsonObject jsonObjectToken1 = gson.fromJson(token, JsonObject.class);
        server.logout(gson.toJson(new LogoutUserDtoRequest(jsonObjectToken1.get("token").getAsString())));
        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        String result = server.addBook(gson.toJson(new AddBookWithTokenDtoRequest("Data",
                new ArrayList<>(Collections.singleton("Lokk")),
                new ArrayList<>(Collections.singleton("UiUx")),
                jsonObjectToken.get("token").getAsString())));
        Assert.assertEquals(errorCode, result);
        server.stopServer(null);
    }
}