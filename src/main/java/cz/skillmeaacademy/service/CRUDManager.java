package cz.skillmeaacademy.service;

import cz.skillmeaacademy.db.Contact;
import cz.skillmeaacademy.db.DBConntactService;
import cz.skillmeaacademy.utility.InputUtils;

import java.util.List;

public class CRUDManager {
    private final DBConntactService contactService;

    public CRUDManager() {
        this.contactService = new DBConntactService();
    }

    public void printOptions() {
        System.out.println("Hello, welcom to contacts manager\n");
        while (true) {
            System.out.println("0. Get all contacts");
            System.out.println("1. Edit contact");
            System.out.println("2. Add contact");
            System.out.println("3. Delete contact");
            System.out.println("4. Search contact");
            System.out.println("5. Exit");

            final int choice = InputUtils.readInt();
            switch (choice) {
                case 0 -> printAllContacts();
                case 1 -> System.out.println("Not implemented");
                case 2 -> createContact();
                case 3 -> deleteContact();
                case 4 -> System.out.println("Not implemented");
                case 5 -> {
                    System.out.println("Good bye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void deleteContact() {
        final List<Contact> contacts = contactService.readAll();

        int choice;
        while (true) {
            System.out.println("0. Cancel");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }

            System.out.println("Enter number of contact you want to delete: ");
            choice = InputUtils.readInt();
            if (choice == 0) {
                return;
            } else if (choice < 1 || choice > contacts.size()){
                System.out.println("Invalid choice");
                continue;
            }
            if(contactService.delete(contacts.get(choice -1).getId()) >0){
                System.out.println("Contact deleted successfully ");
                return;
            }
        }

    }

    private void createContact() {
        System.out.println("Enter name: ");
        final String name = InputUtils.readString();
        System.out.println("Enter email: ");
        final String email = InputUtils.readString();
        System.out.println("Enter phone: ");
        final String phone = InputUtils.readString();

        if (contactService.create(name, email, phone) > 0) {
            System.out.println("Contact created successfully");
        } else {
            System.out.println("Could not create contact");
        }
    }

    private void printAllContacts() {
        final List<Contact> contacts = contactService.readAll();
        contacts.forEach(System.out::println);
    }
}
