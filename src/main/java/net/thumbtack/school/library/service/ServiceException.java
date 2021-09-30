package net.thumbtack.school.library.service;

import net.thumbtack.school.library.exceptions.ErrorCode;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message){super(message);}

    public ServiceException(ErrorCode e){super(e.getErrorString());}
}
