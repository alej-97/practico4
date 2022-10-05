package practico4.logicaPersistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import practico4.logicaPersistencia.valueObjects.VODuenio;
import practico4.logicaPersistencia.valueObjects.VOMascota;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class Fachada {
	private String driver;
	private String url;
	private String password;
	private String user;
	
	public Fachada() {
		Properties p = new Properties();
		String nomArchivo = "config/datos.properties";
		
		try {
			p.load(new FileInputStream (nomArchivo));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			
			Class.forName(driver);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void nuevoDuenio(VODuenio voD) {
		
	}
	
	public void nuevoMascota(int cedula, VOMascota voM) {
		
	}
	
	public void borrarDuenioMascotas(int cedula) {
		
	}
	
	public List<VODuenio> listarDuenios() {
		List<VODuenio> duenios = new ArrayList<>();
		return duenios;
	}
	
	public List<VOMascotaList> listarMascotas(int cedula) {
		List<VOMascotaList> mascotas = new ArrayList<>();
		return mascotas;
	}
	
	public VOMascota obtenerMascota(int cedula, int numero) {
		return new VOMascota("","");
	}
	
	public int contarMascotas(int cedula, String raza) {
		return 0;
	}
	
	
}
