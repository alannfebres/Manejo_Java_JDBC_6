package datos;

import domain.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {
    //Definir Metodos
    public List<UsuarioDTO> seleccionar() throws SQLException;
    public int insertar(UsuarioDTO usuario) throws SQLException;
    public int actualizar(UsuarioDTO usuario) throws SQLException;
    public int eliminar(UsuarioDTO usuario) throws SQLException;
   
}
