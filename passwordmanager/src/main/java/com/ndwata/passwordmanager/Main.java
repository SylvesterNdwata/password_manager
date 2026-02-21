package com.ndwata.passwordmanager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        PasswordManager manager = new PasswordManager();

        UserInterface ui = new UserInterface(reader, manager);
        ui.start();

    }


}
