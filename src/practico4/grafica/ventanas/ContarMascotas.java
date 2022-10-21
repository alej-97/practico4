package practico4.grafica.ventanas;

import javax.swing.JInternalFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import practico4.grafica.controladores.ControladorContarMascotas;
import practico4.grafica.ventanas.util.KeyAdapterNumberOnly;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ContarMascotas extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = -7881508023835624939L;
	private JTextField txtCedula;
	private JTextField txtRaza;
	private JTextField txtCantidad;
	private ControladorContarMascotas controlador;

	public ContarMascotas() {
		setTitle("Contar Mascotas");
		setBounds(100, 100, 247, 177);
		
		JLabel lblCedula = new JLabel("Cedula:");
		
		JLabel lblRaza = new JLabel("Raza:");
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.addKeyListener(new KeyAdapterNumberOnly());
		
		txtRaza = new JTextField();
		txtRaza.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		
		txtCantidad = new JTextField();
		txtCantidad.setEditable(false);
		txtCantidad.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblRaza)
								.addComponent(lblCedula))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtRaza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCantidad)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAceptar)
								.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(230, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCedula)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRaza)
						.addComponent(txtRaza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidad)
						.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAceptar)
					.addContainerGap(138, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	public void setMensaje(boolean hayError, String msg) {
		if (hayError)
			JOptionPane.showMessageDialog(null, msg, "Contar Mascota", JOptionPane.ERROR_MESSAGE);
	}

	public void setControlador(ControladorContarMascotas controlador) {
		this.controlador = controlador;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int cant = controlador.contarMascotas(Integer.parseInt(txtCedula.getText()), txtRaza.getText());
		txtCantidad.setText("" + cant);
	}
}
