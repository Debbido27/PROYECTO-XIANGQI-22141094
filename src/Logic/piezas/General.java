
package Logic.piezas;


public final class General extends Pieza {
    
    public General (int fila, int columna, boolean isR){
        super(fila,columna, isR);
    }
    
    @Override
    public String getSimbolo(){
        return isR ? "将" : "帅";
    }
    
            @Override
        public void setFila(int fila) {
            if (enPalacio(fila, this.columna)) {
                this.fila = fila;
            }
        }

        @Override
        public void setColumna(int columna) {
            if (enPalacio(this.fila, columna)) {
                this.columna = columna;
            }
        }
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero){
        boolean [][] movimientos = new boolean[10][9];
        
        int[] dFila={-1,1,0,0};
        int[] dCol ={0,0,-1,1};
        
        for (int i = 0; i < 4; i++) {
            int nf = fila    + dFila[i];
            int nc = columna + dCol[i];

            if (enPalacio(nf, nc) && puedeMoverA(nf, nc, tablero)) {
                movimientos[nf][nc] = true;
            }
        }
        return movimientos;
            
            
        }
    
    public static boolean generalesMirando(Pieza[][] tablero, int fReyR, int cReyR, int fReyN, int cReyN) {
    if (cReyR != cReyN) return false;
    int minF = Math.min(fReyR, fReyN);
    int maxF = Math.max(fReyR, fReyN);
    for (int i = minF + 1; i < maxF; i++) {
        if (tablero[i][cReyR] != null) return false;
    }
    return true;
}
    
    public final boolean estaEnPalacio(){
        return enPalacio(fila,columna);
    }
    
    
    
     private boolean enPalacio(int f, int c) {
        if (c < 3 || c > 5) return false;
        if (isR) return f >= 7 && f <= 9;
        else        return f >= 0 && f <= 2;
    }
    
    
    }
    
    
    

