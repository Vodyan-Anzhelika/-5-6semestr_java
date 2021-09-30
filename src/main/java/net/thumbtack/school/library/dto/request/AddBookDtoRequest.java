package net.thumbtack.school.library.dto.request;
import java.util.List;

public class AddBookDtoRequest {
    private String nameBook;
    private List<String> authorBook;
    private List<String> sectionsOfBook;

    public AddBookDtoRequest() {
    }

    public AddBookDtoRequest(String nameBook, List<String> authorBook, List<String> sectionsOfBook) {
        this.nameBook = nameBook;
        this.authorBook = authorBook;
        this.sectionsOfBook = sectionsOfBook;
    }

    public String getName() {
        return nameBook;
    }

    public List<String> getAuthor() {
        return authorBook;
    }

    public List<String> getSections() {
        return sectionsOfBook;
    }
}
