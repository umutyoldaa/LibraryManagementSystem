package dev.emre.librarymanagementsystem.models;

import java.math.BigDecimal;
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
<<<<<<< HEAD
    @Override
    public String toString() {
        return "Loan{id=" + id +
                ", person=" + person.getName() +
                ", book=" + book.getTitle() +
                ", loanDate=" + loanDate +
                ", dueDate=" + getDueDate() +
                ", returned=" + isReturned +
                '}';
    }

=======
    public BigDecimal calculateFine(LocalDate returnDate, boolean damaged) {
        BigDecimal fee = BigDecimal.ZERO;
        long dayslate = getDaysOverdue(returnDate);
        if(dayslate > 0){
          long weekLate = (dayslate + 6) / 7;
          fee = fee.add(BigDecimal.valueOf(weekLate * 2));
        }
        if(damaged){
            fee = fee.add(BigDecimal.valueOf(10));
        }
        return fee;

    }
    public long getDaysOverdue(LocalDate date) {
        if (!isOverdue(date)) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(getDueDate(), date);
    }
>>>>>>> main

}
