package negocio;

import modelo.*;
import lavanderia.PrendaLavanderia;
import observer.PrendaSubject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// Patrón Singleton - Una única instancia del negocio
public class NegocioAlquiler {
    private static NegocioAlquiler instancia;
    
    private Map<String, Prenda> listaDePrendas;
    private Map<String, Cliente> listaDeClientes;
    private Map<String, Empleado> listaDeEmpleados;
    private Map<Integer, ServicioAlquiler> serviciosAlquiler;
    private PriorityQueue<PrendaLavanderia> listaLavanderia;
    private PrendaSubject prendaSubject; // Patrón Observer
    
    private int contadorServicios;
    private int contadorOrdenLavanderia;
    
    private NegocioAlquiler() {
        listaDePrendas = new HashMap<>();
        listaDeClientes = new HashMap<>();
        listaDeEmpleados = new HashMap<>();
        serviciosAlquiler = new HashMap<>();
        listaLavanderia = new PriorityQueue<>();
        prendaSubject = new PrendaSubject();
        contadorServicios = 1;
        contadorOrdenLavanderia = 1;
    }
    
    public static NegocioAlquiler getInstancia() {
        if (instancia == null) {
            instancia = new NegocioAlquiler();
        }
        return instancia;
    }
    
    // Registro de entidades
    public void registrarPrenda(Prenda prenda) throws Exception {
        if (listaDePrendas.containsKey(prenda.getReferencia())) {
            throw new Exception("Ya existe una prenda con la referencia: " + prenda.getReferencia());
        }
        listaDePrendas.put(prenda.getReferencia(), prenda);
    }
    
    public void registrarCliente(Cliente cliente) throws Exception {
        if (listaDeClientes.containsKey(cliente.getId())) {
            throw new Exception("Ya existe un cliente con el ID: " + cliente.getId());
        }
        listaDeClientes.put(cliente.getId(), cliente);
    }
    
    public void registrarEmpleado(Empleado empleado) throws Exception {
        if (listaDeEmpleados.containsKey(empleado.getId())) {
            throw new Exception("Ya existe un empleado con el ID: " + empleado.getId());
        }
        listaDeEmpleados.put(empleado.getId(), empleado);
    }
    
    public int registrarServicioAlquiler(String idCliente, String idEmpleado, 
                                        List<String> referenciasPrendas, LocalDate fechaAlquiler) 
                                        throws Exception {
        Cliente cliente = listaDeClientes.get(idCliente);
        if (cliente == null) {
            throw new Exception("Cliente no registrado");
        }
        
        Empleado empleado = listaDeEmpleados.get(idEmpleado);
        if (empleado == null) {
            throw new Exception("Empleado no registrado");
        }
        
        List<Prenda> prendas = new ArrayList<>();
        for (String ref : referenciasPrendas) {
            Prenda prenda = listaDePrendas.get(ref);
            if (prenda == null) {
                throw new Exception("Prenda no registrada: " + ref);
            }
            if (!prenda.isDisponible()) {
                throw new Exception("Prenda no disponible: " + ref);
            }
            prendas.add(prenda);
        }
        
        int numeroServicio = contadorServicios++;
        ServicioAlquiler servicio = new ServicioAlquiler(numeroServicio, LocalDate.now(),
                                                         fechaAlquiler, empleado, cliente);
        
        for (Prenda prenda : prendas) {
            servicio.agregarPrenda(prenda);
            prenda.setDisponible(false);
            prendaSubject.notificarObservadores(prenda, "ALQUILADA");
        }
        
        serviciosAlquiler.put(numeroServicio, servicio);
        return numeroServicio;
    }
    
    public void cancelarServicioAlquiler(int numeroServicio) {
        ServicioAlquiler servicio = serviciosAlquiler.get(numeroServicio);
        if (servicio != null) {
            for (Prenda prenda : servicio.getPrendasAlquiladas()) {
                prenda.setDisponible(true);
                prendaSubject.notificarObservadores(prenda, "DEVUELTA");
            }
            serviciosAlquiler.remove(numeroServicio);
        }
    }
    
    public ServicioAlquiler consultarServicio(int numero) {
        return serviciosAlquiler.get(numero);
    }
    
    public List<ServicioAlquiler> consultarServiciosPorCliente(String idCliente) {
        LocalDate hoy = LocalDate.now();
        return serviciosAlquiler.values().stream()
            .filter(s -> s.getCliente().getId().equals(idCliente))
            .filter(s -> !s.getFechaAlquiler().isBefore(hoy))
            .sorted(Comparator.comparing(ServicioAlquiler::getFechaAlquiler))
            .collect(Collectors.toList());
    }
    
    public List<ServicioAlquiler> consultarServiciosPorFecha(LocalDate fecha) {
        return serviciosAlquiler.values().stream()
            .filter(s -> s.getFechaAlquiler().equals(fecha))
            .collect(Collectors.toList());
    }
    
    public Map<String, List<Prenda>> consultarPrendasPorTalla(String talla) {
        Map<String, List<Prenda>> resultado = new HashMap<>();
        resultado.put("Vestidos Dama", new ArrayList<>());
        resultado.put("Trajes Caballero", new ArrayList<>());
        resultado.put("Disfraces", new ArrayList<>());
        
        for (Prenda prenda : listaDePrendas.values()) {
            if (prenda.getTalla().equalsIgnoreCase(talla)) {
                resultado.get(prenda.getTipo()).add(prenda);
            }
        }
        
        return resultado;
    }
    
    public void registrarPrendaParaLavanderia(String referencia, boolean prioridad) throws Exception {
        Prenda prenda = listaDePrendas.get(referencia);
        if (prenda == null) {
            throw new Exception("Prenda no encontrada");
        }
        
        PrendaLavanderia prendaLav = new PrendaLavanderia(prenda, prioridad, contadorOrdenLavanderia++);
        listaLavanderia.offer(prendaLav);
    }
    
    public List<PrendaLavanderia> visualizarListaLavanderia() {
        return new ArrayList<>(listaLavanderia);
    }
    
    public List<Prenda> enviarPrendasALavanderia(int cantidad) {
        List<Prenda> prendasEnviadas = new ArrayList<>();
        
        for (int i = 0; i < cantidad && !listaLavanderia.isEmpty(); i++) {
            PrendaLavanderia prendaLav = listaLavanderia.poll();
            prendasEnviadas.add(prendaLav.getPrenda());
            prendaLav.getPrenda().setDisponible(true);
            prendaSubject.notificarObservadores(prendaLav.getPrenda(), "EN_LAVANDERIA");
        }
        
        return prendasEnviadas;
    }
    
    public PrendaSubject getPrendaSubject() {
        return prendaSubject;
    }
    
    public Cliente getCliente(String id) { return listaDeClientes.get(id); }
    public Empleado getEmpleado(String id) { return listaDeEmpleados.get(id); }
    public Prenda getPrenda(String ref) { return listaDePrendas.get(ref); }
}
