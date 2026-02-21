package com.ndwata.passwordmanager;

import java.util.Scanner;

public class UserInterface {

    private final Scanner scanner;
    private final PasswordManager manager;

    public UserInterface(Scanner scanner, PasswordManager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void start() {
        System.out.println("Welcome to the Password Manager");
        if (!this.manager.authenticate(this.scanner)) {
            System.out.println("Authentication failed. Exiting the program.");
            return;
        }
        while (true) {
            System.out.print("Type 1 to add a password or 2 to search for a password or 3 to list all passwords or leave empty to exit: ");
            String response = this.scanner.nextLine();

            if (response.isEmpty()) {
                break;
            }

            switch (response) {
                case "1":
                    addPassword();
                    break;

                case "2":
                    searchPassword();
                    break;

                case "3":
                    listPasswords();
                    break;

                default:
                    System.out.println("Invalid option, Please try again.");
            }

        }
    }

    public void addPassword() {
        System.out.print("What service's password would you like to save? ");
        String service = this.scanner.nextLine();

        if (service.isEmpty()) {
            return;
        }

        System.out.print("What is the username? ");
        String username = this.scanner.nextLine();

        System.out.println("Would you like to generate a password? Type Y for yes and N for no. ");
        String response = this.scanner.nextLine();
        String password;
        if (response.equalsIgnoreCase("Y")) {
            PasswordGenerator generator = new PasswordGenerator();
            password = generator.generatePassword();
            System.out.println("Your generated password is: " + password);
        } else {
            System.out.print("What is the password? ");
            password = this.scanner.nextLine();
        }

        PasswordEntry entry = new PasswordEntry(service, username, password);

        System.out.println(entry);
        this.manager.saveToFile(entry);
    }

    public void searchPassword() {
        System.out.print("What service password are you looking for? ");
        String serviceNeeded = this.scanner.nextLine();
        if (serviceNeeded.isEmpty()) {
            return;
        }

        this.manager.loadFromFile(serviceNeeded);
    }

    public void listPasswords() {
        System.out.println("Below are all the services saved into the file");
        this.manager.listPasswords();
    }
}
