package net.thumbtack.school.library.dto.request;

import java.util.ArrayList;
import java.util.List;

public class AddBooksDtoRequest {
    private List<AddBookDtoRequest> addBooksList = new ArrayList<>();
    private String token;

    public AddBooksDtoRequest(List<AddBookDtoRequest> addBooksList, String token) {
        this.addBooksList = addBooksList;
        this.token = token;
    }

    public List<AddBookDtoRequest> getAddBooksList() {
        return addBooksList;
    }

    public String getToken() {
        return token;
    }
}