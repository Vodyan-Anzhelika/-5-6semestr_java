package net.thumbtack.school.library.dto.response;

import java.util.List;

public class AddBooksDtoResponse {
    private List<String> idBooks;

    public AddBooksDtoResponse(List<String> idBooks) {
        this.idBooks = idBooks;
    }

    public List<String> getBooksId() {
        return idBooks;
    }
}
