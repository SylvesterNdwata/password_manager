package com.ndwat.passwordmanager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.ndwata.passwordmanager.PasswordGenerator;

public class PasswordGeneratorTest {
    
    @Test
    public void genratesPasswordCorrectLength() {
        PasswordGenerator gen = new PasswordGenerator();
        String pw = gen.generatePassword();
        assertTrue(pw.length() > 0 && pw.length() <= 20);
    }

}
