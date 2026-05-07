
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
    
    
    
    
    
    
    
    
}
