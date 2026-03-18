package iterator;

import modelo.Prenda;
import java.util.List;
import java.util.stream.Collectors;

// Patrón Iterator - Iterador filtrado por tipo
public class PrendaIteratorPorTipo implements PrendaIterator {
    private List<Prenda> prendasFiltradas;
    private int posicion;
    
    public PrendaIteratorPorTipo(List<Prenda> prendas, String tipo) {
        this.prendasFiltradas = prendas.stream()
            .filter(p -> p.getTipo().equalsIgnoreCase(tipo))
            .collect(Collectors.toList());
        this.posicion = 0;
    }
    
    @Override
    public boolean hasNext() {
        return posicion < prendasFiltradas.size();
    }
    
    @Override
    public Prenda next() {
        if (!hasNext()) {
            return null;
        }
        return prendasFiltradas.get(posicion++);
    }
    
    @Override
    public void reset() {
        posicion = 0;
    }
}
