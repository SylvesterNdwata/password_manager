package com.ndwata.passwordmanager;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PasswordGenerator {
    private final String letters= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String numbers = "0123456789";
    private final String symbols = "!@#$%^&*()_+-=~`|\\:;\"'<>,.?/";

    private final SecureRandom random = new SecureRandom();

    public String generatePassword() {
        int lettersCount = random.nextInt(3) + 10;
        int numberCount = random.nextInt(3) + 2;
        int symbolCount = random.nextInt(3) + 2;

        List<Character> passwordChars = new ArrayList<>();

        for (int i = 0; i < lettersCount; i++) {
            passwordChars.add(letters.charAt(random.nextInt(letters.length())));
        }

        for (int i = 0; i < numberCount; i++) {
            passwordChars.add(numbers.charAt(random.nextInt(numbers.length())));
        }

        for (int i = 0; i < symbolCount; i++) {
            passwordChars.add(symbols.charAt(random.nextInt(symbols.length())));
        }

        Collections.shuffle(passwordChars, random);

        StringBuilder password = new StringBuilder();

        for (char c: passwordChars) {
            password.append(c);
        }

        return password.toString().trim();
    }
}
