package dev.emre.librarymanagementsystem.logic;

import dev.emre.librarymanagementsystem.models.Address;
import dev.emre.librarymanagementsystem.models.Book;
import dev.emre.librarymanagementsystem.models.Loan;
import dev.emre.librarymanagementsystem.models.Person;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private final List<Person> persons = new ArrayList<>();
    private long nextId = 1;

    // CRUD-Methoden für Person
    public boolean updatePerson(Person updated){
        if(updated == null)return false;
        if(updated.getId() == 0)return false;
        for (Person person : persons) {
            if(person.getId() == updated.getId()){
                person.setName(updated.getName());
                person.setSurname(updated.getSurname());
                person.setBirthDate(updated.getBirthDate());
                person.setAddress(updated.getAddress());
                return true;
            }
        }
        return false;
    }
    public List<Person> getAllPersons(){
        return new ArrayList<>(persons);
    }
    public Optional<Person> getPersonById(long id){
        if(id <= 0)return Optional.empty();
        return persons.stream().filter(person -> person.getId() == id).findFirst();
    }
    public Person createPerson(String name, String surname, LocalDate birthDate, Address address){
        if(name == null || surname == null || birthDate == null || address == null)throw new IllegalArgumentException("Null values are not allowed!");
        long id = nextId++;
        Person person = new Person(id,name, surname, birthDate, address);
        persons.add(person);
        return person;
    }
    public boolean deletePerson(long id){
        return persons.removeIf(person -> person.getId() == id);
    }
    public Optional<Person> getPersonByName(String name){
        if(name == null)return Optional.empty();
        return persons.stream().filter(person -> name.equalsIgnoreCase(person.getName())).findFirst();
    }
    public Optional<Person> getPersonBySurname(String surname){
        return persons.stream().filter(person -> person.getSurname().equals(surname)).findFirst();
    }

    public List<Person> findByOpenFeesRange(BigDecimal from, BigDecimal to){
        return persons.stream().filter(person -> person.hasFeesInRange(from, to)).toList();
    }
    public List<Person> findByActiveLoanCountRange(int from, int to, LoanService loanService) {
        return persons.stream()
                .filter(person -> {
                    long count = loanService.countActiveLoansForPerson(person.getId());
                    return count >= from && count <= to;
                })
                .toList();
    }

    public List<Person> findPersonsWithActiveLoans(LoanService loanService){
        return persons.stream().filter(person -> loanService.hasActiveLoans(person.getId())).toList();
    }
    public List<Person> findPersonsWithoutActiveLoans(LoanService loanService){
        return persons.stream().filter(person -> !loanService.hasActiveLoans(person.getId())).toList();
    }

}
