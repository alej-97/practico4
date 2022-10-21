package practico4.grafica.ventanas;

import javax.swing.JInternalFrame;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import practico4.grafica.controladores.ControladorListadoDeMascotas;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarMascotaDeUnDuenio extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = -7839360260926045316L;
	private JTextField txtCedula;
	private JTable table;
	private ControladorListadoDeMascotas controlador;

	public ListarMascotaDeUnDuenio() {
		setTitle("Listar mascotas de un dueño");
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
		);
		
		table = new JTable(new LMascotasTableModel(new ArrayList<VOMascotaList>()));
		scrollPane.setViewportView(table);
		
		JLabel lblCedula = new JLabel("Cedula:");
		panel.add(lblCedula);
		
		txtCedula = new JTextField();
		panel.add(txtCedula);
		txtCedula.setColumns(10);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(this);
		panel.add(btnGenerar);
		getContentPane().setLayout(groupLayout);

	}

	public void setControlador(ControladorListadoDeMascotas controlador) {
		this.controlador = controlador;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<VOMascotaList> mascotas = null;
	
		mascotas = controlador.listarMascotasDuenio(Integer.parseInt(txtCedula.getText()));
		table.setModel(new LMascotasTableModel(mascotas));
	}
	
    public void setMensaje(boolean hayError, String msg) {
        if (hayError)
            JOptionPane.showMessageDialog(null, msg, "Listado de Mascotas de un Dueño", JOptionPane.ERROR_MESSAGE);
    }   

}
