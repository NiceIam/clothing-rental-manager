package strategy;

import modelo.ServicioAlquiler;

// Patrón Strategy - Interfaz de estrategia
public interface EstrategiaDescuento {
    double calcularDescuento(ServicioAlquiler servicio);
    String getDescripcion();
}
