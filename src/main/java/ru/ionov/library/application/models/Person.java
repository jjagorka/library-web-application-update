package ru.ionov.library.application.models;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длино")
    private String full_name;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    @Max(value = 2023, message = "Год рождения должен быть не больше, чем 2023")
    private int year_of_birth;

    public Person() {

    }

    public Person(String full_name, int year_of_birth) {
        this.full_name = full_name;
        this.year_of_birth = year_of_birth;
    }

    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }
}