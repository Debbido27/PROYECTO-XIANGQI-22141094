package Logic;

public class Log {
    protected String ganador;
    protected String perdedor;
    protected String fecha;
    
    public Log(String ganador, String perdedor) {
        this.ganador = ganador;
        this.perdedor = perdedor;
        this.fecha = java.time.LocalDate.now().toString();
    }
    
    public String getTexto() {
        return ganador + " venció a " + perdedor;
    }
    
    public String getFecha() {
        return fecha;
    }
}