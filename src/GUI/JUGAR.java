
package GUI;
import static GUI.JUGAR.PanelTablero.CELDA;
import static GUI.JUGAR.PanelTablero.MARGEN;
import Logic.Login_Manager;
import Logic.Player;
import Logic.piezas.Caballo;
import Logic.piezas.Canon;
import Logic.piezas.Carro;
import Logic.piezas.Consejero;
import Logic.piezas.Elefante;
import Logic.piezas.General;
import Logic.piezas.Pieza;
import Logic.piezas.Soldado;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @athor Dell
 */
public class JUGAR extends JFrame {
    //PALETA DE COLORES
       static final Color FONDO           = new Color(18, 18, 24);
    static final Color PANEL           = new Color(28, 28, 38);
    static final Color ACENTO          = new Color(200, 150, 50);
    static final Color TEXTO           = new Color(220, 220, 230);
    static final Color TEXTO_TENUE     = new Color(120, 120, 140);
    static final Color CAMPO_FONDO     = new Color(12, 12, 18);
    static final Color CAMPO_BORDE     = new Color(60, 60, 80);
    static final Color BTN_SECUNDARIO  = new Color(40, 40, 58);
    static final Color BTN_PELIGRO     = new Color(160, 40, 40);
    static final Color TABLERO_FONDO   = new Color(180, 130, 60);   // madera
    static final Color TABLERO_LINEA   = new Color(80, 50, 20);     // líneas
    static final Color RIO_COLOR       = new Color(40, 80, 140, 80); // río semitransparente
    static final Color PALACIO_COLOR   = new Color(200, 150, 50, 180);// palacio

   
    static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  18);
    static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 12);
    static final Font FUENTE_CAMPO  = new Font("SansSerif", Font.PLAIN, 13);
    static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  13);
    static final Font FUENTE_RIO    = new Font("Serif",     Font.BOLD,  14);

    
    private Login_Manager loginManager;
    private MENUPRINCIPAL menuPrincipal;
    private String jugador1;
    private String jugador2;
    private Pieza[][] tablero;
    
    public JUGAR(Login_Manager loginManager, MENUPRINCIPAL menuPrincipal){
        this.loginManager=loginManager;
        this.menuPrincipal=menuPrincipal;
        this.jugador1=loginManager.getCurrentUser().getUsername();
        
        setTitle("XIANGQI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(FONDO);
        
        pedirOponente();
        
        
    }
    
    
    private void pedirOponente(){
        
        
        JDialog dialog = new JDialog(this, "Seleccionar oponente",true);
        dialog.setSize(900,600);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(FONDO);
        dialog.setLayout(new GridBagLayout());
        
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(420, 420));
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));

        JLabel titulo = new JLabel("Nueva Partida");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblInfo = new JLabel("Jugador 1: " + jugador1);
        lblInfo.setFont(FUENTE_LABEL);
        lblInfo.setForeground(TEXTO_TENUE);
        lblInfo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblOponente = new JLabel("Username del oponente");
        lblOponente.setFont(FUENTE_LABEL);
        lblOponente.setForeground(TEXTO_TENUE);
        lblOponente.setAlignmentX(LEFT_ALIGNMENT);
 
        
        JTextField campoOponente = new JTextField();
        campoOponente.setFont(FUENTE_CAMPO);
        campoOponente.setForeground(TEXTO);
        campoOponente.setBackground(CAMPO_FONDO);
        campoOponente.setCaretColor(ACENTO);
        campoOponente.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CAMPO_BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        campoOponente.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        campoOponente.setAlignmentX(LEFT_ALIGNMENT);

        JLabel lblMensaje = new JLabel(" ");
        lblMensaje.setFont(FUENTE_LABEL);
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnJugar   = crearBoton("Jugar",    ACENTO,         Color.WHITE);
        JButton btnCancelar = crearBoton("Cancelar", BTN_SECUNDARIO, ACENTO);
        
                btnJugar.addActionListener(e -> {
            String oponente = campoOponente.getText().trim();

            if (oponente.isEmpty()) {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText("Ingresa un username.");
                return;
            }
            if (oponente.equals(jugador1)) {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText("No puedes jugar contra ti mismo.");
                return;
            }
            Player p = loginManager.buscarPlayer(oponente);
            if (p == null) {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText("El jugador no existe.");
                return;
            }

            jugador2 = oponente;
            dialog.dispose();
            mostrarTablero();
        });

        btnCancelar.addActionListener(e -> {
            dialog.dispose();
            dispose();
        });
        
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(lblInfo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblOponente);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campoOponente);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblMensaje);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnJugar);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnCancelar);

        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    
    private void inicializarTablero() {
    tablero = new Pieza[10][9];

    // ── Piezas negras (arriba, isR = false) ──
    tablero[0][0] = new Carro(0, 0, false);
    tablero[0][1] = new Caballo(0, 1, false);
    tablero[0][2] = new Elefante(0, 2, false);
    tablero[0][3] = new Consejero(0, 3, false);
    tablero[0][4] = new General(0, 4, false);
    tablero[0][5] = new Consejero(0, 5, false);
    tablero[0][6] = new Elefante(0, 6, false);
    tablero[0][7] = new Caballo(0, 7, false);
    tablero[0][8] = new Carro(0, 8, false);
    tablero[2][1] = new Canon(2, 1, false);
    tablero[2][7] = new Canon(2, 7, false);
    tablero[3][0] = new Soldado(3, 0, false);
    tablero[3][2] = new Soldado(3, 2, false);
    tablero[3][4] = new Soldado(3, 4, false);
    tablero[3][6] = new Soldado(3, 6, false);
    tablero[3][8] = new Soldado(3, 8, false);

    // ── Piezas rojas (abajo, isR = true) ──
    tablero[9][0] = new Carro(9, 0, true);
    tablero[9][1] = new Caballo(9, 1, true);
    tablero[9][2] = new Elefante(9, 2, true);
    tablero[9][3] = new Consejero(9, 3, true);
    tablero[9][4] = new General(9, 4, true);
    tablero[9][5] = new Consejero(9, 5, true);
    tablero[9][6] = new Elefante(9, 6, true);
    tablero[9][7] = new Caballo(9, 7, true);
    tablero[9][8] = new Carro(9, 8, true);
    tablero[7][1] = new Canon(7, 1, true);
    tablero[7][7] = new Canon(7, 7, true);
    tablero[6][0] = new Soldado(6, 0, true);
    tablero[6][2] = new Soldado(6, 2, true);
    tablero[6][4] = new Soldado(6, 4, true);
    tablero[6][6] = new Soldado(6, 6, true);
    tablero[6][8] = new Soldado(6, 8, true);
}
   
    
    
   private void mostrarTablero(){
    getContentPane().removeAll();
    getContentPane().setLayout(new BorderLayout());
    inicializarTablero();

    JPanel panelTop = new JPanel();
    panelTop.setBackground(PANEL);
    panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
    panelTop.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

    JLabel lblJ1 = new JLabel(jugador1+"(NEGRAS)");
    lblJ1.setFont(FUENTE_TITULO);
    lblJ1.setForeground(TEXTO);

    JLabel lblVs = new JLabel("  VS  ");
    lblVs.setFont(FUENTE_TITULO);
    lblVs.setForeground(ACENTO);

    JLabel lblJ2 = new JLabel(jugador2 + "  (Rojas)");
    lblJ2.setFont(FUENTE_TITULO);
    lblJ2.setForeground(new Color(200, 80, 80));

    panelTop.add(lblJ1);
    panelTop.add(lblVs);
    panelTop.add(lblJ2);
    panelTop.add(Box.createHorizontalGlue());

    JPanel panelBot = new JPanel();
    panelBot.setBackground(PANEL);
    panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));
    panelBot.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    JButton btnRetirar = crearBoton("Retirarme", BTN_PELIGRO, Color.WHITE);
    JButton btnVolver  = crearBoton("Volver", BTN_SECUNDARIO, ACENTO);
    btnRetirar.setMaximumSize(new Dimension(150, 38));
    btnVolver.setMaximumSize(new Dimension(150, 38));

    btnRetirar.addActionListener(e -> {});
    btnVolver.addActionListener(e -> dispose());

    panelBot.add(btnRetirar);
    panelBot.add(Box.createHorizontalStrut(12));
    panelBot.add(btnVolver);

    PanelTablero panelTablero = new PanelTablero();

    getContentPane().add(panelTop,     BorderLayout.NORTH);
    getContentPane().add(panelTablero, BorderLayout.CENTER);
    getContentPane().add(panelBot,     BorderLayout.SOUTH);

    revalidate();
    repaint();
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
}
    
    
    
