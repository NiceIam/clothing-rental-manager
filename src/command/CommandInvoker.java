package command;

import java.util.Stack;

// Patrón Command - Invocador que mantiene historial
public class CommandInvoker {
    private Stack<Command> historial;
    private Stack<Command> deshecho;
    
    public CommandInvoker() {
        this.historial = new Stack<>();
        this.deshecho = new Stack<>();
    }
    
    public void ejecutarComando(Command comando) throws Exception {
        comando.execute();
        historial.push(comando);
        deshecho.clear(); // Limpiar el historial de deshacer
    }
    
    public void deshacer() {
        if (!historial.isEmpty()) {
            Command comando = historial.pop();
            comando.undo();
            deshecho.push(comando);
            System.out.println("Deshecho: " + comando.getDescripcion());
        } else {
            System.out.println("No hay comandos para deshacer");
        }
    }
    
    public void rehacer() throws Exception {
        if (!deshecho.isEmpty()) {
            Command comando = deshecho.pop();
            comando.execute();
            historial.push(comando);
            System.out.println("Rehecho: " + comando.getDescripcion());
        } else {
            System.out.println("No hay comandos para rehacer");
        }
    }
    
    public boolean puedeDeshacer() {
        return !historial.isEmpty();
    }
    
    public boolean puedeRehacer() {
        return !deshecho.isEmpty();
    }
}
