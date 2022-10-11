package practico4.logicaPersistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import practico4.logicaPersistencia.accesoBD.AccesoBD;
import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.MascotaRegistradaException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;
import practico4.logicaPersistencia.valueObjects.VOMascota;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class Fachada {
	private String driver;
	private String url;
	private String user;
	private String password;

	public Fachada() {
		Properties p = new Properties();
		String nomArchivo = "config/datos.properties";

		try {
			p.load(new FileInputStream(nomArchivo));
			this.driver = p.getProperty("driver");
			this.url = p.getProperty("url");
			this.user = p.getProperty("user");
			this.password = p.getProperty("password");

			Class.forName(driver);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void nuevoDuenio(VODuenio voD) throws PersistenciaException, DuenioException {
		Connection con = null;
		AccesoBD accesoBD = new AccesoBD();
		String msg = null;
		boolean existsCed = false;
		boolean errorPersistencia = false;
		try {
			con = DriverManager.getConnection(url, user, password);
			existsCed = accesoBD.existsDuenio(con, voD.getCedula());
			if (!existsCed) {
				accesoBD.insertDuenio(con, voD);
			} else {
				msg = "Dueño ya existe";
			}
			con.close();
			con = null;
		} catch (Exception e) {
			errorPersistencia = true;
			msg = "error de acceso a los datos";
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					errorPersistencia = true;
					msg = "error de acceso a los datos";
				}
			}
			if (existsCed)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
	}

	public void nuevaMascota(int cedula, VOMascota voM) throws DuenioException, PersistenciaException {
		Connection con = null;
		AccesoBD accesoBD = new AccesoBD();
		String msg = null;
		boolean existsCed = false;
		boolean errorPersistencia = false;

		try {
			con = DriverManager.getConnection(url, user, password);
			existsCed = accesoBD.existsDuenio(con, cedula);
			if (existsCed) {
				accesoBD.nuevaMascota(con, cedula, voM);
			} else {
				msg = "No existe dueño";
			}
			con.close();
			con = null;
		} catch (SQLException e) {
			e.printStackTrace();
			errorPersistencia = true;
			msg = "error de acceso a los datos";
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					errorPersistencia = true;
					msg = "error de acceso a los datos";
				}
			}
			if (!existsCed)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
	}

	void borrarDuenioMascota(int cedula) {

	}

	public List<VODuenio> listarDuenios() throws PersistenciaException {
		Connection con = null;
		AccesoBD accesoBD = new AccesoBD();
		List<VODuenio> duenios = null;

		try {
			con = DriverManager.getConnection(url, user, password);
			duenios = accesoBD.listDuenios(con);
			con.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new PersistenciaException("error de acceso a los datos");
		}

		return duenios;
	}

	public List<VOMascotaList> listarMascotasDuenio(int cedula) throws DuenioException, PersistenciaException {
		List<VOMascotaList> mascotas = null;
		Connection con = null;
		AccesoBD accesoBD = new AccesoBD();
		String msg = null;
		boolean existsCed = false;
		boolean errorPersistencia = false;

		try {
			con = DriverManager.getConnection(url, user, password);
			existsCed = accesoBD.existsDuenio(con, cedula);
			if (existsCed) {
				mascotas = accesoBD.listarMascotasDuenio(cedula, con);
			} else {
				msg = "No existe dueǹo";
			}
			con.close();
			con = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorPersistencia = true;
			msg = "error de acceso a datos";
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					errorPersistencia = true;
					msg = "error de acceso a los datos";
				}
			}
			if (!existsCed)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}

		return mascotas;
	}

	public VOMascota obtenerMascota(int cedula, int numInscripcion)
			throws DuenioException, PersistenciaException, MascotaRegistradaException {
		Connection con = null;
		AccesoBD accesoBD = new AccesoBD();
		boolean errorPersistencia = false;
		boolean existeDuenio = false;
		boolean mascotaRegistrada = false;
		String msg = null;
		VOMascota mascota = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			existeDuenio = accesoBD.existsDuenio(con, cedula);
			if (existeDuenio) {
				mascotaRegistrada = accesoBD.mascotaRegistrada(con, cedula, numInscripcion);
				if (mascotaRegistrada) {
					mascota = accesoBD.obtenerMascota(con, cedula, numInscripcion);
				} else {
					msg = "Dueño con cedula " + cedula + " no tiene mascota registrada con numero de inscripcion "
							+ numInscripcion;
				}
			} else {
				msg = "No existe dueño";
			}
			con.close();
			con = null;
		} catch (SQLException e) {
			e.printStackTrace();
			errorPersistencia = true;
			msg = "error de acceso a los datos";
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					errorPersistencia = true;
					msg = "error de acceso a los datos";
				}
			}
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
		Connection con = null;
		AccesoBD accesoBD = new AccesoBD();
		boolean errorPersistencia = false;
		boolean existeDuenio = false;
		String msg = null;

		int cantidad = 0;
		try {
			con = DriverManager.getConnection(url, user, password);
			existeDuenio = accesoBD.existsDuenio(con, cedula);
			if (existeDuenio) {
				cantidad = accesoBD.contarMascotas(con, cedula, raza);
			} else {
				msg = "Cedula no registrada";
			}
			con.close();
			con = null;
		} catch (SQLException e) {
			errorPersistencia = true;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					errorPersistencia = true;
					msg = "error de acceso a los datos";
				}
			}
			if (!existeDuenio)
				throw new DuenioException(msg);
			if (errorPersistencia)
				throw new PersistenciaException(msg);
		}
		return cantidad;
	}
}
