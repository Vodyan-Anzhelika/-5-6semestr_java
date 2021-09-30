package net.thumbtack.school.library.dto.request;

import java.util.List;

public class GetBooksBySectionsDtoRequest {
    private List<String> sectionsOfBook;
    private String token;
    public GetBooksBySectionsDtoRequest(List<String> sectionsOfBook, String token) {
        this.sectionsOfBook = sectionsOfBook;
        this.token=token;
    }

    public List<String> getSections() {
        return sectionsOfBook;
    }

    public String getToken() {
        return token;
    }
}