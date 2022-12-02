package domain;

public class Usuario {
    //Atributos de nuestra clase
    private int idUsuario;
    private String nombre;
    private String password;
    
    //Constructores
    public Usuario() {
    }

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public Usuario(int idUsuario, String nombre, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.password = password;
    }
    
    //Get y Set
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //toString

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario= " + idUsuario + ", nombre= " + nombre + ", password= " + password + '}';
    }
          
}
