package strategy;

import modelo.ServicioAlquiler;

// Patrón Strategy - Estrategia concreta para clientes frecuentes
public class DescuentoClienteFrecuente implements EstrategiaDescuento {
    private static final double PORCENTAJE_DESCUENTO = 0.15; // 15%
    
    @Override
    public double calcularDescuento(ServicioAlquiler servicio) {
        double total = servicio.calcularTotal();
        return total * PORCENTAJE_DESCUENTO;
    }
    
    @Override
    public String getDescripcion() {
        return "Descuento Cliente Frecuente (15%)";
    }
}
