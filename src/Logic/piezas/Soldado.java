package Logic.piezas;

public class Soldado extends Pieza {
    
    public Soldado(int fila, int columna, ColorPieza color) {
        super(fila, columna, color);
    }
    
    @Override
    public String getSimbolo() {
        return color.getNombre() + "_SOLDADO";
    }
    
    @Override
    public boolean[][] getMoveValido(Pieza[][] tablero) {
        boolean[][] movimientos = new boolean[10][9];
        
        if (esRojo()) {
            // Adelante (arriba)
            if (enTablero(fila - 1, columna) && puedeMoverA(fila - 1, columna, tablero)) {
                movimientos[fila - 1][columna] = true;
            }
            // Laterales si cruzó el río
            if (cruzoRio()) {
                if (enTablero(fila, columna - 1) && puedeMoverA(fila, columna - 1, tablero))
                    movimientos[fila][columna - 1] = true;
                if (enTablero(fila, columna + 1) && puedeMoverA(fila, columna + 1, tablero))
                    movimientos[fila][columna + 1] = true;
            }
        } else {
            // Adelante (abajo)
            if (enTablero(fila + 1, columna) && puedeMoverA(fila + 1, columna, tablero)) {
                movimientos[fila + 1][columna] = true;
            }
            // Laterales si cruzó el río
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
        if (esRojo()) return fila <= 4;
        else return fila >= 5;
    }
}