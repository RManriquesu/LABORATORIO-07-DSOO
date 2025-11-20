import java.util.*;

public class Banco {

    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Empleado> empleados = new ArrayList<>();
    public ArrayList<Cuenta> cuentas = new ArrayList<>();
    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Atencion> citas = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    // ====== AUXILIARES ======
    public int leerInt() {
        while (true) {
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine();
            if (n >= 0) return n;
        } else {
            sc.next(); // descarta 
        }
        System.out.print("Número inválido. Intente nuevamente: ");
        }
    }

    public float leerFloat() {
        while (true) {
        if (sc.hasNextFloat()) {
            float f = sc.nextFloat();
            sc.nextLine();
            if (f >= 0) return f;
        } else {
            sc.next(); 
        }
        System.out.print("Monto inválido. Intente nuevamente: ");
        }
    }

    public String leerTextoNoVacio(String mensaje) {
    String texto;
    do {
        System.out.print(mensaje);
        texto = sc.nextLine().trim();
    } while (Validaciones.esTextoVacio(texto));
    return texto;
    }

    // ====== USUARIOS ======
    public void agregarUsuario(Usuario u) { usuarios.add(u); }

    public Usuario buscarUsuario(String nombreUsuario) {
        if (Validaciones.esTextoVacio(nombreUsuario)) return null;

        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario.trim()))
                return u;
        }
        return null;
    }

    public Usuario login() {
        Usuario usuarioLogueado = null;
        while (usuarioLogueado == null) {

            String nombre = leerTextoNoVacio("Usuario: ");
            String pass = leerTextoNoVacio("Contraseña: ");

            Usuario u = buscarUsuario(nombre);

            if (u != null && u.login(nombre, pass)) {
                usuarioLogueado = u;
            } else {
                System.out.println("Usuario o contraseña incorrectos.\n");
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
        String id = leerTextoNoVacio("ID Cliente: ");

        for (Cliente c : clientes) {
            if (c.getIdCliente().equalsIgnoreCase(id)) return c;
        }

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
        String id = leerTextoNoVacio("ID Empleado: ");

        for (Empleado e : empleados) {
            if (e.getIdEmpleado().equalsIgnoreCase(id)) return e;
        }

        System.out.println("❌ No existe ese empleado.");
        return null;
    }

    // ====== CUENTAS ======
    public Cuenta crearCuenta(ArrayList<Cliente> titulares, float saldoInicial) {
        if (titulares == null || titulares.isEmpty()) {
        throw new IllegalArgumentException("La cuenta debe tener al menos un titular.");
        }

        if (!Validaciones.validarMonto(saldoInicial)) {
            throw new IllegalArgumentException("Monto inicial inválido.");
        }

        String numero = "C-" + (cuentas.size() + 1);

        Cuenta cuenta = new Cuenta(numero, "Ahorros", saldoInicial, titulares);
        cuentas.add(cuenta);
        return cuenta;
    }

    // ====== CITAS ======
    public void registrarCita(UsuarioCliente uc) {
        Cliente cliente = uc.getCliente();
        if (cliente == null) {
            System.out.println("❌ Usuario sin cliente asociado.");
            return;
        }

        String motivo = leerTextoNoVacio("Motivo de la cita: ");

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

        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        for (Atencion a : citas) {
            System.out.println("ID: " + a.getId() + " | Cliente: " + a.getCliente().getNombre()
                    + " | Motivo: " + a.getMotivo()
                    + " | Empleado: " + a.getEmpleado().getNombre()
                    + " | Fecha: " + a.getFechaHora()
                    + " | Estado: " + (a.isAtendido() ? "Atendido" : "Pendiente"));
        }

        String id = leerTextoNoVacio("\nIngrese ID de la cita para marcar como atendida (0 para salir): ");

        if (id.equals("0")) return;

        Atencion cita = buscarCitaPorId(id);

        if (cita == null) {
            System.out.println("❌ Cita no encontrada.");
            return;
        }

        cita.setAtendido(true);
        System.out.println("✅ Cita ID " + cita.getId() + " marcada como atendida.");
    }

    private Atencion buscarCitaPorId(String id) {
        for (Atencion a : citas)
            if (a.getId().equals(id)) return a;
        return null;
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

        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        // Ordenar por fecha más reciente
        citas.sort((a, b) -> b.getFechaHora().compareTo(a.getFechaHora()));

        for (Atencion a : citas) {
            System.out.println(
                    "ID: " + a.getId() +
                    " | Cliente: " + a.getCliente().getNombre() +
                    " | Empleado: " + a.getEmpleado().getNombre() +
                    " | Motivo: " + a.getMotivo() +
                    " | Estado: " + (a.isAtendido() ? "Atendido" : "Pendiente") +
                    " | Fecha: " + a.getFechaHora()
            );
        }
    }


    // ====== ADMINISTRADOR ======
    public void crearUsuario() {
        System.out.println("\n--- CREAR USUARIO ---");

        String nombre = leerTextoNoVacio("Nombre de usuario: ");

        if (buscarUsuario(nombre) != null) {
            System.out.println("❌ El nombre de usuario ya está en uso.");
            return;
        }

        String pass = leerTextoNoVacio("Contraseña: ");

        System.out.println("Tipo de usuario (1=Cliente, 2=Empleado, 3=Admin): ");
        int tipo = leerInt();

        Usuario u = null;

        switch (tipo) {
            case 1 -> {
                Cliente c = seleccionarCliente();
                if (c != null) u = new UsuarioCliente(nombre, pass, c);
            }
            case 2 -> {
                Empleado e = seleccionarEmpleado();
                if (e != null) u = new UsuarioEmpleado(nombre, pass, e);
            }
            case 3 -> u = new UsuarioAdministrador(nombre, pass);
            default -> {
                System.out.println("❌ Tipo inválido.");
                return;
            }
        }

        if (u != null) {
            agregarUsuario(u);
            System.out.println("✅ Usuario creado correctamente.");
        }
    }

    public void modificarUsuario() {
        System.out.println("\n--- MODIFICAR USUARIO ---");

        String nombre = leerTextoNoVacio("Nombre de usuario a modificar: ");
        Usuario u = buscarUsuario(nombre);

        if (u == null) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }

        String pass = leerTextoNoVacio("Nueva contraseña: ");

        try {
            u.setContraseña(pass);
            System.out.println("✅ Usuario modificado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    public void eliminarUsuario() {
        System.out.println("\n--- ELIMINAR USUARIO ---");

        String nombre = leerTextoNoVacio("Nombre de usuario a eliminar: ");
        Usuario u = buscarUsuario(nombre);

        if (u == null) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }

        usuarios.remove(u);

        System.out.println("✅ Usuario eliminado correctamente.");
    }

    public void gestionarTodasCitas() {
        System.out.println("\n--- GESTIONAR TODAS LAS CITAS ---");

        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        for (Atencion a : citas) {
            System.out.println(
                    "ID: " + a.getId() +
                    " | Cliente: " + a.getCliente().getNombre() +
                    " | Empleado: " + a.getEmpleado().getNombre() +
                    " | Motivo: " + a.getMotivo() +
                    " | Fecha: " + a.getFechaHora()
            );
        }
    }

    public void accesoCompleto() {
        System.out.println("\n--- ACCESO COMPLETO ---");

        listarClientes();
        listarEmpleados();

        for (Cliente c : clientes) {
            System.out.println("\nCuentas de " + c.getNombre());
            for (Cuenta cu : c.getCuentas()) {
                System.out.println(" - " + cu.getNumero() + " Saldo: " + cu.getSaldo());
            }
        }

        historialCitas();
    }

}