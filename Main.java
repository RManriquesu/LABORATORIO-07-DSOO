import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        // ====== CREACIÓN DE DATOS BASE ======
        Banco banco = new Banco();


        // Crear datos del empleado
        Empleado emp = new Empleado("12345678","Omar Andree Guevara Aliaga","Av. Jesus 101","945676321","empleado@banco.com","EMP01","Cajero");
        banco.empleados.add(emp);
        
        // Crear datos del cliente
        Cliente cliente = new Cliente("70663191","Ronald Wilmer Manrique Supanta","Av. Venenzuela 212","912345678","brayan@gmail.com","CLI01");
        banco.clientes.add(cliente);

        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(cliente);
        Cuenta cuenta = banco.crearCuenta(titulares, 1000);
        cliente.agregarCuenta(cuenta);

        int op;

        do {
            System.out.println("   ----| SISTEMA DEL BANCO |----   ");
            System.out.println("1. Listar clientes");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Listar empleados");
            System.out.println("4. Registrar empleado");
            System.out.println("5. Crear cuenta");
            System.out.println("6. Operaciones bancarias");
            System.out.println("7. Resumen de cuenta");
            System.out.println("8. Filtrar movimientos");
            System.out.println("9. Salir");
            System.out.print("Opción: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1 -> banco.listarClientes();
                case 2 -> banco.registrarClienteDesdeTeclado();
                case 3 -> banco.listarEmpleados();
                case 4 -> banco.registrarEmpleadoDesdeTeclado();
                case 5 -> banco.crearCuentaDesdeTeclado();
                case 6 -> banco.operaciones();
                case 7 -> banco.mostrarResumenCuenta();
                case 8 -> banco.filtrarMovimientos();

                case 9 -> System.out.println("Saliendo...");
                default -> System.out.println(" Opción inválida.");
            }

        } while (op != 9);
    }
}
