package popov.ilia.gui;

import popov.ilia.entities.AcquaintanceContact;
import popov.ilia.entities.FamilyContact;
import popov.ilia.entities.FriendContact;
import popov.ilia.entities.Contact;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ContactGuiBuilder {


    private static final Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_VALIDATION_REGEXP = "^(.+)@(\\S+)$";
    private static final String PHONE_NUMBER_VALIDATION_REGEXP = "^[0-9\\-]*$";

    public static FriendContact buildFriendContact() {
        FriendContact friendContact = new FriendContact();
        buildContact(friendContact);
        getYearsOfFriendshipFromGui(friendContact);
        return friendContact;
    }

    public static FamilyContact buildFamilyContact() {
        FamilyContact familyContact = new FamilyContact();
        buildContact(familyContact);
        getRelationFromGui(familyContact);
        return familyContact;
    }

    public static AcquaintanceContact buildAcquaintanceContact() {
        AcquaintanceContact acquaintanceContact = new AcquaintanceContact();
        buildContact(acquaintanceContact);
        return acquaintanceContact;
    }

    private static Contact buildContact(Contact contact) {
        getSurnameFromGui(contact);
        getNameFromGui(contact);
        getNumberFromGui(contact);
        getEmailFromGui(contact);
        getAgeFromGui(contact);
        getHairColorFromGui(contact);
        return contact;
    }

    public static void getNameFromGui(Contact contact) {
        System.out.println("Please enter name");
        String name = scanner.nextLine();
        if (name.trim().equals("")) {
            System.out.println("Name cannot be empty");
            getNameFromGui(contact);
        } else {
            contact.setName(name);
        }
    }

    public static void getSurnameFromGui(Contact contact) {
        System.out.println("Please enter surname");
        String surname = scanner.nextLine();
        if (surname.trim().equals("")) {
            System.out.println("Surname cannot be empty");
            getSurnameFromGui(contact);
        } else {
            contact.setSurname(surname);
        }
    }

    public static void getNumberFromGui(Contact contact) {
        System.out.println("Please enter number");
        String number = scanner.nextLine();
        if (number.trim().equals("") || !patternMatches(number, PHONE_NUMBER_VALIDATION_REGEXP)) {
            System.out.println("Number cannot be empty and can consist of only digits or dashes");
            getNumberFromGui(contact);
        } else {
            contact.setTelephoneNumber(number);
        }
    }

    public static void getEmailFromGui(Contact contact) {
        System.out.println("Please enter email");
        String email = scanner.nextLine();
        if (email.trim().equals("") || !patternMatches(email, EMAIL_VALIDATION_REGEXP)) {
            System.out.println("Email cannot be empty and should be a valid email address");
            getEmailFromGui(contact);
        } else {
            contact.setEmail(email);
        }
    }

    public static void getAgeFromGui(Contact contact) {
        System.out.println("Please enter age(optional)");
        String age = scanner.nextLine();
        if (!age.trim().equals("")) {
            try {
                contact.setAge(Integer.parseInt(age));
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Age must be number");
                getAgeFromGui(contact);
            }
        }
    }

    public static void getHairColorFromGui(Contact contact) {
        System.out.println("Please enter hair color(optional)");
        String color = scanner.nextLine();
        if (!color.trim().equals("")) {
            contact.setHairColor(color);
        }
    }


    public static void getYearsOfFriendshipFromGui(FriendContact friendContact) {
        System.out.println("Please enter years of friendship");
        try {
            friendContact.setYearsOfFriendship(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException n) {
            System.out.println("YearsOfFriendship must be a number!");
            getYearsOfFriendshipFromGui(friendContact);
        }
    }

    public static void getRelationFromGui(FamilyContact familyContact) {
        System.out.println("Please enter relation");
        String relation = scanner.nextLine();
        if (relation.trim().equals("")) {
            System.out.println("Relation cant be empty");
            getRelationFromGui(familyContact);
        } else {
            familyContact.setRelation(relation);
        }
    }

    public static boolean patternMatches(String value, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(value)
                .matches();
    }

}
