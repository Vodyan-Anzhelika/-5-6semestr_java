package net.thumbtack.school.library.dto.response;

import net.thumbtack.school.library.model.Book;
import net.thumbtack.school.library.model.User;

import java.util.Map;

public class StopServerVariables {
    private Map<String,User> users;
    private Map<String,Book> books;

    public StopServerVariables(Map<String, User> users, Map<String, Book> books){
        this.users=users;
        this.books=books;
    }

    public StopServerVariables(){
    }

    public void setUsers(Map<String,User>users){this.users=users;}

    public void setBooks(Map<String,Book>books){this.books=books;}

}
