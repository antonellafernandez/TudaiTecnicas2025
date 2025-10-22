import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActividadTest {
    private Actividad aa;

    @BeforeEach
    public void setUp() throws Exception {
        // Crear actividad
        Persona pp = new Persona("Nombre", "Apellido", "dni", 30);
        aa = new Actividad("Actividad1", pp, 10, 10);

        // Crear y agregar socios
        Persona p1 = new Persona("Nombre1", "Apellido1", "dni1", 11);
        Persona p2 = new Persona("Nombre1", "Apellido1", "dni1", 12);
        Persona p3 = new Persona("Nombre1", "Apellido1", "dni1", 13);

        Socio s1 = new Socio(p1);
        Socio s2 = new Socio(p2);
        Socio s3 = new Socio(p3);

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

}
