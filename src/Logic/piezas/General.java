package Logic.piezas;

public class General extends Pieza {
    
    public General(int fila, int columna, ColorPieza color) {
        super(fila, columna, color);
    }
    
    @Override
    public String getSimbolo() {
        return color.getNombre() + "_GENERAL";
    }
    
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        boolean[][] movimientos = new boolean[10][9];
        
        int[] dFila = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        
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