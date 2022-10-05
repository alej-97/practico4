package practico4.logicaPersistencia.accesoBD;

public class Consultas {
	
	public String insertarDuenio() {
		return "INSERT INTO duenio (cedula, nombre, apellido) values(?,?,?)";
	}
	
	public String existeDuenio() {
		return "SELECT cedula FROM duenio where cedula=?";
	}
	
	public String insertarMascota() {
		return "INSERT INTO mascota (numInscripcion, apodo, raza, cedDuenio) values(?,?,?,?)";
	}
	
	public String existeMascota() {
		return "SELECT cedula FROM mascota where cedula=?";
	}
	
}
