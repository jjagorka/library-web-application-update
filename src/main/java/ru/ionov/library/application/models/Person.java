package ru.ionov.library.application.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class Person {
    private int id_person;
    @NotEmpty(message = "full name should not be empty")
    @Size(min = 3, max = 150, message = "full name should be between 1 and 150 characters")
    private String full_name;
//    @NotEmpty(message = "year should not be empty")
//    @Size(min = 4, max = 4, message = "only 4 numbers")
    private int year_of_birth;

    public Person() {

    }

    public Person(int id_person, String full_name, int year_of_birth) {
        this.id_person = id_person;
        this.full_name = full_name;
        this.year_of_birth = year_of_birth;
    }

    public int getId_person() {
        return id_person;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }
}