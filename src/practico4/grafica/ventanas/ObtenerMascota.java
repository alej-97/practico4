package practico4.grafica.ventanas;

import javax.swing.JInternalFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import practico4.grafica.controladores.ControladorObtenerMascota;
import practico4.grafica.ventanas.util.KeyAdapterNumberOnly;
import practico4.logicaPersistencia.valueObjects.VOMascota;

import javax.swing.JButton;

public class ObtenerMascota extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 8608154425644080288L;
	private JTextField txtCedula;
	private JTextField txtNroInscripcion;
	private JTextField txtApodo;
	private JTextField txtRaza;
	private ControladorObtenerMascota controlador;

	public ObtenerMascota() {
		setTitle("Obtener Mascota");
		setBounds(100, 100, 464, 184);
		
		JLabel lblCedula = new JLabel("Cedula:");
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.addKeyListener(new KeyAdapterNumberOnly());
		
		JLabel lblNmeroDeInscripcin = new JLabel("Número de inscripción:");
		
		txtNroInscripcion = new JTextField();
		txtNroInscripcion.setColumns(10);
		txtNroInscripcion.addKeyListener(new KeyAdapterNumberOnly());
		
		JButton btnObtener = new JButton("Obtener");
		btnObtener.addActionListener(this);
		
		JLabel lblRaza = new JLabel("Raza:");
		
		JLabel lblApodo = new JLabel("Apodo:");
		
		txtApodo = new JTextField();
		txtApodo.setEditable(false);
		txtApodo.setColumns(20);
		
		txtRaza = new JTextField();
		txtRaza.setEditable(false);
		txtRaza.setColumns(20);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblApodo)
						.addComponent(lblRaza)
						.addComponent(lblNmeroDeInscripcin)
						.addComponent(lblCedula))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnObtener)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNroInscripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtApodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRaza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCedula))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmeroDeInscripcin)
						.addComponent(txtNroInscripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApodo)
						.addComponent(txtApodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRaza)
						.addComponent(txtRaza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnObtener)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public void setMensaje(boolean hayError, String msg) {
		if (hayError)
			JOptionPane.showMessageDialog(null, msg, "Obtener Mascota", JOptionPane.ERROR_MESSAGE);
	}

	public void setControlador(ControladorObtenerMascota controlador) {
		this.controlador = controlador;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		VOMascota mascota = controlador.obtenerMascota(Integer.parseInt(txtCedula.getText()), Integer.parseInt(txtNroInscripcion.getText()));
		txtApodo.setText(mascota.getApodo());
		txtRaza.setText(mascota.getRaza());
	}
}
