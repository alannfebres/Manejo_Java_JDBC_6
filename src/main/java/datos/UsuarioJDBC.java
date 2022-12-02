package datos;

import static datos.Conexion.*; //Aca importamos get.Connection, pero reemplazamos por * import static datos.Conexion.getConnection
import domain.*;
import java.sql.*;
import java.util.*;

public class UsuarioJDBC {
    //Atributo Conexion
    private Connection conexionTransac;
    
    //Sentencias SQL
    private static final String SELECT = "SELECT id_usuario, nombre, password FROM test.usuario";
    private static final String INSERT = "INSERT INTO test.usuario(nombre, password) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE test.usuario SET nombre = ?, password = ? WHERE id_usuario = ?";
    private static final String DELETE = "DELETE FROM test.usuario WHERE id_usuario = ?";
    
    //Constructores Conexion
    public UsuarioJDBC() {

    }

    public UsuarioJDBC(Connection conexionTransac) {
        this.conexionTransac = conexionTransac;
    }
    
    //Metodo select personas
    public List<Usuario> seleccionar() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            conn = this.conexionTransac != null ? this.conexionTransac : Conexion.getConnection();
            stmt = conn.prepareStatement(SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idUsuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String password = rs.getString("password");
                                
                usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setPassword(password);
                
                usuarios.add(usuario);
            }
        } 
        finally{
                Conexion.close(rs);
                Conexion.close(stmt);
                if(this.conexionTransac == null){
                    Conexion.close(conn);
            }
            
        }
        
        //Retornamos
        System.out.println("ejecutando query:" + SELECT);
        return usuarios;
        
    }
    
    //Metodo insertar usuario
    public int insertar(Usuario persona) throws SQLException{
        //Variables del metodo
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransac != null ? this.conexionTransac : Conexion.getConnection();
            stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getPassword());
            
            System.out.println("ejecutando query:" + INSERT);
            registros = stmt.executeUpdate();
            System.out.println("Registros afectados:" + registros);
            
        } 
        finally{
                close(stmt);
                if(this.conexionTransac == null){
                    Conexion.close(conn);
            }
            
        }
        return registros;
    }    
        //Metodo actualizar usuario
    public int actualizar(Usuario usuario) throws SQLException{
        //Variables del metodo
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransac != null ? this.conexionTransac : Conexion.getConnection();
            System.out.println("ejecutando query: " + UPDATE);
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + registros);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
                close(stmt);
                if(this.conexionTransac == null){
                    Conexion.close(conn);
            }
            
        }
        return registros;
    }
    
        //Metodo eliminar Usuario
    public int eliminar(Usuario usuario) throws SQLException{
        //Variables del metodo
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransac != null ? this.conexionTransac : Conexion.getConnection();
            System.out.println("Ejecutando query:" + DELETE);
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
            
            registros = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + registros);
                        
        } 
        finally{
                close(stmt);
                if(this.conexionTransac == null){
                    Conexion.close(conn);
            }
            
        }
        return registros;
    }
    
}
