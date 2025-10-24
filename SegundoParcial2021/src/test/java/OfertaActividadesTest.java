import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class OfertaActividadesTest {
    @BeforeAll
    public static void setUp() throws CupoExcedidoException, EdadInsuficienteException { // Reinicia
        OfertaActividades.nomina = new ArrayList<>();

        Persona pp = new Persona("Nombre", "Apellido", "dni", 50);
        Actividad a1 = new Actividad("Actividad1", pp, 10, 10);
        Actividad a2 = new Actividad("Actividad2", pp, 10, 10);

        Socio s1 = new Socio("Nombre1", "Apellido1", "dni1", 10);
        Socio s2 = new Socio("Nombre2", "Apellido2", "dni2", 20);
        Socio s3 = new Socio("Nombre3", "Apellido3", "dni3", 30);

        a1.inscribirSocio(s1);
        a1.inscribirSocio(s2);
        a1.inscribirSocio(s3);

        a2.inscribirSocio(s1);
        a2.inscribirSocio(s2);

        OfertaActividades.nomina.add(a1);
        OfertaActividades.nomina.add(a2);
    }

    @Test // Comprobar que no es posible agregar actividades repetidas
    public void testNoPermiteActividadRepetida() throws Exception {
        Persona pp = new Persona("Nombre1", "Apellido1", "dni1", 10);
        Actividad a3 = new Actividad("Actividad3", pp, 10, 10);
        Actividad a4 = new Actividad("Actividad3", pp, 10, 10);

        assertDoesNotThrow(() -> OfertaActividades.NuevaActividad(a3));
        assertThrows(YaExisteActividadException.class, () -> OfertaActividades.NuevaActividad(a4));
    }

    // Crear un test dinámico que pemita comprobar que ninguna actividad excede su cupo máximo
    @TestFactory
    public Collection<DynamicTest> testActividadesCupoMax() {
        List<DynamicTest> tests = new ArrayList<>();

        for (Actividad aa : OfertaActividades.nomina) {
            tests.add(DynamicTest.dynamicTest("Verificando cupo máximo de: " + aa.getNombre(), () -> {
                assertTrue(aa.getInscriptos().size() <= aa.getCupo());
            }));
        }

        return tests;
    }

    // Generar un script de prueba que permita parametrizar la cantidad de Socios generados para inscribir a una
    // actividad totalmente aleatoria
    @ParameterizedTest
    @CsvFileSource(resources= "/socios.csv", numLinesToSkip = 1) // Salta el header
    public void testGenerarSociosInscribirActividad(String nombre, String apellido, String dni, int edad) {
        Socio ss = new Socio(nombre, apellido, dni, edad);

        Random rnd = new Random();
        Actividad aa = OfertaActividades.nomina.get(rnd.nextInt(OfertaActividades.nomina.size()));

        int edadMinima = aa.getEdadMinima();
        int cupo = aa.getCupo();

        if (ss.getPersona().getEdad() < edadMinima) {
            assertThrows(EdadInsuficienteException.class, () -> aa.inscribirSocio(ss));
        } else if (aa.getInscriptos().size() == cupo) {
            assertThrows(CupoExcedidoException.class, () -> aa.inscribirSocio(ss));
        } else {
            assertDoesNotThrow(() -> aa.inscribirSocio(ss));
        }
    }
}
