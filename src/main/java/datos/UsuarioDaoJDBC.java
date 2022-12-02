package datos;

import domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoJDBC implements UsuarioDao{
    //Atributos
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT id_usuario, nombre, password FROM test.usuario";
    private static final String SQL_INSERT = "INSERT INTO test.usuario(nombre, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE test.usuario SET nombre = ?, password = ? = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM test.usuario WHERE id_usuario = ?";
    
    //Constructores
    public UsuarioDaoJDBC() {

    }

    public UsuarioDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    //Metodo regresar lista de usuarios
    public List<UsuarioDTO> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDTO usuario = null;
        List<UsuarioDTO> usuarios = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                                
                usuario = new UsuarioDTO();
                usuario.setIdUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setPassword(password);
                                
                usuarios.add(usuario);
            }
        } 
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
            
        }
        
        //Retornamos
        return usuarios;
        
    }
    
    //Metodo insertar
    public int insertar(UsuarioDTO usuario)throws SQLException{
        //Variables del metodo
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
                        
            System.out.println("ejecutando query:" + SQL_INSERT);
            registros = stmt.executeUpdate();
            System.out.println("Registros afectados:" + registros);
            
        } 
        finally{
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
            
        }
        return registros;
    }    
        //Metodo actualizar
    public int actualizar(UsuarioDTO usuario)throws SQLException{
        //Variables del metodo
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
                        
            registros = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + registros);
            
            
        } 
        finally{
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }
    
        //Metodo eliminar
    public int eliminar(UsuarioDTO usuario) throws SQLException{
        //Variables del metodo
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + registros);
                        
        } 
        finally{
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
            
        }
        return registros;
    }

       
}
