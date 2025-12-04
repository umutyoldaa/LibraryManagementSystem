package dev.emre.librarymanagementsystem.models;

import java.time.LocalDate;
import java.util.Optional;

public class Loan {
    private static final int MAX_LOAN_DAYS = 30;
    private long id;
    private Person person;
    private Book book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean isReturned;
    public Loan(Person person, Book book, LocalDate loanDate) {
        this.person = person;
        this.book = book;
        this.loanDate = loanDate;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return loanDate.plusDays(MAX_LOAN_DAYS );
    }
    public Book getBook() {
        return book;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public boolean isReturned() {
        return isReturned;
    }
    public boolean isOverdue(LocalDate today) {
        return !isReturned && today.isAfter(getDueDate());
    }
    public boolean markAsReturned(LocalDate returnDate) {
        if (returnDate.isAfter(loanDate)) {
            this.returnDate = returnDate;
            isReturned = true;
            return true;
        }
        return false;
    }

}
