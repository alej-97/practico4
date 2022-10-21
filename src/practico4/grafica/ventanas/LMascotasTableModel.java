package practico4.grafica.ventanas;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import practico4.logicaPersistencia.valueObjects.VOMascotaList;

public class LMascotasTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 5425339546682543836L;

	private List<VOMascotaList> mascotas;

	private String[] columnNames = new String[] { "Número de Inscripción", "Apodo", "Raza" };
	private Class<?>[] columnClass = new Class<?>[] { Integer.class, String.class, String.class };
	
	public LMascotasTableModel(List<VOMascotaList> mascotas) {
		super();
		this.mascotas = mascotas;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return mascotas.size();
	}
	
    @Override
    public String getColumnName(int column)
    {   
        return columnNames[column];
    }   

	@Override
	public Object getValueAt(int nroFila, int nroColumna) {
		VOMascotaList mascota = mascotas.get(nroFila);
		Object objeto = null;
		switch(nroColumna) {
		case 0:
			objeto = mascota.getNumInscripcion();
			break;
		case 1:
			objeto = mascota.getApodo();
			break;
		case 2:
			objeto = mascota.getRaza();
			break;
		}
		return objeto;
	}
}
