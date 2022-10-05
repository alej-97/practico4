package practico4.logicaPersistencia.valueObjects;

public class VODuenio {
	private int cedula;
	private String nombre;
	private String apellido;
	
	public VODuenio(int cedula, String nombre, String apellido) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}
}
