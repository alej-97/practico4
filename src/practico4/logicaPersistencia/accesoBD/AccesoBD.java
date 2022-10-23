package practico4.logicaPersistencia.accesoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;
import practico4.logicaPersistencia.valueObjects.VOMascota;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class AccesoBD {
	public boolean existsDuenio(IConexion icon, int cedula) throws SQLException {
		PreparedStatement pstmt = null;
		Consultas consultas = new Consultas();
		ResultSet rs = null;
		boolean existsDuenio = false;
		
		pstmt = ((Conexion) icon).getConnection().prepareStatement(consultas.existeDuenio());
		pstmt.setInt(1, cedula);
		rs = pstmt.executeQuery();
		if (rs.next())
			existsDuenio = true;
		rs.close();
		pstmt.close();
		return existsDuenio;
	}

	public void insertDuenio(IConexion icon, VODuenio voD) throws PersistenciaException {
		PreparedStatement pstmt = null;
		Consultas consultas = new Consultas();

		try {
			pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.insertDuenio());
			pstmt.setInt(1, voD.getCedula());
			pstmt.setString(2, voD.getNombre());
			pstmt.setString(3, voD.getApellido());
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PersistenciaException("error: " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new PersistenciaException("error: " + e.getMessage());
				}
			}
		}
	}

	public List<VODuenio> listDuenios(IConexion icon) throws SQLException {
		ArrayList<VODuenio> duenios = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Consultas consultas = new Consultas();
		stmt = ((Conexion)icon).getConnection().createStatement();
		rs = stmt.executeQuery(consultas.listDuenios());
		while (rs.next()) {
			int cedula = rs.getInt("cedula");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			VODuenio voD = new VODuenio(cedula, nombre, apellido);
			duenios.add(voD);
		}
		rs.close();
		stmt.close();

		return duenios;
	}

	public void nuevaMascota(IConexion icon, int cedula, VOMascota voM) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Consultas consultas = new Consultas();
		int cantidad = 0;
		
		pstmt = ((Conexion) icon).getConnection().prepareStatement(consultas.cantMascotasXDuenio());
		pstmt.setInt(1,cedula);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			cantidad = rs.getInt("cant");
		}
		rs.close();
		pstmt.close();
		cantidad += 1;
		
		pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.insertMascota());
		pstmt.setInt(1, cantidad);
		pstmt.setString(2, voM.getApodo());
		pstmt.setString(3, voM.getRaza());
		pstmt.setInt(4, cedula);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public List<VOMascotaList> listarMascotasDuenio(int cedula, IConexion icon) throws SQLException {
		ArrayList<VOMascotaList> mascotas = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Consultas consultas = new Consultas();
		
		pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.listarMascotasDuenio());
		pstmt.setInt(1, cedula);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int numIns = rs.getInt("numInscripcion");
			String apodo = rs.getString("apodo");
			String raza  = rs.getString("raza");
			VOMascotaList voML = new VOMascotaList(apodo, raza, numIns);
			mascotas.add(voML);
		}
		rs.close();
		pstmt.close();
		return mascotas;
	}
	
	public VOMascota obtenerMascota(IConexion icon, int cedula, int numInscripcion) throws SQLException  {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Consultas consultas = new Consultas();
		VOMascota mascota = null;
		
		pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.obtenerMascota());
		pstmt.setInt(1, numInscripcion);
		pstmt.setInt(2, cedula);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String apodo = rs.getString("apodo");
			String raza  = rs.getString("raza");
			mascota = new VOMascota(apodo, raza);
		}
		
		rs.close();
		pstmt.close();
		return mascota;
		
	}
	
	public boolean mascotaRegistrada(IConexion icon, int cedula, int numInscripcion) throws SQLException {
		PreparedStatement pstmt = null;
		Consultas consultas = new Consultas();
		ResultSet rs = null;
		boolean mascotaRegistrada = false;
		
		pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.mascotaRegistrada());
		pstmt.setInt(1, numInscripcion);
		pstmt.setInt(2, cedula);
		rs = pstmt.executeQuery();
		if (rs.next())
			mascotaRegistrada = true;
		rs.close();
		pstmt.close();
		return mascotaRegistrada;
	}
	
	public int contarMascotas(IConexion icon, int cedula, String raza) throws SQLException {
		int cantidad = 0;
		PreparedStatement pstmt = null;
		Consultas consultas = new Consultas();
		ResultSet rs = null;
		
		pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.contarMascotas());
		pstmt.setInt(1, cedula);
		pstmt.setString(2, raza);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			cantidad = rs.getInt("cantidad");
		}
		rs.close();
		pstmt.close();

		return cantidad;
	}
	
	public void borrarDuenioMascotas(IConexion icon, int cedula) throws SQLException {
		PreparedStatement pstmt = null;
		Consultas consultas = new Consultas();
		
		pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.borrarMascotas());
		pstmt.setInt(1, cedula);
		pstmt.executeUpdate();
		pstmt.close();
		
		pstmt = ((Conexion)icon).getConnection().prepareStatement(consultas.borrarDuenio());
		pstmt.setInt(1, cedula);
		pstmt.executeUpdate();
		pstmt.close();
	}
}
