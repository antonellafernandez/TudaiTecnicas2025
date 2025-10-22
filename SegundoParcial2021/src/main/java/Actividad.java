import java.util.ArrayList;

public class Actividad {
	private String nombre;
	private Persona encargado;
	private ArrayList<Socio> inscriptos;
	private int edadMinima;
	private int cupo;

	public Actividad(String nombre, Persona encargado, int edadMinima, int cupo) {
		this.nombre = nombre;
		this.encargado = encargado;
		this.inscriptos = new ArrayList<>();
		this.edadMinima = edadMinima;
		this.cupo = cupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Persona getEncargado() {
		return encargado;
	}

	public void setEncargado(Persona encargado) {
		this.encargado = encargado;
	}

	public ArrayList<Socio> getInscriptos() {
		return new ArrayList<>(inscriptos);
	}

	public void inscribirSocio(Socio s) throws CupoExcedidoException, EdadInsuficienteException {
		if (s.getPersona().getEdad() < edadMinima) throw new EdadInsuficienteException();
		if (this.getInscriptos().size() > this.getCupo()) throw new CupoExcedidoException();

		inscriptos.add(s);
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public String toString() {
		return this.getNombre()
				+ "  a cargo de "
				+ this.getEncargado().getNombre();
	}
}
