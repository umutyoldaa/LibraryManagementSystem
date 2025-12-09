package dev.emre.librarymanagementsystem.logic;

import dev.emre.librarymanagementsystem.models.Person;

import javax.swing.text.html.Option;
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
            }
        }
        return true;
    }
    public List<Person> getAllPersons(){
        return new ArrayList<>(persons);
    }
    public Optional<Person> getPersonById(long id){
        if(id <= 0)return Optional.empty();
        return persons.stream().filter(person -> person.getId() == id).findFirst();
    }
    public void addPerson(Person person){
        if(person == null)return;
        if(person.getName() == null || person.getSurname() == null || person.getBirthDate() == null || person.getAddress() == null){
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
