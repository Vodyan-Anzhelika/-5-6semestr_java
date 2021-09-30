package net.thumbtack.school.library.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import net.thumbtack.school.library.Server.JsonGetter;
import net.thumbtack.school.library.dao.BookDao;
import net.thumbtack.school.library.daoimpl.BookDaoImpl;
import net.thumbtack.school.library.dto.request.*;
import net.thumbtack.school.library.dto.response.*;
import net.thumbtack.school.library.exceptions.ErrorCode;
import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookService {
    private BookDao bookDao = new BookDaoImpl();
    private Gson gson;
    private Type bookType = new TypeToken<Map<String, Book>>() {
    }.getType();

    public BookService() {
        gson = new Gson();
    }

    public void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            File file = new File(savedDataFileName);
            JsonObject jsonObject;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                jsonObject = gson.fromJson(br, JsonObject.class);
                String jsonString = jsonObject.getAsJsonObject("books").toString();
                if (!jsonString.equals("") && jsonString != null) {
                    Map<String, Book> map = gson.fromJson(jsonString, bookType);
                    bookDao.startServer(map);
                }
            } catch (NullPointerException | IOException e) {
                gson.toJson(new ErrorResponse(e));
            }
        }
    }


    public StopServerVariables stopServer(StopServerVariables stopServerVariables) {
        stopServerVariables.setBooks(bookDao.stopServer());
        return stopServerVariables;
    }

    public String getBooks(String jsonString) {
        try {
            GetBooksDtoRequest request = JsonGetter.getClassFromJson(jsonString, GetBooksDtoRequest.class);
            getUserByToken(request.getToken());
            GetBooksDtoResponse response = new GetBooksDtoResponse(bookDao.getBooks());
            return gson.toJson(response);

        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String getBooksByAuthors(String jsonString) {
        try {
            GetBooksByAuthorsDtoRequest request = JsonGetter.getClassFromJson(jsonString, GetBooksByAuthorsDtoRequest.class);
            validateGetBooksByAuthors(request.getAuthors());
            getUserByToken(request.getToken());
            GetBooksDtoResponse response = new GetBooksDtoResponse(bookDao.getBooksByAuthors(request.getAuthors()));
            return gson.toJson(response);

        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String getBooksBySection(String jsonString) {
        try {
            GetBooksBySectionsDtoRequest request = JsonGetter.getClassFromJson(jsonString, GetBooksBySectionsDtoRequest.class);
            validateGetBooksBySections(request.getSections());
            getUserByToken(request.getToken());
            GetBooksDtoResponse response = new GetBooksDtoResponse(bookDao.getBooksBySections(request.getSections()));
            return gson.toJson(response);

        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String getBooksByName(String jsonString) {
        try {
            GetBooksByNameDtoRequest request = JsonGetter.getClassFromJson(jsonString, GetBooksByNameDtoRequest.class);
            validateGetBooksByName(request.getNameBook());
            getUserByToken(request.getToken());
            GetBooksDtoResponse response = new GetBooksDtoResponse(bookDao.getBooksByName(request.getNameBook()));
            return gson.toJson(response);

        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String addBook(String jsonString) {
        try {
            AddBookWithTokenDtoRequest request = JsonGetter.getClassFromJson(jsonString, AddBookWithTokenDtoRequest.class);
            validateAddBook(request);
            Book book = new Book(request.getName(), request.getAuthor(), request.getSections(), null, null, getUserByToken(request.getToken()), null);
            AddBookDtoResponse response = new AddBookDtoResponse(bookDao.addBook(book));
            return gson.toJson(response);
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String addBooks(String jsonString) {
        try {
            AddBooksDtoRequest request = JsonGetter.getClassFromJson(jsonString, AddBooksDtoRequest.class);
            if (request.getAddBooksList() != null) {
                List<Book> bookList = new ArrayList<>();
                validateAddBooks(request.getAddBooksList());
                for (AddBookDtoRequest r : request.getAddBooksList()) {
                    bookList.add(new Book(r.getName(), r.getAuthor(), r.getSections(), null, null, getUserByToken(request.getToken()), null));
                }
                AddBooksDtoResponse response = new AddBooksDtoResponse(bookDao.addBooks(bookList));
                return gson.toJson(response);
            } else {
                throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
            }
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String orderBook(String jsonString) {
        try {
            OrderBookDtoRequest request = JsonGetter.getClassFromJson(jsonString, OrderBookDtoRequest.class);
            validateOrderBook(request);
            OrderBookDtoResponse response = new OrderBookDtoResponse
                    (bookDao.orderBook(request.getBookId(), request.getFinishOrderDay(), getUserByToken(request.getToken())));
            return gson.toJson(response);
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String deleteBook(String jsonString) {
        try {

            DeleteBookDtoRequest request = JsonGetter.getClassFromJson(jsonString, DeleteBookDtoRequest.class);
            validateDeleteBook(request);
            DeleteBookDtoResponse response = new DeleteBookDtoResponse
                    (bookDao.deleteBook(request.getIdBook(), getUserByToken(request.getToken())));
            return gson.toJson(response);
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    private User getUserByToken(String token) throws ServiceException {
        User user;
        user = bookDao.getUserByToken(token);
        if (token == null || user == null) {
            throw new ServiceException(ErrorCode.INCORRECT_TOKEN);
        }
        return user;
    }

    private void validateDeleteBook(DeleteBookDtoRequest request) throws ServiceException {
        if (request.getIdBook() == null || request.getIdBook().equals("")) {
            throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
        }
    }

    private void validateOrderBook(OrderBookDtoRequest request) throws ServiceException {
        if (request.getBookId() == null || request.getBookId().equals("") ||
                request.getFinishOrderDay() == null || request.getFinishOrderDay().equals("")) {
            throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
        }
    }

    private void validateAddBook(AddBookWithTokenDtoRequest request) throws ServiceException {
        if (request.getName() == null || request.getAuthor() == null || request.getSections() == null ||
                request.getName().equals("") || request.getAuthor().size() == 0 || request.getSections().size() == 0) {
            throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
        }
    }

    private void validateAddBooks(List<AddBookDtoRequest> books) throws ServiceException {
        for (AddBookDtoRequest book : books) {
            if (book.getName() == null || book.getAuthor() == null || book.getSections() == null ||
                    book.getName().equals("") || book.getAuthor().size() == 0 || book.getSections().size() == 0) {
                throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
            }
        }
    }

    private void validateGetBooksByAuthors(List<String> authors) throws ServiceException {
        for (String author : authors) {
            if (author == null || author.equals("")) {
                throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
            }
        }
    }

    private void validateGetBooksBySections(List<String> sections) throws ServiceException {
        for (String section : sections) {
            if (section == null || section.equals("")) {
                throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
            }
        }
    }

    private void validateGetBooksByName(String bookName) throws ServiceException {
        if (bookName == null || bookName.equals("")) {
            throw new ServiceException(ErrorCode.NOT_VALIDATE_BOOK);
        }
    }

}
