package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = "positive")
public class PositiveDetectionTest {
    final int TR_EQUILATERAL = 1; // равносторонний
    final int TR_ISOSCELES = 2;   // равнобедренный
    final int TR_EQUILATERAL_ISOSCELES = 3;   // равносторонний+равнобедренный
    final int TR_ORDYNARY = 4;     // обычный
    final int TR_RECTANGULAR = 8; // прямоугольный
    final int TR_RECTANGULAR_ISOSCELES = 10; // прямоугольный+равнобедренный


    private Triangle triangle;

    @BeforeClass
    public void before() {
        System.out.println("Positive test starting.");
    }

    @AfterClass
    public void after() {
        System.out.println("Positive test ending.");
    }

    @DataProvider
    public Object[][] getRectangularTriangle() {
        return new Double[][]{
                {3.0, 4.0, 5.0},
                {3.0, 5.0, 4.0},
                {4.0, 3.0, 5.0},
                {4.0, 5.0, 3.0},
                {5.0, 3.0, 4.0},
                {5.0, 4.0, 3.0},
        };
    }

    @Test(dataProvider = "getRectangularTriangle")
    public void checkRectangular(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), TR_RECTANGULAR,
                "sides [" + a + "] [" + b + "] [" + c + "] don't form a rectangular triangle.");
    }

    @DataProvider
    public Object[][] getOrdinaryTriangle() {
        return new Double[][]{
                {1.0, 2.0, 2.7},
                {1.0, 2.7, 2.0},
                {2.0, 1.0, 2.7},
                {2.0, 2.7, 1.0},
                {2.7, 1.0, 2.0},
                {2.7, 2.0, 1.0},
                {2.0, 5.0, 4.0},
        };
    }

    @Test(dataProvider = "getOrdinaryTriangle")
    public void checkOrdinary(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), TR_ORDYNARY,
                "sides [" + a + "] [" + b + "] [" + c + "] don't form an ordinary triangle.");
    }

    @DataProvider
    public Object[][] getIsoscelesTriangle() {
        return new Double[][]{
                {1.0, 3.0, 3.0},
                {3.0, 3.0, 1.0},
                {3.0, 1.0, 3.0},
                {1.1, 3.3, 3.3},
                {3.3, 3.3, 1.3},
                {3.3, 1.5, 3.3},
        };
    }

    @Test(dataProvider = "getIsoscelesTriangle")
    public void checkIsosceles(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), TR_ISOSCELES,
                "sides [" + a + "] [" + b + "] [" + c + "] don't form an isosceles triangle.");
    }

    @DataProvider
    public Object[][] getEquilateralTriangle() {
        return new Double[][]{
                {13.0, 13.0, 13.0},
                {333.0, 333.0, 333.0},
                {3.3, 3.3, 3.3},
        };
    }

    @Test(dataProvider = "getEquilateralTriangle")
    public void checkEquilateral(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), TR_EQUILATERAL_ISOSCELES,
                "sides [" + a + "] [" + b + "] [" + c + "] don't form an equilateral triangle.");
    }

    @DataProvider
    public Object[][] getRectangularIsoscelesTriangle() {
        return new Double[][]{
                {3.0, 3.0, Math.pow(2 * 3 * 3, 0.5)},
                {1.0, Math.pow(2, 0.5), 1.0},
                {Math.pow(2 * 9.7 * 9.7, 0.5), 9.7, 9.7},
        };
    }

    @Test(dataProvider = "getRectangularIsoscelesTriangle")
    public void checkRectangularIsosceles(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), TR_RECTANGULAR_ISOSCELES,
                "sides [" + a + "] [" + b + "] [" + c + "] don't form a rectangular isosceles triangle.");
    }

    @DataProvider
    public Object[][] getSomeTriangle() {
        return new Double[][]{
                {1.0, 2.0, 3.0},
                {1.0, 3.0, 2.0},
                {2.0, 1.0, 3.0},
                {2.0, 3.0, 1.0},
                {3.0, 1.0, 2.0},
                {3.0, 2.0, 1.0},
        };
    }

    @Test(dataProvider = "getSomeTriangle", expectedExceptions = ArithmeticException.class)
    public void checkNotTriangle(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        triangle.detectTriangle();
    }
}
