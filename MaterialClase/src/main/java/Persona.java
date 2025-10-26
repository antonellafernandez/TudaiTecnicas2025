public class Persona {
	private String nombre;
	private String dni;
	private String fechaNacimiento;
	private int edad;
	private boolean habilitadoParaVotar;

	public Persona(String nombre, String dni, String fechaNacimiento, int edad, boolean habilitadoParaVotar) {
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.habilitadoParaVotar = habilitadoParaVotar;
		this.dni=dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isHabilitadoParaVotar() {
		return habilitadoParaVotar;
	}

	public void setHabilitadoParaVotar(boolean habilitadoParaVotar) {
		this.habilitadoParaVotar = habilitadoParaVotar;
	}
}
