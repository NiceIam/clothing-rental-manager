package modelo;

// Patrón Composite - Componente base
public abstract class Prenda {
    protected String referencia;
    protected String color;
    protected String marca;
    protected String talla;
    protected double valorAlquiler;
    protected boolean disponible;
    
    public Prenda(String referencia, String color, String marca, String talla, double valorAlquiler) {
        this.referencia = referencia;
        this.color = color;
        this.marca = marca;
        this.talla = talla;
        this.valorAlquiler = valorAlquiler;
        this.disponible = true;
    }
    
    public abstract String getTipo();
    public abstract String getDetalles();
    
    public String getReferencia() { return referencia; }
    public String getColor() { return color; }
    public String getMarca() { return marca; }
    public String getTalla() { return talla; }
    public double getValorAlquiler() { return valorAlquiler; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
