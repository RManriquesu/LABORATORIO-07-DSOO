import java.util.ArrayList;

public class Cliente extends Persona {

    private String idCliente;
    private ArrayList<Cuenta> cuentas;

    public Cliente(String dni, String nombre, String direccion,
                   String telefono, String email, String idCliente) {

        super(dni, nombre, direccion, telefono, email);
        this.idCliente = idCliente;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getIdCliente() { return idCliente; }

    public float getSaldoTotal() {
        float total = 0;
        for (Cuenta c : cuentas) total += c.getSaldo();
        return Math.round(total * 100) / 100f;
    }
    public void mostrarResumenCuentas() {
        System.out.println("\n--- CUENTAS DEL CLIENTE " + nombre + " ---");
        for (Cuenta c : cuentas) {
            System.out.println(c.getNumero() + " - Saldo: S/ " + c.getSaldo());
        }
    }
}