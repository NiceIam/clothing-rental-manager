package modelo;

public class VestidoDama extends Prenda {
    private boolean pedreria;
    private String altura; // "largo" o "corto"
    private int cantPiezas;
    
    public VestidoDama(String referencia, String color, String marca, String talla, 
                       double valorAlquiler, boolean pedreria, String altura, int cantPiezas) {
        super(referencia, color, marca, talla, valorAlquiler);
        this.pedreria = pedreria;
        this.altura = altura;
        this.cantPiezas = cantPiezas;
    }
    
    @Override
    public String getTipo() { return "Vestido Dama"; }
    
    @Override
    public String getDetalles() {
        return String.format("Vestido Dama - Ref: %s, Talla: %s, Pedrería: %s, %s, %d piezas",
            referencia, talla, pedreria ? "Sí" : "No", altura, cantPiezas);
    }
    
    public boolean isPedreria() { return pedreria; }
    public String getAltura() { return altura; }
    public int getCantPiezas() { return cantPiezas; }
}
