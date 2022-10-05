package practico4.logicaPersistencia.valueObjects;

import java.io.Serializable;

public class VOMascota implements Serializable {

	private static final long serialVersionUID = 3649848661228845641L;
	private String apodo;
	private String raza;
	
	public VOMascota(String apodo, String raza) {
		super();
		this.apodo = apodo;
		this.raza = raza;
	}

	public String getApodo() {
		return apodo;
	}

	public String getRaza() {
		return raza;
	}
}
