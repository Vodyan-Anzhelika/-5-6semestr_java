package net.thumbtack.school.library.dto.request;

public class OrderBookDtoRequest {
    private String idBook;
    private String finalOrderDay;
    private String token;

    public OrderBookDtoRequest(String idBook, String finalOrderDay, String token) {
        this.idBook = idBook;
        this.finalOrderDay = finalOrderDay;
        this.token = token;
    }

    public String getBookId() {
        return idBook;
    }

    public String getFinishOrderDay() {
        return finalOrderDay;
    }

    public String getToken() {
        return token;
    }
}

