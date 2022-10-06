package practico4.logicaPersistencia.accesoBD;

import java.sql.Connection;
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
	public boolean existsDuenio(Connection con, int cedula) throws SQLException {
		PreparedStatement pstmt = null;
		Consultas consultas = new Consultas();
		ResultSet rs = null;
		boolean existsDuenio = false;
		
		pstmt = con.prepareStatement(consultas.existeDuenio());
		pstmt.setInt(1, cedula);
		rs = pstmt.executeQuery();
		if (rs.next())
			existsDuenio = true;
		rs.close();
		pstmt.close();
		return existsDuenio;
	}

	public void insertDuenio(Connection con, VODuenio voD) throws PersistenciaException {
		PreparedStatement pstmt = null;
		Consultas consultas = new Consultas();

		try {
			pstmt = con.prepareStatement(consultas.insertDuenio());
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

	public List<VODuenio> listDuenios(Connection con) throws SQLException {
		ArrayList<VODuenio> duenios = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		Consultas consultas = new Consultas();
		stmt = con.createStatement();
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

	public void nuevaMascota(Connection con, int cedula, VOMascota voM) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Consultas consultas = new Consultas();
		int cantidad = 0;
		
		pstmt = con.prepareStatement(consultas.cantMascotasXDuenio());
		pstmt.setInt(1,cedula);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			cantidad = rs.getInt("cant");
		}
		rs.close();
		pstmt.close();
		cantidad += 1;
		
		pstmt = con.prepareStatement(consultas.insertMascota());
		pstmt.setInt(1, cantidad);
		pstmt.setString(2, voM.getApodo());
		pstmt.setString(3, voM.getRaza());
		pstmt.setInt(4, cedula);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public List<VOMascotaList> listarMascotasDuenio(int cedula, Connection con) throws SQLException {
		ArrayList<VOMascotaList> mascotas = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Consultas consultas = new Consultas();
		
		pstmt = con.prepareStatement(consultas.listarMascotasDuenio());
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
	
}
