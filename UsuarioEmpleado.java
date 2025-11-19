public class UsuarioEmpleado extends Usuario {

    public UsuarioEmpleado(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña);
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos de EMPLEADO:");
        System.out.println("- Gestionar citas de clientes.");
        System.out.println("- Registrar atenciones.");
        System.out.println("- Consultar historial de citas.");
    }
}
