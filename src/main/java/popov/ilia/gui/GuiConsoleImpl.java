package popov.ilia.gui;

import lombok.AllArgsConstructor;
import popov.ilia.entities.FamilyContact;
import popov.ilia.entities.FriendContact;
import popov.ilia.entities.Contact;
import popov.ilia.reposiroty.ContactDao;
import popov.ilia.reposiroty.ContactDaoInMemoryImpl;
import popov.ilia.service.ContactService;
import popov.ilia.service.ContactServiceImpl;
import popov.ilia.utils.CommonConst;
import popov.ilia.utils.FileUtils;
import popov.ilia.utils.MappingUtils;
import popov.ilia.utils.ConsolePrinterUtil;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class GuiConsoleImpl implements Gui {

    private final Scanner scanner;
    private final ContactDao contactDao;
    private final ContactService contactService;

    @Override
    public void run() throws IOException {
        boolean exit = false;
        ConsolePrinterUtil.clearConsole();
        System.out.println("Welcome to address book application");
        while (!exit) {
            System.out.println("To view all commands print: commands");
            String command = scanner.nextLine();
            ConsolePrinterUtil.clearConsole();
            switch (command) {
                case "all":
                    viewAllContacts();
                    break;
                case "add":
                    addContact();
                    break;
                case "exit":
                    FileUtils.save(contactDao.getAll());
                    exit = true;
                    break;
                case "save":
                    FileUtils.save(contactDao.getAll());
                    break;
                case "view":
                    viewContact();
                    break;
                case "commands":
                    ConsolePrinterUtil.printCommands();
                    break;
                default:
                    System.out.println("Not supported command");
                    break;
            }
        }
    }

    @Override
    public void addContact() {
        Contact contact = getContactFromGui();
        contactService.addPerson(contact);
        ConsolePrinterUtil.clearConsole();
        System.out.println("You have successfully added new person");
    }

    @Override
    public void viewContact() {
        System.out.println("Please enter surname of person who you want to view");
        String fullName = scanner.nextLine();
        System.out.println("Please enter name of person who you want to view");
        fullName += scanner.nextLine();
        Optional<Contact> person = contactService.getPersonByName(fullName);
        if (person.isPresent()) {
            ConsolePrinterUtil.printFullPersonInfo(person.get());
            viewContactCommands(person.get());
            ConsolePrinterUtil.clearConsole();
        } else {
            System.out.println("Person with given name dont exist");
        }
    }

    @Override
    public void editContact(Contact contact) {
        System.out.println("Please enter name of field you want to change");
        String category = scanner.nextLine();
        String oldFullName = contact.getFullName();
        switch (category) {
            case "name":
                ContactGuiBuilder.getNameFromGui(contact);
                break;
            case "surname":
                ContactGuiBuilder.getSurnameFromGui(contact);
                break;
            case "telephone":
                ContactGuiBuilder.getNumberFromGui(contact);
                break;
            case "email":
                ContactGuiBuilder.getEmailFromGui(contact);
                break;
            case "age":
                ContactGuiBuilder.getAgeFromGui(contact);
                break;
            case "hair colour":
                ContactGuiBuilder.getHairColorFromGui(contact);
                break;
            case "years of friendship":
                if (contact.getCategory().equals(CommonConst.FRIEND_CATEGORY)) {
                    ContactGuiBuilder.getYearsOfFriendshipFromGui((FriendContact) contact);
                } else {
                    System.out.println("Selected contact is not a friend");
                }
                break;
            case "relation":
                if (contact.getCategory().equals(CommonConst.FAMILY_CATEGORY)) {
                    ContactGuiBuilder.getRelationFromGui((FamilyContact) contact);
                } else {
                    System.out.println("Selected contact is not a family");
                }
                break;
            default:
                System.out.println("Wrong field name");
                break;
        }
        contactService.editPerson(oldFullName, contact);
        ConsolePrinterUtil.clearConsole();
        System.out.println("You have successfully modified " + contact.getName() + " " + contact.getSurname());
    }

    @Override
    public void viewAllContacts() {
        ConsolePrinterUtil.printPersons(contactService.getAll());
    }

    public Contact getContactFromGui() {
        System.out.println("Please enter category");
        switch (scanner.nextLine()) {
            case (CommonConst.FRIEND_CATEGORY):
                return ContactGuiBuilder.buildFriendContact();
            case (CommonConst.FAMILY_CATEGORY):
                return ContactGuiBuilder.buildFamilyContact();
            case (CommonConst.ACQUAINTANCE_CATEGORY):
                return ContactGuiBuilder.buildAcquaintanceContact();
            default:
                System.out.println("Supported categories are: friend, family, acquaintance");
                return getContactFromGui();
        }
    }

    private void viewContactCommands(Contact contact) {
        System.out.println("Write 'edit' to edit this person, 'delete' to delete, 'back' to return to main screen");
        switch (scanner.nextLine()) {
            case "edit":
                editContact(contact);
                ConsolePrinterUtil.printFullPersonInfo(contact);
                viewContactCommands(contact);
                break;
            case "delete":
                contactService.deletePersonByFullName(contact.getFullName());
                break;
            case "back":
                ConsolePrinterUtil.clearConsole();
                break;
            default:
                System.out.println("Command not supported");
                viewContactCommands(contact);
                break;
        }
    }


}
