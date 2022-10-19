package practico4.grafica.ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 7988368683410415113L;
	private JDesktopPane desk;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal("Certamenes");
					//window.pack();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaPrincipal(String title) {
		super(title);
		intialize();
	}

	private void intialize() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		desk = new JDesktopPane();
		this.setContentPane(desk);
		this.setSize(500, 500);
		
		createMenuBar();
	}

	private void createMenuBar() {
		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Acciones");
		JMenuItem nuevoDuenio = new JMenuItem("Nuevo Dueño");
		JMenuItem nuevaMascota = new JMenuItem("Nueva Mascota");
		JMenuItem borrarDuenio = new JMenuItem("Borrar Dueño");
		JMenuItem listarDuenios = new JMenuItem("Listar Duenios");
		JMenuItem listarMascotasDuenio = new JMenuItem("Listar mascotas de un dueño");
		JMenuItem obtenerMascota = new JMenuItem("Obtener Mascota");
		JMenuItem contarMascotas = new JMenuItem("Contar Mascotas");
		this.setJMenuBar(mb);
		mb.add(menu);
		menu.add(nuevoDuenio);
		menu.add(nuevaMascota);
		menu.add(borrarDuenio);
		menu.add(listarDuenios);
		menu.add(listarMascotasDuenio);
		menu.add(contarMascotas);
		menu.add(obtenerMascota);
		
		nuevoDuenio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				NuevoDuenio nd = new NuevoDuenio();
				
				nd.setClosable(true);
				nd.pack();
				nd.setVisible(true);
				desk.add(nd);
			}
			
		});
	}
	
}
