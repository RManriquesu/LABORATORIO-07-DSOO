public class UsuarioCliente extends Usuario {

    public UsuarioCliente(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña);
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos de CLIENTE:");
        System.out.println("- Registrar citas.");
        System.out.println("- Consultar citas.");
    }
}
