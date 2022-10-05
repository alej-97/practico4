package practico4;

import java.io.FileInputStream;
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
		String nomArchivo = "config/datos.properties";
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String insert = "INSERT INTO certamenes.duenios (cedula, nombre, apellido) VALUES (?,?,?)";
		String[][] duenios = {{"1234567", "Jimmy", "Hendrix"},
							   {"2345678", "Jannice", "Joplin"},
							   {"3456789", "Jim", "Morrison"},
							   {"4567890", "Kurt", "Kobain"},
							   {"5678901", "Amy", "Winehouse"}};

		try {
			p.load(new FileInputStream (nomArchivo));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, user, password);
			String sql0 = "CREATE DATABASE IF NOT EXISTS certamenes";
			stmt = con.createStatement();
			stmt.executeUpdate(sql0);
			
			String sql1 = "CREATE TABLE IF NOT EXISTS certamenes.duenios " +
			             "(cedula INT NOT NULL, nombre VARCHAR(45), apellido VARCHAR(45)," +
					     " Primary key (cedula))";
			System.out.println(sql1);
			stmt.executeUpdate(sql1);
			
			String sql2 = "CREATE TABLE IF NOT EXISTS certamenes.mascotas " +
		             "(numInscripcion INT not null, apodo VARCHAR(45) NOT NULL, raza VARCHAR(45), cedDuenio INT," +
				     " Primary key (numInscripcion, apodo)," +
		             " Foreign Key (cedDuenio) REFERENCES certamenes.duenios(cedula))";
			System.out.println(sql2);
			stmt.executeUpdate(sql2);
			stmt.close();
			stmt = null;
			
			pstmt = con.prepareStatement(insert);
			
			for (String[] a: duenios) {
				pstmt.setInt(1, Integer.parseInt(a[0]));
				pstmt.setString(2, a[1]);
				pstmt.setString(3, a[2]);
				
				pstmt.executeUpdate();
			}
			pstmt.close();
			pstmt = null;
			
			con.close();
			con = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
