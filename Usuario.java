public class Usuario {

    protected String nombreUsuario;
    protected String contraseña;
    protected boolean estado;  // true = activo, false = inactivo

    public Usuario(String nombreUsuario, String contraseña) {
        if (Validaciones.esTextoVacio(nombreUsuario)) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }

        if (!Validaciones.validarConRegex(nombreUsuario, "^[a-zA-Z0-9._-]{4,20}$")) {
            throw new IllegalArgumentException("El nombre de usuario debe tener 4-20 caracteres y solo puede contener letras, números, '.', '_' o '-'.");
        }

        if (Validaciones.esTextoVacio(contraseña)) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

        if (!Validaciones.validarConRegex(contraseña, "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$")) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula y un número.");
        }

        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.estado = true; // por defecto activo
    }

    public boolean login(String usuarioIngresado, String contraseñaIngresada) {

        if (!estado) {
            System.out.println("Usuario inactivo.");
            return false;
        }

        // Validar que el usuario no esté vacío
        if (Validaciones.esTextoVacio(usuarioIngresado) || Validaciones.esTextoVacio(contraseñaIngresada)) {
            System.out.println("Usuario o contraseña vacíos.");
            return false;
        }

        if (this.nombreUsuario.equals(usuarioIngresado.trim()) &&
            this.contraseña.equals(contraseñaIngresada)) {

            System.out.println("Inicio de sesión correcto para: " + nombreUsuario);
            return true;
        }

        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public boolean isActivo() {
        return estado;
    }

    public void setActivo(boolean activo) {
        this.estado = activo;
    }

    public void setContraseña(String nuevaContraseña) {
        
        if (Validaciones.esTextoVacio(nuevaContraseña)) {
            throw new IllegalArgumentException("La nueva contraseña no puede estar vacía.");
        }

        if (!Validaciones.validarConRegex(nuevaContraseña, "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$")) {
            throw new IllegalArgumentException("La nueva contraseña no cumple los requisitos de seguridad.");
        }

        this.contraseña = nuevaContraseña;
    }
    
    public void mostrarDatos() {
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Estado: " + (estado ? "Activo" : "Inactivo"));
    }

    public void mostrarPermisos() {
        System.out.println("Permisos genéricos de usuario.");
    }
}