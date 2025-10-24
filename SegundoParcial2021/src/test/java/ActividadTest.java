import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ActividadTest {
    private Actividad aa;

    @BeforeEach
    public void setUp() throws Exception {
        NominaSocios.nomina = new ArrayList<>();

        // Crear actividad
        Persona pp = new Persona("Nombre", "Apellido", "dni", 30);
        NominaSocios.Asociar(pp);
        aa = new Actividad("Actividad1", pp, 10, 3);

        // Crear y agregar socios
        Socio s1 = new Socio("Nombre1", "Apellido1", "dni1", 11);
        Socio s2 = new Socio("Nombre2", "Apellido2", "dni2", 12);
        Socio s3 = new Socio("Nombre3", "Apellido3", "dni3", 13);

        aa.inscribirSocio(s1);
        aa.inscribirSocio(s2);
        aa.inscribirSocio(s3);
    }

    // Crear un test dinámico que pemita comprobar que todos los inscriptos de una actividad cumplen con la edad mínima
    @TestFactory
    public Collection<DynamicTest> testInscriptosEdadMinima() {
        List<DynamicTest> tests = new ArrayList<>();

        for (Socio s : aa.getInscriptos()) {
            tests.add(DynamicTest.dynamicTest("Verificando edad de Inscripto " + s.getIdSocio(), () -> {
                assertTrue(s.getPersona().getEdad() >= aa.getEdadMinima());
            }));
        }

        return tests;
    }

    // Comprobar que al intentar inscribir un socio con una edad no permitida se dispara la excepción EdadInsuficieneException
    @Test
    public void testInscribirSocio() {
        Socio ss = new Socio("Nombre", "Apellido", "dni", 1);
        assertThrows(EdadInsuficienteException.class, () -> {
            aa.inscribirSocio(ss);
        });
    }

    // Comprobar que el encargado de una actividad es un socio de la nómina
    @Test
    public void testEncargadoSocio() {
        assertTrue(NominaSocios.YaExisteEnNomina(aa.getEncargado()));
    }

    // Comprobar que el método toString() de java.Actividad devuelve una cadena
    // con el formato "<NombreActividad> a cargo de <Apellido>"
    @Test
    public void testValidarToString() {
        assertEquals(aa.toString(), aa.getNombre() + " a cargo de " + aa.getEncargado().getApellido());
    }

    // Comprobar que al intentar inscribir más usuarios del cupo permitido,
    // se dispara la excepción java.CupoExcedidoException
    @Test
    public void testValidarCupo() {
        assertThrows(CupoExcedidoException.class, () -> {
            aa.inscribirSocio(new Socio("Nombre", "Apellido", "dni", 11));
        });
    }

    // Crear un generador de datos de Actividades aleatorias para comprobar que el cupo es siempre > 0
    @RepeatedTest(10)
    public void testCupoAleatorias(RepetitionInfo rep) { // Comienza en 1
        int idx = rep.getCurrentRepetition() - 1;
        String nombre = "Actividad" + idx;
        Persona encargado = new Persona("Nombre", "Apellido", "dni", 30);
        int edadMinima = idx;
        int cupo = idx + 1;

        Actividad nueva = new Actividad(nombre, encargado, edadMinima, cupo);
        assertTrue(nueva.getCupo() > 0);
    }
}
