package net.thumbtack.school.library.dto.request;

public class GetBooksDtoRequest {

    private String token;

    public GetBooksDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
