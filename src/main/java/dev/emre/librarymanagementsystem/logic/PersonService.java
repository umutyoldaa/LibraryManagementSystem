package dev.emre.librarymanagementsystem.logic;

import dev.emre.librarymanagementsystem.models.Person;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private final List<Person> persons = new ArrayList<>();
    private long nextId = 1;
    public PersonService(){}
    public List<Person> getAllPersons(){
        return new ArrayList<>(persons);
    }
    public Optional<Person> getPersonById(long id){
        return persons.stream().filter(person -> person.getId() == id).findFirst();
    }
    public void addPerson(Person person){
        if(person == null)return;
        if(person.getName() == null || person.getSurname() == null || person.getBirthDate() == null || person.getAdress() == null){
            return;
        }
        person.setId(nextId++);
        persons.add(person);
    }
    public boolean deletePerson(long id){
        return persons.removeIf(person -> person.getId() == id);
    }
    public Optional<Person> getPersonByName(String name){
        return persons.stream().filter(person -> person.getName().equals(name)).findFirst();
    }
    public Optional<Person> getPersonBySurname(String surname){
        return persons.stream().filter(person -> person.getSurname().equals(surname)).findFirst();
    }
}
