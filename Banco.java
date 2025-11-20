import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Banco {

    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Empleado> empleados = new ArrayList<>();
    public ArrayList<Cuenta> cuentas = new ArrayList<>();
    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Atencion> citas = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    // ====== AUXILIARES ======
    public int leerInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Número inválido. Intente nuevamente: ");
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    public float leerFloat() {
        while (!sc.hasNextFloat()) {
            sc.next();
            System.out.print("Monto inválido. Intente nuevamente: ");
        }
        float f = sc.nextFloat();
        sc.nextLine();
        return f;
    }

    // ====== USUARIOS ======
    public void agregarUsuario(Usuario u) { usuarios.add(u); }

    public Usuario buscarUsuario(String nombreUsuario) {
        for (Usuario u : usuarios) if (u.getNombreUsuario().equals(nombreUsuario)) return u;
        return null;
    }

    public Usuario login() {
        Usuario usuarioLogueado = null;
        while (usuarioLogueado == null) {
            System.out.println("--- LOGIN ---");
            System.out.print("Usuario: ");
            String nombre = sc.nextLine();
            System.out.print("Contraseña: ");
            String pass = sc.nextLine();

            Usuario u = buscarUsuario(nombre);
            if (u != null && u.login(nombre, pass)) {
                usuarioLogueado = u;
            } else {
                System.out.println("❌ Usuario o contraseña incorrectos. Intente nuevamente.\n");
            }
        }
        System.out.println("\n¡Bienvenido " + usuarioLogueado.getNombreUsuario() + "!");
        usuarioLogueado.mostrarPermisos();
        return usuarioLogueado;
    }

    // ====== CLIENTES ======
    public void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente c : clientes) {
            System.out.println(c.getIdCliente() + " - " + c.getNombre());
        }
    }

    public Cliente seleccionarCliente() {
        listarClientes();
        System.out.print("ID Cliente: ");
        String id = sc.nextLine();
        for (Cliente c : clientes) if (c.getIdCliente().equals(id)) return c;
        System.out.println("❌ No existe ese cliente.");
        return null;
    }

    // ====== EMPLEADOS ======
    public void listarEmpleados() {
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (Empleado e : empleados) {
            System.out.println(e.getIdEmpleado() + " - " + e.getNombre());
        }
    }

    public Empleado seleccionarEmpleado() {
        listarEmpleados();
        System.out.print("ID Empleado: ");
        String id = sc.nextLine();
        for (Empleado e : empleados) if (e.getIdEmpleado().equals(id)) return e;
        System.out.println("❌ No existe ese empleado.");
        return null;
    }

    // ====== CUENTAS ======
    public Cuenta crearCuenta(ArrayList<Cliente> titulares, float saldoInicial) {
        String numero = "C" + (cuentas.size() + 1);
        Cuenta c = new Cuenta(numero, "Ahorros", saldoInicial, titulares);
        cuentas.add(c);
        return c;
    }

    // ====== CITAS ======
    public void registrarCita(UsuarioCliente uc) {
        Cliente cliente = uc.getCliente();
        if (cliente == null) {
            System.out.println("❌ No se encontró el cliente asociado al usuario.");
            return;
        }

        System.out.print("Motivo de la cita: ");
        String motivo = sc.nextLine();

        Empleado empleado = seleccionarEmpleado();
        if (empleado == null) return;

        Atencion cita = new Atencion(cliente, motivo, empleado);
        citas.add(cita);
        System.out.println("✅ Cita registrada con ID: " + cita.getId());
    }

    public void consultarCitas(UsuarioCliente uc) {
        Cliente cliente = uc.getCliente();
        System.out.println("\n--- MIS CITAS ---");
        boolean encontrado = false;
        for (Atencion a : citas) {
            if (a.getCliente() == cliente) {
                System.out.println("ID: " + a.getId() +
                        " | Motivo: " + a.getMotivo() +
                        " | Empleado: " + a.getEmpleado().getNombre() +
                        " | Fecha: " + a.getFechaHora());
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No tienes citas registradas.");
    }

    public void crearCuentaCliente(UsuarioCliente uc) {
        Cliente cliente = uc.getCliente();
        if (cliente == null) return;

        System.out.print("Saldo inicial: ");
        float saldo = leerFloat();

        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(cliente);

        Cuenta cuenta = crearCuenta(titulares, saldo);
        cliente.agregarCuenta(cuenta);
        System.out.println("✅ Cuenta creada con número: " + cuenta.getNumero());
    }

    // ====== EMPLEADO ======
    public void gestionarCitas(UsuarioEmpleado ue) {
    System.out.println("\n--- GESTIONAR CITAS ---");

    if (citas.isEmpty()) {
        System.out.println("No hay citas registradas.");
        return;
    }

    for (Atencion a : citas) {
        System.out.println("ID: " + a.getId() +
                " | Cliente: " + a.getCliente().getNombre() +
                " | Motivo: " + a.getMotivo() +
                " | Empleado: " + a.getEmpleado().getNombre() +
                " | Fecha: " + a.getFechaHora() +
                " | Estado: " + (a.isAtendido() ? "Atendido" : "Pendiente"));
    }

    System.out.print("\nIngrese ID de la cita para marcar como atendida (0 para salir): ");
    String id = sc.nextLine();

    if (id.equals("0")) return;

    Atencion citaSeleccionada = null;
    for (Atencion a : citas) {
        if (a.getId().equals(id)) {
            citaSeleccionada = a;
            break;
        }
    }

    if (citaSeleccionada == null) {
        System.out.println("❌ Cita no encontrada.");
        return;
    }

    // Marcar como atendida
    citaSeleccionada.setAtendido(true);
    System.out.println("✅ Cita ID " + citaSeleccionada.getId() + " marcada como atendida.");
    }

    public void registrarAtencion(UsuarioEmpleado ue) {
        Cliente cliente = seleccionarCliente();
        if (cliente == null) return;

        Empleado empleado = ue.getEmpleado();
        if (empleado == null) empleado = seleccionarEmpleado();

        System.out.print("Motivo de atención: ");
        String motivo = sc.nextLine();

        Atencion atencion = new Atencion(cliente, motivo, empleado);
        citas.add(atencion);
        System.out.println("✅ Atención registrada con ID: " + atencion.getId());
    }

    public void historialCitas() {
        System.out.println("\n--- HISTORIAL DE CITAS ---");
        for (Atencion a : citas) {
            System.out.println("ID: " + a.getId() +
                    " | Cliente: " + a.getCliente().getNombre() +
                    " | Empleado: " + a.getEmpleado().getNombre() +
                    " | Motivo: " + a.getMotivo() +
                    " | Fecha: " + a.getFechaHora());
        }
    }

    // ====== ADMINISTRADOR ======
    public void crearUsuario() {
        System.out.println("\n--- CREAR USUARIO ---");
        System.out.print("Nombre de usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        System.out.println("Tipo de usuario (1=Cliente, 2=Empleado, 3=Admin): ");
        int tipo = leerInt();

        Usuario u = null;
        switch (tipo) {
            case 1 -> {
                Cliente c = seleccionarCliente();
                if (c == null) return;
                u = new UsuarioCliente(nombre, pass, c);
            }
            case 2 -> {
                Empleado e = seleccionarEmpleado();
                if (e == null) return;
                u = new UsuarioEmpleado(nombre, pass, e);
            }
            case 3 -> u = new UsuarioAdministrador(nombre, pass);
        }

        if (u != null) {
            agregarUsuario(u);
            System.out.println("✅ Usuario creado correctamente.");
        }
    }

    public void modificarUsuario() {
        System.out.println("\n--- MODIFICAR USUARIO ---");
        System.out.print("Nombre de usuario a modificar: ");
        String nombre = sc.nextLine();
        Usuario u = buscarUsuario(nombre);
        if (u == null) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }

        System.out.print("Nueva contraseña: ");
        String pass = sc.nextLine();
        u.setContraseña(pass);
        System.out.println("✅ Usuario modificado.");
    }

    public void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");
        System.out.print("Nombre de usuario a eliminar: ");
        String nombre = sc.nextLine();
        Usuario u = buscarUsuario(nombre);
        if (u == null) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }
        usuarios.remove(u);
        System.out.println("✅ Usuario eliminado.");
    }

    public void gestionarTodasCitas() {
        System.out.println("\n--- GESTIONAR TODAS LAS CITAS ---");
        for (Atencion a : citas) {
            System.out.println("ID: " + a.getId() +
                    " | Cliente: " + a.getCliente().getNombre() +
                    " | Empleado: " + a.getEmpleado().getNombre() +
                    " | Motivo: " + a.getMotivo() +
                    " | Fecha: " + a.getFechaHora());
        }
    }

    public void accesoCompleto() {
        System.out.println("\n--- ACCESO COMPLETO ---");
        listarClientes();
        listarEmpleados();
        for (Cliente c : clientes) {
            System.out.println("Cuentas de " + c.getNombre());
            for (Cuenta cu : c.getCuentas()) {
                System.out.println(" - " + cu.getNumero() + " Saldo: " + cu.getSaldo());
            }
        }
        gestionarTodasCitas();
    }

}