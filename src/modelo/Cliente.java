package modelo;

public class Cliente extends Persona {
    private String email;
    
    public Cliente(String id, String nombre, String direccion, String telefono, String email) {
        super(id, nombre, direccion, telefono);
        this.email = email;
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
