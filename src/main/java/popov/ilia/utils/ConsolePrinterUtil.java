package popov.ilia.utils;

import popov.ilia.entities.Contact;

import java.io.IOException;
import java.util.Set;

public class ConsolePrinterUtil {

    private static void printBasicTable() {
        System.out.format("%-16s %-16s", "Surname", "Name");
    }

    private static void printFullTable() {
        System.out.format("%-16s %-16s %-16s %-16s %-16s %-16s %-16s", "Category", "Surname", "Name", "Telephone", "Email", "Age", "Hair Color");
    }

    private static void printFriendTable() {
        printFullTable();
        System.out.format("%-16s", "Years of friendship");
    }

    private static void printFamilyTable() {
        printFullTable();
        System.out.format("%-16s", "Relation");
    }


    public static void printPersons(Set<Contact> contacts) {
        printBasicTable();
        System.out.println();
        printBaseDividerLine();
        for (Contact contact : contacts) {
            System.out.println(contact.getBasicFormatedPersonInfo());
        }
    }

    public static void printFullPersonInfo(Contact contact) {
        switch (contact.getCategory()) {
            case (CommonConst.FRIEND_CATEGORY):
                printFriendTable();
                break;
            case (CommonConst.FAMILY_CATEGORY):
                printFamilyTable();
                break;
            default:
                printFullTable();
                break;
        }
        System.out.println();
        printFullDividerLine();
        System.out.println(contact.getFullFormatedPersonInfo());
    }

    //todo make it more prettier and fitting the table perfectly
    public static void printFullDividerLine(){
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
    }
    public static void printBaseDividerLine(){
        System.out.println("---------------------------------------");
    }

    public static void printCommands() {
        System.out.println("This is simple address book java application.");
        System.out.println("To view all contacts print: all");
        System.out.println("To add new contact print: add");
        System.out.println("To view contact print: view");
        System.out.println("To save your changes print: save");
        System.out.println("To exit print: exit");
    }

    //Not a great way and platform dependant but for a small test application I think this should do it :)
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ignored) {
        }
    }

}
