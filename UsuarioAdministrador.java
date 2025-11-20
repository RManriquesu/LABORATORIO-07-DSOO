public class UsuarioAdministrador extends Usuario {

    public UsuarioAdministrador(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña);
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos de ADMINISTRADOR:");
        System.out.println("- Crear usuarios.");
        System.out.println("- Modificar usuarios.");
        System.out.println("- Eliminar usuarios.");
        System.out.println("- Gestionar todas las citas del sistema.");
        System.out.println("- Acceso completo al sistema.");
    }
}
