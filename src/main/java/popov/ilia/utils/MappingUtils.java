package popov.ilia.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import popov.ilia.entities.AcquaintanceContact;
import popov.ilia.entities.FamilyContact;
import popov.ilia.entities.FriendContact;
import popov.ilia.entities.Contact;

import java.io.IOException;
import java.util.*;

public class MappingUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static  {
        mapper.registerModule(new Jdk8Module());
    }

    public static TreeMap<String, Contact> mapStringsToContacts(List<String> strings) {
        TreeMap<String, Contact> contacts = new TreeMap<>();
        for (String string : strings) {
            Contact contact = mapStringToPerson(string);
            contacts.put(contact.getName() + contact.getSurname(), contact);
        }
        return contacts;
    }

    public static String mapPersonToString(Contact contact) {
        try {
            return mapper.writeValueAsString(contact);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static Contact mapStringToPerson(String string) {
        try {
            if (string.contains("\"category\":\"friend\"")) {
                return mapper.readValue(string, FriendContact.class);
            } else if (string.contains("\"category\":\"family\"")) {
                return mapper.readValue(string, FamilyContact.class);
            } else {
                return mapper.readValue(string, AcquaintanceContact.class);
            }
        } catch (IOException e) {
            System.out.println("Save file might be corrupted");
            throw new RuntimeException(e);
        }

    }
}
