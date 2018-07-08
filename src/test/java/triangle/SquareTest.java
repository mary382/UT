package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SquareTest {
    private Triangle triangle;

    @BeforeClass
    public void before(){
        System.out.println("Getting square test starting.");
    }

    @AfterClass
    public void after(){
        System.out.println("Getting square test ending.");
    }

    @DataProvider
    public Object[][] getSides() {
        return new Double[][]{
                {3.0, 4.0, 5.0, 6.0},
                {4.0, 3.0, 5.0, 6.0},
                {5.0, 3.0, 4.0, 6.0},
                {1.0, 2.1, 3.0, 0.54},
                {4.7, 5.1, 9.6, 4.72},
                {21.7, 13.8, 35.49, 7.28},
        };
    }

    @Test(dataProvider = "getSides", groups = "positive")
    public void checkSquare(Double a, Double b, Double c, Double square){
        triangle = new Triangle(a, b, c);
        Assert.assertEquals( triangle.getSquare(), square, 0.01);
    }

    @DataProvider
    public Object[][] getInvalidSides() {
        return new Double[][]{
                {-3.0, 4.0, 5.0},
                {4.0, -3.0, 5.0},
                {5.0, 3.0, -4.0},
                {-3.0, -4.0, 5.0},
                {4.0, -3.0, -5.0},
                {-5.0, 3.0, -4.0},
                {-3.0, -4.0, -5.0},
                {0.0, 4.0, 5.0},
                {4.0, 0.0, 5.0},
                {5.0, 3.0, 0.0},
                {0.0, 0.0, 5.0},
                {4.0, 0.0, 0.0},
                {0.0, 3.0, 0.0},
                {0.0, 0.0, 0.0},
                {1.0, 2.0, 3.0},
                {3.0, 2.0, 5.0},
        };
    }

    @Test(dataProvider = "getInvalidSides", expectedExceptions = ArithmeticException.class)
    public void checkInvalidSquare(Double a, Double b, Double c){
        triangle = new Triangle(a, b, c);
        triangle.getSquare();
    }


}
