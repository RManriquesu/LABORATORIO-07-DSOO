public class Empleado extends Persona {

    private String idEmpleado;
    private String cargo;

    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String idEmpleado, String cargo) {

        super(dni, nombre, direccion, telefono, email);
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }

    public String getIdEmpleado() { return idEmpleado; }
    public String getCargo() { return cargo; }

    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        return new Deposito(cuenta, monto, cliente, this);
    }

    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        return new Retiro(cuenta, monto, cliente, this);
    }
}
