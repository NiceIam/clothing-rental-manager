package factory;

import modelo.*;

// Patrón Factory Method - Para crear diferentes tipos de prendas
public class PrendaFactory {
    
    public static Prenda crearVestidoDama(String referencia, String color, String marca, 
                                          String talla, double valorAlquiler, boolean pedreria,
                                          String altura, int cantPiezas) {
        return new VestidoDama(referencia, color, marca, talla, valorAlquiler, 
                              pedreria, altura, cantPiezas);
    }
    
    public static Prenda crearTrajeCaballero(String referencia, String color, String marca,
                                            String talla, double valorAlquiler, String tipo,
                                            String aderezo) {
        return new TrajeCaballero(referencia, color, marca, talla, valorAlquiler, tipo, aderezo);
    }
    
    public static Prenda crearDisfraz(String referencia, String color, String marca,
                                     String talla, double valorAlquiler, String nombre) {
        return new Disfraz(referencia, color, marca, talla, valorAlquiler, nombre);
    }
}
