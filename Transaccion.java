import java.time.LocalDateTime;

public class Transaccion {

    protected String id;
    protected LocalDateTime fechaHora;
    protected float monto;
    protected boolean atendidoPorEmpleado;
    protected Empleado empleado;
    protected Cuenta cuenta;
    protected Cliente cliente;

    public Transaccion() {
        this.fechaHora = LocalDateTime.now();
    }

    public Transaccion(String id, float monto, Cuenta cuenta, Cliente cliente, Empleado empleado) {

        if (!Validaciones.validarConRegex(id, "^TRX-\\d{4}$")) {
        throw new IllegalArgumentException("ID de transacción inválido. Formato requerido: TRX-XXXX");
        }

        if (!Validaciones.validarMonto(monto)) {
            throw new IllegalArgumentException("Monto inválido. Debe ser mayor a 0.");
        }

        if (cuenta == null) {
            throw new IllegalArgumentException("La transacción debe estar asociada a una cuenta.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException("La transacción debe tener un cliente.");
        }

        this.id = id;
        this.monto = monto;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.empleado = empleado;
        this.atendidoPorEmpleado = (empleado != null);
        this.fechaHora = LocalDateTime.now();
    }

    //Getters

    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public float getMonto() { return monto; }
    public boolean getAtendidoPorEmpleado() { return atendidoPorEmpleado; }
    public Empleado getEmpleado() { return empleado; }
    public Cuenta getCuenta() { return cuenta; }
    public Cliente getCliente() { return cliente; }

    public String getResumen() {
        return "ID: " + id +
                "\nFecha: " + fechaHora +
                "\nMonto: S/ " + monto +
                "\nEmpleado: " + (empleado != null ? empleado.getNombre() : "No Aplica") +
                "\nCliente: " + cliente.getNombre() +
                "\nCuenta: " + cuenta.getNumero();
    }

    public void procesar() {
        System.out.println("Procesando transacción...");
    }
}

