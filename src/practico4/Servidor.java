package practico4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import practico4.logicaPersistencia.Certamenes;
import practico4.logicaPersistencia.excepciones.PersistenciaException;

public class Servidor {
	private Certamenes certamenes;

	public Servidor() throws RemoteException, PersistenciaException {
		super();
		certamenes = new Certamenes();
	}

	public static void main(String[] args) {

		Servidor servidor;

		try {
			servidor = new Servidor();
			servidor.iniciarServicio();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void iniciarServicio() throws FileNotFoundException, IOException {
		String nomArchivo = "config/servidor-config.properties";
		Properties props = new Properties();
		String ipServidor;
		String puertoServidor;
		String ruta;

		props.load(new FileInputStream(nomArchivo));
		ipServidor = props.getProperty("ipServidor");
		puertoServidor = props.getProperty("puertoServidor");
		ruta = "//" + ipServidor + ":" + puertoServidor + "/Certamenes";
		LocateRegistry.createRegistry(Integer.parseInt(puertoServidor));
		Naming.rebind(ruta, certamenes);

	}
}
