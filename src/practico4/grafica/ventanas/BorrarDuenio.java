package practico4.grafica.ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import practico4.grafica.controladores.ControladorBorrarDuenio;
import practico4.grafica.ventanas.util.KeyAdapterNumberOnly;

import javax.swing.JButton;

public class BorrarDuenio extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = -9054104286232308695L;
	private JTextField txtCedula;
	private ControladorBorrarDuenio controlador;

	public BorrarDuenio() {
		setTitle("Borrar Dueño");
		setBounds(100, 100, 218, 112);
		
		JLabel lblCedula = new JLabel("Cedula:");
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.addKeyListener(new KeyAdapterNumberOnly());
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCedula)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBorrar)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(248, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCedula)
						.addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBorrar)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int answer = JOptionPane.showConfirmDialog(this, 
				"Borrar", "Borrar Dueño", 
		        JOptionPane.YES_NO_OPTION);

		if (answer == JOptionPane.YES_NO_OPTION) {
			controlador.borrarDuenioMascota(Integer.parseInt(txtCedula.getText()));
		}
	}

	public void setControlador(ControladorBorrarDuenio controlador) {
		this.controlador = controlador;
	}
	
	public void setMensaje(boolean hayError, String msg) {
		if (hayError)
			JOptionPane.showMessageDialog(null, msg, "Borrar Dueño", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, msg, "Borrar Dueño", JOptionPane.INFORMATION_MESSAGE);
	}
}
