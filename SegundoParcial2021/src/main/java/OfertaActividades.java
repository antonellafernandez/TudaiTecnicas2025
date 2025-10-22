import java.util.ArrayList;

public class OfertaActividades {
	public static ArrayList<Actividad> nomina;

	public static void NuevaActividad(Actividad act) throws YaExisteActividadException {
		if (nomina == null) {
			nomina = new ArrayList<Actividad>();
		}

		if (!OfertaActividades.YaExisteEnNomina(act)) {
			nomina.add(act);
		} else throw new YaExisteActividadException();
	}

	public static boolean YaExisteEnNomina(Actividad a) {
		for(Actividad aa : nomina) {
			if (a.getNombre().equals(aa.getNombre())) {
				return true;
			}
		}

		return false;
	}

	public static void BorrarActividad(Actividad act) {
		if (!nomina.contains(act)) {
			nomina.remove(act);
		}
	}

	public static int CantidadActividades() {
		return nomina.size();
	}

	public static ArrayList<Actividad> GetNomina() {
		return nomina;
	}
}
