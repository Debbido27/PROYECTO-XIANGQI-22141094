
package Logic.piezas;

/**
 *
 * @author Dell
 */
public class Caballo extends Pieza {
    
    public Caballo(int fila, int columna, boolean isR){
        super(fila, columan, isR);
    }
    
    
    @Override
    public String getSimbolo(){
        return isR ? "傌" : "馬";
    }
    
    
    
}
