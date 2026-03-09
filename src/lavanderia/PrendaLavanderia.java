package lavanderia;

import modelo.Prenda;

public class PrendaLavanderia implements Comparable<PrendaLavanderia> {
    private Prenda prenda;
    private boolean prioridad;
    private int orden;
    
    public PrendaLavanderia(Prenda prenda, boolean prioridad, int orden) {
        this.prenda = prenda;
        this.prioridad = prioridad;
        this.orden = orden;
    }
    
    @Override
    public int compareTo(PrendaLavanderia otra) {
        // Primero por prioridad (true antes que false)
        if (this.prioridad != otra.prioridad) {
            return this.prioridad ? -1 : 1;
        }
        // Luego por orden de llegada
        return Integer.compare(this.orden, otra.orden);
    }
    
    public Prenda getPrenda() { return prenda; }
    public boolean isPrioridad() { return prioridad; }
    public int getOrden() { return orden; }
}
