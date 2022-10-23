package practico4.logicaPersistencia;

import java.sql.SQLException;
import java.util.List;

import practico4.logicaPersistencia.accesoBD.AccesoBD;
import practico4.logicaPersistencia.accesoBD.IConexion;
import practico4.logicaPersistencia.accesoBD.IPoolConexiones;
import practico4.logicaPersistencia.accesoBD.PoolConexiones;
import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.MascotaRegistradaException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;
import practico4.logicaPersistencia.valueObjects.VOMascota;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class Fachada {
	IPoolConexiones pool;
	
	public Fachada() throws PersistenciaException {
		pool = new PoolConexiones();
	}

	public void nuevoDuenio(VODuenio voD) throws PersistenciaException, DuenioException {
		IConexion icon = null;
		AccesoBD accesoBD = new AccesoBD();
		String msg = null;
		boolean existsCed = false;
		boolean errorPersistencia = false;
		try {
			icon = pool.obtenerConexion();
			existsCed = accesoBD.existsDuenio(icon, voD.getCedula());
			if (!existsCed) {
				accesoBD.insertDuenio(icon, voD);
			} else {
				msg = "Dueño ya existe";
			}
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			try {
				pool.liberarConexion(icon, false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			errorPersistencia = true;
			msg = "error de acceso a los datos";
		} finally {
			if (existsCed)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
	}

	public void nuevaMascota(int cedula, VOMascota voM) throws DuenioException, PersistenciaException {
		IConexion icon = null;
		AccesoBD accesoBD = new AccesoBD();
		String msg = null;
		boolean existsCed = false;
		boolean errorPersistencia = false;

		try {
			icon = pool.obtenerConexion();
			existsCed = accesoBD.existsDuenio(icon, cedula);
			if (existsCed) {
				accesoBD.nuevaMascota(icon, cedula, voM);
			} else {
				msg = "No existe dueño";
			}
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				pool.liberarConexion(icon, false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			errorPersistencia = true;
			msg = "error de acceso a los datos";
		} finally {
			if (!existsCed)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
	}

	public void borrarDuenioMascota(int cedula) throws DuenioException, PersistenciaException {
		IConexion icon = null;
		AccesoBD accesoBD = new AccesoBD();
		
		String msg = null;
		boolean existsCed = false;
		boolean errorPersistencia = false;
		
		try {
			icon = pool.obtenerConexion();
			existsCed = accesoBD.existsDuenio(icon, cedula);
			if (existsCed) {
				accesoBD.borrarDuenioMascotas(icon, cedula);
			} else {
				msg = "No existe dueǹo";
			}
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				pool.liberarConexion(icon, false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			errorPersistencia = true;
			msg = "error de acceso a datos";
		} finally {
			if (!existsCed)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
	}

	public List<VODuenio> listarDuenios() throws PersistenciaException {
		IConexion icon = null;
		AccesoBD accesoBD = new AccesoBD();
		List<VODuenio> duenios = null;

		try {
			icon = pool.obtenerConexion();
			duenios = accesoBD.listDuenios(icon);
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				pool.liberarConexion(icon, false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new PersistenciaException("error de acceso a los datos");
		}

		return duenios;
	}

	public List<VOMascotaList> listarMascotasDuenio(int cedula) throws DuenioException, PersistenciaException {
		List<VOMascotaList> mascotas = null;
		IConexion icon = null;
		AccesoBD accesoBD = new AccesoBD();
		String msg = null;
		boolean existsCed = false;
		boolean errorPersistencia = false;

		try {
			icon = pool.obtenerConexion();
			existsCed = accesoBD.existsDuenio(icon, cedula);
			if (existsCed) {
				mascotas = accesoBD.listarMascotasDuenio(cedula, icon);
			} else {
				msg = "No existe dueǹo";
			}
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				pool.liberarConexion(icon, false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			errorPersistencia = true;
			msg = "error de acceso a datos";
		} finally {
			if (!existsCed)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
		return mascotas;
	}

	public VOMascota obtenerMascota(int cedula, int numInscripcion)
			throws DuenioException, PersistenciaException, MascotaRegistradaException {
		IConexion icon = null;
		AccesoBD accesoBD = new AccesoBD();
		boolean errorPersistencia = false;
		boolean existeDuenio = false;
		boolean mascotaRegistrada = false;
		String msg = null;
		VOMascota mascota = null;
		try {
			icon = pool.obtenerConexion();
			existeDuenio = accesoBD.existsDuenio(icon, cedula);
			if (existeDuenio) {
				mascotaRegistrada = accesoBD.mascotaRegistrada(icon, cedula, numInscripcion);
				if (mascotaRegistrada) {
					mascota = accesoBD.obtenerMascota(icon, cedula, numInscripcion);
				} else {
					msg = "Dueño con cedula " + cedula + " no tiene mascota registrada con numero de inscripcion "
							+ numInscripcion;
				}
			} else {
				msg = "No existe dueño";
			}
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				pool.liberarConexion(icon, false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			errorPersistencia = true;
			msg = "error de acceso a los datos";
		} finally {
			if (!existeDuenio)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
			if (!mascotaRegistrada)
				throw new MascotaRegistradaException(msg);
		}
		return mascota;
	}

	public int contarMascotas(int cedula, String raza) throws PersistenciaException, DuenioException {
		IConexion icon = null;
		AccesoBD accesoBD = new AccesoBD();
		boolean errorPersistencia = false;
		boolean existeDuenio = false;
		String msg = null;

		int cantidad = 0;
		try {
			icon = pool.obtenerConexion();
			existeDuenio = accesoBD.existsDuenio(icon, cedula);
			if (existeDuenio) {
				cantidad = accesoBD.contarMascotas(icon, cedula, raza);
			} else {
				msg = "Cedula no registrada";
			}
			pool.liberarConexion(icon, true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				pool.liberarConexion(icon, false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			errorPersistencia = true;
		} finally {
			if (!existeDuenio)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
		return cantidad;
	}
}
