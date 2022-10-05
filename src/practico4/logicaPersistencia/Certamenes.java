package practico4.logicaPersistencia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;

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
}
