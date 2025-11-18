public class AutoServicio {

    private String tipo;

    public AutoServicio(String tipo) {
        this.tipo = tipo;
    }

    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        Deposito d = new Deposito(cuenta, monto, cliente, null);
        d.procesar();
        return d;
    }

    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        Retiro r = new Retiro(cuenta, monto, cliente, null);
        r.procesar();
        return r;
    }

    @Override
    public String toString() {
        return tipo;
    }
}