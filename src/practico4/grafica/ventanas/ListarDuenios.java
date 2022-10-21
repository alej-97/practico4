package practico4.grafica.ventanas;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import practico4.grafica.controladores.ControladorListadoDuenios;
import practico4.logicaPersistencia.valueObjects.VODuenio;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListarDuenios extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = -7296504301896966105L;
	private JTable table;
	ControladorListadoDuenios controlador;

	public ListarDuenios() {
		setTitle("Listado de Dueños");
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
		);
		
		JButton btnGenerarListado = new JButton("Generar Listado");
		btnGenerarListado.addActionListener(this);
		panel.add(btnGenerarListado);
		
		table = new JTable(new DuenioTableModel(new ArrayList<VODuenio>()));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<VODuenio> duenios = null;
		
		duenios = controlador.listarDuenios();
		table.setModel(new DuenioTableModel(duenios));
		//table.repaint();
		//this.repaint();
	}

	public void setControlador(ControladorListadoDuenios controlador) {
		this.controlador = controlador;
	}
	
	public void setMensaje(boolean hayError, String msg) {
		if (hayError)
			JOptionPane.showMessageDialog(null, msg, "Nueva Mascota", JOptionPane.ERROR_MESSAGE);
	}
}
