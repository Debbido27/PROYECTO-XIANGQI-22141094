package Logic.piezas;

public final class Caballo extends Pieza {
    private final TipoPieza tipo = TipoPieza.CABALLO;
    
    public Caballo(int fila, int columna, ColorPieza color) {
        super(fila, columna, color);
    }
    
    @Override
    public TipoPieza getTipo() {
        return tipo;
    }
    
    @Override
    public String getSimbolo() {
    return "CABALLO_" + color.getNombre();
    }
    
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        boolean[][] movimientos = new boolean[10][9];
        
        int[] dFila = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dCol = {-1, 1, -2, 2, -2, 2, -1, 1};
        int[] blkFila = {-1, -1, 0, 0, 0, 0, 1, 1};
        int[] blkCol = {0, 0, -1, 1, -1, 1, 0, 0};
        
        for (int i = 0; i < 8; i++) {
            int nf = fila + dFila[i];
            int nc = columna + dCol[i];
            int bf = fila + blkFila[i];
            int bc = columna + blkCol[i];
            
            if (enTablero(nf, nc) && tablero[bf][bc] == null 
                && puedeMoverA(nf, nc, tablero)) {
                movimientos[nf][nc] = true;
            }
        }
        return movimientos;
    }
}