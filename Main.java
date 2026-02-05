
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to the Password Manager");

        while (true) {

            System.out.print("Type 1 to add a password or 2 to search for a password or leave empty to exit: ");
            String response = reader.nextLine();

            if (response.isEmpty()) {
                break;
            }

            if (response.equals("1")) {

                System.out.print("What service's password would you like to save? ");
                String service = reader.nextLine();

                if (service.isEmpty()) {
                    break;
                }

                System.out.print("What is the username? ");
                String username = reader.nextLine();

                System.out.print("What is the password? ");
                String password = reader.nextLine();

                PasswordEntry entry = new PasswordEntry(service, username, password);

                System.out.println(entry);

                PasswordManager manager = new PasswordManager();
                manager.saveToFile(entry);
            } else if (response.equals("2")) {
                PasswordManager extract = new PasswordManager();

                System.out.print("What service password are you looking for? ");
                String serviceNeeded = reader.nextLine();

                extract.loadFromFile(serviceNeeded);
            }

        }

    }
}
