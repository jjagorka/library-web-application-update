package ru.ionov.library.application.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов длиной")
    private String name_of_book;
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    private String author;

    @Max(value = 2023, message = "Год публикации должен быть не больше, чем 2023")
    private int publishing_year;
    public Book(String name_of_book, String author, int publishing_year) {
        this.name_of_book = name_of_book;
        this.author = author;
        this.publishing_year = publishing_year;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }


    public String getName_of_book() {
        return name_of_book;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishing_year() {
        return publishing_year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_of_book(String name_of_book) {
        this.name_of_book = name_of_book;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishing_year(int publishing_year) {
        this.publishing_year = publishing_year;
    }
}
