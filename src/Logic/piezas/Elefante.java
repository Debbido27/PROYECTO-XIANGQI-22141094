
package Logic.piezas;

/**
 *
 * @author Dell
 */
public class Elefante extends Pieza{
    
    public Elefante (int fila, int columna, boolean isR){
        super(fila,columna,isR);
    }
    
    
    @Override
    public String getSimbolo(){
       return isR ? "相" : "象";

    }
   
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero){
        boolean[][] movimientos = new boolean[10][9];
        
        int[] dFila = {-2, -2,  2,  2};
        int[] dCol  = {-2,  2, -2,  2};
        int[] ojof  = {-1, -1,  1,  1};
        int[] ojoc  = {-1,  1, -1,  1};

        for (int i = 0; i < 4; i++) {
            int nf   = fila    + dFila[i];
            int nc   = columna + dCol[i];
            int eyef = fila    + ojof[i];
            int eyec = columna + ojoc[i];

            if (enTablero(nf, nc) && !cruzaRio(nf) 
                && tablero[eyef][eyec] == null 
                && puedeMoverA(nf, nc, tablero)) {
                movimientos[nf][nc] = true;
            }
        }
        return movimientos;
    }
    
    
    private boolean cruzaRio(int f) {
        // rojas en filas 5-9, negras en filas 0-4
        if (isR) return f < 5;
        else     return f > 4;
    }
    
}
