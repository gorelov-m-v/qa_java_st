package ru.stqa.pft.sandbox;

public class Point {
    // Координаты точки A(x, y) на координатной плоскости.
    public int ax;
    public int ay;

    // Координаты точки B(x, y) на координатной плоскости.
    public int bx;
    public int by;

    public Point(int ax, int ay, int bx, int by) {
        this.ax = ax;
        this.ay = ay;
        this.bx = bx;
        this.by = by;
    }

    // Расстояние между двумя точками нахожу по формуле: distance = √((xb - xa)^2 + (yb - ya)^2)
    public double distance() {
        return  Math.sqrt(Math.pow((bx - ax), 2) + Math.pow((by - ay), 2));
    }
}
