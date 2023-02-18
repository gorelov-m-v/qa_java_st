package ru.stqa.pft.sandbox;

public class Point {
    // Координаты точки A(x, y) на координатной плоскости.
    public int x;
    public int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Расстояние между двумя точками нахожу по формуле: distance = √((xb - xa)^2 + (yb - ya)^2)
    public double distance(Point p) {
        return  Math.sqrt(Math.pow((this.x - p.x), 2) + Math.pow((this.y - p.y), 2));
    }
}
