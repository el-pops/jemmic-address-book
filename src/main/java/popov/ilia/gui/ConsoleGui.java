package popov.ilia.gui;

import popov.ilia.entities.FamilyContact;
import popov.ilia.entities.FriendContact;
import popov.ilia.entities.Contact;
import popov.ilia.reposiroty.ContactRepository;
import popov.ilia.service.ContactService;
import popov.ilia.utils.CommonConst;
import popov.ilia.utils.FileUtils;
import popov.ilia.utils.MappingUtils;
import popov.ilia.utils.PrinterUtil;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleGui implements Gui {


    private final Scanner scanner;
    private final ContactRepository contactRepository;
    private final ContactService contactService;

    public ConsoleGui() throws IOException {
        scanner = new Scanner(System.in);
        contactRepository = new ContactRepository(MappingUtils.mapStringsToContacts(FileUtils.readFromFile()));
        contactService = new ContactService(contactRepository, scanner);
    }

    @Override
    public void run() throws IOException {
        boolean exit = false;
        PrinterUtil.clearConsole();
        System.out.println("Welcome to address book application");
        while (!exit) {
            System.out.println("To view all commands print: commands");
            String command = scanner.nextLine();
            PrinterUtil.clearConsole();
            switch (command) {
                case "all":
                    viewAllContacts();
                    break;
                case "add":
                    addContact();
                    break;
                case "exit":
                    FileUtils.save(contactRepository.getAll());
                    exit = true;
                    break;
                case "save":
                    FileUtils.save(contactRepository.getAll());
                    break;
                case "view":
                    viewContact();
                    break;
                case "commands":
                    PrinterUtil.printCommands();
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
        PrinterUtil.clearConsole();
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
            PrinterUtil.printFullPersonInfo(person.get());
            viewContactCommands(person.get());
            PrinterUtil.clearConsole();
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
        PrinterUtil.clearConsole();
        System.out.println("You have successfully modified " + contact.getName() + " " + contact.getSurname());
    }

    @Override
    public void viewAllContacts() {
        PrinterUtil.printPersons(contactService.getAll());
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
                PrinterUtil.printFullPersonInfo(contact);
                viewContactCommands(contact);
                break;
            case "delete":
                contactService.deletePersonByFullName(contact.getFullName());
                break;
            case "back":
                PrinterUtil.clearConsole();
                break;
            default:
                System.out.println("Command not supported");
                viewContactCommands(contact);
                break;
        }
    }


}
