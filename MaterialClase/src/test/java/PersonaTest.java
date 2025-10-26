/* JUnit
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class PersonaTest {

    static Persona ejemplos[];
    static Persona casoActual;
    static Persona buCasoActual;

    @BeforeClass
    public static void cargarEjemplos() throws Exception {
        ejemplos= new Persona[6];

        ejemplos[0] = new Persona("Juan","26.150.235","1979-01-01",42,true);
        ejemplos[1]  = new Persona("Pedro","27.280.234","1980-02-01",41,true);
        ejemplos[2]  = new Persona("Maria","28.184.259","1981-03-01",47,true);
        ejemplos[3]  = new Persona("Cecilia","32.234.528","1983-04-01",38,true);
        ejemplos[4] = new Persona("Carlos","33.124.235","1985-04-01",36,true);
        ejemplos[5]  = new Persona("Jose","35.345.534","1987-04-01",34,true);
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("Bye byte ");
    }


    @Before
    public void elegirUno() throws Exception {
        //generador de numeros aleatorios
        Random generadorAleatorios = new Random();
        //genera un numero entre 1 y 5 y lo guarda en la variable numeroAleatorio
        int numeroAleatorio = generadorAleatorios.nextInt(PersonaTest.ejemplos.length);
        casoActual=ejemplos[numeroAleatorio];
        //  buCasoActual= clone(casoActual)

    }


    @Test
    public void tesHabilitadoParaVotar() {
        boolean obtenido = casoActual.isHabilitadoParaVotar();
        boolean esperado = casoActual.getEdad()>16;
        assertEquals(obtenido, esperado);
    }


    @Test
    public void testSetEdad() {
        casoActual.setFechaNacimiento("2000-10-08");
        int edadReportada = casoActual.getEdad();
        int edadReal = getEdad(casoActual.getFechaNacimiento());
        Assert.assertTrue(edadReportada == edadReal);
        //Assert.assertTrue("Fallo en el calculo de la edad", edadReportada == edadReal);
    }


    // Esta es una funcion auxiliar que no tiene ninguna anotacion
    public int getEdad(String fechaNacimiento) {
        try {
            // Crea un objeto SimpleDateFormat para parsear la fecha de nacimiento.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Parsea la fecha de nacimiento.
            Date fechaNac = sdf.parse(fechaNacimiento);

            // Obtiene la fecha actual.
            Date fechaActual = new Date();

            // Crea un objeto Calendar para calcular la diferencia entre las fechas.
            Calendar calNac = Calendar.getInstance();
            Calendar calActual = Calendar.getInstance();
            calNac.setTime(fechaNac);
            calActual.setTime(fechaActual);

            // Calcula la diferencia de años.
            int edad = calActual.get(Calendar.YEAR) - calNac.get(Calendar.YEAR);

            // Verifica si el cumpleaños ya pasó este año.
            if (calNac.get(Calendar.MONTH) > calActual.get(Calendar.MONTH) ||
                    (calNac.get(Calendar.MONTH) == calActual.get(Calendar.MONTH) &&
                            calNac.get(Calendar.DAY_OF_MONTH) > calActual.get(Calendar.DAY_OF_MONTH))) {
                edad--;
            }

            return edad;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Manejo de errores
        }
    }

    // TestsDinamicos
    @Test
    @DisplayName("Verificando varias características a la vez")
    public void testVariasCaracteristicas() {
        Persona p = new Persona("Mickey","2.234.528","1983-04-01",38,true);
        Assertions.assertAll("Probando que se cumplen varias características",
                ()-> assertEquals("No se almacena el dni",p.getDni(), "2.234.528"),
                ()-> assertEquals("Error al almacenar la edad",p.getEdad(),38)
        );
    }


    @TestFactory
    public Collection<DynamicTest> dynamicTestsFromUsuarios() {
        List<DynamicTest> tests = new ArrayList<>();
        List<Persona> testList = new ArrayList<Persona>();

        Persona p1 = new Persona("Juan","26.150.235","1979-01-01",44,true);
        Persona p2 = new Persona("Pedro","27.280.234","1980-02-01",43,true);
        Persona p3 = new Persona("Maria","28.184.259","1981-03-01",42,true);

        testList.add(p1);
        testList.add(p2);
        testList.add(p3);

        for (Persona p : testList) {
            tests.add(DynamicTest.dynamicTest("Testing: ", () -> {
                assertEquals(p.getEdad(), getEdad(p.getFechaNacimiento()));
            }));
        }

        return tests;
    }

} */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PersonaTest {

    @DataProvider
    public Persona[] GeneradorPersona() {
        System.out.println("Genero casos");
        return new Persona[]{
                new Persona("Juan", "26.150.235", "1979-01-01", 42, true),
                new Persona("Pedro", "27.280.234", "1980-02-01", 41, true),
                new Persona("Maria", "28.184.259", "1981-03-01", 47, true),
                new Persona("Cecilia", "32.234.528", "1983-04-01", 38, true),
                new Persona("Carlos", "33.124.235", "1985-04-01", 36, true),
                new Persona("Jose", "35.345.534", "1987-04-01", 34, true)

        };
    }


    @Test(dataProvider = "GeneradorPersona")
    public void testEdadBienCalculada(Persona p) {
        int edadReportada = p.getEdad();
        int edadReal = getEdad(p.getFechaNacimiento());

        Assert.assertTrue(edadReportada == edadReal);
        //Assert.assertTrue("Fallo en el calculo de la edad", edadReportada == edadReal);
    }


    public int getEdad(String fechaNacimiento) {
        try {
            // Crea un objeto SimpleDateFormat para parsear la fecha de nacimiento.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Parsea la fecha de nacimiento.
            Date fechaNac = sdf.parse(fechaNacimiento);

            // Obtiene la fecha actual.
            Date fechaActual = new Date();

            // Crea un objeto Calendar para calcular la diferencia entre las fechas.
            Calendar calNac = Calendar.getInstance();
            Calendar calActual = Calendar.getInstance();
            calNac.setTime(fechaNac);
            calActual.setTime(fechaActual);

            // Calcula la diferencia de años.
            int edad = calActual.get(Calendar.YEAR) - calNac.get(Calendar.YEAR);

            // Verifica si el cumpleaños ya pasó este año.
            if (calNac.get(Calendar.MONTH) > calActual.get(Calendar.MONTH) ||
                    (calNac.get(Calendar.MONTH) == calActual.get(Calendar.MONTH) &&
                            calNac.get(Calendar.DAY_OF_MONTH) > calActual.get(Calendar.DAY_OF_MONTH))) {
                edad--;
            }

            return edad;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Manejo de errores
        }
    }

}