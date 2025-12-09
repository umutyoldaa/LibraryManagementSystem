package dev.emre.librarymanagementsystem.logic;

import dev.emre.librarymanagementsystem.models.Book;
import dev.emre.librarymanagementsystem.models.Loan;
import dev.emre.librarymanagementsystem.models.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/*
 Wichtiges Muster
Optional<T> → erst isEmpty()/isPresent() prüfen

Danach mit .get() den Wert holen

Dann mit dem „normalen“ Objekt weiterarbeiten
 * */
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
        if(bookOpt.get().getAvailableCopies() <= 0)return Optional.empty();
        bookOpt.get().setAvailableCopies(bookOpt.get().getAvailableCopies() - 1);
        Loan loan = new Loan(personOpt.get(), bookOpt.get(), loanDate);
        loan.setId(nextId++);
        loans.add(loan);
        return Optional.of(loan);
    }
    public List<Loan> getAllLoans(){
        return new ArrayList<>(loans);
    }


    public Optional<Loan> getLoanById(long id){
        if(id <= 0)return Optional.empty();
        return loans.stream().filter(loan -> loan.getId() == id).findFirst();
    }


    public Optional<BigDecimal> returnBook(long loanId, LocalDate returnDate, boolean damaged){
    Optional<Loan> loanOpt = getLoanById(loanId);
    if(loanOpt.isEmpty())return Optional.empty();

    Loan loan = loanOpt.get();

    BigDecimal fee = loan.calculateFine(returnDate, damaged);

    if(!loan.markAsReturned(returnDate)){
        return Optional.empty();
    }
    loan.getPerson().addFees(fee);

    Book book = loan.getBook();
    if(damaged){
        book.markedAsDamaged();
    }
    book.setAvailableCopies(book.getAvailableCopies() + 1);
    return Optional.of(fee);


    }
    public List<Loan> getLoansByPersonId(long personId){
        return loans.stream().filter(loan -> loan.getPerson().getId() == personId).toList();
    }

}
