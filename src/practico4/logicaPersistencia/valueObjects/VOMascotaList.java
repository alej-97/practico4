package practico4.logicaPersistencia.valueObjects;

public class VOMascotaList extends VOMascota {

	private static final long serialVersionUID = -5642382459435297764L;
	private int numInscripcion;

	public VOMascotaList(String apodo, String raza, int numInscripcion) {
		super(apodo, raza);
		this.numInscripcion = numInscripcion;
	}

	public int getNumInscripcion() {
		return numInscripcion;
	}

}
