package practico4.logicaPersistencia.accesoBD;

import java.sql.SQLException;

public interface IPoolConexiones {
	public IConexion obtenerConexion() throws SQLException, InterruptedException;
	public void liberarConexion(IConexion con, boolean ok) throws SQLException;
}
