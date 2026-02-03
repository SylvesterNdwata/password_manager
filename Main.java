import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to the Password Manager");

        System.out.print("What service's password would you like to save? ");
        String service = reader.nextLine();

        System.out.print("What is the username? ");
        String username = reader.nextLine();

        System.out.print("What is the password? ");
        String password = reader.nextLine();

        PasswordEntry entry = new PasswordEntry(service, username, password);

        System.out.println(entry);


    }
}