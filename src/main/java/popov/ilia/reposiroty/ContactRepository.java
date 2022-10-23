package popov.ilia.reposiroty;

import lombok.AllArgsConstructor;
import lombok.Setter;
import popov.ilia.entities.FamilyContact;
import popov.ilia.entities.FriendContact;
import popov.ilia.entities.Contact;
import popov.ilia.utils.CommonConst;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@AllArgsConstructor
public class ContactRepository {
    Map<String, Contact> contactsMap;

    public Set<Contact> getAll() {
        return new TreeSet<>(contactsMap.values());
    }

    public Set<FriendContact> getAllFriends() {
        return contactsMap.values().stream().filter(person -> person.getCategory().equals(CommonConst.FRIEND_CATEGORY)).
                map(person -> (FriendContact) person).
                collect(Collectors.toSet());
    }

    public Set<FamilyContact> getAllFamily() {
        return contactsMap.values().stream().filter(person -> person.getCategory().equals(CommonConst.FAMILY_CATEGORY)).
                map(person -> (FamilyContact) person).
                collect(Collectors.toSet());
    }

    public Contact addPerson(Contact contact) {
        return contactsMap.put(contact.getSurname() + contact.getName(), contact);
    }

    public Optional<Contact> getPersonByNameAndSurname(String name, String surname) {
        return getPersonByFullName(name + surname);
    }

    public Optional<Contact> getPersonByFullName(String fullName) {
        return Optional.ofNullable(contactsMap.get(fullName));
    }

    public Contact editPerson(Contact contact) {
        return contactsMap.put(contact.getSurname() + contact.getName(), contact);
    }

    public Contact deletePersonByFullName(String fullName) {
        return contactsMap.remove(fullName);
    }


}