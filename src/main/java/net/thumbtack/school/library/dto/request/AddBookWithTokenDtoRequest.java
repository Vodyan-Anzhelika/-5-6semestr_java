package net.thumbtack.school.library.dto.request;

import java.util.List;

public class AddBookWithTokenDtoRequest extends AddBookDtoRequest{
    private String token;

    public AddBookWithTokenDtoRequest(String nameBook, List<String> authorBook, List<String> sectionsOfBook, String token) {
        super(nameBook, authorBook, sectionsOfBook);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
