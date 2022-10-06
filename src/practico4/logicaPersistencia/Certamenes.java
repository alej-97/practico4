package practico4.logicaPersistencia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;
import practico4.logicaPersistencia.valueObjects.VOMascota;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class Certamenes extends UnicastRemoteObject implements ICertamenes {

	private Fachada fachada;
	
	private static final long serialVersionUID = 5476454874034098350L;

	public Certamenes() throws RemoteException {
		super();
		fachada = new Fachada();
	}

	@Override
	public void nuevoDuenio(VODuenio voD) throws PersistenciaException, DuenioException {
		fachada.nuevoDuenio(voD);
	}

	@Override
	public List<VODuenio> listarDuenios() throws PersistenciaException {
		return fachada.listarDuenios();
	}

	@Override
	public void nuevaMascota(int cedula, VOMascota voM) throws RemoteException, DuenioException, PersistenciaException {
		fachada.nuevaMascota(cedula, voM);
	}

	@Override
	public List<VOMascotaList> listarMascotasDuenio(int cedula)
			throws RemoteException, DuenioException, PersistenciaException {
		return fachada.listarMascotasDuenio(cedula);
	}
}
