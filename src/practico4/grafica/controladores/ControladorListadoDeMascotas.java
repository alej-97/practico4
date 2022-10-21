package practico4.grafica.controladores;

import java.rmi.RemoteException;
import java.util.List;
import practico4.grafica.ventanas.ListarMascotaDeUnDuenio;
import practico4.logicaPersistencia.ICertamenes;
import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class ControladorListadoDeMascotas {
	ListarMascotaDeUnDuenio ventana;
	private ICertamenes certamenes;
	
	public ControladorListadoDeMascotas(ListarMascotaDeUnDuenio ventana, ICertamenes certamenes) {
		super();
		this.ventana = ventana;
		this.certamenes = certamenes;
	}
	
	public List<VOMascotaList> listarMascotasDuenio(int cedula) {
		List<VOMascotaList> mascotas = null;
		boolean hayError = false;
		String msg = null;
		
		try {
			mascotas = certamenes.listarMascotasDuenio(cedula);
		} catch (RemoteException e) {
			hayError = true;
			msg = "Error de comunicación";
		} catch (DuenioException | PersistenciaException e) {
			hayError = true;
			msg = e.getMessage();
		} 
		if (hayError) {
			ventana.setMensaje(hayError, msg);
		}
		return mascotas;
	}
	
}
