public class Empleado extends Persona {

    private String idEmpleado;
    private String cargo;

    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String idEmpleado, String cargo) {

        super(dni, nombre, direccion, telefono, email);

        if (!Validaciones.validarCodigoEmpleado(idEmpleado)) {
            throw new IllegalArgumentException("ID de empleado inválido. Formato requerido: EM-XXXX");
        }

        if (Validaciones.esTextoVacio(cargo)) {
            throw new IllegalArgumentException("El cargo no puede estar vacío.");
        }

        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }

    //Getters

    public String getIdEmpleado() { return idEmpleado; }
    public String getCargo() { return cargo; }

    //Setters 

    public void setIdEmpleado(String idEmpleado) {
        if (!Validaciones.validarCodigoEmpleado(idEmpleado)) {
            throw new IllegalArgumentException("Formato de ID inválido. Se requiere: EM-XXXX");
        }
        this.idEmpleado = idEmpleado;
    }

    public void setCargo(String cargo) {
        if (Validaciones.esTextoVacio(cargo)) {
            throw new IllegalArgumentException("El cargo no puede estar vacío.");
        }
        this.cargo = cargo;
    }

    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        return new Deposito(monto, cuenta, cliente, this);
    }

    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        return new Retiro(monto, cuenta, cliente, this);
    }
}
