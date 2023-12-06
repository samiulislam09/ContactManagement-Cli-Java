import java.io.*;
import java.util.*;

class Person {
    String name;
    String email;
    String mobile;
    public Person(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }
}

class Contact extends Person {
    public Contact(String name, String email, String mobile) {
        super(name, email, mobile);
    }
    public void printContact() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Mobile: " + mobile);
    }
}

class ContactManager {
    private static final String FILENAME = "contacts.txt";
    private static final ArrayList<Contact> contacts = new ArrayList<>();
    public static void main(String[] args) {
        readContacts();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 1 to create a new contact");
            System.out.println("Enter 2 to update an existing contact");
            System.out.println("Enter 3 to exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createContact(scanner);
                    printContacts();
                    break;
                case 2:
                    updateContact(scanner);
                    printContacts();
                    break;
                case 3:
                    writeContacts();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    private static void readContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading contacts from file");
        }
        printContacts();
    }
    private static void writeContacts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Contact contact : contacts) {
                bw.write(contact.name + "," + contact.email + "," + contact.mobile + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing contacts to file");
        }
    }
    private static void createContact(Scanner scanner) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter mobile:");
        String mobile = scanner.nextLine();
        Contact contact = new Contact(name, email, mobile);
        contacts.add(contact);
        System.out.println("Contact added:");
        contact.printContact();
    }
    private static void updateContact(Scanner scanner) {
        System.out.println("Enter name of contact to update:");
        String name = scanner.nextLine();
        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                System.out.println("Enter new email:");
                String email = scanner.nextLine();
                System.out.println("Enter new mobile:");
                String mobile = scanner.nextLine();
                contact.email = email;
                contact.mobile = mobile;
                System.out.println("Contact updated:");
                contact.printContact();
                return;
            }
        }
        System.out.println("Contact not found");
    }
    private static void printContacts() {
        System.out.println("Contacts:");
        for (Contact contact : contacts) {
            contact.printContact();
            System.out.println("\n");
        }

    }
}
