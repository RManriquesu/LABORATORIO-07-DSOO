import java.util.ArrayList;

public class Cliente extends Persona {

    private String idCliente;
    private ArrayList<Cuenta> cuentas;

    public Cliente(String dni, String nombre, String direccion,
                   String telefono, String email, String idCliente) {

        super(dni, nombre, direccion, telefono, email);

        if (!Validaciones.validarCodigoCliente(idCliente)) {
            throw new IllegalArgumentException("ID de cliente inválido. Formato requerido: CL-XXXX");
        }

        this.idCliente = idCliente;
        this.cuentas = new ArrayList<>();
    }

    //Getters

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getIdCliente() { return idCliente; }

    //Setters

    public void setIdCliente(String idCliente) {
        if (!Validaciones.validarCodigoCliente(idCliente)) {
            throw new IllegalArgumentException("ID de cliente inválido. Formato CL-XXXX");
        }
        this.idCliente = idCliente;
    }
    
    public void agregarCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }
        cuentas.add(cuenta);
    }
    
    public float getSaldoTotal() {
        float total = 0;
        for (Cuenta c : cuentas) total += c.getSaldo();
        return Math.round(total * 100) / 100f;
    }

    public void mostrarResumenCuentas() {
        System.out.println("\n--- CUENTAS DEL CLIENTE " + nombre + " ---");
        if (cuentas.isEmpty()) {
            System.out.println("Este cliente no tiene cuentas registradas.");
            return;
        }
        for (Cuenta c : cuentas) {
            System.out.println(c.getNumero() + " - Saldo: S/ " + c.getSaldo());
        }
    }
}
