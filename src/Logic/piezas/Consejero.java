
package Logic.piezas;

/**
 *
 * @author Dell
 */
public class Consejero extends Pieza {
    
    public Consejero(int fila, int columna, boolean isR){
        super(fila, columna, isR);
    }
    
    @Override
    public String getSimbolo(){
      return isR ? "仕" : "士";

    }
    
}
