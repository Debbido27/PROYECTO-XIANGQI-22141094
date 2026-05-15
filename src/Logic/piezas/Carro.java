package Logic.piezas;

public final class Carro extends Pieza {
    private final TipoPieza tipo = TipoPieza.CARRO;
    
    public Carro(int fila, int columna, ColorPieza color) {
        super(fila, columna, color);
    }
    
    @Override
    public TipoPieza getTipo() {
        return tipo;
    }
    @Override
    public String getSimbolo() {
    return "CARRO_" + color.getNombre();
    }
    
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        boolean[][] movimientos = new boolean[10][9];
        
        buscarMovimientos(tablero, movimientos, fila - 1, columna, -1, 0);
        buscarMovimientos(tablero, movimientos, fila + 1, columna, 1, 0);
        buscarMovimientos(tablero, movimientos, fila, columna - 1, 0, -1);
        buscarMovimientos(tablero, movimientos, fila, columna + 1, 0, 1);
        
        return movimientos;
    }
    
    private void buscarMovimientos(Pieza[][] tablero, boolean[][] movimientos, int f, int c, int df, int dc) {
        if (!enTablero(f, c)) return;
        
        Pieza destino = tablero[f][c];
        
        if (destino == null) {
            movimientos[f][c] = true;
            buscarMovimientos(tablero, movimientos, f + df, c + dc, df, dc);
        } else if (destino.color != this.color) {
            movimientos[f][c] = true;
        }
    }
}