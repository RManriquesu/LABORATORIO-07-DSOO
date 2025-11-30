import java.util.*;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // ===== CLIENTE DE EJEMPLO =====
        Cliente cli = new Cliente(
                "70663191",
                "Ronald W. Manrique",
                "Av. Venezuela 212",
                "912345678",
                "ronaldms376@gmail.com",
                "CL-0001"
        );
        banco.clientes.add(cli);
        Usuario uCli = new UsuarioCliente("cliente01", "Cliente01A", cli);
        banco.agregarUsuario(uCli);

        // ===== EMPLEADO DE EJEMPLO =====
        Empleado emp = new Empleado(
                "12345678",
                "Omar G. Aliaga",
                "Av. Jesus 101",
                "945676321",
                "empleado@banco.com",
                "EM-0001",
                "Cajero"
        );
        banco.empleados.add(emp);
        Usuario uEmp = new UsuarioEmpleado("empleado01", "Empleado01A", emp);
        banco.agregarUsuario(uEmp);

        // ===== ADMIN DE EJEMPLO =====
        Usuario uAdm = new UsuarioAdministrador("admin01", "Admin01A");
        banco.agregarUsuario(uAdm);

        // ===== LOGIN =====
        Usuario usuarioLogueado = banco.login();

        if (usuarioLogueado instanceof UsuarioCliente uc) {
            menuCliente(banco, uc);
        }
        else if (usuarioLogueado instanceof UsuarioEmpleado ue) {
            menuEmpleado(banco, ue);
        }
        else if (usuarioLogueado instanceof UsuarioAdministrador adm) {
            menuAdministrador(banco, adm);
        }
    }
    
    // ==========================================
    // ------------- MENÚ CLIENTE ---------------
    // ==========================================
    private static void menuCliente(Banco banco, UsuarioCliente uc) {

        int opcion;

        do {
            System.out.println("\n===== MENÚ CLIENTE =====");
            System.out.println("1. Registrar cita");
            System.out.println("2. Consultar citas");
            System.out.println("3. Crear cuenta");
            System.out.println("4. Salir");
            opcion = banco.leerInt();

            switch (opcion) {
                case 1 -> banco.registrarCita(uc);
                case 2 -> banco.consultarCitas(uc);
                case 3 -> banco.crearCuentaCliente(uc);
                case 4 -> System.out.println("Saliendo del menú Cliente...");
                default -> System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 4);
    }


    // ==========================================
    // ------------- MENÚ EMPLEADO --------------
    // ==========================================
    private static void menuEmpleado(Banco banco, UsuarioEmpleado ue) {

        int opcion;

        do {
            System.out.println("\n===== MENÚ EMPLEADO =====");
            System.out.println("1. Gestionar citas de clientes");
            System.out.println("2. Registrar atención");
            System.out.println("3. Consultar historial de citas");
            System.out.println("4. Salir");
            opcion = banco.leerInt();

            switch (opcion) {
                case 1 -> banco.gestionarCitas(ue);
                case 2 -> banco.registrarAtencion(ue);
                case 3 -> banco.historialCitas();
                case 4 -> System.out.println("Saliendo del menú Empleado...");
                default -> System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 4);
    }


    // ==========================================
    // ---------- MENÚ ADMINISTRADOR ------------
    // ==========================================
    private static void menuAdministrador(Banco banco, UsuarioAdministrador adm) {

        int opcion;

        do {
            System.out.println("\n===== MENÚ ADMINISTRADOR =====");
            System.out.println("1. Crear usuario");
            System.out.println("2. Modificar usuario");
            System.out.println("3. Eliminar usuario");
            System.out.println("4. Gestionar todas las citas");
            System.out.println("5. Acceso completo al sistema");
            System.out.println("6. Salir");
            opcion = banco.leerInt();

            switch (opcion) {
                case 1 -> banco.crearUsuario();
                case 2 -> banco.modificarUsuario();
                case 3 -> banco.eliminarUsuario();
                case 4 -> banco.gestionarTodasCitas();
                case 5 -> banco.accesoCompleto();
                case 6 -> System.out.println("Saliendo del menú Administrador...");
                default -> System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 6);
    }
}