class PanelTablero extends JPanel {

    static final int COLS   = 9;
    static final int FILAS  = 10;
    static final int CELDA  = 62;
    static final int MARGEN = 45;
private int filaSeleccionada = -1;
private int colSeleccionada  = -1;
    public PanelTablero() {
        setBackground(TABLERO_FONDO);
        setPreferredSize(new Dimension(
            MARGEN * 2 + CELDA * (COLS - 1),
            MARGEN * 2 + CELDA * (FILAS - 1)
                
                
        ));


addMouseListener(new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        int c = (e.getX() - MARGEN + CELDA / 2) / CELDA;
        int f = (e.getY() - MARGEN + CELDA / 2) / CELDA;

        if (!enTablero(f, c)) return;

        if (filaSeleccionada != -1) {
            // ya hay pieza seleccionada — mover
            boolean[][] moves = tablero[filaSeleccionada][colSeleccionada].getMoveValido(tablero);
            if (moves[f][c]) {
                tablero[f][c] = tablero[filaSeleccionada][colSeleccionada];
                tablero[filaSeleccionada][colSeleccionada] = null;
                tablero[f][c].setFila(f);
                tablero[f][c].setColumna(c);
            }
            filaSeleccionada = -1;
            colSeleccionada  = -1;
        } else if (tablero[f][c] != null) {
            // seleccionar pieza
            filaSeleccionada = f;
            colSeleccionada  = c;
        }
        repaint();
    }
});
    }

    private boolean enTablero(int f, int c) {
    return f >= 0 && f < 10 && c >= 0 && c < 9;
}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarLineas(g2);
        dibujarRio(g2);
        dibujarPiezas(g2);
        dibujarPalacios(g2);
    }

    private void dibujarLineas(Graphics2D g2) {
        g2.setColor(TABLERO_LINEA);
        g2.setStroke(new BasicStroke(2.5f));

        for (int f = 0; f < FILAS; f++) {
            int y = MARGEN + f * CELDA;
            g2.drawLine(MARGEN, y, MARGEN + (COLS - 1) * CELDA, y);
        }

        for (int c = 0; c < COLS; c++) {
            int x = MARGEN + c * CELDA;
            g2.drawLine(x, MARGEN,          x, MARGEN + 4 * CELDA);
            g2.drawLine(x, MARGEN + 5 * CELDA, x, MARGEN + 9 * CELDA);
        }
    }

    private void dibujarRio(Graphics2D g2) {
        int x = MARGEN;
        int y = MARGEN + 4 * CELDA;
        int w = (COLS - 1) * CELDA;
        int h = CELDA;

        g2.setColor(RIO_COLOR);
        g2.fillRect(x, y, w, h);

        g2.setColor(new Color(180, 210, 255, 200));
        g2.setFont(FUENTE_RIO);
        int mitad = w / 2;
        g2.drawString("", x + mitad / 2 - 28,        y + h / 2 + 6);
        g2.drawString("", x + mitad + mitad / 2 - 28, y + h / 2 + 6);
    }

    private void dibujarPalacios(Graphics2D g2) {
    g2.setColor(TABLERO_LINEA);
    g2.setStroke(new BasicStroke(2.5f));

    // ── Palacio superior (columnas 3-5, filas 0-2) ──
    int px1 = MARGEN + 3 * CELDA;
    int py1 = MARGEN;
    int pw  = 2 * CELDA;  // 3 columnas = 2 espacios
    int ph  = 2 * CELDA;  // 3 filas = 2 espacios

    // cuadrado exterior
    g2.drawRect(px1, py1, pw, ph);
    // diagonales de esquina a esquina
    g2.drawLine(px1,      py1,      px1 + pw, py1 + ph);
    g2.drawLine(px1 + pw, py1,      px1,      py1 + ph);
    // línea horizontal del medio
    g2.drawLine(px1,      py1 + CELDA, px1 + pw, py1 + CELDA);
    // línea vertical del medio
    g2.drawLine(px1 + CELDA, py1, px1 + CELDA, py1 + ph);

    // ── Palacio inferior (columnas 3-5, filas 7-9) ──
    int px2 = MARGEN + 3 * CELDA;
    int py2 = MARGEN + 7 * CELDA;

    // cuadrado exterior
    g2.drawRect(px2, py2, pw, ph);
    // diagonales
    g2.drawLine(px2,      py2,      px2 + pw, py2 + ph);
    g2.drawLine(px2 + pw, py2,      px2,      py2 + ph);
    // línea horizontal del medio
    g2.drawLine(px2,      py2 + CELDA, px2 + pw, py2 + CELDA);
    // línea vertical del medio
    g2.drawLine(px2 + CELDA, py2, px2 + CELDA, py2 + ph);
}
    
    private void dibujarPiezas(Graphics2D g2) {
    for (int f = 0; f < 10; f++) {
        for (int c = 0; c < 9; c++) {
            Pieza p = tablero[f][c];
            if (p != null) {
                int x = MARGEN + c * CELDA;
                int y = MARGEN + f * CELDA;
                int radio = CELDA / 2 - 5;

                // círculo
                if (p.isIsR()) {
                    g2.setColor(new Color(200, 50, 50));
                } else {
                    g2.setColor(new Color(30, 30, 30));
                }
                g2.fillOval(x - radio, y - radio, radio * 2, radio * 2);

                // borde
                g2.setColor(new Color(200, 150, 50));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawOval(x - radio, y - radio, radio * 2, radio * 2);

                // símbolo
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Serif", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                String s = p.getSimbolo();
                g2.drawString(s, x - fm.stringWidth(s) / 2, y + fm.getAscent() / 2 - 2);
            }
        }
    }
}
    
}



    // ── Helper botón ─────────────────────────────────────────────────────────
    private JButton crearBoton(String texto, Color fondo, Color colorTexto) {
        JButton btn = new JButton(texto);
        btn.setFont(FUENTE_BOTON);
        btn.setForeground(colorTexto);
        btn.setBackground(fondo);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setAlignmentX(LEFT_ALIGNMENT);

        Color hover = fondo.brighter();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(fondo); }
        });
        return btn;
    }
    
    
    
    
}
