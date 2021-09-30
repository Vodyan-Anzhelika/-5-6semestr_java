package net.thumbtack.school.library.dto.request;


import java.util.List;

public class GetBooksByAuthorsDtoRequest {
    private List<String> authorsBook;
    private String token;

    public GetBooksByAuthorsDtoRequest(List<String> authorsBook, String token) {
        this.authorsBook = authorsBook;
        this.token = token;
    }

    public List<String> getAuthors() {
        return authorsBook;
    }

    public String getToken() {
        return token;
    }
}