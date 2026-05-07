
package Logic;

/**
 *
 * @author Dell
 */
public abstract class Pieza {
    protected int fila;
    protected int columna;
    protected boolean isR;
    
    public Pieza(int fila, int columna, boolean isR){
        this.fila=fila;
        this.columna=columna;
        this.isR=isR;
    }
    
    
    //metodos abstractos
    public abstract boolean[][] getMoveValido(Pieza [][] tablero);
    
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

    public boolean isIsR() {
        return isR;
    }

    public void setIsR(boolean isR) {
        this.isR = isR;
    }
    
   
    
    
    
    
    
    
}
