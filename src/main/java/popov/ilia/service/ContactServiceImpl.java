package popov.ilia.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import popov.ilia.entities.Contact;
import popov.ilia.reposiroty.ContactDao;


import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private ContactDao contactDao;
    private Scanner scanner;

    @Override
    public Set<Contact> getAll(){
        return contactDao.getAll();
    }

    @Override
    public void addPerson(Contact contact) {
        contactDao.addPerson(contact);
    }

    @Override
    public boolean editPerson(String oldFullName, Contact changedContact) {
        if (personExists(oldFullName)) {
            contactDao.editPerson(changedContact);
            if (!oldFullName.equals(changedContact.getName() + changedContact.getSurname())) {
                contactDao.deletePersonByFullName(oldFullName);
            }
            return true;
        } else return false;
    }

    @Override
    public boolean personExists(String fullName) {
        return contactDao.getContactByFullName(fullName).isPresent();
    }

    @Override
    public Optional<Contact> getPersonByName(String name){
        return contactDao.getContactByFullName(name);
    }

    @Override
    public void deletePersonByFullName(String fullName){
        contactDao.deletePersonByFullName(fullName);
    }
}
