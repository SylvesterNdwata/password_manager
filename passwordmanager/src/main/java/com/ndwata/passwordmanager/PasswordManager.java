package com.ndwata.passwordmanager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordManager {
    private final ArrayList<PasswordEntry> entries;

    public PasswordManager() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(PasswordEntry entry) {
        entries.add(entry);
    }

    public void listEntries() {
        for (PasswordEntry e : entries) {
            System.out.println(e);
        }
    }

    public void saveToFile(PasswordEntry entry) {
        try (FileWriter writer = new FileWriter("passwords.txt", true)) {

            writer.write(entry.getService().toLowerCase() + "," + entry.getUsername() + "," + entry.getPassword() + "\n");
        } catch (IOException e) {
            System.out.println("Could not add entry to the file.");
        }
    }

    public void loadFromFile(String serviceNeeded) {
        boolean found = false;
        try (Scanner reader = new Scanner(Paths.get("passwords.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                String[] parts = line.split(",");

                String service = parts[0].trim();
                String username = parts[1].trim();
                String password = parts[2].trim();

                if (serviceNeeded.equalsIgnoreCase(service)) {
                    System.out.println("For the service, " + serviceNeeded + ": The username is: " + username + " and password is: " + password);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Password for the service " + serviceNeeded + " was not found");
            }
        } catch (IOException e) {
            System.out.println("Could not read the file.");
        }
    }

    public void listPasswords() {
        try (Scanner reader = new Scanner(Paths.get("passwords.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Could not load the file");
        }
    }
}
