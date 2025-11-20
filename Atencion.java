import java.time.LocalDateTime;

public class Atencion {

    private String id;
    private LocalDateTime fechaHora;
    private Cliente cliente;
    private Empleado empleado;
    private String motivo;
    private String notas = "";
    private boolean atendido;

    public Atencion(Cliente cliente, String motivo, Empleado empleado) {

        if (cliente == null) {
            throw new IllegalArgumentException("La atención debe tener un cliente.");
        }

        if (empleado == null) {
            throw new IllegalArgumentException("La atención debe asignarse a un empleado.");
        }

        // Usando tu método correcto
        if (Validaciones.esTextoVacio(motivo) || motivo.length() < 3) {
            throw new IllegalArgumentException("El motivo debe contener al menos 3 caracteres.");
        }

        this.id = generarId();
        this.fechaHora = LocalDateTime.now();
        this.cliente = cliente;
        this.empleado = empleado;
        this.motivo = motivo.trim();
        this.atendido = false;
    }

    private String generarId() {
        int num = (int) (Math.random() * 9000 + 1000);
        return "AT-" + num;
    }

    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Cliente getCliente() { return cliente; }
    public Empleado getEmpleado() { return empleado; }
    public String getMotivo() { return motivo; }
    public String getNotas() { return notas; }

    public boolean isAtendido() {
    return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public void agregarNota(String nota) {
        if (Validaciones.esTextoVacio(nota)) {
            throw new IllegalArgumentException("La nota no puede estar vacía.");
        }
        if (nota.length() > 200) {
            throw new IllegalArgumentException("La nota no puede exceder los 200 caracteres.");
        }

    notas += nota.trim() + "\n";
    }
}

