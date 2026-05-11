package Logic.piezas;

public class PiezaConcreta extends Pieza {
    
    protected TipoPieza tipo;
    
    public PiezaConcreta(int fila, int columna, boolean esRojo, TipoPieza tipo) {
        super(fila, columna, esRojo);
        this.tipo = tipo;
    }
    
    
    @Override
public String getSimbolo() {
    if (isR) {
        return tipo.name() + "_ROJO";
    } else {
        return tipo.name() + "_NEGRO";
    }
}

    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        return tipo.getMovimientos(this, tablero);
    }
    
    public boolean enPalacio(int f, int c) {
        return tipo.enPalacio(this, f, c);
    }
    
    public TipoPieza getTipo() {
        return tipo;
    }
    
    
}