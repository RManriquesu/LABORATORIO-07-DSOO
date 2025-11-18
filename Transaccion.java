import java.time.LocalDateTime;

public class Transaccion {

    protected String id;
    protected LocalDateTime fechaHora;
    protected float monto;
    protected boolean atendidoPorEmpleado;
    protected Empleado empleado;
    protected Cuenta cuenta;
    protected Cliente cliente;
    protected AutoServicio canal;

    public Transaccion() {
        this.fechaHora = LocalDateTime.now();
    }

    public Transaccion(String id, LocalDateTime fechaHora, float monto,
                       boolean atendidoPorEmpleado, Empleado empleado,
                       Cuenta cuenta, Cliente cliente, AutoServicio canal) {

        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.atendidoPorEmpleado = atendidoPorEmpleado;
        this.empleado = empleado;
        this.cuenta = cuenta;
        this.cliente = cliente;
        this.canal = canal;
    }

    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public float getMonto() { return monto; }
    public boolean getAtendidoPorEmpleado() { return atendidoPorEmpleado; }
    public Empleado getEmpleado() { return empleado; }
    public Cuenta getCuenta() { return cuenta; }
    public Cliente getCliente() { return cliente; }
    public AutoServicio getCanal() { return canal; }

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
