package net.thumbtack.school.boxes.library.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.school.library.Server.Server;
import net.thumbtack.school.library.dto.request.DeleteUserRequest;
import net.thumbtack.school.library.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.library.dto.request.LogoutUserDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.library.dto.response.ErrorResponse;
import net.thumbtack.school.library.exceptions.ErrorCode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testUser {
    @Test
    public void testRegisterUserWithAlreadyRegisterLogin() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String error = gson.toJson(new ErrorResponse(ErrorCode.ALREADY_REGISTER.getErrorString()));

        server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));
        String jsonResponse = server.registerUser(gson.toJson(
                new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));
        assertEquals(error, jsonResponse);
        server.stopServer(null);
    }

    @Test
    public void testRegisterUserIncorrectData() throws IOException {
        Gson gson = new Gson();
        Server server = new Server();
        server.startServer(null);
        String error = gson.toJson(new ErrorResponse(ErrorCode.NOT_VALIDATE_REGISTRATION.getErrorString()));
        String jsonResponse =
                server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "", "jude@gmail.com", "fairy")));
        assertEquals(error, jsonResponse);
        server.stopServer(null);
    }

    @Test
    public void testLoginUserThatNotRegister() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);
        String error = gson.toJson(new ErrorResponse(ErrorCode.ACCOUNT_NOT_FOUND.getErrorString()));
        String jsonResponse = server.login(gson.toJson(new LoginUserDtoRequest("jude@gmail.com", "fairy")));
        assertEquals(error, jsonResponse);
        server.stopServer(null);
    }

    @Test
    public void testDeleteAccountUser() throws IOException {
        Server server = new Server();
        Gson gson = new Gson();
        server.startServer(null);

        String error = gson.toJson(new ErrorResponse(ErrorCode.ACCOUNT_NOT_FOUND.getErrorString()));
        server.registerUser(gson.toJson(new RegisterUserDtoRequest("Jude", "Duarte", "jude@gmail.com", "fairy")));
        String token =  server.login(gson.toJson(new LoginUserDtoRequest("jude@gmail.com", "fairy")));
        JsonObject jsonObjectToken = gson.fromJson(token, JsonObject.class);
        server.logout(gson.toJson(new LogoutUserDtoRequest(jsonObjectToken.get("token").getAsString())));
        String token1 = server.login(gson.toJson(new LoginUserDtoRequest("jude@gmail.com", "fairy")));
        JsonObject jsonObjectToken1 = gson.fromJson(token1, JsonObject.class);
        server.deleteAccount(gson.toJson(new DeleteUserRequest(jsonObjectToken1.get("token").getAsString())));
        String jsonResponse = server.login(gson.toJson(new LoginUserDtoRequest("jude@gmail.com", "fairy")));
        assertEquals(error, jsonResponse);
        server.stopServer(null);
    }
}
