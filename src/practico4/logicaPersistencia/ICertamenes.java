package practico4.logicaPersistencia;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;
import practico4.logicaPersistencia.valueObjects.VOMascota;

public interface ICertamenes extends Remote {
	public void nuevoDuenio(VODuenio voD) throws RemoteException, PersistenciaException, DuenioException;
	public List<VODuenio> listarDuenios() throws RemoteException, PersistenciaException;
	public void nuevaMascota(int cedula, VOMascota voM) throws RemoteException, DuenioException, PersistenciaException;
}
