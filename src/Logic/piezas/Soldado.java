
package Logic.piezas;


public class Soldado extends Pieza {
  
    public Soldado(int fila, int columna, boolean isR){
        super(fila, columna, isR);
    }
    
    @Override
    public String getSimbolo() {
        return isR ? "SOLDADO_ROJO" : "SOLDADO_NEGRO";
    }
    
    
     @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        boolean[][] movimientos = new boolean[10][9];

        if (isR) {
            if (enTablero(fila - 1, columna) && puedeMoverA(fila - 1, columna, tablero)) {
                movimientos[fila - 1][columna] = true;
            }
            if (cruzoRio()) {
                if (enTablero(fila, columna - 1) && puedeMoverA(fila, columna - 1, tablero))
                    movimientos[fila][columna - 1] = true;
                if (enTablero(fila, columna + 1) && puedeMoverA(fila, columna + 1, tablero))
                    movimientos[fila][columna + 1] = true;
            }
        } else {
            if (enTablero(fila + 1, columna) && puedeMoverA(fila + 1, columna, tablero)) {
                movimientos[fila + 1][columna] = true;
            }
            if (cruzoRio()) {
                if (enTablero(fila, columna - 1) && puedeMoverA(fila, columna - 1, tablero))
                    movimientos[fila][columna - 1] = true;
                if (enTablero(fila, columna + 1) && puedeMoverA(fila, columna + 1, tablero))
                    movimientos[fila][columna + 1] = true;
            }
        }
        return movimientos;
    }

    private boolean cruzoRio() {
        if (isR) return fila <= 4;
        else     return fila >= 5;
    }
    
}
