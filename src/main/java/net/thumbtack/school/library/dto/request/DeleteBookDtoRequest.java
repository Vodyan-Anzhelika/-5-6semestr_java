package net.thumbtack.school.library.dto.request;

public class DeleteBookDtoRequest {
    private String idBook;
    private String token;


    public DeleteBookDtoRequest(String idBook, String token) {
        this.idBook = idBook;
        this.token = token;
    }

    public String getIdBook() {
        return idBook;
    }

    public String getToken() {
        return token;
    }
}
