package facade;

import modelo.*;
import negocio.NegocioAlquiler;
import factory.PrendaFactory;
import lavanderia.PrendaLavanderia;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// Patrón Facade - Simplifica las operaciones del negocio
public class FacadeNegocio {
    private NegocioAlquiler negocio;
    
    public FacadeNegocio() {
        this.negocio = NegocioAlquiler.getInstancia();
    }
    
    public void registrarVestidoDama(String referencia, String color, String marca, String talla,
                                    double valorAlquiler, boolean pedreria, String altura, 
                                    int cantPiezas) throws Exception {
        Prenda prenda = PrendaFactory.crearVestidoDama(referencia, color, marca, talla,
                                                       valorAlquiler, pedreria, altura, cantPiezas);
        negocio.registrarPrenda(prenda);
    }
    
    public void registrarTrajeCaballero(String referencia, String color, String marca, String talla,
                                       double valorAlquiler, String tipo, String aderezo) throws Exception {
        Prenda prenda = PrendaFactory.crearTrajeCaballero(referencia, color, marca, talla,
                                                          valorAlquiler, tipo, aderezo);
        negocio.registrarPrenda(prenda);
    }
    
    public void registrarDisfraz(String referencia, String color, String marca, String talla,
                                double valorAlquiler, String nombre) throws Exception {
        Prenda prenda = PrendaFactory.crearDisfraz(referencia, color, marca, talla,
                                                   valorAlquiler, nombre);
        negocio.registrarPrenda(prenda);
    }
    
    public void registrarCliente(String id, String nombre, String direccion, String telefono,
                                String email) throws Exception {
        Cliente cliente = new Cliente(id, nombre, direccion, telefono, email);
        negocio.registrarCliente(cliente);
    }
    
    public void registrarEmpleado(String id, String nombre, String direccion, String telefono,
                                 String cargo) throws Exception {
        Empleado empleado = new Empleado(id, nombre, direccion, telefono, cargo);
        negocio.registrarEmpleado(empleado);
    }
    
    public int registrarServicioAlquiler(String idCliente, String idEmpleado,
                                        List<String> referenciasPrendas, LocalDate fechaAlquiler) 
                                        throws Exception {
        return negocio.registrarServicioAlquiler(idCliente, idEmpleado, referenciasPrendas, fechaAlquiler);
    }
    
    public ServicioAlquiler consultarServicio(int numero) {
        return negocio.consultarServicio(numero);
    }
    
    public List<ServicioAlquiler> consultarServiciosPorCliente(String idCliente) {
        return negocio.consultarServiciosPorCliente(idCliente);
    }
    
    public List<ServicioAlquiler> consultarServiciosPorFecha(LocalDate fecha) {
        return negocio.consultarServiciosPorFecha(fecha);
    }
    
    public Map<String, List<Prenda>> consultarPrendasPorTalla(String talla) {
        return negocio.consultarPrendasPorTalla(talla);
    }
    
    public void registrarPrendaParaLavanderia(String referencia, boolean prioridad) throws Exception {
        negocio.registrarPrendaParaLavanderia(referencia, prioridad);
    }
    
    public List<PrendaLavanderia> visualizarListaLavanderia() {
        return negocio.visualizarListaLavanderia();
    }
    
    public List<Prenda> enviarPrendasALavanderia(int cantidad) {
        return negocio.enviarPrendasALavanderia(cantidad);
    }
    
    public Cliente getCliente(String id) { return negocio.getCliente(id); }
    public Empleado getEmpleado(String id) { return negocio.getEmpleado(id); }
    public Prenda getPrenda(String ref) { return negocio.getPrenda(ref); }
}
