package practico4.grafica.controladores;

import java.rmi.RemoteException;

import practico4.grafica.ventanas.BorrarDuenio;
import practico4.logicaPersistencia.ICertamenes;
import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;

public class ControladorBorrarDuenio {
	private BorrarDuenio ventana;
	private ICertamenes certamenes;
	
	public ControladorBorrarDuenio(BorrarDuenio ventana, ICertamenes certamenes) {
		super();
		this.ventana = ventana;
		this.certamenes = certamenes;
	}
	
	public void borrarDuenioMascota(int cedula) {
		String msg = "Borrado exitoso";
		boolean hayError = false;
		
		try {
			certamenes.borrarDuenioMascota(cedula);
		} catch (RemoteException e) {
			hayError = true;
			msg = "Error de comunicación";
		} catch (DuenioException | PersistenciaException e) {
			hayError = true;
			msg = e.getMessage();
		}
		ventana.setMensaje(hayError, msg);
	}
}
