public class Validaciones {

    
    // VALIDACIÓN DE TEXTO
    
    public static boolean esTextoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean validarSoloLetras(String texto) {
        return texto != null && texto.matches("^[a-zA-ZÁÉÍÓÚáéíóúÑñ .'-]{2,50}$");
    }

    public static boolean validarLongitud(String texto, int min, int max) {
        return texto != null && texto.length() >= min && texto.length() <= max;
    }

    
    // VALIDACIÓN DE CORREO
    
    public static boolean validarCorreo(String correo) {
        return correo != null &&
                correo.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    
    // VALIDACIÓN DE CONTRASEÑA
    // Fuerte: 8+ chars, 1 mayus, 1 minus, 1 número
    
    public static boolean validarContrasenaFuerte(String contrasena) {
        return contrasena != null &&
                contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
    }

    
    // VALIDACIÓN DE DNI (8 dígitos)
    
    public static boolean validarDNI(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }

    
    // VALIDACIÓN DE TELÉFONO (9 dígitos)
    
    public static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("\\d{9}");
    }

    
    // VALIDACIÓN DE NOMBRE DE USUARIO
    // Letras, números, puntos y guiones. 4 a 20 caracteres
    
    public static boolean validarNombreUsuario(String usuario) {
        return usuario != null &&
                usuario.matches("^[a-zA-Z0-9_.-]{4,20}$");
    }

    
    // VALIDACIÓN DE MONTOS
    // Mayor a 0
    
    public static boolean validarMonto(double monto) {
        return monto > 0;
    }

    
    // VALIDAR CÓDIGOS
    
    public static boolean validarCodigoCliente(String codigo) {
        return codigo != null && codigo.matches("^CL-\\d{4}$");
    }

    public static boolean validarCodigoEmpleado(String codigo) {
        return codigo != null && codigo.matches("^EM-\\d{4}$");
    }

    public static boolean validarNumeroCuenta(String numero) {
        return numero != null && numero.matches("^CU-\\d{6}$");
    }

    
    // VALIDACIÓN DE FECHAS
    
    public static boolean validarFechaNoNula(java.util.Date fecha) {
        return fecha != null;
    }

    public static boolean validarFechaNoFutura(java.util.Date fecha) {
        if (fecha == null) return false;
        java.util.Date hoy = new java.util.Date();
        return !fecha.after(hoy);
    }

    
    // VALIDACIÓN PERSONALIZADA CON EXPRESIÓN REGULAR
    
    public static boolean validarConRegex(String texto, String regex) {
        return texto != null && texto.matches(regex);
    }
}