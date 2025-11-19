public class Usuario {

    protected String nombreUsuario;
    protected String contraseña;
    protected boolean estado;  

    public Usuario(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.estado = false; 
    }

    public boolean login(String usuarioIngresado, String contraseñaIngresada) {
        if (this.nombreUsuario.equals(usuarioIngresado) &&
            this.contraseña.equals(contraseñaIngresada)) {

            estado = true;
            System.out.println("Inicio de sesión correcto para: " + nombreUsuario);
            return true;
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
            return false;
        }
    }

    public void mostrarDatos() {
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Estado: " + (estado ? "Activo" : "Inactivo"));
    }

    public void mostrarPermisos() {
        System.out.println("Permisos genéricos de usuario.");
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public boolean isActivo() {
        return estado;
    }
}
