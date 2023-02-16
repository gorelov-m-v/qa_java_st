package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testPointsInDifferentQuarters() {
        Point p = new Point(-1, -1, 4,5);
        Assert.assertEquals(p.distance(), 7.810249675906654);
    }
    @Test
    public void testPointsInSameQuarter() {
        Point p = new Point(1, 1, 4,5);
        Assert.assertEquals(p.distance(), 5.0);
    }
    @Test
    public void testPointsInSamePlace() {
        Point p = new Point(1, 1, 1,1);
        Assert.assertEquals(p.distance(), 0.0);
    }
}
