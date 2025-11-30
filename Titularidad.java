import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Titularidad {

    private Date fechaInicio;
    private String tipoTitular;   

    public Titularidad() {
        this.fechaInicio = new Date();
        this.tipoTitular = "";
    }

    // ======================================
    // MÉTODO PRINCIPAL
    // ======================================

    public void asignarTitular(Cliente cliente, Cuenta cuenta) {

        // ===== VALIDACIONES =====
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula.");
        }

        if (cuenta.getTitulares().contains(cliente)) {
            System.out.println("⚠ El cliente ya es titular de esta cuenta.");
            return;
        }

        // ===== ASIGNACIÓN DEL TIPO =====
        if (cuenta.getTitulares().isEmpty()) {
            tipoTitular = "Principal";
        } else {
            tipoTitular = "Secundario";
        }

        // ===== LÓGICA DE TITULARIDAD =====
        cuenta.getTitulares().add(cliente);
        cliente.agregarCuenta(cuenta);

        System.out.println("Titular asignado (" + tipoTitular + "): "
                + cliente.getNombre() + " a la Cuenta " + cuenta.getNumero());
    }

    public List<Cliente> obtenerTitulares(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta nula.");
        }
        return new ArrayList<>(cuenta.getTitulares());
    }

    public Date getFechaInicio() { return fechaInicio; }

    public String getTipoTitular() { return tipoTitular; }
}
