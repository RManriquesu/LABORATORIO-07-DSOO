public class UsuarioEmpleado extends Usuario {
    private Empleado empleado;  // vínculo al empleado real

    public UsuarioEmpleado(String nombreUsuario, String contraseña, Empleado empleado) {
        super(nombreUsuario, contraseña);

        if (empleado == null) {
            throw new IllegalArgumentException("El usuario empleado debe estar asociado a un empleado real.");
        }

        this.empleado = empleado;
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
