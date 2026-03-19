package observer;

import modelo.Prenda;

// Patrón Observer - Observador concreto para notificaciones por email
public class NotificadorEmail implements PrendaObserver {
    
    @Override
    public void actualizar(Prenda prenda, String evento) {
        if (evento.equals("ALQUILADA")) {
            enviarEmail("Confirmación de alquiler: " + prenda.getReferencia());
        } else if (evento.equals("DEVUELTA")) {
            enviarEmail("Devolución registrada: " + prenda.getReferencia());
        }
    }
    
    private void enviarEmail(String mensaje) {
        System.out.println("[EMAIL] Enviando: " + mensaje);
    }
}
