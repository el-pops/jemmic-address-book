package popov.ilia.gui;

import popov.ilia.entities.Contact;

import java.io.IOException;

public interface Gui {
    void run() throws IOException;

    void addContact();

    void viewContact();

    void editContact(Contact contact);

    void viewAllContacts();
}

