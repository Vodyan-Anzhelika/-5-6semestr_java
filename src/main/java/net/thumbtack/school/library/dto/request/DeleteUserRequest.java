package net.thumbtack.school.library.dto.request;

public class DeleteUserRequest {
    private String token;


    public DeleteUserRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
