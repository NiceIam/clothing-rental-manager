package iterator;

import modelo.Prenda;
import java.util.ArrayList;
import java.util.List;

// Patrón Iterator - Colección concreta
public class PrendaCollection {
    private List<Prenda> prendas;
    
    public PrendaCollection() {
        this.prendas = new ArrayList<>();
    }
    
    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }
    
    public void removerPrenda(Prenda prenda) {
        prendas.remove(prenda);
    }
    
    public PrendaIterator crearIterador() {
        return new PrendaIteratorImpl(prendas);
    }
    
    public PrendaIterator crearIteradorPorTipo(String tipo) {
        return new PrendaIteratorPorTipo(prendas, tipo);
    }
    
    public int size() {
        return prendas.size();
    }
}
