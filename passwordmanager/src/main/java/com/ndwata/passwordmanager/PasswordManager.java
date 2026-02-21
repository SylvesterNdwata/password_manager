package com.ndwata.passwordmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public boolean authenticate(Scanner scanner) {
        File f = new File("master.txt");

        String storedHash = null;

        if (f.exists()) {
            try (Scanner reader = new Scanner(Paths.get("master.txt"))) {
                if (reader.hasNextLine()) {
                    storedHash = reader.nextLine().trim();
                }
            } catch (IOException e) {
                System.out.println("Could not write to the master password file");
            }
        }

        if (storedHash == null || storedHash.isEmpty()) {
            System.out.println("No master password set. Please set a master password: ");
            String masterPassword = scanner.nextLine();
            String hash = hash(masterPassword);

            try (FileWriter writer = new FileWriter(f)) {
                writer.write(hash + "\n");
                System.out.println("Master password set successfully.");
                return true;
            } catch (IOException e) {
                System.out.println("Could not write to the master password file.");
                return false;
            }
        }

        System.out.println("Enter the master password: ");
        String enteredPassword = scanner.nextLine();
        String enteredHash = hash(enteredPassword);

        if (storedHash.equals(enteredHash)) {
            return true;
        } else {
            System.out.println("Incorrect master password.");
            return false;
        }
    }

    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();
            for (byte b: hashBytes) {
                String hexByte = Integer.toHexString(0xff & b);
                if (hexByte.length() == 1) {
                    hex.append("0");
                }
                hex.append(hexByte);
            }

            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}
