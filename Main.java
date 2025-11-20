import java.util.*;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // ===== CLIENTE DE EJEMPLO =====
        Cliente cli = new Cliente("70663191", "Ronald W. Manrique", "Av. Venezuela 212", "912345678", "brayan@gmail.com", "CLI01");
        banco.clientes.add(cli);
        Usuario uCli = new UsuarioCliente("cliente01", "1234", cli);
        banco.agregarUsuario(uCli);

        // ===== EMPLEADO DE EJEMPLO =====
        Empleado emp = new Empleado("12345678", "Omar G. Aliaga", "Av. Jesus 101", "945676321", "empleado@banco.com", "EMP01", "Cajero");
        banco.empleados.add(emp);
        Usuario uEmp = new UsuarioEmpleado("empleado01", "1234", emp);
        banco.agregarUsuario(uEmp);

        // ===== ADMIN DE EJEMPLO =====
        Usuario uAdm = new UsuarioAdministrador("admin01", "1234");
        banco.agregarUsuario(uAdm);

        // ===== LOGIN =====
        Usuario usuarioLogueado = banco.login();

        // ===== MENÚ SEGÚN USUARIO =====
        if(usuarioLogueado instanceof UsuarioCliente){
            UsuarioCliente uc = (UsuarioCliente) usuarioLogueado;
            int op;
            do {
                System.out.println("\n--- MENU CLIENTE ---");
                System.out.println("1. Registrar cita");
                System.out.println("2. Consultar citas");
                System.out.println("3. Crear cuenta");
                System.out.println("4. Salir");
                System.out.print("Opción: ");
                op = banco.leerInt();
                switch(op){
                    case 1 -> banco.registrarCita(uc);
                    case 2 -> banco.consultarCitas(uc);
                    case 3 -> banco.crearCuentaCliente(uc);
                    case 4 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } while(op!=4);
        } else if(usuarioLogueado instanceof UsuarioEmpleado){
            UsuarioEmpleado ue = (UsuarioEmpleado) usuarioLogueado;
            int op;
            do {
                System.out.println("\n--- MENU EMPLEADO ---");
                System.out.println("1. Gestionar citas de clientes");
                System.out.println("2. Registrar atenciones");
                System.out.println("3. Consultar historial de citas");
                System.out.println("4. Salir");
                System.out.print("Opción: ");
                op = banco.leerInt();
                switch(op){
                    case 1 -> banco.gestionarCitas(ue);
                    case 2 -> banco.registrarAtencion(ue);
                    case 3 -> banco.historialCitas();
                    case 4 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } while(op!=4);
        } else if(usuarioLogueado instanceof UsuarioAdministrador){
            int op;
            do {
                System.out.println("\n--- MENU ADMINISTRADOR ---");
                System.out.println("1. Crear usuario");
                System.out.println("2. Modificar usuario");
                System.out.println("3. Eliminar usuario");
                System.out.println("4. Gestionar todas las citas");
                System.out.println("5. Acceso completo al sistema");
                System.out.println("6. Salir");
                System.out.print("Opción: ");
                op = banco.leerInt();
                switch(op){
                    case 1 -> banco.crearUsuario();
                    case 2 -> banco.modificarUsuario();
                    case 3 -> banco.eliminarUsuario();
                    case 4 -> banco.gestionarTodasCitas();
                    case 5 -> banco.accesoCompleto();
                    case 6 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } while(op!=6);
        }
    }
}


