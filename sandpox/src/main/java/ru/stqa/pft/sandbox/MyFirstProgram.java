package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("Михаил");

        double n = 5;
        System.out.println("Площадь квадрата со стороной " + n + " = " +area(n));

        double a = 4;
        double b = 6;
        System.out.println("прямоугольника со сторонами " + a + " + " + b + " = " +area(a, b));

    }

    public static void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static double area(double l) {
        return l * l;
    }

    public static double area(double a, double b) {
        return a * b;
    }
}