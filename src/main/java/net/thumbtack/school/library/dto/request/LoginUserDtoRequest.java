package net.thumbtack.school.library.dto.request;

public class LoginUserDtoRequest {
    private String login;
    private String password;

    public LoginUserDtoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginUserDtoRequest(){

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
