import java.util.ArrayList;

/**
 * Clase Cuenta
 * Representa una cuenta bancaria con soporte para depósitos, retiros
 * y registro de movimientos. El saldo se maneja con redondeo a 2 decimales.
 */
public class Cuenta {

    private String numero;
    private String tipo;
    private float saldo;
    private ArrayList<Transaccion> movimientos;
    private ArrayList<Cliente> titulares;

    /**
     * Constructor de la cuenta
     */
    public Cuenta(String numero, String tipo, float saldoInicial, ArrayList<Cliente> titulares) {

        if (!Validaciones.validarNumeroCuenta(numero)) {
            throw new IllegalArgumentException("Número de cuenta inválido. Formato requerido: CU-XXXXXX");
        }

        if (Validaciones.esTextoVacio(tipo)) {
            throw new IllegalArgumentException("El tipo de cuenta no puede estar vacío.");
        }

        if (!tipo.equalsIgnoreCase("Ahorros") && !tipo.equalsIgnoreCase("Corriente")) {
            throw new IllegalArgumentException("Tipo de cuenta inválido. Use: Ahorros o Corriente.");
        }

        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }

        if (titulares == null || titulares.isEmpty()) {
            throw new IllegalArgumentException("La cuenta debe tener al menos un titular.");
        }

        this.numero = numero;
        this.tipo = tipo;
        this.saldo = redondear(saldoInicial);
        this.titulares = (titulares != null) ? titulares : new ArrayList<>();
        this.movimientos = new ArrayList<>();
    }

    /**
     * Metodo que redondea un monto a 2 decimales.
     */
    private float redondear(float monto) {
        return Math.round(monto * 100) / 100f;
    }

    
    // Getters
    
    public String getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public float getSaldo() { return saldo; }
    public ArrayList<Cliente> getTitulares() { return titulares; }
    public ArrayList<Transaccion> getMovimientos() { return movimientos; }

    
    // Setters
    
    public void setNumero(String numero) {
        if (!Validaciones.validarNumeroCuenta(numero)) {
            throw new IllegalArgumentException("Formato inválido para número de cuenta (CU-XXXXXX).");
        }
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        if (Validaciones.esTextoVacio(tipo)) {
            throw new IllegalArgumentException("El tipo no puede estar vacío.");
        }
        if (!tipo.equalsIgnoreCase("Ahorros") && !tipo.equalsIgnoreCase("Corriente")) {
            throw new IllegalArgumentException("Tipo inválido. Use Ahorros o Corriente.");
        }
        this.tipo = tipo;
    }

    public void setSaldo(float saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo.");
        }
        this.saldo = redondear(saldo);
    }

    public void setTitulares(ArrayList<Cliente> titulares) {
        if (titulares == null || titulares.isEmpty()) {
            throw new IllegalArgumentException("Debe tener al menos un titular.");
        }
        this.titulares = titulares;
    }

    // MÉTODOS DE NEGOCIO
    
    /**
     * Acredita un monto a la cuenta.
     */
    public void acreditar(float monto, Transaccion t) {
        if (!Validaciones.validarMonto(monto)) {
            throw new IllegalArgumentException("El monto a acreditar debe ser mayor a 0.");
        }
        if (t == null) {
            throw new IllegalArgumentException("La transacción no puede ser nula.");
        }
        saldo = redondear(saldo + monto);
        movimientos.add(t);
    }

    /**
     * Debita un monto si hay saldo suficiente.
     */
    public boolean debitar(float monto, Transaccion t) {
        if (!Validaciones.validarMonto(monto)) {
            throw new IllegalArgumentException("Monto a debitar inválido.");
        }
        if (t == null) {
            throw new IllegalArgumentException("La transacción no puede ser nula.");
        }
        if (saldo < monto) {
            return false;
        }

        saldo = redondear(saldo - monto);
        movimientos.add(t);
        return true;
    }


    /**
     * Agrega un nuevo titular a la cuenta.
     */
    public void agregarTitular(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El titular no puede ser nulo.");
        }
        titulares.add(cliente);
    }

    public void mostrarMovimientos() {
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos.");
            return;
        }

        for (Transaccion t : movimientos) {
            System.out.println(t.getResumen());
            System.out.println("----------------");
        }
    }

    public void mostrarResumen() {
        System.out.println("\n--- RESUMEN DE CUENTA ---");
        System.out.println("Número: " + numero);
        System.out.println("Tipo: " + tipo);
        System.out.println("Saldo: S/ " + saldo);

        System.out.println("Titulares:");
        for (Cliente c : titulares) {
            System.out.println(" - " + c.getNombre());
        }

        mostrarMovimientos();
    }

}
