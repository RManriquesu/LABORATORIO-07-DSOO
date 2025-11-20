
public class Deposito extends Transaccion {

    public Deposito(float monto, Cuenta cuenta, Cliente cliente, Empleado empleado) {
    super(
        generarId(),   
        monto,         
        cuenta,        
        cliente,       
        empleado       
    );

    // Procesar depósito
    cuenta.acreditar(monto, this);
}

    //Genera ID unico basico
    private static String generarId() {
        int num = (int) (Math.random() * 9000 + 1000);  // 1000–9999
        return "TRX-" + num;
    }

    public void procesar() {
        if (!Validaciones.validarMonto(monto)) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a 0.");
        }

        cuenta.acreditar(monto, this);

        System.out.println("Depósito procesado correctamente.");
        System.out.println("Cuenta: " + cuenta.getNumero());
        System.out.println("Monto: S/ " + monto);
        System.out.println("Nuevo saldo: S/ " + cuenta.getSaldo());
    }
}

