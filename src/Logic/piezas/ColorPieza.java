package Logic.piezas;

public enum ColorPieza {
    ROJO, NEGRO;
    
    public ColorPieza oponente() {
        return this == ROJO ? NEGRO : ROJO;
    }
    
    public String getNombre() {
        return this == ROJO ? "ROJO" : "NEGRO";
    }
}