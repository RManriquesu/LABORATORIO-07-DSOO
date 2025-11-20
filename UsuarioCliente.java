public class UsuarioCliente extends Usuario {
    private Cliente cliente;

    public UsuarioCliente(String nombreUsuario, String contraseña, Cliente cliente) {
        super(nombreUsuario, contraseña);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos de CLIENTE:");
        System.out.println("- Registrar citas.");
        System.out.println("- Consultar citas.");
        System.out.println("- Crear cuenta.");
    }
}
