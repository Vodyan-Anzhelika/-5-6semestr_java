package net.thumbtack.school.library.dto.response;

public class LogoutUserDtoResponse {
    private String idUser;

    LogoutUserDtoResponse(){}

    public LogoutUserDtoResponse(String idUser){this.idUser=idUser;}

    public String getIdUser() {
        return idUser;
    }

    public void setToken(String token){this.idUser=token;}
}
