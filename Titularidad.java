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

    public void asignarTitular(Cliente cliente, Cuenta cuenta) {

        if (cuenta.getTitulares().isEmpty()) {
            tipoTitular = "Principal";
        } else {
            tipoTitular = "Secundario";
        }

        cuenta.getTitulares().add(cliente);
        cliente.agregarCuenta(cuenta);

        System.out.println("Titular asignado (" + tipoTitular + "): "
                + cliente.getNombre() + " Cuenta " + cuenta.getNumero());
    }

    public List<Cliente> obtenerTitulares(Cuenta cuenta) {
        return new ArrayList<>(cuenta.getTitulares());
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public String getTipoTitular() {
        return tipoTitular;
    }
}
