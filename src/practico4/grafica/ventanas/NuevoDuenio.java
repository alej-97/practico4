package practico4.grafica.ventanas;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import practico4.grafica.controladores.ControladorNuevoDuenio;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NuevoDuenio extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 7570799154863740413L;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private ControladorNuevoDuenio controlador;



	public NuevoDuenio() {
		setBounds(50, 50, 400, 170);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblApellido = new JLabel("Apellido:");
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(25);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(25);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(lblCedula)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(240, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(242))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRegistrar)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblApellido)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(240, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCedula)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRegistrar)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public void setControlador(ControladorNuevoDuenio controlador) {
		this.controlador = controlador;
	}
	
	public void setMensaje(boolean hayError, String msg) {
		if (hayError)
			JOptionPane.showMessageDialog(null, msg, "Nuevo Dueño", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, msg, "Nuevo Dueño", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controlador.nuevoDuenio(Integer.parseInt(txtCedula.getText()), txtNombre.getText(), txtApellido.getText()); 
	}
}
