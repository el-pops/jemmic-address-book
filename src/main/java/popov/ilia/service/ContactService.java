package popov.ilia.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import popov.ilia.entities.Contact;
import popov.ilia.reposiroty.ContactRepository;


import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ContactService {
    private ContactRepository contactRepository;
    private Scanner scanner;

    public Set<Contact> getAll(){
        return contactRepository.getAll();
    }

    public void addPerson(Contact contact) {
        contactRepository.addPerson(contact);
    }

    public boolean editPerson(String oldFullName, Contact changedContact) {
        if (personExists(oldFullName)) {
            contactRepository.editPerson(changedContact);
            if (!oldFullName.equals(changedContact.getName() + changedContact.getSurname())) {
                contactRepository.deletePersonByFullName(oldFullName);
            }
            return true;
        } else return false;
    }

    public boolean personExists(String fullName) {
        return contactRepository.getPersonByFullName(fullName).isPresent();
    }

    public Optional<Contact> getPersonByName(String name){
        return contactRepository.getPersonByFullName(name);
    }

    public void deletePersonByFullName(String fullName){
        contactRepository.deletePersonByFullName(fullName);
    }
}
