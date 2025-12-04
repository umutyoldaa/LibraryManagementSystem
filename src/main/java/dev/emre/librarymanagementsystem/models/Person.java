package dev.emre.librarymanagementsystem.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Person {
    private long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Address address;

    public Person(
            String name,
            String surname,
            LocalDate birthDate,
            Address address
    ){
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public Address getAdress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return String.format("%s %s, geboren %s ", name, surname, birthDate);
    }

}
