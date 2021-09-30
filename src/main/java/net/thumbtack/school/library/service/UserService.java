package net.thumbtack.school.library.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import net.thumbtack.school.library.Server.JsonGetter;
import net.thumbtack.school.library.dao.UserDao;
import net.thumbtack.school.library.daoimpl.UserDaoImpl;
import net.thumbtack.school.library.dto.request.DeleteUserRequest;
import net.thumbtack.school.library.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.library.dto.request.LogoutUserDtoRequest;
import net.thumbtack.school.library.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.library.dto.response.*;
import net.thumbtack.school.library.exceptions.ErrorCode;
import net.thumbtack.school.library.model.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserService {
    private UserDao userDao = new UserDaoImpl();
    private Gson gson;
    private Type userType = new TypeToken<Map<String, User>>() {
    }.getType();

    public UserService() {
        gson = new Gson();
    }

    public void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            File file = new File(savedDataFileName);
            JsonObject jsonObject;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
                jsonObject = gson.fromJson(br, JsonObject.class);
                String jsonString = jsonObject.getAsJsonObject("users").toString();
                validateStartServerJson(jsonString);
                userDao.startServer(gson.fromJson(jsonString, userType));
            } catch (NullPointerException | IOException | ServiceException e) {
                gson.toJson(new ErrorResponse(e));
            }
        }

    }

    public StopServerVariables stopServer(StopServerVariables stopServerVariables) {
        stopServerVariables.setUsers(userDao.stopServer());
        return stopServerVariables;
    }


    public String registerUser(String jsonString) {
        String token;
        try {
            RegisterUserDtoRequest request = JsonGetter.getClassFromJson(jsonString, RegisterUserDtoRequest.class);
            validateRegistration(request);
            token = userDao.register(new User(request.getFirstName(), request.getLastName(), request.getLogin(), request.getPassword()));
            RegisterUserDtoResponse response = new RegisterUserDtoResponse(token);
            return gson.toJson(response);
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));}
    }

    public String loginUser(String jsonString) {
        try {
            LoginUserDtoRequest request = JsonGetter.getClassFromJson(jsonString, LoginUserDtoRequest.class);
            validateLogin(request);
            String token = userDao.login(request.getLogin(), request.getPassword());
            LoginUserDtoResponse response = new LoginUserDtoResponse(token);
            return gson.toJson(response);
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String logoutUser(String jsonString) {
        LogoutUserDtoResponse response;
        try {
            LogoutUserDtoRequest request = JsonGetter.getClassFromJson(jsonString, LogoutUserDtoRequest.class);
            response = new LogoutUserDtoResponse(userDao.logout(getTokenFromJson(request.getToken())));
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
        return gson.toJson(response);
    }

    public String deleteUser(String jsonString) {
        try {
            DeleteUserRequest request = JsonGetter.getClassFromJson(jsonString, DeleteUserRequest.class);
            DeleteUserDtoResponse response =
                    new DeleteUserDtoResponse(userDao.deleteUser(getTokenFromJson(request.getToken())));
            return gson.toJson(response);
        } catch (ServiceException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    private String getTokenFromJson(String token) throws ServiceException {
        if (token == null) {
            throw new ServiceException(ErrorCode.INCORRECT_TOKEN);
        }
        return token;
    }

    private void validateLogin(LoginUserDtoRequest request) throws ServiceException {
        if (request.getLogin() == null ||
                request.getPassword() == null ||
                request.getLogin().equals("") ||
                request.getPassword().equals("")) {
            throw new ServiceException(ErrorCode.NOT_VALIDATE_USER);
        }
    }

    private void validateRegistration(RegisterUserDtoRequest request) throws ServiceException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getLogin() == null || request.getPassword() == null ||
                request.getFirstName().equals("") || request.getLastName().equals("") || request.getLogin().equals("") || request.getPassword().equals("")) {
            throw new ServiceException(ErrorCode.NOT_VALIDATE_REGISTRATION);
        }
    }

    private void validateStartServerJson(String jsonString) throws ServiceException {
        if (jsonString == null || jsonString.equals("")) {
            throw new ServiceException(ErrorCode.NOT_VALIDATE_USER);
        }
    }

}
