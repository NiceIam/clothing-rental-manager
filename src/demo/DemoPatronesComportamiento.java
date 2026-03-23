package demo;

import facade.FacadeNegocio;
import negocio.NegocioAlquiler;
import observer.*;
import command.*;
import iterator.*;
import strategy.*;
import modelo.*;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Clase de demostración de los patrones de comportamiento implementados
 */
public class DemoPatronesComportamiento {
    
    public static void main(String[] args) {
        try {
            System.out.println("=== DEMOSTRACIÓN DE PATRONES DE COMPORTAMIENTO ===\n");
            
            FacadeNegocio facade = new FacadeNegocio();
            NegocioAlquiler negocio = NegocioAlquiler.getInstancia();
            
            // Cargar datos de prueba
            cargarDatos(facade);
            
            // 1. Demostración del patrón OBSERVER
            demoObserver(negocio);
            
            // 2. Demostración del patrón COMMAND
            demoCommand(facade, negocio);
            
            // 3. Demostración del patrón ITERATOR
            demoIterator(negocio);
            
            // 4. Demostración del patrón STRATEGY
            demoStrategy(negocio);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void cargarDatos(FacadeNegocio facade) throws Exception {
        System.out.println("--- Cargando datos de prueba ---\n");
        
        // Empleados
        facade.registrarEmpleado("E001", "Juan Pérez", "Calle 1", "3001234567", "Vendedor");
        facade.registrarEmpleado("E002", "Ana López", "Calle 2", "3007654321", "Gerente");
        
        // Clientes
        facade.registrarCliente("C001", "María García", "Calle 3", "3009876543", "maria@email.com");
        facade.registrarCliente("C002", "Pedro Ruiz", "Calle 4", "3005551234", "pedro@email.com");
        
        // Prendas
        facade.registrarVestidoDama("VD001", "Rojo", "Elegance", "M", 150000, true, "largo", 2);
        facade.registrarVestidoDama("VD002", "Azul", "Glamour", "M", 120000, false, "corto", 1);
        facade.registrarVestidoDama("VD003", "Negro", "Elegance", "S", 180000, true, "largo", 3);
        
        facade.registrarTrajeCaballero("TC001", "Negro", "Formal", "L", 120000, "frac", "corbatín");
        facade.registrarTrajeCaballero("TC002", "Gris", "Executive", "M", 100000, "convencional", "corbata");
        
        facade.registrarDisfraz("DF001", "Multicolor", "Party", "M", 80000, "Superhéroe");
        facade.registrarDisfraz("DF002", "Verde", "Fantasy", "L", 90000, "Duende");
        
        System.out.println("Datos cargados exitosamente\n");
    }
    
    private static void demoObserver(NegocioAlquiler negocio) {
        System.out.println("\n========== PATRÓN OBSERVER ==========");
        System.out.println("Permite notificar automáticamente a múltiples observadores");
        System.out.println("cuando cambia el estado de una prenda.\n");
        
        // Configurar observadores
        PrendaSubject subject = negocio.getPrendaSubject();
        subject.agregarObservador(new NotificadorInventario("Sistema Inventario"));
        subject.agregarObservador(new NotificadorEmail());
        
        System.out.println("Observadores registrados: NotificadorInventario y NotificadorEmail\n");
        System.out.println("Las notificaciones se activarán automáticamente en las operaciones...\n");
    }
    
    private static void demoCommand(FacadeNegocio facade, NegocioAlquiler negocio) throws Exception {
        System.out.println("\n========== PATRÓN COMMAND ==========");
        System.out.println("Encapsula operaciones como objetos, permitiendo deshacer/rehacer.\n");
        
        CommandInvoker invoker = new CommandInvoker();
        
        // Crear y ejecutar comando
        System.out.println("1. Ejecutando comando: Registrar alquiler");
        RegistrarAlquilerCommand comando1 = new RegistrarAlquilerCommand(
            negocio, "C001", "E001",
            Arrays.asList("VD001", "TC001"),
            LocalDate.now().plusDays(3)
        );
        
        invoker.ejecutarComando(comando1);
        System.out.println("   ✓ Servicio registrado: #" + comando1.getNumeroServicio());
        
        // Otro comando
        System.out.println("\n2. Ejecutando comando: Otro alquiler");
        RegistrarAlquilerCommand comando2 = new RegistrarAlquilerCommand(
            negocio, "C002", "E002",
            Arrays.asList("VD002", "DF001"),
            LocalDate.now().plusDays(5)
        );
        
        invoker.ejecutarComando(comando2);
        System.out.println("   ✓ Servicio registrado: #" + comando2.getNumeroServicio());
        
        // Deshacer último comando
        System.out.println("\n3. Deshaciendo último comando...");
        invoker.deshacer();
        System.out.println("   ✓ Servicio #" + comando2.getNumeroServicio() + " cancelado");
        
        // Rehacer
        System.out.println("\n4. Rehaciendo comando...");
        invoker.rehacer();
        System.out.println("   ✓ Servicio #" + comando2.getNumeroServicio() + " restaurado");
    }
    
    private static void demoIterator(NegocioAlquiler negocio) {
        System.out.println("\n========== PATRÓN ITERATOR ==========");
        System.out.println("Permite recorrer colecciones sin exponer su estructura interna.\n");
        
        // Crear colección de prendas
        PrendaCollection collection = new PrendaCollection();
        
        // Agregar todas las prendas
        negocio.getPrenda("VD001");
        for (String ref : Arrays.asList("VD001", "VD002", "VD003", "TC001", "TC002", "DF001", "DF002")) {
            Prenda prenda = negocio.getPrenda(ref);
            if (prenda != null) {
                collection.agregarPrenda(prenda);
            }
        }
        
        // Iterar todas las prendas
        System.out.println("1. Iterando todas las prendas:");
        PrendaIterator iterator = collection.crearIterador();
        int count = 0;
        while (iterator.hasNext()) {
            Prenda prenda = iterator.next();
            count++;
            System.out.println("   " + count + ". " + prenda.getDetalles());
        }
        
        // Iterar solo vestidos de dama
        System.out.println("\n2. Iterando solo Vestidos de Dama:");
        PrendaIterator iteratorVestidos = collection.crearIteradorPorTipo("Vestido Dama");
        count = 0;
        while (iteratorVestidos.hasNext()) {
            Prenda prenda = iteratorVestidos.next();
            count++;
            System.out.println("   " + count + ". " + prenda.getDetalles());
        }
        
        // Iterar solo disfraces
        System.out.println("\n3. Iterando solo Disfraces:");
        PrendaIterator iteratorDisfraces = collection.crearIteradorPorTipo("Disfraz");
        count = 0;
        while (iteratorDisfraces.hasNext()) {
            Prenda prenda = iteratorDisfraces.next();
            count++;
            System.out.println("   " + count + ". " + prenda.getDetalles());
        }
    }
    
    private static void demoStrategy(NegocioAlquiler negocio) {
        System.out.println("\n========== PATRÓN STRATEGY ==========");
        System.out.println("Permite cambiar el algoritmo de cálculo de descuentos en tiempo de ejecución.\n");
        
        ServicioAlquiler servicio = negocio.consultarServicio(1);
        if (servicio == null) {
            System.out.println("No hay servicios para demostrar");
            return;
        }
        
        double total = servicio.calcularTotal();
        System.out.println("Servicio #" + servicio.getNumero());
        System.out.println("Cliente: " + servicio.getCliente().getNombre());
        System.out.println("Prendas: " + servicio.getPrendasAlquiladas().size());
        System.out.println("Total base: $" + String.format("%.2f", total));
        
        // Estrategia 1: Sin descuento
        System.out.println("\n1. Estrategia: Sin Descuento");
        CalculadoraPrecio calc = new CalculadoraPrecio(new SinDescuento());
        double precio1 = calc.calcularPrecioFinal(servicio);
        System.out.println("   Descuento: $0.00");
        System.out.println("   Total a pagar: $" + String.format("%.2f", precio1));
        
        // Estrategia 2: Descuento por cantidad
        System.out.println("\n2. Estrategia: Descuento por Cantidad");
        calc.setEstrategia(new DescuentoPorCantidad());
        double descuento2 = calc.getDescuento(servicio);
        double precio2 = calc.calcularPrecioFinal(servicio);
        System.out.println("   Descuento: $" + String.format("%.2f", descuento2));
        System.out.println("   Total a pagar: $" + String.format("%.2f", precio2));
        
        // Estrategia 3: Descuento cliente frecuente
        System.out.println("\n3. Estrategia: Descuento Cliente Frecuente (15%)");
        calc.setEstrategia(new DescuentoClienteFrecuente());
        double descuento3 = calc.getDescuento(servicio);
        double precio3 = calc.calcularPrecioFinal(servicio);
        System.out.println("   Descuento: $" + String.format("%.2f", descuento3));
        System.out.println("   Total a pagar: $" + String.format("%.2f", precio3));
        
        System.out.println("\n✓ La estrategia se puede cambiar dinámicamente según el tipo de cliente");
    }
}
