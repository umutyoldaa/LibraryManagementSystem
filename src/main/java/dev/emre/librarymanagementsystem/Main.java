package dev.emre.librarymanagementsystem;

import dev.emre.librarymanagementsystem.logic.BookService;
import dev.emre.librarymanagementsystem.logic.LoanService;
import dev.emre.librarymanagementsystem.logic.PersonService;
import dev.emre.librarymanagementsystem.models.Address;
import dev.emre.librarymanagementsystem.models.Book;
import dev.emre.librarymanagementsystem.models.Loan;
import dev.emre.librarymanagementsystem.models.Person;
import dev.emre.librarymanagementsystem.models.enums.Genre;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("erfurt","dresdener","12345");
        Person p = new Person("Max", "Muster", LocalDate.of(2000,1,1), address);
        Book b = new Book("Titel", "Autor", Genre.SCIFI, 3);
        Book e = new Book("Titel", "Autor", Genre.SCIFI, 3);

// Services vorbereiten (je nach deiner Implementierung)
        BookService bookService = new BookService();
        PersonService personService = new PersonService();
// Person + Book in Services speichern ...
        bookService.addBook(b);
        bookService.addBook(e);
        System.out.println(bookService.getAllBooks());
        personService.addPerson(p);

        LoanService loanService = new LoanService(bookService, personService);

        Optional<Loan> loanOpt = loanService.createLoan(b.getId(), p.getId(), LocalDate.of(2024,1,1));
        if (loanOpt.isEmpty()) {
            System.out.println("Loan konnte nicht erstellt werden");
            return;
        }
        Loan loan = loanOpt.get();
        System.out.println("Available copies after loan: " + b.getAvailableCopies());

        Optional<BigDecimal> feeOpt = loanService.returnBook(loan.getId(), LocalDate.of(2024,2,7), true);
        if (feeOpt.isEmpty()) {
            System.out.println("Rückgabe fehlgeschlagen");
        } else {
            System.out.println("Fee: " + feeOpt.get());
        }

        System.out.println("Person open fees: " + p.getOpenFees());



    }
}
