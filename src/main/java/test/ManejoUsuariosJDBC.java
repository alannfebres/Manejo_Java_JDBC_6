package test;

import datos.*;
import domain.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class ManejoUsuariosJDBC {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            
            UsuarioJDBC personaJdbc = new UsuarioJDBC(conexion);
            
            Usuario updUsuario = new Usuario();
            updUsuario.setIdUsuario(2);
            updUsuario.setNombre("Karla Ivonne");
            updUsuario.setPassword("zxcv4321");    
            personaJdbc.actualizar(updUsuario);
            
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre("Carlos");
            //nuevoUsuario.setPassword("aaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbcccccccccccc");
            nuevoUsuario.setPassword("asdf4321");
            personaJdbc.insertar(nuevoUsuario);
            
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
        
    }
}
