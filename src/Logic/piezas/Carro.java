
package Logic.piezas;

/**
 *
 * @author Dell
 */
public class Carro extends Pieza{
    
    public Carro(int fila, int columna, boolean isR){
        super(fila,columna,isR);
    }
    
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero){
        boolean[][] movimientos = new boolean[10][9];
        
        buscarMovimientos(tablero, movimientos, fila - 1, columna,  -1,  0);
        buscarMovimientos(tablero, movimientos, fila + 1, columna,   1,  0);
        buscarMovimientos(tablero, movimientos, fila,     columna - 1, 0, -1);
        buscarMovimientos(tablero, movimientos, fila,     columna + 1, 0,  1);

        return movimientos;
        
        
    }
    
}
