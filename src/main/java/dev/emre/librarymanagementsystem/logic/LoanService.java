package dev.emre.librarymanagementsystem.logic;

import dev.emre.librarymanagementsystem.models.Book;
import dev.emre.librarymanagementsystem.models.Loan;
import dev.emre.librarymanagementsystem.models.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanService {
    LocalDate today = LocalDate.now();
    private long nextId = 1;
    private final List<Loan> loans = new ArrayList<>();
    private final BookService bookService;
    private final PersonService personService;

    public LoanService(BookService bookService, PersonService personService)
    {
        this.bookService = bookService;
        this.personService = personService;

    }

    public Optional<Loan> createLoan(long bookid, long personId, LocalDate loanDate) {
        Optional<Book> bookOpt = bookService.getBookById(bookid);
        Optional<Person> personOpt = personService.getPersonById(personId);
        if(bookOpt.isEmpty() || personOpt.isEmpty()) {
            return Optional.empty();
        }
        Loan loan = new Loan(personOpt.get(), bookOpt.get(), loanDate);
        loan.setId(nextId++);
        loans.add(loan);
        return Optional.of(loan);


    }
    public List<Loan> getAllLoans(){
        return new ArrayList<>(loans);
    }

}
