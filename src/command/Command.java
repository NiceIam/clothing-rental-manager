package command;

// Patrón Command - Interfaz del comando
public interface Command {
    void execute() throws Exception;
    void undo();
    String getDescripcion();
}
