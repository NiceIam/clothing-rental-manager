package strategy;

import modelo.ServicioAlquiler;

// Patrón Strategy - Estrategia concreta por cantidad de prendas
public class DescuentoPorCantidad implements EstrategiaDescuento {
    
    @Override
    public double calcularDescuento(ServicioAlquiler servicio) {
        int cantidad = servicio.getPrendasAlquiladas().size();
        double total = servicio.calcularTotal();
        
        if (cantidad >= 5) {
            return total * 0.20; // 20% para 5 o más prendas
        } else if (cantidad >= 3) {
            return total * 0.10; // 10% para 3-4 prendas
        }
        
        return 0;
    }
    
    @Override
    public String getDescripcion() {
        return "Descuento por Cantidad (10% ≥3 prendas, 20% ≥5 prendas)";
    }
}
