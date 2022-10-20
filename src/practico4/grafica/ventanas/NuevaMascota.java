package practico4.grafica.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import practico4.grafica.controladores.ControladorNuevaMascota;
import practico4.grafica.ventanas.util.KeyAdapterNumberOnly;

import javax.swing.JTextField;
import javax.swing.JButton;

public class NuevaMascota extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 6187714745415249751L;
	private JTextField txtCedula;
	private JTextField txtApodo;
	private JTextField txtRaza;
	ControladorNuevaMascota controlador;

	/**
	 * Create the frame.
	 */
	public NuevaMascota() {
		setBounds(100, 100, 388, 185);
		this.setTitle("Nueva Mascota");
		
		JLabel lblCedula = new JLabel("Cedula:");
		
		JLabel lblApodo = new JLabel("Apodo:");
		
		JLabel lblRaza = new JLabel("Raza:");
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.addKeyListener(new KeyAdapterNumberOnly());
		
		txtApodo = new JTextField();
		txtApodo.setColumns(25);
		
		txtRaza = new JTextField();
		txtRaza.setColumns(25);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblApodo)
						.addComponent(lblRaza)
						.addComponent(lblCedula))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtApodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnRegistrar)
							.addComponent(txtRaza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCedula)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApodo)
						.addComponent(txtApodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRaza)
						.addComponent(txtRaza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRegistrar)
					.addContainerGap(129, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	public void setControlador(ControladorNuevaMascota controlador) {
		this.controlador = controlador;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
		controlador.nuevaMascota(Integer.parseInt(txtCedula.getText()), txtApodo.getText(), txtRaza.getText());
		txtCedula.setText("");
		txtApodo.setText("");
		txtRaza.setText("");
	}
	
	public void setMensaje(boolean hayError, String msg) {
		if (hayError)
			JOptionPane.showMessageDialog(null, msg, "Nueva Mascota", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, msg, "Nuev Mascota", JOptionPane.INFORMATION_MESSAGE);
	}
}
