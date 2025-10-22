import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OfertaActividadesTest {
    @BeforeEach
    public void setUp() { // Reinicia
        OfertaActividades.nomina = new ArrayList<>();
    }

    @Test // Comprobar que no es posible agregar actividades repetidas
    public void noPermiteActividadRepetida() throws Exception {
        Persona p = new Persona("Nombre1", "Apellido1", "dni1", 10);
        Actividad a1 = new Actividad("Actividad1", p, 10, 10);
        Actividad a2 = new Actividad("Actividad1", p, 10, 10);

        assertDoesNotThrow(() -> OfertaActividades.NuevaActividad(a1));
        assertThrows(YaExisteActividadException.class, () -> OfertaActividades.NuevaActividad(a2));
    }

}
