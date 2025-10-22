import java.util.Date;

public class Socio {
	Persona persona;
	int idSocio;
	Date fechaIngreso;

	public Socio(Persona p) {
		this.persona = p;
	}

	/***
	 * Da de crea un objeto socio partiendo de tipos primitivos
	 * @param nombre Nombre del socio
	 * @param apellido Apellido del socio
	 * @param dni
	 * @param edad
	 */
	public Socio(String nombre, String apellido, String dni, int edad) {
		Persona p = new Persona(nombre, apellido, dni, edad);
		this.persona = p;
		this.setFechaIngreso(new Date());
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public int getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String toString() {
		return this.getPersona().getApellido() + ",  " + this.getPersona().getNombre();
	}
}
