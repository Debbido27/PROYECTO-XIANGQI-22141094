
package Logic.piezas;

/**
 *
 * @author Dell
 */
public class Caballo extends Pieza {
    
    public Caballo(int fila, int columna, boolean isR){
        super(fila, columna, isR);
    }
    
    
    @Override
    public String getSimbolo(){
        return isR ? "傌" : "馬";
    }
    
    
    public boolean[][] getMoveValido(Pieza[][] tablero){
        boolean[][] movimientos = new boolean [10][9];
        
       int[] dFila  = {-2, -2, -1, -1,  1,  1,  2,  2};
        int[] dCol   = {-1,  1, -2,  2, -2,  2, -1,  1};
        int[] blkFila = {-1, -1,  0,  0,  0,  0,  1,  1};
        int[] blkCol  = { 0,  0, -1,  1, -1,  1,  0,  0};

        for (int i = 0; i < 8; i++) {
            int nf   = fila    + dFila[i];
            int nc   = columna + dCol[i];
            int bf   = fila    + blkFila[i];
            int bc   = columna + blkCol[i];

            if (enTablero(nf, nc) 
                && tablero[bf][bc] == null 
                && puedeMoverA(nf, nc, tablero)) {
                movimientos[nf][nc] = true;
            }
        }
        return movimientos;
    }
        
    }
   

