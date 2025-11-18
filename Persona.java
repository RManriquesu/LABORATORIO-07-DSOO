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

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) {
        while (true) {
            if (telefono.matches("\\d{9}")) {
                this.telefono = telefono;
                return;
            }
            System.out.println("Teléfono inválido. Debe contener 9 dígitos.\nIntente nuevamente:");
            telefono = sc.nextLine();
        }
    }
    public void setEmail(String email) { this.email = email; }
    public void setDni(String dni) {
        while (true) {
            if (dni.matches("\\d{8}")) {
                this.dni = dni;
                return;
            }
            System.out.println("DNI inválido. Debe contener 8 dígitos.\nIntente nuevamente:");
            dni = sc.nextLine();
        }
    }

}
