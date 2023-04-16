package ru.ionov.library.application.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id_book;
    private int id_person = 0;

    @NotEmpty(message = "name of book should not be empty")
    @Size(min = 1, max = 150, message = "name of book should be between 1 and 50 characters")
    private String name_of_book;
    @NotEmpty(message = "full name should not be empty")
    @Size(min = 1, max = 150, message = "full name should be between 1 and 150 characters")
    private String full_name;

    private int publishing_year;
    public Book(int id_book, String name_of_book, String full_name, int date_of_birth) {
        this.id_book = id_book;
        this.name_of_book = name_of_book;
        this.full_name = full_name;
        this.publishing_year = date_of_birth;
    }

    public Book(int id_book,int id_person, String name_of_book, String full_name, int date_of_birth) {
        this.id_book = id_book;
        this.id_person = id_person;
        this.name_of_book = name_of_book;
        this.full_name = full_name;
        this.publishing_year = date_of_birth;
    }

    public Book() {

    }

    public int getId_book() {
        return id_book;
    }

    public int getId_person() {
        return id_person;
    }

    public String getName_of_book() {
        return name_of_book;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getPublishing_year() {
        return publishing_year;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public void setName_of_book(String name_of_book) {
        this.name_of_book = name_of_book;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setPublishing_year(int publishing_year) {
        this.publishing_year = publishing_year;
    }
}
