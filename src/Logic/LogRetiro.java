package Logic;

public class LogRetiro extends Log {
    
    public LogRetiro(String ganador, String perdedor) {
        super(ganador, perdedor);
    }
    
    @Override
    public String getTexto() {
        return perdedor + " SE HA RETIRADO FELICIDADES " + ganador + " HAS GANADO 3 PUNTOS";
    }
}