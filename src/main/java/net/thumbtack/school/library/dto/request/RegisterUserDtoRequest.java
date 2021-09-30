package net.thumbtack.school.library.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.library.model.User;

public class RegisterUserDtoRequest {
    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public RegisterUserDtoRequest(){}


    public RegisterUserDtoRequest(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }


    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getLogin() { return login; }

    public String getPassword() { return password; }

}




