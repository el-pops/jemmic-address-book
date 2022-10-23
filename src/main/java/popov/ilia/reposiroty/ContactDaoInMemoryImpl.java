package popov.ilia.reposiroty;

import lombok.AllArgsConstructor;
import lombok.Setter;
import popov.ilia.entities.FamilyContact;
import popov.ilia.entities.FriendContact;
import popov.ilia.entities.Contact;
import popov.ilia.utils.CommonConst;

import java.util.*;

@Setter
@AllArgsConstructor
public class ContactDaoInMemoryImpl implements ContactDao{
    Map<String, Contact> contactsMap;

    @Override
    public Set<Contact> getAll() {
        return new TreeSet<>(contactsMap.values());
    }

    @Override
    public Contact addPerson(Contact contact) {
        return contactsMap.put(contact.getSurname() + contact.getName(), contact);
    }

    @Override
    public Optional<Contact> getContactByFullName(String fullName) {
        return Optional.ofNullable(contactsMap.get(fullName));
    }

    @Override
    public Contact editPerson(Contact contact) {
        return contactsMap.put(contact.getSurname() + contact.getName(), contact);
    }

    @Override
    public Contact deletePersonByFullName(String fullName) {
        return contactsMap.remove(fullName);
    }


    //    public Set<FriendContact> getAllFriends() {
//        return contactsMap.values().stream().filter(person -> person.getCategory().equals(CommonConst.FRIEND_CATEGORY)).
//                map(person -> (FriendContact) person).
//                collect(Collectors.toSet());
//    }

//    public Set<FamilyContact> getAllFamily() {
//        return contactsMap.values().stream().filter(person -> person.getCategory().equals(CommonConst.FAMILY_CATEGORY)).
//                map(person -> (FamilyContact) person).
//                collect(Collectors.toSet());
//    }
}