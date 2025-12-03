package dev.emre.librarymanagementsystem.models;

import java.util.Date;
import java.util.Objects;

public class Person {
    private long id;
    private String name;
    private String surname;
    private Date birthDate;
    private Address address;

    public Person(
            String name,
            String surname,
            Date birthDate,
            Address adress
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
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Address getAdress() {
        return address;
    }
    public void setAdress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return String.format("%s %s %d", name, surname, birthDate);
    }

}
