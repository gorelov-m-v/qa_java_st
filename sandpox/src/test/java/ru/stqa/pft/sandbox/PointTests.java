package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testPointsInDifferentQuarters() {
        Point a = new Point(-1, -1);
        Point b = new Point(4, 5);
        Assert.assertEquals(a.distance(b), 7.810249675906654);
    }
    @Test
    public void testPointsInSameQuarter() {
        Point a = new Point(1, 1);
        Point b = new Point(4, 5);
        Assert.assertEquals(a.distance(b), 5.0);
    }
    @Test
    public void testPointsInSamePlace() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 1);
        Assert.assertEquals(a.distance(b), 0.0);
    }
}
