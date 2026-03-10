package src;

import facade.FacadeNegocio;
import modelo.*;
import lavanderia.PrendaLavanderia;
import java.time.LocalDate;
import java.util.*;

public class Main {
    private static FacadeNegocio facade = new FacadeNegocio();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        cargarDatosPrueba();
        
        while (true) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            
            try {
                switch (opcion) {
                    case 1 -> registrarPrenda();
                    case 2 -> registrarCliente();
                    case 3 -> registrarEmpleado();
                    case 4 -> registrarServicioAlquiler();
                    case 5 -> consultarServicio();
                    case 6 -> consultarServiciosPorCliente();
                    case 7 -> consultarServiciosPorFecha();
                    case 8 -> consultarPrendasPorTalla();
                    case 9 -> registrarPrendaParaLavanderia();
                    case 10 -> visualizarListaLavanderia();
                    case 11 -> enviarPrendasALavanderia();
                    case 0 -> {
                        System.out.println("Saliendo...");
                        return;
                    }
                    default -> System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE ALQUILER DE VESTUARIO ===");
        System.out.println("1. Registrar prenda");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Registrar empleado");
        System.out.println("4. Registrar servicio de alquiler");
        System.out.println("5. Consultar servicio");
        System.out.println("6. Consultar servicios por cliente");
        System.out.println("7. Consultar servicios por fecha");
        System.out.println("8. Consultar prendas por talla");
        System.out.println("9. Registrar prenda para lavandería");
        System.out.println("10. Visualizar lista de lavandería");
        System.out.println("11. Enviar prendas a lavandería");
        System.out.println("0. Salir");
    }

    private static void registrarPrenda() throws Exception {
        System.out.println("\n=== REGISTRAR PRENDA ===");
        System.out.println("1. Vestido Dama");
        System.out.println("2. Traje Caballero");
        System.out.println("3. Disfraz");
        int tipo = leerEntero("Tipo de prenda: ");
        
        String ref = leerTexto("Referencia: ");
        String color = leerTexto("Color: ");
        String marca = leerTexto("Marca: ");
        String talla = leerTexto("Talla: ");
        double valor = leerDouble("Valor alquiler: ");
        
        switch (tipo) {
            case 1 -> {
                boolean pedreria = leerBoolean("¿Tiene pedrería? (s/n): ");
                String altura = leerTexto("Altura (largo/corto): ");
                int piezas = leerEntero("Cantidad de piezas: ");
                facade.registrarVestidoDama(ref, color, marca, talla, valor, pedreria, altura, piezas);
            }
            case 2 -> {
                String tipoTraje = leerTexto("Tipo (convencional/frac/sacoleva/otro): ");
                String aderezo = leerTexto("Aderezo (corbata/corbatín/plastrón): ");
                facade.registrarTrajeCaballero(ref, color, marca, talla, valor, tipoTraje, aderezo);
            }
            case 3 -> {
                String nombre = leerTexto("Nombre del disfraz: ");
                facade.registrarDisfraz(ref, color, marca, talla, valor, nombre);
            }
            default -> throw new Exception("Tipo inválido");
        }
        System.out.println("Prenda registrada exitosamente");
    }
    
    private static void registrarCliente() throws Exception {
        System.out.println("\n=== REGISTRAR CLIENTE ===");
        String id = leerTexto("ID: ");
        String nombre = leerTexto("Nombre: ");
        String direccion = leerTexto("Dirección: ");
        String telefono = leerTexto("Teléfono: ");
        String email = leerTexto("Email: ");
        
        facade.registrarCliente(id, nombre, direccion, telefono, email);
        System.out.println("Cliente registrado exitosamente");
    }
    
    private static void registrarEmpleado() throws Exception {
        System.out.println("\n=== REGISTRAR EMPLEADO ===");
        String id = leerTexto("ID: ");
        String nombre = leerTexto("Nombre: ");
        String direccion = leerTexto("Dirección: ");
        String telefono = leerTexto("Teléfono: ");
        String cargo = leerTexto("Cargo: ");
        
        facade.registrarEmpleado(id, nombre, direccion, telefono, cargo);
        System.out.println("Empleado registrado exitosamente");
    }
    
    private static void registrarServicioAlquiler() throws Exception {
        System.out.println("\n=== REGISTRAR SERVICIO DE ALQUILER ===");
        String idCliente = leerTexto("ID Cliente: ");
        String idEmpleado = leerTexto("ID Empleado: ");
        
        List<String> referencias = new ArrayList<>();
        while (true) {
            String ref = leerTexto("Referencia prenda (vacío para terminar): ");
            if (ref.isEmpty()) break;
            referencias.add(ref);
        }
        
        LocalDate fechaAlquiler = leerFecha("Fecha alquiler (YYYY-MM-DD): ");
        
        int numero = facade.registrarServicioAlquiler(idCliente, idEmpleado, referencias, fechaAlquiler);
        System.out.println("Servicio registrado con número: " + numero);
    }
    
    private static void consultarServicio() {
        System.out.println("\n=== CONSULTAR SERVICIO ===");
        int numero = leerEntero("Número de servicio: ");
        
        ServicioAlquiler servicio = facade.consultarServicio(numero);
        if (servicio == null) {
            System.out.println("Servicio no encontrado");
            return;
        }
        
        mostrarServicio(servicio);
    }
    
    private static void consultarServiciosPorCliente() {
        System.out.println("\n=== CONSULTAR SERVICIOS POR CLIENTE ===");
        String idCliente = leerTexto("ID Cliente: ");
        
        Cliente cliente = facade.getCliente(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        
        System.out.println("\nDatos del cliente:");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Email: " + cliente.getEmail());
        
        List<ServicioAlquiler> servicios = facade.consultarServiciosPorCliente(idCliente);
        System.out.println("\nServicios vigentes: " + servicios.size());
        servicios.forEach(Main::mostrarServicio);
    }

    private static void consultarServiciosPorFecha() {
        System.out.println("\n=== CONSULTAR SERVICIOS POR FECHA ===");
        LocalDate fecha = leerFecha("Fecha (YYYY-MM-DD): ");
        
        List<ServicioAlquiler> servicios = facade.consultarServiciosPorFecha(fecha);
        System.out.println("\nServicios encontrados: " + servicios.size());
        servicios.forEach(Main::mostrarServicio);
    }
    
    private static void consultarPrendasPorTalla() {
        System.out.println("\n=== CONSULTAR PRENDAS POR TALLA ===");
        String talla = leerTexto("Talla: ");
        
        Map<String, List<Prenda>> prendas = facade.consultarPrendasPorTalla(talla);
        
        for (Map.Entry<String, List<Prenda>> entry : prendas.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            if (entry.getValue().isEmpty()) {
                System.out.println("  No hay prendas");
            } else {
                entry.getValue().forEach(p -> System.out.println("  - " + p.getDetalles()));
            }
        }
    }
    
    private static void registrarPrendaParaLavanderia() throws Exception {
        System.out.println("\n=== REGISTRAR PRENDA PARA LAVANDERÍA ===");
        String ref = leerTexto("Referencia: ");
        boolean prioridad = leerBoolean("¿Prioridad? (s/n): ");
        
        facade.registrarPrendaParaLavanderia(ref, prioridad);
        System.out.println("Prenda registrada para lavandería");
    }
    
    private static void visualizarListaLavanderia() {
        System.out.println("\n=== LISTA DE LAVANDERÍA ===");
        List<PrendaLavanderia> lista = facade.visualizarListaLavanderia();
        
        if (lista.isEmpty()) {
            System.out.println("No hay prendas en lista");
            return;
        }
        
        System.out.println("Total prendas: " + lista.size());
        for (PrendaLavanderia pl : lista) {
            System.out.printf("%s - %s - Prioridad: %s\n",
                pl.getPrenda().getReferencia(),
                pl.getPrenda().getDetalles(),
                pl.isPrioridad() ? "SÍ" : "NO");
        }
    }
    
    private static void enviarPrendasALavanderia() {
        System.out.println("\n=== ENVIAR PRENDAS A LAVANDERÍA ===");
        int cantidad = leerEntero("Cantidad a enviar: ");
        
        List<Prenda> enviadas = facade.enviarPrendasALavanderia(cantidad);
        
        System.out.println("\nPrendas enviadas: " + enviadas.size());
        enviadas.forEach(p -> System.out.println("  - " + p.getDetalles()));
    }
    
    private static void mostrarServicio(ServicioAlquiler servicio) {
        System.out.println("\n--- Servicio #" + servicio.getNumero() + " ---");
        System.out.println("Cliente: " + servicio.getCliente().getNombre());
        System.out.println("Empleado: " + servicio.getEmpleado().getNombre());
        System.out.println("Fecha solicitud: " + servicio.getFechaSolicitud());
        System.out.println("Fecha alquiler: " + servicio.getFechaAlquiler());
        System.out.println("Prendas:");
        servicio.getPrendasAlquiladas().forEach(p -> System.out.println("  - " + p.getDetalles()));
        System.out.println("Total: $" + servicio.calcularTotal());
    }
    
    private static void cargarDatosPrueba() {
        try {
            facade.registrarEmpleado("E001", "Juan Pérez", "Calle 1", "3001234567", "Vendedor");
            facade.registrarCliente("C001", "María García", "Calle 2", "3007654321", "maria@email.com");
            
            facade.registrarVestidoDama("VD001", "Rojo", "Elegance", "M", 150000, true, "largo", 2);
            facade.registrarTrajeCaballero("TC001", "Negro", "Formal", "L", 120000, "frac", "corbatín");
            facade.registrarDisfraz("DF001", "Multicolor", "Party", "S", 80000, "Superhéroe");
            
            System.out.println("Datos de prueba cargados");
        } catch (Exception e) {
            System.out.println("Error cargando datos: " + e.getMessage());
        }
    }
    
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
    
    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            }
        }
    }
    
    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            }
        }
    }
    
    private static boolean leerBoolean(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }
    
    private static LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Formato inválido. Use YYYY-MM-DD");
            }
        }
    }
}
