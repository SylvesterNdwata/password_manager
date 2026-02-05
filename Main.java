
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        PasswordManager manager = new PasswordManager();
        System.out.println("Welcome to the Password Manager");

        while (true) {

            System.out.print("Type 1 to add a password or 2 to search for a password or leave empty to exit: ");
            String response = reader.nextLine();

            if (response.isEmpty()) {
                break;
            }

            switch (response) {
                case "1":
                    addPassword(reader, manager);
                    break;

                case "2":
                    searchPassword(reader, manager);
                    break;

                default:
                    System.out.println("Invalid option, Please try again.");
            }

        }

    }

    private static void addPassword(Scanner reader, PasswordManager manager) {
        System.out.print("What service's password would you like to save? ");
        String service = reader.nextLine();

        if (service.isEmpty()) return;

        System.out.print("What is the username? ");
        String username = reader.nextLine();

        System.out.print("What is the password? ");
        String password = reader.nextLine();

        PasswordEntry entry = new PasswordEntry(service, username, password);

        System.out.println(entry);
        manager.saveToFile(entry);
    }

    private static void searchPassword(Scanner reader, PasswordManager manager) {
        System.out.print("What service password are you looking for? ");
        String serviceNeeded = reader.nextLine();
        if (serviceNeeded.isEmpty()) return; 

        manager.loadFromFile(serviceNeeded);
    }

    
}
