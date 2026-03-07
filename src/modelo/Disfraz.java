package modelo;

public class Disfraz extends Prenda {
    private String nombre;
    
    public Disfraz(String referencia, String color, String marca, String talla,
                   double valorAlquiler, String nombre) {
        super(referencia, color, marca, talla, valorAlquiler);
        this.nombre = nombre;
    }
    
    @Override
    public String getTipo() { return "Disfraz"; }
    
    @Override
    public String getDetalles() {
        return String.format("Disfraz - Ref: %s, Nombre: %s, Talla: %s",
            referencia, nombre, talla);
    }
    
    public String getNombreDisfraz() { return nombre; }
}
