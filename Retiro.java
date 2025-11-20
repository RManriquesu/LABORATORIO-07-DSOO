public class Retiro extends Transaccion {

    public Retiro(float monto, Cuenta cuenta, Cliente cliente, Empleado empleado) {
        super(
                generarId(),  
                monto,
                cuenta,
                cliente,
                empleado
        );

        if (!cuenta.debitar(monto, this)) {
        throw new IllegalArgumentException("Saldo insuficiente para retiro.");
        }
    }

    //Genera ID unico basico
    private static String generarId() {
        int num = (int) (Math.random() * 9000 + 1000); 
        return "TRX-" + num;
    }

    @Override
    public void procesar() {

        if (!Validaciones.validarMonto(monto)) {
            throw new IllegalArgumentException("El monto del retiro debe ser mayor a 0.");
        }

        if (cuenta.getSaldo() < monto) {
            System.out.println(" Saldo insuficiente para realizar el retiro.");
            return;
        }

        // Debitar desde la cuenta
        boolean ok = cuenta.debitar(monto, this);

        if (!ok) {
            System.out.println(" No se pudo realizar el retiro.");
            return;
        }

        System.out.println("Retiro procesado correctamente.");
        System.out.println("Cuenta: " + cuenta.getNumero());
        System.out.println("Monto: S/ " + monto);
        System.out.println("Nuevo saldo: S/ " + cuenta.getSaldo());
    }
}

