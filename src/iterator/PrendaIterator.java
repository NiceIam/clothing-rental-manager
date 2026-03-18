package iterator;

import modelo.Prenda;

// Patrón Iterator - Interfaz del iterador
public interface PrendaIterator {
    boolean hasNext();
    Prenda next();
    void reset();
}
