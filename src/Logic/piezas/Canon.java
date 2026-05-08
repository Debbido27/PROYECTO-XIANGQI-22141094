
package Logic.piezas;


public class Canon extends Pieza{
    
    public Canon(int fila, int columna, boolean isR){
        super(fila, columna, isR);
    }
    
    @Override
    public String getSimbolo(){
      return isR ? "炮" : "砲";

    }
    
    @Override
    public boolean [][] getMoveValido(Pieza[][] tablero){
        boolean [][] movimientos = new boolean [10][9];
        
        buscarMovimientos(tablero, movimientos, fila - 1, columna,  -1,  0, false);
        buscarMovimientos(tablero, movimientos, fila + 1, columna,   1,  0, false);
        buscarMovimientos(tablero, movimientos, fila,     columna - 1, 0, -1, false);
        buscarMovimientos(tablero, movimientos, fila,     columna + 1, 0,  1, false);

        return movimientos;
    }
    
}
