package modelo;

public class TrajeCaballero extends Prenda {
    private String tipo; // "convencional", "frac", "sacoleva", "otro"
    private String aderezo; // "corbata", "corbatín", "plastrón"
    
    public TrajeCaballero(String referencia, String color, String marca, String talla,
                          double valorAlquiler, String tipo, String aderezo) {
        super(referencia, color, marca, talla, valorAlquiler);
        this.tipo = tipo;
        this.aderezo = aderezo;
    }
    
    @Override
    public String getTipo() { return "Traje Caballero"; }
    
    @Override
    public String getDetalles() {
        return String.format("Traje Caballero - Ref: %s, Talla: %s, Tipo: %s, Aderezo: %s",
            referencia, talla, tipo, aderezo);
    }
    
    public String getTipoTraje() { return tipo; }
    public String getAderezo() { return aderezo; }
}
