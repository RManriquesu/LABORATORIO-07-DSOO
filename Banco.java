import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Empleado> empleados = new ArrayList<>();
    public ArrayList<Cuenta> cuentas = new ArrayList<>();

    private Scanner sc = new Scanner(System.in);

    //   ----| CLIENTES |----

    public void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente c : clientes) {
            System.out.println(c.getIdCliente() + " - " + c.getNombre());
        }
    }

    public void registrarClienteDesdeTeclado() {
        System.out.println("\n--- REGISTRAR CLIENTE ---");

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("ID Cliente: ");
        String idCliente = sc.nextLine();

        Cliente nuevo = new Cliente(dni, nombre, direccion, telefono, email, idCliente);
        clientes.add(nuevo);

        System.out.println("✅ Cliente registrado correctamente.");
    }

    public Cliente seleccionarCliente() {
        listarClientes();
        System.out.print("ID Cliente: ");
        String id = sc.nextLine();

        for (Cliente c : clientes)
            if (c.getIdCliente().equals(id))
                return c;

        System.out.println("❌ No existe ese cliente.");
        return null;
    }

    //   ----| EMPLEADOS |----

    public void listarEmpleados() {
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (Empleado e : empleados) {
            System.out.println(e.getIdEmpleado() + " - " + e.getNombre());
        }
    }

    public void registrarEmpleadoDesdeTeclado() {
        System.out.println("\n--- REGISTRAR EMPLEADO ---");

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("ID Empleado: ");
        String idEmpleado = sc.nextLine();

        System.out.print("Cargo: ");
        String cargo = sc.nextLine();

        Empleado nuevo = new Empleado(dni, nombre, direccion, telefono, email, idEmpleado, cargo);
        empleados.add(nuevo);

        System.out.println("✅ Empleado registrado correctamente.");
    }

    public Empleado seleccionarEmpleado() {
        listarEmpleados();
        System.out.print("ID Empleado: ");
        String id = sc.nextLine();

        for (Empleado e : empleados)
            if (e.getIdEmpleado().equals(id))
                return e;

        System.out.println("❌ No existe ese empleado.");
        return null;
    }

    //   ----| CUENTAS |----

    public void crearCuentaDesdeTeclado() {
        Cliente titular = seleccionarCliente();
        if (titular == null) return;

        System.out.print("Saldo inicial: ");
        float saldo = leerFloat();

        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(titular);

        Cuenta cuenta = crearCuenta(titulares, saldo);
        titular.agregarCuenta(cuenta);

        System.out.println("✅ Cuenta creada con número: " + cuenta.getNumero());
    }

    public Cuenta crearCuenta(ArrayList<Cliente> titulares, float saldoInicial) {
        String numero = "C" + (cuentas.size() + 1);
        Cuenta c = new Cuenta(numero, "Ahorros", saldoInicial, titulares);
        cuentas.add(c);
        return c;
    }

    public Cuenta seleccionarCuenta(Cliente cli) {
        System.out.println("\n--- CUENTAS DE " + cli.getNombre() + " ---");
        for (Cuenta c : cli.getCuentas()) {
            System.out.println(c.getNumero() + " - Saldo: S/ " + c.getSaldo());
        }

        System.out.print("Número de cuenta: ");
        String num = sc.nextLine();

        for (Cuenta c : cli.getCuentas())
            if (c.getNumero().equals(num))
                return c;

        System.out.println("❌ No existe esa cuenta.");
        return null;
    }

    //   ----| OPERACIONES |----

    public void operaciones() {

        Cliente cli = seleccionarCliente();
        if (cli == null) return;

        Cuenta cuenta = seleccionarCuenta(cli);
        if (cuenta == null) return;

        Empleado emp = seleccionarEmpleado();
        if (emp == null) return;

        int op;
        do {
            System.out.println("\n--- OPERACIONES ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depósito");
            System.out.println("3. Retiro");
            System.out.println("4. Ver movimientos");
            System.out.println("5. Volver");
            System.out.print("Opción: ");

            op = leerInt();

            switch (op) {

                case 1 -> System.out.println("Saldo actual: S/ " + cuenta.getSaldo());

                case 2 -> {
                    System.out.print("Monto depósito: ");
                    float m = leerFloat();
                    Deposito d = emp.registrarDeposito(cuenta, m, cli);
                    d.procesar();
                }

                case 3 -> {
                    System.out.print("Monto retiro: ");
                    float m = leerFloat();
                    Retiro r = emp.registrarRetiro(cuenta, m, cli);
                    r.procesar();
                }

                case 4 -> cuenta.mostrarMovimientos();
            }

        } while (op != 5);
    }

    public void mostrarResumenCuenta() {

        Cliente cli = seleccionarCliente();
        if (cli == null) return;

        Cuenta cuenta = seleccionarCuenta(cli);
        if (cuenta == null) return;

        cuenta.mostrarResumen();
    }

    //   ----| FILTRO DE MOVIMIENTOS |----

    public void filtrarMovimientos() {

        Cliente cli = seleccionarCliente();
        if (cli == null) return;

        Cuenta cuenta = seleccionarCuenta(cli);
        if (cuenta == null) return;

        cuenta.filtrarMovimientos();
    }


    //   ----| AUXILIARES |----

    private int leerInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Número inválido: ");
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    private float leerFloat() {
        while (!sc.hasNextFloat()) {
            sc.next();
            System.out.print("Monto inválido: ");
        }
        float f = sc.nextFloat();
        sc.nextLine();
        return f;
    }
}