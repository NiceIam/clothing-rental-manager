package observer;

import modelo.Prenda;

// Patrón Observer - Observador concreto para inventario
public class NotificadorInventario implements PrendaObserver {
    private String nombre;
    
    public NotificadorInventario(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public void actualizar(Prenda prenda, String evento) {
        System.out.println("[" + nombre + "] Prenda " + prenda.getReferencia() + 
                         " - Evento: " + evento);
        
        if (evento.equals("ALQUILADA")) {
            System.out.println("  → Inventario actualizado: Prenda no disponible");
        } else if (evento.equals("DEVUELTA")) {
            System.out.println("  → Inventario actualizado: Prenda disponible");
        } else if (evento.equals("EN_LAVANDERIA")) {
            System.out.println("  → Prenda enviada a lavandería");
        }
    }
}
