package Logic.piezas;

public enum TipoPieza {
    GENERAL,
    CONSEJERO,
    ELEFANTE,
    CABALLO,
    CARRO,
    CANON,
    SOLDADO;
    
    public String getNombre() {
        return this.name();
    }
}