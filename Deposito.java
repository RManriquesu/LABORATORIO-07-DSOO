import java.time.LocalDateTime;

public class Deposito extends Transaccion {

    public Deposito(Cuenta cuenta, float monto, Cliente cliente, Empleado empleado) {
        super("D-" + System.currentTimeMillis(),
                LocalDateTime.now(),
                monto,
                true,
                empleado,
                cuenta,
                cliente);
    }

    @Override
    public void procesar() {
        cuenta.acreditar(monto, this);
        System.out.println("✅ Depósito exitoso.");
    }
}
