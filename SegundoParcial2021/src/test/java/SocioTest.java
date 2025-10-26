import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.testng.annotations.BeforeTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SocioTest {
    // Comprobar que el método toString() de java.Socio devuelve una cadena con el formato <Apellido>, <Nombre>
    @Test
    public void testValidarToString() {
        Socio ss = new Socio("Nombre", "Apellido", "dni", 11);
        assertEquals(ss.toString(), ss.getPersona().getApellido() + ", " + ss.getPersona().getNombre());
    }

    // Crear un generador de datos de Socios para comprobar que el método toString()
    // devuelve una cadena en formato <Apellido>, <Nombre>
    // @Test(invocationCount = 10) -> TestNG
    @RepeatedTest(value = 10) // -> JUnit5
    public void testValidarToStringGenerador(RepetitionInfo rep) { // RepetitionInfo empieza en 1
        int idx = rep.getCurrentRepetition() - 1;

        String nombre = "Nombre" + idx;
        String apellido = "Apellido" + idx;
        String dni = "Dni" + idx;

        Socio ss = new Socio(nombre, apellido, dni, idx);
        assertEquals(ss.toString(), ss.getPersona().getApellido() + ", " + ss.getPersona().getNombre());
    }
}
