package observer;

import modelo.Prenda;
import java.util.ArrayList;
import java.util.List;

// Patrón Observer - Sujeto observable
public class PrendaSubject {
    private List<PrendaObserver> observadores;
    
    public PrendaSubject() {
        this.observadores = new ArrayList<>();
    }
    
    public void agregarObservador(PrendaObserver observador) {
        observadores.add(observador);
    }
    
    public void removerObservador(PrendaObserver observador) {
        observadores.remove(observador);
    }
    
    public void notificarObservadores(Prenda prenda, String evento) {
        for (PrendaObserver observador : observadores) {
            observador.actualizar(prenda, evento);
        }
    }
}
