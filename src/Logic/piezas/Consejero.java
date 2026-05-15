package Logic.piezas;

public final class Consejero extends Pieza {
    private final TipoPieza tipo = TipoPieza.CONSEJERO;
    public Consejero(int fila, int columna, ColorPieza color) {
        super(fila, columna, color);
    }
    
    @Override
    public TipoPieza getTipo() {
        return tipo;
    }
    
    @Override
    public String getSimbolo() {
     return "CONSEJERO_" + color.getNombre();
    }
    
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        boolean[][] movimientos = new boolean[10][9];
        
        int[] dFila = {-1, -1, 1, 1};
        int[] dCol = {-1, 1, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int nf = fila + dFila[i];
            int nc = columna + dCol[i];
            
            if (enPalacio(nf, nc) && puedeMoverA(nf, nc, tablero)) {
                movimientos[nf][nc] = true;
            }
        }
        return movimientos;
    }
    

}