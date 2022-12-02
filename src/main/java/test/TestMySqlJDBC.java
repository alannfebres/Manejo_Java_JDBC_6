package test;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMySqlJDBC {
    public static void main(String[] args) {
        //cadena de conexion (buscar en internet la cadena de conexion - 
        //cada base de datos tiene su cadena de conexion para cada version de la BBDD
        var url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver"); //esta linea es necesaria para conexion de apps web
            Connection conexion = DriverManager.getConnection(url,"root", "admin");
            Statement instruccion = conexion.createStatement(); //Crear un objeto de tipo statement el cual es un tipo interface, 
            //pero conexion nos regresa tipo conexion a la BBDD que estemos utilizando, en este caso conector de Mysql
            var sql = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
            ResultSet resultado = instruccion.executeQuery(sql);
            while(resultado.next()){
                System.out.print("Id Persona: " + resultado.getInt("id_persona") );
                System.out.print(" Nombre: " + resultado.getString("nombre"));
                System.out.print(" Apellido: " + resultado.getString("apellido"));
                System.out.print(" Email: " + resultado.getString("email"));
                System.out.print(" Telefono: " + resultado.getString("telefono"));
                System.out.println(" ");
            }
            resultado.close();
            instruccion.close();
            conexion.close();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        }
        
    
}
