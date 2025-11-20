public class UsuarioCliente extends Usuario {
    private Cliente cliente;

    public UsuarioCliente(String nombreUsuario, String contraseña, Cliente cliente) {
        super(nombreUsuario, contraseña);

        if (cliente == null) {
            throw new IllegalArgumentException("El usuario cliente debe estar asociado a un cliente real.");
        }

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
