
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
    
    private void buscarMovimientos(Pieza[][] tablero, boolean[][] movimientos, int f, int c, int df, int dc){
        if (!enTablero(f, c)) return;

        Pieza destino = tablero[f][c];

        if (destino == null) {
            movimientos[f][c] = true;
            buscarMovimientos(tablero, movimientos, f + df, c + dc, df, dc);
        } else if (destino.isR != this.isR) {
            movimientos[f][c] = true;
        }
        
    }
    
}
