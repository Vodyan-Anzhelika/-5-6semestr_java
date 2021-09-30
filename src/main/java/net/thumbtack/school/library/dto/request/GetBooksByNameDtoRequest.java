package net.thumbtack.school.library.dto.request;

import javax.swing.plaf.SpinnerUI;

public class GetBooksByNameDtoRequest {
    private String nameBook;
    private String token;

    public GetBooksByNameDtoRequest(String nameBook, String token) {
        this.nameBook = nameBook;
        this.token = token;
    }

    public String getNameBook() {
        return nameBook;
    }

    public String getToken() {
        return token;
    }
}
