package command;

import negocio.NegocioAlquiler;
import modelo.ServicioAlquiler;
import java.time.LocalDate;
import java.util.List;

// Patrón Command - Comando concreto para registrar alquiler
public class RegistrarAlquilerCommand implements Command {
    private NegocioAlquiler negocio;
    private String idCliente;
    private String idEmpleado;
    private List<String> referenciasPrendas;
    private LocalDate fechaAlquiler;
    private int numeroServicio;
    
    public RegistrarAlquilerCommand(NegocioAlquiler negocio, String idCliente, 
                                   String idEmpleado, List<String> referenciasPrendas,
                                   LocalDate fechaAlquiler) {
        this.negocio = negocio;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.referenciasPrendas = referenciasPrendas;
        this.fechaAlquiler = fechaAlquiler;
    }
    
    @Override
    public void execute() throws Exception {
        numeroServicio = negocio.registrarServicioAlquiler(idCliente, idEmpleado, 
                                                           referenciasPrendas, fechaAlquiler);
    }
    
    @Override
    public void undo() {
        if (numeroServicio > 0) {
            negocio.cancelarServicioAlquiler(numeroServicio);
        }
    }
    
    @Override
    public String getDescripcion() {
        return "Registrar alquiler para cliente " + idCliente;
    }
    
    public int getNumeroServicio() {
        return numeroServicio;
    }
}
