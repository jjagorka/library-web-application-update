package ru.ionov.library.application.models;

import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_of_book")
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов длиной")
    private String nameOfBook;
    @Column(name = "author")
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    private String author;

    @Column(name = "publishing_year")
    @Max(value = 2023, message = "Год публикации должен быть не больше, чем 2023")
    private int publishingYear;

    @Column(name = "taking_time")
    private Date takingTime;

    @Transient
    private boolean isProphetic;

    @ManyToOne
    @JoinColumn(name="person_id",referencedColumnName = "id")
    private Person person;

    public Book(String nameOfBook, String author, int publishingYear) {
        this.nameOfBook = nameOfBook;
        this.author = author;
        this.publishingYear = publishingYear;
    }

    public Book() {

    }

    public int getId() {
        return id;
    }

    public Date getTakingTime() {
        return takingTime;
    }

    public void setTakingTime(Date takingTime) {
        this.takingTime = takingTime;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public boolean getIsProphetic() {
        if (this.getTakingTime()!=null){
            long milliseconds = new Date().getTime()-this.getTakingTime().getTime();
            return (milliseconds / (24 * 60 * 60 * 1000))>10;
        }
        return false;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setNameOfBook(String name_of_book) {
        this.nameOfBook = name_of_book;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishingYear(int publishing_year) {
        this.publishingYear = publishing_year;
    }
}
