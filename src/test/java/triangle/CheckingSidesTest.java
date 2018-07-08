package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckingSidesTest {
    private Triangle triangle;

    @BeforeClass
    public void before() {
        System.out.println("Checking sides test starting.");
    }

    @AfterClass
    public void after() {
        System.out.println("Checking sides test ending.");
    }

    @DataProvider
    public Object[][] getValidSides() {
        return new Double[][]{
                {3.0, 4.0, 5.0},
                {3.0, 5.0, 4.0},
                {4.0, 3.0, 5.0},
                {4.0, 5.0, 3.0},
                {5.0, 3.0, 4.0},
                {5.0, 4.0, 3.0},
        };
    }

    @Test(dataProvider = "getValidSides", groups = "positive")
    public void areValidSides(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertTrue(triangle.checkTriangle(),
                "sides [" + a + "] [" + b + "] [" + c + "] don't form a triangle.");
    }

    @DataProvider
    public Object[][] getInvalidSides() {
        return new Object[][]{
                {-3.0, 4.0, -5.0, "a<=0"},
                {0.0, -5.0, 4.0, "a<=0"},
                {4.0, -3.0, -5.0, "b<=0"},
                {4.0, 0.0, -5.0, "b<=0"},
                {4.0, 3.0, -5.0, "c<=0"},
                {4.0, 3.0, 0.0, "c<=0"},
                {4.0, 5.0, 10.0, "a+b<=c"},
                {4.0, 5.0, 9.0, "a+b<=c"},
                {4.0, 15.0, 10.0, "a+c<=b"},
                {4.0, 14.0, 10.0, "a+c<=b"},
                {8.0, 4.0, 3.0, "b+c<=a"},
                {7.0, 4.0, 3.0, "b+c<=a"},
        };
    }

    @Test(dataProvider = "getInvalidSides")
    public void areNotValidSides(Double a, Double b, Double c, String message) {
        triangle = new Triangle(a, b, c);
        boolean isValid = triangle.checkTriangle();
        Assert.assertFalse(isValid, "sides [" + a + "] [" + b + "] [" + c + "] don't form a triangle.");
    }

    @Test(dataProvider = "getInvalidSides")
    public void isCorrectMessage(Double a, Double b, Double c, String message) {
        triangle = new Triangle(a, b, c);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), message,
                "actual message doesn't correspond to expected.");
    }

    @Test
    public void checkSimple() {
        triangle = new Triangle(3.1, 4.0, 7.0999999999999999);
        Assert.assertTrue(triangle.checkTriangle());
    }
}
