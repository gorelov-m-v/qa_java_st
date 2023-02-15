package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("Михаил");
        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " +s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("прямоугольника со сторонами " + r.a + " + " + r.b + " = " +r.area());
    }
    public static void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }
}