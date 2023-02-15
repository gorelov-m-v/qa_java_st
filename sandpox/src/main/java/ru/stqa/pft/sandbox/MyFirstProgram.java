package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("Михаил");
        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " +s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " + " + r.b + " = " +r.area());

        // Задание №2
        Point p = new Point(1,1,4,5);
        System.out.println("Расстояние между двумя точками на плоскости: A(" + p.ax + ", " + p.ay + ") и B(" + p.bx + ", " + p.by + ") равно: " + p.distance());
    }
    public static void hello(String name) {
        System.out.println("Hello, " + name + "!");
    }
}