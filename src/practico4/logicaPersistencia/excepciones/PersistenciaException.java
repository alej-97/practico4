package practico4.logicaPersistencia.excepciones;

public class PersistenciaException extends Exception {
	private static final long serialVersionUID = 3701794063557774359L;
	
	public PersistenciaException(String mensaje) {
		super(mensaje);
	}
}
