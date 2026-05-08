
package Logic.piezas;


public class Soldado extends Pieza {
  
    public Soldado(int fila, int columna, boolean isR){
        super(fila, columna, isR);
    }
    
    @Override
    public String getSimbolo() {
        return isR ? "兵" : "卒";
    }
    
}
