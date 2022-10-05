package practico4.logicaPersistencia.accesoBD;

public class Consultas {
	public String existeDuenio() {
		return "SELECT cedula FROM duenios WHERE cedula = ?";
	}
	
	public String insertDuenio() {
		return "INSERT INTO duenios (cedula, nombre, apellido) VALUES (?,?,?)";
	}
	
	public String listDuenios() {
		return "SELECT cedula, nombre, apellido from duenios";
	}
	
	public String cantMascotasXDuenio() {
		return "SELECT count(cedDuenio) as cant FROM mascotas WHERE cedDuenio = ?";
	}
	
	public String insertMascota() {
		return "INSERT INTO mascotas (numInscripcion, apodo, raza, cedDuenio) VALUES (?,?,?,?)";
	}
}
