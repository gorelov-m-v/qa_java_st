package ru.stqa.pft.addressbook.generators;

import java.util.Random;

public class StringGenerator {

    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String DIGITS = "0123456789";

    Random random = new Random();

    public String randomString(int length) {
        String combination = LOWER + DIGITS + UPPER;
        char[] string = new char[length];
        for(int i = 0; i < length; i++) {
            string[i] = combination.charAt(random.nextInt(combination.length()));
        }
        return new String(string);
    }

    public String randomEmail() {
        String suffix = "@testmail.ru";
        String randomEmail = randomString(10) + suffix;
        return randomEmail.toLowerCase();
    }

    public String randomName() {
        String prefix = "test-";
        return prefix + randomString(20 - prefix.length());
    }

    public String randomPhone() {
        String combination = DIGITS;
        char[] string = new char[7];
        for(int i = 0; i < 7; i++) {
            string[i] = combination.charAt(random.nextInt(combination.length()));
        }
        return "7913" + new String(string);
    }
}
