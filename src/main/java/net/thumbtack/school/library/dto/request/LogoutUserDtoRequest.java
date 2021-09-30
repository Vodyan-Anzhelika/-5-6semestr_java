package net.thumbtack.school.library.dto.request;

public class LogoutUserDtoRequest {
    private String token;

    public LogoutUserDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}