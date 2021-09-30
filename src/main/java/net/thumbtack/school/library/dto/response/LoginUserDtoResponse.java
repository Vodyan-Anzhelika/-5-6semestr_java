package net.thumbtack.school.library.dto.response;

public class LoginUserDtoResponse {
    private String token;

    public LoginUserDtoResponse(){}

    public LoginUserDtoResponse(String token){
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
