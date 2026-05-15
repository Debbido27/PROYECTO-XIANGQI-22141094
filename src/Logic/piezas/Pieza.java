
package Logic.piezas;

public abstract class Pieza {
    protected int fila;
    protected int columna;
    protected ColorPieza color;
    
    public Pieza(int fila, int columna, ColorPieza color){
        this.fila=fila;
        this.columna=columna;
        this.color=color;
    }
    
    protected boolean enPalacio(int f, int c) {
    if (c < 3 || c > 5) return false;
    if (esRojo()) return f >= 7 && f <= 9;
    else return f >= 0 && f <= 2;
}
    
     public boolean esRojo() { return color == ColorPieza.ROJO; }
     public boolean esNegro() { return color == ColorPieza.NEGRO; }
    
    
    //metodos abstractos
    public abstract boolean[][] getMoveValido(Pieza [][] tablero);
    public abstract TipoPieza getTipo();
    public abstract String getSimbolo();
    
    //Metodos concretos

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }


   
    protected final boolean enTablero(int f, int c){
       return f >= 0 && f < 10 && c >= 0 && c < 9;
    }
    
    protected final  boolean puedeMoverA(int f, int c, Pieza[][] tablero){
      if (!enTablero(f, c)) return false;
        Pieza destino = tablero[f][c];
        return destino == null || destino.esRojo() != this.esRojo();
    }
    
    
    public static final boolean generalesMirando(Pieza[][] tablero, int fReyR, int cReyR, int fReyN, int cReyN) {
    if (cReyR != cReyN) return false;
    int minF = Math.min(fReyR, fReyN);
    int maxF = Math.max(fReyR, fReyN);
    for (int i = minF + 1; i < maxF; i++) {
        if (tablero[i][cReyR] != null) return false;
    }
    return true;
}
    
    
}
