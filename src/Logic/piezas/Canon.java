package Logic.piezas;

public final class Canon extends Pieza {
    private final TipoPieza tipo = TipoPieza.CANON;
    
    public Canon(int fila, int columna, ColorPieza color) {
        super(fila, columna, color);
    }
    
    @Override
    public TipoPieza getTipo() {
        return tipo;
    }
    
    @Override
    public String getSimbolo() {
    return "CANON_" + color.getNombre();
    }
    
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        boolean[][] movimientos = new boolean[10][9];
        
        buscarMovimientos(tablero, movimientos, fila - 1, columna, -1, 0, false);
        buscarMovimientos(tablero, movimientos, fila + 1, columna, 1, 0, false);
        buscarMovimientos(tablero, movimientos, fila, columna - 1, 0, -1, false);
        buscarMovimientos(tablero, movimientos, fila, columna + 1, 0, 1, false);
        
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
            } else if (destino.color != this.color) {
                movimientos[f][c] = true;
            }
        }
    }
}