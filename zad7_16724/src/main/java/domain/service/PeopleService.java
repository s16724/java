
package domain.service;

import domain.Person;

import java.util.ArrayList;
import java.util.List;

import repo.PeopleRepository;


public class PeopleService {

    private static List<Person> db = new ArrayList<Person>();
    private static int currentId = 1;
    PeopleRepository rep = new PeopleRepository();

    public List<Person> getData() {
        return rep.getData();
    }

    public Person get(int id) {
        for (Person p : db) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void addPerson(Person p) {
        p.setId(currentId++);
        rep.addPerson(p);
    }

    public void update(Person person) {
        rep.update(person);
    }

    public void delete(int id) {
        rep.delete(id);
    }

}
