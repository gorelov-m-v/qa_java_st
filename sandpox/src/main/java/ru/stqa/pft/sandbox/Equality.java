package ru.stqa.pft.sandbox;

import java.sql.SQLOutput;

public class Equality {

    public static void main(String[] args) {
        String s1 = "firefox";
        String s2 = s1; // String s2 = new String(s1)

        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        // "==" - Сравнивает ссылки. "equals" - Сравнивает содержимое объектов.
    }
}
