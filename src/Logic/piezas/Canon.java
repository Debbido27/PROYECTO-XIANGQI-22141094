
package Logic.piezas;


public class Canon extends Pieza{
    
    public Canon(int fila, int columna, boolean isR){
        super(fila, columna, isR);
    }
    
    @Override
    public String getSimbolo(){
      return isR ? "炮" : "砲";

    }
    
}
