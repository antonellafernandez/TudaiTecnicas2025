import java.util.ArrayList;

public class NominaSocios {
	static ArrayList<Socio> nomina;
	static int cupo;
	static int proxSocio;

	/***
	 *
	 * @param p java.Persona a asociar. Si la persona ya existe, no se efectiviza el alta y se lanza una excepcion
	 * @author oscar
	 * */
	public static void Asociar(Persona p) throws YaExisteSocioException {
		Socio s = new Socio(p);
		if (nomina == null) nomina = new ArrayList<Socio>();

		if (!NominaSocios.YaExisteEnNomina(p)) {
			s.setIdSocio(proxSocio);
			proxSocio++;
			nomina.add(s);
		} else throw new YaExisteSocioException();
	}

	/**
	 * Obtiene el id Autonumérico del próximo socio
	 */
	public static int getNextID() {
		return proxSocio;
	}

	/**
	 * Indica si la persona existe en la nómina según su valor autonumérico
	 * @param p java.Persona a buscar
	 */
	public static boolean YaExisteEnNomina(Persona p) {
		for(Socio s : nomina) {
			if (p.getNombre().equals(s.getPersona().getNombre())) {
				return true;
			}
		}

		return false;
	}

	/***
	 * Da de baja un socio específíco
	 * @param s java.Socio a dar de baja
	 * @throws NoExisteSocioException
	 */
	public static void Baja(Socio s) throws NoExisteSocioException {
		if (nomina.contains(s)) { // Redefinir equals en java.Socio
			nomina.remove(s);
		} else throw new NoExisteSocioException();
	}

	/***
	 * Cuenta la cantidad de socios en la nómina
	 * @return cantidad d socios
	 */
	public static int ContarSocios() {
		return proxSocio;
	}

	public static ArrayList<Socio> GetNomina(){
		return nomina;
	}

	public static int getCupo() {
		return cupo;
	}

	public static void setCupo(int cupo) {
		NominaSocios.cupo = cupo;
	}
}
