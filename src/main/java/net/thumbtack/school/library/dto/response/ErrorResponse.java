package net.thumbtack.school.library.dto.response;

import net.thumbtack.school.library.service.ServiceException;

public class ErrorResponse {
    private String error;

    public ErrorResponse(Exception e) {
        this.error = e.getMessage();
    }

    public ErrorResponse(String string) {
        this.error=string;
    }
}

