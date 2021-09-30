package net.thumbtack.school.library.dto.response;

import net.thumbtack.school.library.model.Book;

import java.util.List;

public class GetBooksDtoResponse {
    private List<Book> books;


    public GetBooksDtoResponse(List<Book> books) {
        this.books = books;
    }

    GetBooksDtoResponse() {
    }
}
