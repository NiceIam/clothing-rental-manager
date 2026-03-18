package iterator;

import modelo.Prenda;
import java.util.List;

// Patrón Iterator - Iterador concreto
public class PrendaIteratorImpl implements PrendaIterator {
    private List<Prenda> prendas;
    private int posicion;
    
    public PrendaIteratorImpl(List<Prenda> prendas) {
        this.prendas = prendas;
        this.posicion = 0;
    }
    
    @Override
    public boolean hasNext() {
        return posicion < prendas.size();
    }
    
    @Override
    public Prenda next() {
        if (!hasNext()) {
            return null;
        }
        return prendas.get(posicion++);
    }
    
    @Override
    public void reset() {
        posicion = 0;
    }
}
