package net.thumbtack.school.library.dto.response;

public class DeleteUserDtoResponse {
    String idUser;

    DeleteUserDtoResponse(){}

    public DeleteUserDtoResponse(String idUser){this.idUser=idUser;}

    public String getIdUser() {
        return idUser;
    }

    public void setToken(String token){this.idUser=token;}
}
