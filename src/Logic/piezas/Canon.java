
package Logic.piezas;


public class Canon extends Pieza{
    
    public Canon(int fila, int columna, boolean isR){
        super(fila, columna, isR);
    }
    
    @Override
    public String getSimbolo(){
      return isR ? "CANON_ROJO" : "CANON_NEGRO";

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
    
    
    
    
    private void buscarMovimientos(Pieza[][] tablero, boolean[][] movimientos,
                               int f, int c, int df, int dc, boolean pantalla) {
    if (!enTablero(f, c)) return;

    Pieza destino = tablero[f][c];

    if (!pantalla) {
        if (destino == null) {
            movimientos[f][c] = true;
            buscarMovimientos(tablero, movimientos, f + df, c + dc, df, dc, false);
        } else {
            buscarMovimientos(tablero, movimientos, f + df, c + dc, df, dc, true);
        }
    } else {
        if (destino == null) {
         
            buscarMovimientos(tablero, movimientos, f + df, c + dc, df, dc, true);
        } else if (destino.isR != this.isR) {
            movimientos[f][c] = true;
        }
    }
}
    
    
}
