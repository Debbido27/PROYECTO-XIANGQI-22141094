package Logic;

public class LogVictoria extends Log {
    
    public LogVictoria(String ganador, String perdedor) {
        super(ganador, perdedor);
    }
    
    @Override
    public String getTexto() {
        return ganador + " VENCIO  A JUGADOR " + perdedor+" FELICIDADES HAS GANADO 3 PUNTOS";
    }
}
