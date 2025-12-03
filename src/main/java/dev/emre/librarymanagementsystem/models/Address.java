package dev.emre.librarymanagementsystem.models;

import java.util.Objects;

public class Address {
    private String city;
    private String street;
    private String zipCode;
    public Address(String city,
                   String street,
                   String zipCode
    ) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getZipCode() {
        return zipCode;
    }
    @Override
    public String toString() {
        return String.format("%s %s %s", city, street, zipCode);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if( obj == null || getClass() != obj.getClass() ) return false;
        Address address = (Address) obj;
        return Objects.equals(city, address.city)
                && Objects.equals(street, address.street)
                && Objects.equals(zipCode, address.zipCode);
    }
    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipCode);
    }
}
