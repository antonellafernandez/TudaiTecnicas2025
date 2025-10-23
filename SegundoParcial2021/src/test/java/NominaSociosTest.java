import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NominaSociosTest {
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
    }

}
