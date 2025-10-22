import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Random;

public class SocioTest {
    private final Random rnd = new Random();
    private static final String[] nombres = {"Ana","Luis","Carla","Jorge","Sofía","Nicolás","Valentina","Mateo","Camila","Lucas"};
    private static final String[] apellidos = {"García","Rodríguez","López","Martínez","González","Pérez","Sánchez","Ramírez","Torres","Flores"};

    // Generar un script de prueba que permita parametrizar la cantidad de elementos Socios generados aleatoriamente
    // @Test(invocationCount = 10) -> TestNG
    @RepeatedTest(value = 10) // -> JUnit5
    void generarSocioAleatorioJUnit() {
        String nombre = nombres[rnd.nextInt(nombres.length)];
        String apellido = apellidos[rnd.nextInt(apellidos.length)];
        String dni = String.valueOf(10_000_000 + rnd.nextInt(89_999_999));
        int edad = 10 + rnd.nextInt(11); // Genera int entre 10 y 20 inclusives

        Socio s = new Socio(nombre, apellido, dni, edad);
        assertNotNull(s);
    }
}
