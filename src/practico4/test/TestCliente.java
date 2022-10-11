package practico4.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import practico4.logicaPersistencia.ICertamenes;
import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.MascotaRegistradaException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;
import practico4.logicaPersistencia.valueObjects.VOMascota;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class TestCliente {
	public static void main(String[] args) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String strOpcion = null;
		int opcion = 0;
		String ruta = "//127.0.0.1:1099/Certamenes";

		try {
			ICertamenes certamenes = (ICertamenes) Naming.lookup(ruta);
			mostrarMenu();
			strOpcion = br.readLine();
			opcion = Integer.parseInt(strOpcion);
			while (opcion != 8) {
				switch (opcion) {
				case 1:
					altaDeDuenio(certamenes);
					break;
				case 2:
					listarDuenios(certamenes);
					break;
				case 3:
					nuevaMascota(certamenes);
					break;
				case 4:
					listarMascotasDuenio(certamenes);
					break;
				case 5:
					obtenerMascota(certamenes);
					break;
				case 6:
					contarMascotas(certamenes);
					break;
				case 7:
					borrarDuenioMascotas(certamenes);
					break;
				}
				mostrarMenu();
				strOpcion = br.readLine();
				opcion = Integer.parseInt(strOpcion);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void borrarDuenioMascotas(ICertamenes certamenes) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		int cedula = 0;
		boolean fin = false;
		boolean errorEntradaSalida = false;

		System.out.println("");
		System.out.println("Borrar Duenio");
		System.out.println("-------------");
		while (!fin) {
			try {
				System.out.println("Ingrese número de cédula: ");
				cedula = Integer.parseInt(br.readLine());
				System.out.print("Los datos son correctos? (S/N) ");
				fin = aceptarOpcion();
			} catch (IOException e) {
				System.out.println("");
				System.out.println("ERROR: error de E/S");
				fin = false;
				errorEntradaSalida = true;
			}
		}
		if (!errorEntradaSalida) {
			try {
				certamenes.borrarDuenioMascota(cedula);
				System.out.println("");
				System.out.println("Eliminación exitosa");
			} catch (RemoteException e) {
				System.out.println("");
				System.out.println("ERROR: error de comunicación con el servidor");
			} catch (DuenioException e) {
				System.out.println("");
				System.out.println("ERROR: " + e.getMessage());
			} catch (PersistenciaException e) {
				System.out.println("");
				System.out.println("ERROR: " + e.getMessage());
			}
			System.out.println("");
		}
	}

	private static void contarMascotas(ICertamenes certamenes) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		int cedula = 0;
		String raza = null;
		boolean fin = false;
		boolean errorEntradaSalida = false;
		int cantidad = 0;

		System.out.println("");
		System.out.println("Contar Mascotas");
		System.out.println("---------------");
		while (!fin) {
			try {
				System.out.println("Ingrese número de cédula: ");
				cedula = Integer.parseInt(br.readLine());
				System.out.println("Ingrese raza            : ");
				raza = br.readLine();
				System.out.print("Los datos son correctos? (S/N) ");
				fin = aceptarOpcion();
			} catch (IOException e) {
				System.out.println("");
				System.out.println("ERROR: error de E/S");
				fin = false;
				errorEntradaSalida = true;
			}
		}
		if (!errorEntradaSalida) {
			try {
				cantidad = certamenes.contarMascotas(cedula, raza);
				System.out.println("");
				System.out.println("Cantidad:" + cantidad);
			} catch (RemoteException e) {
				System.out.println("");
				System.out.println("ERROR: error de comunicación con el servidor");
			} catch (DuenioException e) {
				System.out.println("");
				System.out.println("ERROR: " + e.getMessage());
			} catch (PersistenciaException e) {
				System.out.println("");
				System.out.println("ERROR: " + e.getMessage());
			}
			System.out.println("");
		}
	}

	private static void obtenerMascota(ICertamenes certamenes) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		int cedula = 0;
		int numInscripcion = 0;
		boolean fin = false;
		boolean errorEntradaSalida = false;
		VOMascota mascota = null;
		System.out.println("");
		System.out.println("Obtener Mascota:");
		System.out.println("---------------------------");
		while (!fin) {
			try {
				System.out.print("Ingrese nro de cedula: ");
				cedula = Integer.parseInt(br.readLine());
				System.out.print("Ingrese nro de inscripcion: ");
				numInscripcion = Integer.parseInt(br.readLine());
				System.out.println("");
				System.out.print("Los datos son correctos? (S/N) ");
				fin = aceptarOpcion();
			} catch (IOException e) {
				System.out.println("");
				System.out.println("ERROR: error de E/S");
				fin = false;
				errorEntradaSalida = true;
			}
			if (!errorEntradaSalida) {
				try {
					mascota = certamenes.obtenerMascota(cedula, numInscripcion);
					System.out.println("");
					System.out.println("Apodo:" + mascota.getApodo());
					System.out.println("Raza:" + mascota.getRaza());

				} catch (RemoteException e) {
					System.out.println("");
					System.out.println("ERROR: error de comunicación con el servidor");
				} catch (DuenioException e) {
					System.out.println("");
					System.out.println("ERROR: " + e.getMessage());
				} catch (PersistenciaException e) {
					System.out.println("");
					System.out.println("ERROR: " + e.getMessage());
				} catch (MascotaRegistradaException e) {
					System.out.println("");
					System.out.println("ERROR: " + e.getMessage());
				}
				System.out.println("");
			}
		}

	}

	private static void listarMascotasDuenio(ICertamenes certamenes) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		int cedula = 0;
		boolean fin = false;
		boolean errorEntradaSalida = false;

		System.out.println("");
		System.out.println("Listar Mascotas de un Dueño");
		System.out.println("---------------------------");
		while (!fin) {
			System.out.print("Ingrese nro de cedula: ");
			try {
				cedula = Integer.parseInt(br.readLine());
				System.out.println("");
				System.out.print("Los datos son correctos? (S/N) ");
				fin = aceptarOpcion();
			} catch (IOException e) {
				System.out.println("");
				System.out.println("ERROR: error de E/S");
				fin = false;
				errorEntradaSalida = true;
			}
			if (!errorEntradaSalida) {
				try {
					List<VOMascotaList> mascotas = certamenes.listarMascotasDuenio(cedula);
					for (VOMascotaList voML : mascotas) {
						System.out.println("Número de Inscripción: " + voML.getNumInscripcion());
						System.out.println("Apodo:                 " + voML.getApodo());
						System.out.println("Raza:                  " + voML.getRaza());
						System.out.println("");
					}
				} catch (RemoteException e) {
					System.out.println("");
					System.out.println("ERROR: error de comunicación con el servidor");
				} catch (DuenioException e) {
					System.out.println("");
					System.out.println("ERROR: " + e.getMessage());
				} catch (PersistenciaException e) {
					System.out.println("");
					System.out.println("ERROR: " + e.getMessage());
				}
				System.out.println("");
			}
		}
	}

	private static void nuevaMascota(ICertamenes certamenes) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String cedula = null;
		String apodo = null;
		String raza = null;

		System.out.println("");
		System.out.println("Nueva Mascota");
		System.out.println("-------------");
		boolean fin = false;

		try {
			while (!fin) {
				System.out.print("Ingrese nro de cedula: ");
				cedula = br.readLine();
				System.out.print("Ingrese apodo: ");
				apodo = br.readLine();
				System.out.print("Ingrese raza: ");
				raza = br.readLine();
				System.out.println("");
				System.out.print("Los datos son correctos? (S/N) ");
				fin = aceptarOpcion();
			}
			VOMascota voM = new VOMascota(apodo, raza);
			certamenes.nuevaMascota(Integer.parseInt(cedula), voM);
			System.out.println("Mascota registrada correctamente.");
		} catch (IOException e) {
			System.out.println("ERROR: error de E/S");
		} catch (NumberFormatException e) {
			System.out.println("ERROR: la cedula no es un número");
		} catch (DuenioException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (PersistenciaException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	private static void listarDuenios(ICertamenes certamenes) {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);

		System.out.println("");
		System.out.println("Listado de Dueños");
		System.out.println("-----------------");
		try {
			List<VODuenio> duenios = certamenes.listarDuenios();
			for (VODuenio voD : duenios) {
				System.out.println("Cédula:   " + voD.getCedula());
				System.out.println("Nombre:   " + voD.getNombre());
				System.out.println("Apellido: " + voD.getApellido());
				System.out.println("");
			}
			System.out.println("Digite Enter para continuar...");
			br.readLine();
		} catch (PersistenciaException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR: error de E/S");
		}
	}

	private static void altaDeDuenio(ICertamenes certamenes) throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String cedula = null;
		String nombre = null;
		String apellido = null;

		System.out.println("");
		System.out.println("Alta de Dueño");
		System.out.println("-------------");
		boolean fin = false;
		while (!fin) {
			System.out.print("Ingrese nro de cedula: ");
			cedula = br.readLine();
			System.out.print("Ingrese nombre: ");
			nombre = br.readLine();
			System.out.print("Ingrese apellido: ");
			apellido = br.readLine();
			System.out.println("");
			System.out.print("Los datos son correctos? (S/N) ");
			fin = aceptarOpcion();
		}
		try {
			certamenes.nuevoDuenio(new VODuenio(Integer.parseInt(cedula), nombre, apellido));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenciaException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("Digite enter para continuar");
			br.readLine();
		} catch (DuenioException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("Digite enter para continuar");
			br.readLine();
		}

	}

	private static boolean aceptarOpcion() throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String opcion = br.readLine().toUpperCase();
		while (!opcion.equals("S") && !opcion.equals("N")) {
			System.out.println("");
			System.out.print("Los datos son correctos? (S/N)");
			opcion = br.readLine().toUpperCase();
		}
		return opcion.equals("S");
	}

	private static void mostrarMenu() {
		System.out.println("");
		System.out.println(" 1. Alta de Dueño");
		System.out.println(" 2. Listar Dueǹos");
		System.out.println(" 3. Nueva Mascota");
		System.out.println(" 4. Listar Mascotas de un Dueño");
		System.out.println(" 5. Obtener Mascota");
		System.out.println(" 6. Contar Mascotas");
		System.out.println(" 7. Borrar Dueño");
		System.out.println("");
		System.out.println(" 8. Salir");
		System.out.println("Ingrese opción:");
	}
}
