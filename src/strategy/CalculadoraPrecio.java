package strategy;

import modelo.ServicioAlquiler;

// Patrón Strategy - Contexto que usa la estrategia
public class CalculadoraPrecio {
    private EstrategiaDescuento estrategia;
    
    public CalculadoraPrecio(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }
    
    public void setEstrategia(EstrategiaDescuento estrategia) {
        this.estrategia = estrategia;
    }
    
    public double calcularPrecioFinal(ServicioAlquiler servicio) {
        double total = servicio.calcularTotal();
        double descuento = estrategia.calcularDescuento(servicio);
        return total - descuento;
    }
    
    public double getDescuento(ServicioAlquiler servicio) {
        return estrategia.calcularDescuento(servicio);
    }
    
    public String getDescripcionEstrategia() {
        return estrategia.getDescripcion();
    }
}
