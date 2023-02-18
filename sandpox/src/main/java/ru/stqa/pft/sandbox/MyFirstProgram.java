package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("Михаил");
        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " +s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " + " + r.b + " = " +r.area());

        // Задание №2
        Point a = new Point(1, 1);
        Point b = new Point(4, 5);
        System.out.println("Расстояние между двумя точками на плоскости: A(" + a.x + ", " + a.y + ") и B(" + b.x + ", " + b.y + ") равно: " + a.distance(b));
    }
    public static void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }
}