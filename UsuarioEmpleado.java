public class UsuarioEmpleado extends Usuario {
    private Empleado empleado;  // vínculo al empleado real

    public UsuarioEmpleado(String nombreUsuario, String contraseña, Empleado empleado) {
        super(nombreUsuario, contraseña);
        this.empleado = empleado;  // se asigna correctamente
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos de EMPLEADO:");
        System.out.println("- Gestionar citas de clientes.");
        System.out.println("- Registrar atenciones.");
        System.out.println("- Consultar historial de citas.");
    }
}
