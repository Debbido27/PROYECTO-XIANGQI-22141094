
package Logic.piezas;



import java.awt.Color;

public enum TipoPieza {
    
    GENERAL {
        @Override
        public boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero) {
            boolean[][] movimientos = new boolean[10][9];
            int fila = pieza.getFila();
            int columna = pieza.getColumna();
            
            int[] dFila = {-1, 1, 0, 0};
            int[] dCol = {0, 0, -1, 1};
            
            for (int i = 0; i < 4; i++) {
                int nf = fila + dFila[i];
                int nc = columna + dCol[i];
                
                if (pieza.enPalacio(nf, nc) && pieza.puedeMoverA(nf, nc, tablero)) {
                    movimientos[nf][nc] = true;
                }
            }
            return movimientos;
        }
        
        @Override
        public boolean enPalacio(Pieza pieza, int f, int c) {
            if (c < 3 || c > 5) return false;
            if (pieza.isR) return f >= 7 && f <= 9;
            else return f >= 0 && f <= 2;
        }
    },
    
    CONSEJERO {
        @Override
        public boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero) {
            boolean[][] movimientos = new boolean[10][9];
            int fila = pieza.getFila();
            int columna = pieza.getColumna();
            
            int[] dFila = {-1, -1, 1, 1};
            int[] dCol = {-1, 1, -1, 1};
            
            for (int i = 0; i < 4; i++) {
                int nf = fila + dFila[i];
                int nc = columna + dCol[i];
                
                if (pieza.enPalacio(nf, nc) && pieza.puedeMoverA(nf, nc, tablero)) {
                    movimientos[nf][nc] = true;
                }
            }
            return movimientos;
        }
        
        @Override
        public boolean enPalacio(Pieza pieza, int f, int c) {
            if (c < 3 || c > 5) return false;
            if (pieza.isR) return f >= 7 && f <= 9;
            else return f >= 0 && f <= 2;
        }
    },
    
    ELEFANTE {
        @Override
        public boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero) {
            boolean[][] movimientos = new boolean[10][9];
            int fila = pieza.getFila();
            int columna = pieza.getColumna();
            
            int[] dFila = {-2, -2, 2, 2};
            int[] dCol = {-2, 2, -2, 2};
            int[] ojof = {-1, -1, 1, 1};
            int[] ojoc = {-1, 1, -1, 1};
            
            for (int i = 0; i < 4; i++) {
                int nf = fila + dFila[i];
                int nc = columna + dCol[i];
                int eyef = fila + ojof[i];
                int eyec = columna + ojoc[i];
                
                if (pieza.enTablero(nf, nc) && !cruzaRio(pieza, nf) 
                    && tablero[eyef][eyec] == null 
                    && pieza.puedeMoverA(nf, nc, tablero)) {
                    movimientos[nf][nc] = true;
                }
            }
            return movimientos;
        }
        
        private boolean cruzaRio(Pieza pieza, int f) {
            if (pieza.isR) return f < 5;
            else return f > 4;
        }
    },
    
    CABALLO {
        @Override
        public boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero) {
            boolean[][] movimientos = new boolean[10][9];
            int fila = pieza.getFila();
            int columna = pieza.getColumna();
            
            int[] dFila = {-2, -2, -1, -1, 1, 1, 2, 2};
            int[] dCol = {-1, 1, -2, 2, -2, 2, -1, 1};
            int[] blkFila = {-1, -1, 0, 0, 0, 0, 1, 1};
            int[] blkCol = {0, 0, -1, 1, -1, 1, 0, 0};
            
            for (int i = 0; i < 8; i++) {
                int nf = fila + dFila[i];
                int nc = columna + dCol[i];
                int bf = fila + blkFila[i];
                int bc = columna + blkCol[i];
                
                if (pieza.enTablero(nf, nc) && tablero[bf][bc] == null 
                    && pieza.puedeMoverA(nf, nc, tablero)) {
                    movimientos[nf][nc] = true;
                }
            }
            return movimientos;
        }
    },
    
    CARRO {
        @Override
        public boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero) {
            boolean[][] movimientos = new boolean[10][9];
            int fila = pieza.getFila();
            int columna = pieza.getColumna();
            
            buscarMovimientosLineales(tablero, movimientos, fila - 1, columna, -1, 0, pieza);
            buscarMovimientosLineales(tablero, movimientos, fila + 1, columna, 1, 0, pieza);
            buscarMovimientosLineales(tablero, movimientos, fila, columna - 1, 0, -1, pieza);
            buscarMovimientosLineales(tablero, movimientos, fila, columna + 1, 0, 1, pieza);
            
            return movimientos;
        }
        
        private void buscarMovimientosLineales(Pieza[][] tablero, boolean[][] movimientos,
                                               int f, int c, int df, int dc, Pieza pieza) {
            if (!pieza.enTablero(f, c)) return;
            
            Pieza destino = tablero[f][c];
            
            if (destino == null) {
                movimientos[f][c] = true;
                buscarMovimientosLineales(tablero, movimientos, f + df, c + dc, df, dc, pieza);
            } else if (destino.isR != pieza.isR) {
                movimientos[f][c] = true;
            }
        }
    },
    
    CANON {
        @Override
        public boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero) {
            boolean[][] movimientos = new boolean[10][9];
            int fila = pieza.getFila();
            int columna = pieza.getColumna();
            
            buscarMovimientosConPantalla(tablero, movimientos, fila - 1, columna, -1, 0, false, pieza);
            buscarMovimientosConPantalla(tablero, movimientos, fila + 1, columna, 1, 0, false, pieza);
            buscarMovimientosConPantalla(tablero, movimientos, fila, columna - 1, 0, -1, false, pieza);
            buscarMovimientosConPantalla(tablero, movimientos, fila, columna + 1, 0, 1, false, pieza);
            
            return movimientos;
        }
        
        private void buscarMovimientosConPantalla(Pieza[][] tablero, boolean[][] movimientos,
                                                  int f, int c, int df, int dc, boolean pantalla, Pieza pieza) {
            if (!pieza.enTablero(f, c)) return;
            
            Pieza destino = tablero[f][c];
            
            if (!pantalla) {
                if (destino == null) {
                    movimientos[f][c] = true;
                    buscarMovimientosConPantalla(tablero, movimientos, f + df, c + dc, df, dc, false, pieza);
                } else {
                    buscarMovimientosConPantalla(tablero, movimientos, f + df, c + dc, df, dc, true, pieza);
                }
            } else {
                if (destino == null) {
                    buscarMovimientosConPantalla(tablero, movimientos, f + df, c + dc, df, dc, true, pieza);
                } else if (destino.isR != pieza.isR) {
                    movimientos[f][c] = true;
                }
            }
        }
    },
    
    SOLDADO {
        @Override
        public boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero) {
            boolean[][] movimientos = new boolean[10][9];
            int fila = pieza.getFila();
            int columna = pieza.getColumna();
            
            if (pieza.isR) {
                // Adelante (arriba)
                if (pieza.enTablero(fila - 1, columna) && pieza.puedeMoverA(fila - 1, columna, tablero)) {
                    movimientos[fila - 1][columna] = true;
                }
                // Laterales si cruzó el río
                if (cruzoRio(pieza)) {
                    if (pieza.enTablero(fila, columna - 1) && pieza.puedeMoverA(fila, columna - 1, tablero))
                        movimientos[fila][columna - 1] = true;
                    if (pieza.enTablero(fila, columna + 1) && pieza.puedeMoverA(fila, columna + 1, tablero))
                        movimientos[fila][columna + 1] = true;
                }
            } else {
                // Adelante (abajo)
                if (pieza.enTablero(fila + 1, columna) && pieza.puedeMoverA(fila + 1, columna, tablero)) {
                    movimientos[fila + 1][columna] = true;
                }
                // Laterales si cruzó el río
                if (cruzoRio(pieza)) {
                    if (pieza.enTablero(fila, columna - 1) && pieza.puedeMoverA(fila, columna - 1, tablero))
                        movimientos[fila][columna - 1] = true;
                    if (pieza.enTablero(fila, columna + 1) && pieza.puedeMoverA(fila, columna + 1, tablero))
                        movimientos[fila][columna + 1] = true;
                }
            }
            return movimientos;
        }
        
        private boolean cruzoRio(Pieza pieza) {
            int fila = pieza.getFila();
            if (pieza.isR) return fila <= 4;
            else return fila >= 5;
        }
    };
    
    // Métodos abstractos que cada tipo debe implementar
    public abstract boolean[][] getMovimientos(Pieza pieza, Pieza[][] tablero);
    
    // Método opcional (por defecto lanza excepción, lo sobreescriben GENERAL y CONSEJERO)
    public boolean enPalacio(Pieza pieza, int f, int c) {
        return false;  // Solo GENERAL y CONSEJERO usan esto
    }
    
    
    // Método de utilidad para obtener el símbolo
    public String getSimbolo(Pieza pieza) {
        return (pieza.isR ? "ROJO_" : "NEGRO_") + this.name();
    }
}
