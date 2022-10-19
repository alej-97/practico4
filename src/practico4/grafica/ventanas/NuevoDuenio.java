package practico4.grafica.ventanas;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NuevoDuenio extends JInternalFrame {

	private static final long serialVersionUID = 7570799154863740413L;
	
	private JPanel panelDatos;
	private JPanel panelBotones;
	private JButton btnCancelar;
	private JButton btnOk;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblCedula;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtApellido;
	
	public NuevoDuenio() {
		super("Nuevo Dueño");
		initialize();
	}

	private void initialize() {
		panelDatos = new JPanel();
		panelBotones = new JPanel();
		btnCancelar = new JButton("Cancelar");
		btnOk = new JButton("OK");
		lblNombre = new JLabel();
		lblApellido = new JLabel();
		lblCedula = new JLabel();
		txtCedula = new JTextField();
		txtNombre = new JTextField();
		txtApellido = new JTextField();
		
		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(btnCancelar);
		panelBotones.add(btnOk);
		
		panelDatos.add(lblCedula);
		panelDatos.add(txtCedula);
		panelDatos.add(lblNombre);
		panelDatos.add(txtNombre);
		panelDatos.add(lblApellido);
		panelDatos.add(txtApellido);
		panelDatos.add(new JLabel(""));
		panelDatos.add(panelBotones);
		
		this.add(panelDatos);
	}

}
