package net.thumbtack.school.library.model;

import java.util.Objects;

public class User {

    private String firstName;
    private String lastName;
    private String login;
    private String password;



    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public User(){}

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (firstName == null || Objects.equals(firstName, user.firstName)) &&
                (lastName == null || Objects.equals(lastName, user.lastName)) &&
                (login == null || Objects.equals(login, user.login)) &&
                (password == null || Objects.equals(password, user.password));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, login, password);
    }


}

