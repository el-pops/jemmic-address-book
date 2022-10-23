package popov.ilia.reposiroty;

import popov.ilia.entities.Contact;

import java.util.Optional;
import java.util.Set;

public interface ContactDao {
    Set<Contact> getAll();
    Optional<Contact> getContactByFullName(String fullName);
    Contact addPerson(Contact contact);
    Contact editPerson(Contact contact);
    Contact deletePersonByFullName(String fullName);
}
