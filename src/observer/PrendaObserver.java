package observer;

import modelo.Prenda;

// Patrón Observer - Interfaz del observador
public interface PrendaObserver {
    void actualizar(Prenda prenda, String evento);
}
