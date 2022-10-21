package practico4.grafica.ventanas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import practico4.logicaPersistencia.valueObjects.VODuenio;

public class DuenioTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 372188907404017456L;
	private List<VODuenio> duenios;

	private String[] columnNames = new String[] { "Cedula", "Nombre", "Apellido" };
	private Class<?>[] columnClass = new Class<?>[] { Integer.class, String.class, String.class };

	public DuenioTableModel(List<VODuenio> duenios)
   {
       this.duenios = duenios;
   }

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
    
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return duenios.size();
	}

	@Override
	public Object getValueAt(int nroFila, int nroColumna) {
		VODuenio duenio = duenios.get(nroFila);
		Object objeto = null;
		switch(nroColumna) {
		case 0:
			objeto = duenio.getCedula();
			break;
		case 1: 
			objeto =  duenio.getNombre();
			break;
		case 2:
			objeto = duenio.getApellido();
			break;
		}
		return objeto;
	}
}
