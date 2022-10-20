package practico4.grafica.controladores;

import java.rmi.RemoteException;

import practico4.grafica.ventanas.NuevoDuenio;
import practico4.logicaPersistencia.ICertamenes;
import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;

public class ControladorNuevoDuenio {
	private NuevoDuenio nd;
	private ICertamenes certamenes;
	
	public ControladorNuevoDuenio(NuevoDuenio nd, ICertamenes certamenes) {
		super();
		this.nd = nd;
		this.certamenes = certamenes;
	}
	
	public void nuevoDuenio(int cedula, String nombre, String apellido) {
		boolean hayError = false;
		String msg = "Dueño registrado correctamente";
		VODuenio vod = new VODuenio(cedula, nombre, apellido);
		try {
			certamenes.nuevoDuenio(vod);
		} catch (RemoteException e) {
			hayError = true;
			msg = "Error de comunicación";
		} catch ( PersistenciaException | DuenioException e) {
			hayError = true;
			msg = e.getMessage();
		}
		nd.setMensaje(hayError, msg);
	}
}
