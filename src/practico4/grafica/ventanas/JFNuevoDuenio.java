package practico4.grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import practico4.logicaPersistencia.ICertamenes;
import practico4.logicaPersistencia.excepciones.DuenioException;
import practico4.logicaPersistencia.excepciones.PersistenciaException;
import practico4.logicaPersistencia.valueObjects.VODuenio;

public class JFNuevoDuenio extends JFrame implements ActionListener {
	private static final long serialVersionUID = 4355946865962605799L;
	private JButton btnCancelar, btnAceptar;
	private JPanel panelBotones, panelLabels, panelDatos;
	private JLabel labelCedula, labelNombre, labelApellido;
	private JTextField txtCedula, txtNombre, txtApellido;
	private ICertamenes certamenes;
	
	public JFNuevoDuenio(ICertamenes certamenes) {
		this.certamenes = certamenes;
		inicializoComponentes();
	}
	
	private void inicializoComponentes() {
		
		//los botones de aceptar/cancelar
		btnCancelar = new JButton("Cancelar");
		btnAceptar = new JButton("Aceptar");
		btnCancelar.addActionListener(this);
		btnAceptar.addActionListener(this);
		panelBotones = new JPanel();
		panelBotones.add(btnCancelar);
		panelBotones.add(btnAceptar);
		panelBotones.setLayout(new GridLayout(1, 2, 5, 0));
		
		//los nombres de los datos a ingresar
		labelCedula = new JLabel("Cedula:     ");
		labelNombre = new JLabel("Apodo:     ");
		labelApellido = new JLabel("Raza:     ");
		panelLabels = new JPanel();
		panelLabels.add(labelCedula);
		panelLabels.add(labelNombre);
		panelLabels.add(labelApellido);
		panelLabels.setLayout(new GridLayout(3, 1, 0, 7));
		
		//los campos en donde se ingresan los datos
		txtCedula = new JTextField();
		txtNombre = new JTextField();
		txtApellido = new JTextField();
				
		panelDatos = new JPanel();
		panelDatos.add(txtCedula);
		panelDatos.add(txtNombre);
		panelDatos.add(txtApellido);
		panelDatos.setLayout(new GridLayout(1, 1, 0, 7));
		
		
		//se crea la ventana
		this.setTitle("Nueva Duenio");
		this.setResizable(false);
		this.setSize(300,200);
		this.setVisible(true);
		this.add(panelLabels, BorderLayout.WEST);
		this.add(panelDatos, BorderLayout.CENTER);
		this.add(panelBotones, BorderLayout.PAGE_END);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCancelar) {
			dispose();
		}
		if(e.getSource()==btnAceptar) {
			if(txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Los campons no pueden quedar vacios");				
			}
			else {
				VODuenio duenio = new VODuenio(Integer.parseInt(txtCedula.getText()), txtNombre.getText(), txtApellido.getText());
				try {
					certamenes.nuevoDuenio(duenio);
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (PersistenciaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (DuenioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		
		}
	}
}

