package net.thumbtack.school.library.exceptions;

public enum ErrorCode {
    NOT_VALIDATE_USER,
    INCORRECT_TOKEN,
    WRONG_JSON ,
    NOT_VALIDATE_BOOK,
    ALREADY_REGISTER,
    ACCOUNT_NOT_FOUND,
    INCORRECT_PASSWORD,
    BOOK_DOES_NOT_EXIST,
    INCORRECT_USER,
    ALREADY_ORDER_BOOK,
    NOT_VALIDATE_REGISTRATION;

    private String errorString;

    ErrorCode(){}

    public String getErrorString(){return errorString;}
    ErrorCode(String errorString){this.errorString=errorString;}


}
