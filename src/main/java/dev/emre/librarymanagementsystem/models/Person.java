package dev.emre.librarymanagementsystem.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Person {
    private long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Address address;
    private BigDecimal openFees = BigDecimal.ZERO;

    public Person(
            long id,
            String name,
            String surname,
            LocalDate birthDate,
            Address address
    ){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.address = address;
    }
    public BigDecimal getOpenFees() {
        return openFees;
    }
    public long getId() {
        return id;
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
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void addFees(BigDecimal fees) {
        if(fees == null || fees.compareTo(BigDecimal.ZERO) <= 0 ) return;
        this.openFees = this.openFees.add(fees);
    }
    public void payFees(BigDecimal fees) {
        if(fees == null || fees.compareTo(BigDecimal.ZERO) <= 0 ) return;
        if(!hasOpenFees()) return;
        openFees = openFees.subtract(fees);
        if(this.openFees.compareTo(BigDecimal.ZERO) < 0) this.openFees = BigDecimal.ZERO;
    }
    public boolean hasOpenFees() {
        return openFees != null && this.openFees.compareTo(BigDecimal.ZERO) > 0;
    }
    public boolean hasFeesInRange(BigDecimal from, BigDecimal to) {
        if(!hasOpenFees()) return false;
        if(from != null && openFees.compareTo(from) < 0) return false;
        if(to != null && openFees.compareTo(to) > 0) return false;
        return true;
    }
    @Override
    public String toString() {
        return String.format("%s %s, geboren %s ", name, surname, birthDate);
    }

}
