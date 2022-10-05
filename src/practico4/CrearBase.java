package practico4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CrearBase {

	public static void main(String[] args) {
		Properties p = new Properties();
		String nomArchivo = "config/root.properties";
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		String[][] duenios = { { "1234567", "Jimi", "Hendrix" }, { "2345678", "Janis", "Joplin" },
				{ "3456789", "Jim", "Morrison" }, { "4567890", "Kurt", "Cobain" }, { "5678901", "Amy", "Winehouse" } };
		try {
			p.load(new FileInputStream(nomArchivo));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");

			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			String sql0 = "CREATE DATABASE IF NOT EXISTS certamenes";
			stmt = con.createStatement();
			System.out.println(sql0);
			stmt.executeUpdate(sql0);
			System.out.println("OK");

			String sql1 = "CREATE TABLE IF NOT EXISTS certamenes.duenios ("
					+ "cedula int not null, nombre varchar(45), apellido varchar(45)," + "Primary key (cedula))";
			System.out.println(sql1);
			stmt.executeUpdate(sql1);
			System.out.println("OK");

			String sql2 = "CREATE TABLE IF NOT EXISTS certamenes.mascotas ("
					+ "numInscripcion int not null, apodo varchar(45) not null, raza varchar(45), cedDuenio int, "
					+ "Primary key (numInscripcion, apodo), "
					+ "Foreign key (cedDuenio) References certamenes.duenios(cedula))";
			System.out.println(sql2);
			stmt.executeUpdate(sql2);
			System.out.println("OK");
			stmt.close();
			stmt = null;

			String insert = "INSERT INTO certamenes.duenios (cedula, nombre, apellido) VALUES (?,?,?)";
			pstmt = con.prepareStatement(insert);

			for (String[] a : duenios) {
				pstmt.setInt(1, Integer.parseInt(a[0]));
				pstmt.setString(2, a[1]);
				pstmt.setString(3, a[2]);

				pstmt.executeUpdate();
			}
			System.out.println("carga exitosa");
			pstmt.close();
			pstmt = null;

			con.close();
			con = null;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
