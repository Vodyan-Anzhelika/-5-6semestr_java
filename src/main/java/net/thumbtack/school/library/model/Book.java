package net.thumbtack.school.library.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Book {
    private String nameBook;
    private List<String> authorBook;
    private List<String> sectionsOfBook;
    private String idBook;
    private LocalDate finalOrderDay;
    private User owner;
    private User renter;

    public Book(String nameBook, List<String> authorBook, List<String> sectionsOfBook, String idBook, LocalDate finalOrderDay, User owner, User renter) {
        this.nameBook = nameBook;
        this.authorBook = authorBook;
        this.sectionsOfBook = sectionsOfBook;
        this.idBook = idBook;
        this.finalOrderDay = finalOrderDay;
        this.owner = owner;
        this.renter = renter;
    }

    public String getName() {
        return nameBook;
    }

    public void setName(String name) {
        this.nameBook = nameBook;
    }

    public List<String> getAuthor() {
        return authorBook;
    }

    public void setAuthor(List<String> author) {
        this.authorBook = authorBook;
    }

    public List<String> getSections() {
        return sectionsOfBook;
    }

    public void setSections(List<String> sections) {
        this.sectionsOfBook = sectionsOfBook;
    }

    public String getBookId() {
        return idBook;
    }

    public void setBookId(String idBook) {
        this.idBook = idBook;
    }

    public LocalDate getFinishOrderDay() {
        return finalOrderDay;
    }

    public void setFinishOrderDay(LocalDate finishOrderDay) {
        this.finalOrderDay = finishOrderDay;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getRenter() {
        return renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return (nameBook == null || Objects.equals(nameBook, book.nameBook)) &&
                (authorBook == null || Arrays.equals(new List[]{authorBook}, new List[]{book.authorBook})) &&
                (sectionsOfBook == null || Arrays.equals(new List[]{sectionsOfBook}, new List[]{book.sectionsOfBook})) &&
                (idBook == null || Objects.equals(idBook, book.idBook)) &&
                (finalOrderDay == null || Objects.equals(finalOrderDay, book.finalOrderDay));
    }

}