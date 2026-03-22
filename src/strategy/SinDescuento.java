package strategy;

import modelo.ServicioAlquiler;

// Patrón Strategy - Estrategia sin descuento
public class SinDescuento implements EstrategiaDescuento {
    
    @Override
    public double calcularDescuento(ServicioAlquiler servicio) {
        return 0;
    }
    
    @Override
    public String getDescripcion() {
        return "Sin Descuento";
    }
}
