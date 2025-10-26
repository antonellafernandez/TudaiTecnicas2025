// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NominaSociosTest {
    /* JUnit5
    @BeforeEach // Se ejecuta antes de cada test
    public void setUp() { // Reinicia
        NominaSocios.nomina = new ArrayList<>();
        NominaSocios.proxSocio = 0;
    }

    @Test // Comprobar que no pueden agregarse socios repetidos
    public void testNoPermiteSocioRepetido() throws Exception {
        Persona p1 = new Persona("Nombre1", "Apellido1", "dni1", 10);
        Persona p2 = new Persona("Nombre1", "Apellido1", "dni1", 10);

        assertDoesNotThrow(() -> NominaSocios.Asociar(p1));
        assertThrows(YaExisteSocioException.class, () -> NominaSocios.Asociar(p2));
    } */

    @BeforeMethod // Se ejecuta antes de cada método
    public void clear(){
        NominaSocios.nomina = new ArrayList<>();
        NominaSocios.proxSocio = 0;
    }

    // Generar un script de prueba que permita parametrizar la cantidad de elementos Socios generados aleatoriamente
    @DataProvider
    public Object[][] getCantSociosAleatorios(){
        return new Object[][]{
                { 10 }, { 20 }, { 30 }, { 40 }, { 100 },
        };
    }

    // Generar un script de prueba que permita parametrizar la cantidad de elementos Socios generados aleatoriamente
    @Test(dataProvider = "getCantSociosAleatorios")
    public void testSociosAleatorios(int cantidad) throws CupoExcedidoException, YaExisteSocioException {
        System.out.println("testSociosAleatorios con cantidad: " + cantidad);
        assertEquals(0, NominaSocios.GetNomina().size());
        NominaSocios.setCupo(cantidad);

        for (int i = 0; i < cantidad; i++){
            NominaSocios.Asociar(
                    new Persona(
                            "Nombre" + i,
                            "Apellido" + i,
                            "Dni" + i,
                            10 + i
                    )
            );
        }

        assertEquals(cantidad, NominaSocios.GetNomina().size(), "Se debe agregar " + cantidad + " Socios a la nomina");
    }
}
