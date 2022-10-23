package popov.ilia.service;

import popov.ilia.entities.Contact;

import java.util.Optional;
import java.util.Set;

public interface ContactService {
    Set<Contact> getAll();

    void addPerson(Contact contact);

    boolean editPerson(String oldFullName, Contact changedContact);

    boolean personExists(String fullName);

    Optional<Contact> getPersonByName(String name);

    void deletePersonByFullName(String fullName);
}
