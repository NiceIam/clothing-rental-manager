package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioAlquiler {
    private int numero;
    private LocalDate fechaSolicitud;
    private LocalDate fechaAlquiler;
    private Empleado empleado;
    private Cliente cliente;
    private List<Prenda> prendasAlquiladas;
    
    public ServicioAlquiler(int numero, LocalDate fechaSolicitud, LocalDate fechaAlquiler,
                           Empleado empleado, Cliente cliente) {
        this.numero = numero;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAlquiler = fechaAlquiler;
        this.empleado = empleado;
        this.cliente = cliente;
        this.prendasAlquiladas = new ArrayList<>();
    }
    
    public void agregarPrenda(Prenda prenda) {
        prendasAlquiladas.add(prenda);
    }
    
    public int getNumero() { return numero; }
    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public LocalDate getFechaAlquiler() { return fechaAlquiler; }
    public Empleado getEmpleado() { return empleado; }
    public Cliente getCliente() { return cliente; }
    public List<Prenda> getPrendasAlquiladas() { return prendasAlquiladas; }
    
    public double calcularTotal() {
        return prendasAlquiladas.stream()
            .mapToDouble(Prenda::getValorAlquiler)
            .sum();
    }
}
