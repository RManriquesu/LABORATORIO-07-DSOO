import java.util.Scanner;

public class Persona {
    private Scanner sc = new Scanner(System.in);

    protected String dni;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected String email;

    public Persona(String dni, String nombre, String direccion, String telefono, String email) {
        setDni(dni);
        setNombre(nombre);
        setDireccion(direccion);
        setTelefono(telefono);
        setEmail(email);
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    public void setNombre(String nombre) {
        while (true) {
            if (Validaciones.validarSoloLetras(nombre)) {
                this.nombre = nombre;
                return;
            }
            System.out.println("Nombre inválido. Solo letras.\nIntente nuevamente:");
            nombre = sc.nextLine();
        }
    }
    
    public void setDireccion(String direccion) {
        if (Validaciones.esTextoVacio(direccion)) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        while (true) {
            if (Validaciones.validarTelefono(telefono)) {
                this.telefono = telefono;
                return;
            }
            System.out.println("Teléfono inválido. Debe contener 9 dígitos.\nIntente nuevamente:");
            telefono = sc.nextLine();
        }
    }

    public void setEmail(String email) {
        while (true) {
            if (Validaciones.validarCorreo(email)) {
                this.email = email;
                return;
            }
            System.out.println("Correo inválido.\nIntente nuevamente:");
            email = sc.nextLine();
        }
    }

    public void setDni(String dni) {
        while (true) {
            if (Validaciones.validarDNI(dni)) {
                this.dni = dni;
                return;
            }
            System.out.println("DNI inválido. Debe contener 8 dígitos.\nIntente nuevamente:");
            dni = sc.nextLine();
        }
    }

}
