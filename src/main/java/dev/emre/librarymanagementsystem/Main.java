package dev.emre.librarymanagementsystem;

import dev.emre.librarymanagementsystem.logic.BookService;
import dev.emre.librarymanagementsystem.logic.LoanService;
import dev.emre.librarymanagementsystem.logic.PersonService;
import dev.emre.librarymanagementsystem.models.Address;
import dev.emre.librarymanagementsystem.models.Book;
import dev.emre.librarymanagementsystem.models.Loan;
import dev.emre.librarymanagementsystem.models.Person;
import dev.emre.librarymanagementsystem.models.enums.Genre;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        PersonService personService = new PersonService();
        LoanService loanService = new LoanService(bookService, personService);
        Person p = new Person();
        p.setName("Emre");
        p.setSurname("Kaya");
        p.setBirthDate(LocalDate.of(1999, 12, 12));
        Address a = new Address();
        a.setCity("Kahramanmaras");
        a.setStreet("Kahramanmaras Cd.");
        a.setZipCode("34567");
        p.setAddress(a);
        personService.addPerson(p);
        // Alle Personen
        System.out.println("Alle Personen:");
        for (Person b : personService.getAllPersons()) {
            System.out.println(b.getId() + " - " + b.getName() + " " + b.getSurname());
        }
        // Suche per ID
        System.out.println("Suche ID 1: " + personService.getPersonById(1));
        // Suche per Name
        System.out.println("Suche Name 'Emre': " + personService.getPersonByName("Anna"));
        boolean deleted = personService.deletePerson(1);
        System.out.println("Person 1 gelöscht? " + deleted);
        System.out.println("Alle Personen nach Löschen: " + personService.getAllPersons().size());
        Genre genre = Genre.ROMANCE;
        Book b = new Book();
        b.setTitle("Clean Code");
        b.setAuthor("Robert C. Martin");
        b.setGenre(genre);
        b.setTotalCopies(3);
        bookService.addBook(b);
        Book b2 = new Book();
        Genre genre1 = Genre.BIOGRAPHY;
        b2.setTitle("Effective Java");
        b2.setAuthor("Joshua Bloch");
        b2.setGenre(genre1);
        b2.setTotalCopies(2);
        bookService.addBook(b2);

        // Alle Bücher
        System.out.println("Alle Bücher:");
        for (Book c : bookService.getAllBooks()) {
            System.out.println(c.getId() + " - " + c.getTitle() + " von " + c.getAuthor());
        }
        // Suche per ID
        System.out.println("Suche Buch ID 1: " + bookService.getBookById(1));

// Löschen
        boolean deletedBook = bookService.deleteBook(1);
        System.out.println("Buch 1 gelöscht? " + deletedBook);
        System.out.println("Alle Bücher nach Löschen: " + bookService.getAllBooks().size());
        long bookId = b.getId();
        long personId = p.getId();
        System.out.println("Person ID: " + p.getId());
        System.out.println("Book ID: " + b.getId());
        loanService.createLoan(bookId, personId, LocalDate.now());

        for (Loan loan : loanService.getAllLoans()) {
            System.out.println(loan);
        }




    }
}
