import java.util.ArrayList;
import java.util.Scanner;

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
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = redondear(saldoInicial);
        this.titulares = (titulares != null) ? titulares : new ArrayList<>();
        this.movimientos = new ArrayList<>();
    }

    /**
     * Redondea un monto a 2 decimales.
     */
    private float redondear(float monto) {
        return Math.round(monto * 100) / 100f;
    }

    // ====================================
    // GETTERS
    // ====================================
    public String getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public float getSaldo() { return saldo; }
    public ArrayList<Cliente> getTitulares() { return titulares; }
    public ArrayList<Transaccion> getMovimientos() { return movimientos; }

    // ====================================
    // SETTERS
    // ====================================
    public void setNumero(String numero) { this.numero = numero; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setSaldo(float saldo) { this.saldo = redondear(saldo); }
    public void setTitulares(ArrayList<Cliente> titulares) { this.titulares = titulares; }

    // ====================================
    // MÉTODOS DE NEGOCIO
    // ====================================

    /**
     * Acredita un monto a la cuenta.
     */
    public void acreditar(float monto, Transaccion t) {
        saldo = redondear(saldo + monto);
        movimientos.add(t);
    }

    /**
     * Debita un monto si hay saldo suficiente.
     */
    public boolean debitar(float monto, Transaccion t) {
        if (saldo >= monto) {
            saldo = redondear(saldo - monto);
            movimientos.add(t);
            return true;
        }
        return false;
    }

    /**
     * Retorna la lista completa de movimientos.
     */
    public ArrayList<Transaccion> listarMovimientos() {
        return movimientos;
    }

    /**
     * Agrega un nuevo titular a la cuenta.
     */
    public void agregarTitular(Cliente cliente) {
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

    public void filtrarMovimientos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n1. Solo depósitos");
        System.out.println("2. Solo retiros");
        System.out.print("Opción: ");
        int op = sc.nextInt();

        for (Transaccion t : movimientos) {
            if (op == 1 && t instanceof Deposito)
                System.out.println(t.getResumen());
            if (op == 2 && t instanceof Retiro)
                System.out.println(t.getResumen());
        }
    }

    }
