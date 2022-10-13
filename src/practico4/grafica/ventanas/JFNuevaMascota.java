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
import practico4.logicaPersistencia.valueObjects.VOMascota;



public class JFNuevaMascota extends JFrame implements ActionListener {
	private static final long serialVersionUID = -143566124503225147L;
	private JButton btnCancelar, btnAceptar;
	private JPanel panelBotones, panelLabels, panelDatos;
	private JLabel labelCedula, labelApodo, labelRaza;
	private JTextField txtCedula, txtApodo, txtRaza;
	private ICertamenes certamenes;
	
	public JFNuevaMascota(ICertamenes certamenes) {
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
		labelApodo = new JLabel("Apodo:     ");
		labelRaza = new JLabel("Raza:     ");
		panelLabels = new JPanel();
		panelLabels.add(labelCedula);
		panelLabels.add(labelApodo);
		panelLabels.add(labelRaza);
		panelLabels.setLayout(new GridLayout(3, 1, 0, 7));
		
		//los campos en donde se ingresan los datos
		txtCedula = new JTextField();
		txtApodo = new JTextField();
		txtRaza = new JTextField();
				
		panelDatos = new JPanel();
		panelDatos.add(txtCedula);
		panelDatos.add(txtApodo);
		panelDatos.add(txtRaza);
		panelDatos.setLayout(new GridLayout(1, 1, 0, 7));
		
		
		//se crea la ventana
		this.setTitle("Nueva Mascota");
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
			if(txtCedula.getText().isEmpty() || txtApodo.getText().isEmpty() || txtRaza.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Los campons no pueden quedar vacios");				
			}
			else {
				VOMascota mascota = new VOMascota(txtApodo.getText(), txtRaza.getText());
				try {
					certamenes.nuevaMascota(Integer.parseInt(txtCedula.getText()), mascota);
					JOptionPane.showMessageDialog(null, "Mascota agregada exitosamente");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (DuenioException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (PersistenciaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
			//JOptionPane.showMessageDialog(null, cliente.toString());
		}
		
	}
	
	
	
}
