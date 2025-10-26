import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
// import org.junit.Test;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoPrecedencia {
    /* JUnit
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("DemoPrecedencia -> BeforeClass ");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("DemoPrecedencia -> AfterClass ");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("DemoPrecedencia -> Before");

    }

    // tearDown() método para instancias de test
    @After
    public void tearDown() throws Exception {
        System.out.println("DemoPrecedencia -> After");
    }

    @Test
    public void testFuncion1() {
        System.out.println("DemoPrecedencia -> testFuncion1");
    }

    @Test
    public void testFuncion2() {
        System.out.println("DemoPrecedencia -> TestFuncion2");
    }

    @Test
    public void testFuncion3() {
        System.out.println("DemoPrecedencia -> TestFuncion3");
    } */

    // TestNG
    @Test(dataProvider = "Generador")
    public void TestGenerado(Integer n, String s) {
        System.out.println("El generador provee: "+s+" -"+" - "+n);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("beforeMethod()");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("afterMethod()");
    }

    @DataProvider
    public Object[][] Generador() {
        System.out.println("Genero casos");
        return new Object[][] {
                new Object[] { 1, "a" },
                new Object[] { 2, "b" },
        };
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite()");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite()");
    }

    @Test
    public void test1() {
        System.out.println("Ejecuto Test1");
    }

    @Test
    public void test2() {
        System.out.println("Ejecuto Test2");
    }

    @Test
    public void testSinEmplementar() {
        throw new RuntimeException("Este test aun no esta implementado");
    }
}
