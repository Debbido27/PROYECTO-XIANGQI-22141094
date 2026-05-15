
        package GUI;
        import static GUI.JUGAR.PanelTablero.CELDA;
        import static GUI.JUGAR.PanelTablero.MARGEN;
        import Logic.Login_Manager;
        import Logic.Player;
import Logic.piezas.Caballo;
import Logic.piezas.Canon;
import Logic.piezas.Carro;
import Logic.piezas.ColorPieza;
import Logic.piezas.Consejero;
import Logic.piezas.Elefante;
import Logic.piezas.General;
   
        import Logic.piezas.Pieza;
import Logic.piezas.Soldado;
import Logic.piezas.TipoPieza;
        import java.awt.BasicStroke;
        import java.awt.BorderLayout;
        import java.awt.CardLayout;
        import java.awt.Color;
        import java.awt.Cursor;
        import java.awt.Dimension;
import java.awt.FlowLayout;
        import java.awt.Font;
        import java.awt.FontMetrics;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.GridBagLayout;
        import java.awt.GridLayout;
        import java.awt.Image;
        import java.awt.RenderingHints;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;
        import java.net.URL;
        import javax.swing.BorderFactory;
        import javax.swing.Box;
        import javax.swing.BoxLayout;
        import javax.swing.ImageIcon;
        import javax.swing.JButton;
        import javax.swing.JDialog;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JOptionPane;
        import javax.swing.JPanel;
        import javax.swing.JTextField;
        import javax.swing.SwingUtilities;





        //ATRIBUTOS
        ////////////////////

        public class JUGAR extends JPanel {
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
        static final Color TABLERO_FONDO   = new Color(180, 130, 60);   
        static final Color TABLERO_LINEA   = new Color(80, 50, 20);     
        static final Color RIO_COLOR       = new Color(40, 80, 140, 80); 
        static final Color PALACIO_COLOR   = new Color(200, 150, 50, 180);



        static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  18);
        static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 12);
        static final Font FUENTE_CAMPO  = new Font("SansSerif", Font.PLAIN, 13);
        static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  13);
        static final Font FUENTE_RIO    = new Font("Serif",     Font.BOLD,  14);
        private boolean turnoRojo = true;

        private JLabel lblTurno;
        private Login_Manager loginManager;
        private MENUPRINCIPAL menuPrincipal;
        private String jugador1;
        private String jugador2;
        private Pieza[][] tablero;
        private CardLayout cardLayout;
        private JPanel cardPanel;
private java.util.ArrayList<Pieza> piezasCapturadasRojas;
private java.util.ArrayList<Pieza> piezasCapturadasNegras;
private JPanel panelCementerio;





    //////CONSTRUCTOR




        public JUGAR(Login_Manager loginManager, MENUPRINCIPAL menuPrincipal, java.awt.CardLayout cardLayout, JPanel cardPanel){
            this.loginManager=loginManager;
            this.menuPrincipal=menuPrincipal;
            this.jugador1=loginManager.getCurrentUser().getUsername();
            this.cardLayout = cardLayout;
            this.cardPanel = cardPanel;
piezasCapturadasRojas = new java.util.ArrayList<>();
piezasCapturadasNegras = new java.util.ArrayList<>();

           setLayout(new BorderLayout());
           setBackground(FONDO);


           pedirOponente();


        }



        //METODO PEIR OPONNENTE
        

        private void pedirOponente(){

            turnoRojo = true;
            jugador2 = null;
            piezasCapturadasRojas.clear();
            piezasCapturadasNegras.clear();
            if (panelCementerio != null) {
                panelCementerio.removeAll();
                panelCementerio.revalidate();
                panelCementerio.repaint();
            }
            
        removeAll();
        setLayout(new BorderLayout());


            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(420, 420));
            panel.setBackground(PANEL);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACENTO, 1),
                BorderFactory.createEmptyBorder(40, 80, 40, 80)
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
                    mostrarTablero();
                });


                 btnCancelar.addActionListener(e -> {
                cardLayout.show(cardPanel, "menu");
                cardPanel.revalidate();
                cardPanel.repaint();


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

            add(panel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }





        private void inicializarTablero() {
            tablero = new Pieza[10][9];

            // === PIEZAS NEGRAS (arriba) ===
            tablero[0][0] = new Carro(0, 0, ColorPieza.NEGRO);
            tablero[0][1] = new Caballo(0, 1, ColorPieza.NEGRO);
            tablero[0][2] = new Elefante(0, 2, ColorPieza.NEGRO);
            tablero[0][3] = new Consejero(0, 3, ColorPieza.NEGRO);
            tablero[0][4] = new General(0, 4, ColorPieza.NEGRO);
            tablero[0][5] = new Consejero(0, 5, ColorPieza.NEGRO);
            tablero[0][6] = new Elefante(0, 6, ColorPieza.NEGRO);
            tablero[0][7] = new Caballo(0, 7, ColorPieza.NEGRO);
            tablero[0][8] = new Carro(0, 8, ColorPieza.NEGRO);

            tablero[2][1] = new Canon(2, 1, ColorPieza.NEGRO);
            tablero[2][7] = new Canon(2, 7, ColorPieza.NEGRO);

            tablero[3][0] = new Soldado(3, 0, ColorPieza.NEGRO);
            tablero[3][2] = new Soldado(3, 2, ColorPieza.NEGRO);
            tablero[3][4] = new Soldado(3, 4, ColorPieza.NEGRO);
            tablero[3][6] = new Soldado(3, 6, ColorPieza.NEGRO);
            tablero[3][8] = new Soldado(3, 8, ColorPieza.NEGRO);

            // === PIEZAS ROJAS (abajo) ===
            tablero[9][0] = new Carro(9, 0, ColorPieza.ROJO);
            tablero[9][1] = new Caballo(9, 1, ColorPieza.ROJO);
            tablero[9][2] = new Elefante(9, 2, ColorPieza.ROJO);
            tablero[9][3] = new Consejero(9, 3, ColorPieza.ROJO);
            tablero[9][4] = new General(9, 4, ColorPieza.ROJO);
            tablero[9][5] = new Consejero(9, 5, ColorPieza.ROJO);
            tablero[9][6] = new Elefante(9, 6, ColorPieza.ROJO);
            tablero[9][7] = new Caballo(9, 7, ColorPieza.ROJO);
            tablero[9][8] = new Carro(9, 8, ColorPieza.ROJO);

            tablero[7][1] = new Canon(7, 1, ColorPieza.ROJO);
            tablero[7][7] = new Canon(7, 7, ColorPieza.ROJO);

            tablero[6][0] = new Soldado(6, 0, ColorPieza.ROJO);
            tablero[6][2] = new Soldado(6, 2, ColorPieza.ROJO);
            tablero[6][4] = new Soldado(6, 4, ColorPieza.ROJO);
            tablero[6][6] = new Soldado(6, 6, ColorPieza.ROJO);
            tablero[6][8] = new Soldado(6, 8, ColorPieza.ROJO);
        }




//METODO PARA VALIDAR GENERALES ENFRENTADOS

        private boolean generalesEnfrentados() {
    int fR = -1, cR = -1, fN = -1, cN = -1;
    
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 9; j++) {
            Pieza p = tablero[i][j];
            if (p != null && p.getTipo() == TipoPieza.GENERAL) {
                if (p.esRojo()) {
                    fR = i;
                    cR = j;
                } else {
                    fN = i;
                    cN = j;
                }
            }
        }
    }
    
    if (fR == -1 || fN == -1) return false;
    
    return Pieza.generalesMirando(tablero, fR, cR, fN, cN);
}



        
        //METODO PARA MOSTRAR TABLERO
        
         private void mostrarTablero(){    

             //VLIDA JUGADOR
             
          if (jugador2 == null || jugador2.trim().isEmpty()) return;

           if (loginManager == null) {
            JOptionPane.showMessageDialog(this, "Error interno: LoginManager no inicializado");
            return;
        }    

        removeAll();
        setLayout(new BorderLayout());
        
        
        inicializarTablero();

        JPanel panelTop = new JPanel();
        panelTop.setBackground(PANEL);
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
        panelTop.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        JLabel lblJ1 = new JLabel(jugador1+"(ROJAS)");
        lblJ1.setFont(FUENTE_TITULO);
        lblJ1.setForeground(TEXTO);

        JLabel lblVs = new JLabel("  VS  ");
        lblVs.setFont(FUENTE_TITULO);
        lblVs.setForeground(ACENTO);

        JLabel lblJ2 = new JLabel(jugador2 + "  (NEGRAS)");
        lblJ2.setFont(FUENTE_TITULO);
        lblJ2.setForeground(new Color(200, 80, 80));

        panelTop.add(lblJ1);
        panelTop.add(lblVs);
        panelTop.add(lblJ2);
        panelTop.add(Box.createHorizontalGlue());

        
            JPanel panelJuego = new JPanel(new BorderLayout());
    PanelTablero panelTablero = new PanelTablero();
    JPanel centro = new JPanel(new GridBagLayout());
    centro.setBackground(TABLERO_FONDO);
    centro.add(panelTablero);

    // Crear el cementerio
    panelCementerio = new JPanel();
    panelCementerio.setBackground(PANEL);
    panelCementerio.setPreferredSize(new Dimension(200, 568));
    panelCementerio.setLayout(new BoxLayout(panelCementerio, BoxLayout.Y_AXIS));
    panelCementerio.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createLineBorder(ACENTO), "Cementerio"
    ));

panelJuego.add(centro, BorderLayout.CENTER);
panelJuego.add(panelCementerio, BorderLayout.EAST);
add(panelJuego, BorderLayout.CENTER);
        JPanel panelBot = new JPanel();
        panelBot.setBackground(PANEL);
        panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));
        panelBot.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblTurno = new JLabel("Turno: " + jugador1 + " (Rojas)");
        lblTurno.setFont(FUENTE_LABEL);
        lblTurno.setForeground(new Color(200, 80, 80));
        panelTop.add(lblTurno);

        JButton btnRetirar = crearBoton("Retirarme", BTN_PELIGRO, Color.WHITE);

        btnRetirar.setMaximumSize(new Dimension(150, 38));

        btnRetirar.addActionListener(e -> {
            
         int confirm = JOptionPane.showConfirmDialog(JUGAR.this, "¿Seguro que quieres retirarte? Perderás la partida.", "Confirmar retiro", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
         if (confirm != JOptionPane.YES_OPTION) {
         return;
         }


         String ganador  = turnoRojo ? jugador1 : jugador2;
         String retirado = turnoRojo ? jugador2 : jugador1;

         Player pGanador = loginManager.buscarPlayer(ganador);
         if (pGanador != null) {
            pGanador.setPuntos(pGanador.getPuntos() + 3);
         }



        String log = retirado + " SE HA RETIRADO, FELICIDADES " + ganador + ", HAS GANADO 3 PUNTOS";
        loginManager.guardarPartida(log);

        removeAll();
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(ACENTO, 1),
        BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));

        JLabel lblMsg = new JLabel(retirado + " SE HA RETIRADO");
        lblMsg.setFont(FUENTE_TITULO);
        lblMsg.setForeground(BTN_PELIGRO);
        lblMsg.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblGanador = new JLabel(ganador + "! +3 puntos");
        lblGanador.setFont(FUENTE_LABEL);
        lblGanador.setForeground(ACENTO);
        lblGanador.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnOtra = crearBoton("Jugar Otra Partida", BTN_SECUNDARIO, ACENTO);
        JButton btnOk = crearBoton("Volver al Menu", ACENTO, Color.WHITE);

        btnOtra.addActionListener(ev -> {
        turnoRojo = true;
        jugador2 = null;
        piezasCapturadasRojas.clear();
        piezasCapturadasNegras.clear();
        pedirOponente();
        });

        btnOk.addActionListener(ev -> {
        cardLayout.show(cardPanel, "menu");
        cardPanel.revalidate();
        cardPanel.repaint();
        });

        panel.add(lblMsg);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblGanador);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnOtra);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnOk);

        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();    

        });  




        panelBot.add(btnRetirar);
        panelBot.add(Box.createHorizontalStrut(12));

        JButton btnAyuda = crearBoton("Ayuda", new Color(50, 80, 150), Color.WHITE);
        btnAyuda.setMaximumSize(new Dimension(150, 38));
        final boolean[] ayudaActiva = {false};
        btnAyuda.addActionListener(e -> {
        ayudaActiva[0] = !ayudaActiva[0];
        if (ayudaActiva[0]) {
        btnAyuda.setBackground(new Color(80, 130, 200));
        btnAyuda.setText("Ayuda ON");
        } else {
        btnAyuda.setBackground(new Color(50, 80, 150));
        btnAyuda.setText("Ayuda");
        }
        panelTablero.setAyudaActiva(ayudaActiva[0]);
        panelTablero.repaint();
        });
        panelBot.add(btnAyuda);


        centro.setBackground(TABLERO_FONDO);
        centro.add(panelTablero);

        add(panelTop,     BorderLayout.NORTH);
        add(panelJuego,       BorderLayout.CENTER);
        add(panelBot,     BorderLayout.SOUTH);
        actualizarCementerio();
        revalidate();
        repaint();




        }


private final java.util.Map<String, Image> cacheImagenes = new java.util.HashMap<>();

        private Image cargarImagen(String nombre) {

        return cacheImagenes.computeIfAbsent(nombre, k -> {
        URL url = getClass().getResource("/IMAGENES/" + k + ".png");
        if (url == null) return null;
        try {
        return javax.imageio.ImageIO.read(url); // sin escalar
        } catch (Exception e) {
        return null;
        }
        });
        }

//CLASE PANEL TABLERO
         
        class PanelTablero extends JPanel {

        static final int COLS   = 9;
        static final int FILAS  = 10;
        static final int CELDA  = 52;
        static final int MARGEN = 50;
        private int filaSeleccionada = -1;
        private int colSeleccionada  = -1;
        private boolean ayudaActiva = false;
        private boolean[][] movimientosActuales = null;

        
        //CARGAR IMAGNE
        
        private Image cargarImagen(String nombre) {
            return JUGAR.this.cargarImagen(nombre);
        }
        


        public PanelTablero() {
        setBackground(TABLERO_FONDO);
        setPreferredSize(new Dimension(
        MARGEN * 2 + CELDA * (COLS - 1),
        MARGEN * 2 + CELDA * (FILAS - 1)
        ));

        
        //MOUSE LISTNEER

        addMouseListener(new MouseAdapter() {



        public void mouseClicked(MouseEvent e) {

        int c = (e.getX() - MARGEN + CELDA / 2) / CELDA;
        int f = (e.getY() - MARGEN + CELDA / 2) / CELDA;

        if (!enTablero(f, c)) return;


        if (filaSeleccionada != -1 && (filaSeleccionada < 0 || filaSeleccionada >= 10 || colSeleccionada < 0 || colSeleccionada >= 9)) {
            filaSeleccionada = -1;
            colSeleccionada = -1;
            repaint();
            return;
        }



        if (filaSeleccionada != -1) {
           Pieza piezaSeleccionada = tablero[filaSeleccionada][colSeleccionada];
           if (piezaSeleccionada == null) {
           filaSeleccionada = -1;
           colSeleccionada = -1;
           repaint();
           return;
         }


         Pieza comida = tablero[f][c];
         boolean[][] moves = piezaSeleccionada.getMoveValido(tablero);

         if (moves == null) {
            filaSeleccionada = -1;
            colSeleccionada = -1;
            repaint();
            return;
           }


         


        if (moves[f][c]) {                     
        Pieza origenSim = tablero[filaSeleccionada][colSeleccionada];
        Pieza destinoSim = tablero[f][c];

        tablero[f][c] = origenSim;
        tablero[filaSeleccionada][colSeleccionada] = null;

        // Verificar regla
        if (generalesEnfrentados()) {
            tablero[filaSeleccionada][colSeleccionada] = origenSim;
            tablero[f][c] = destinoSim;
            JOptionPane.showMessageDialog(PanelTablero.this, 
                "Movimiento inválido: los generales no pueden quedar enfrentados sin piezas entre ellos");
            filaSeleccionada = -1;
            colSeleccionada = -1;
            repaint();
            return;
        }

        tablero[filaSeleccionada][colSeleccionada] = origenSim;
        tablero[f][c] = destinoSim;

        Pieza comidaLocal = tablero[f][c];
        try {

        Pieza origen = tablero[filaSeleccionada][colSeleccionada];

        tablero[f][c] = origen;
        tablero[filaSeleccionada][colSeleccionada] = null;

        
         if (comidaLocal != null) {
            // Agregar al cementerio según el color
            if (comidaLocal.esRojo()) {
                piezasCapturadasRojas.add(comidaLocal);
            } else {
                piezasCapturadasNegras.add(comidaLocal);
            }
            actualizarCementerio();
         }
                
                
        if (comidaLocal != null && comidaLocal.getTipo() == TipoPieza.GENERAL) {
        String ganador = turnoRojo ? jugador1 : jugador2;
        String perdedor = turnoRojo ? jugador2 : jugador1;

        Player pGanador = loginManager.buscarPlayer(ganador);
        if (pGanador != null) {
            pGanador.setPuntos(pGanador.getPuntos() + 3);
        }


        String log = ganador + " venció a " + perdedor;
        loginManager.guardarPartida(log);

        removeAll();
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(ACENTO, 1),
        BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));

        JLabel lblGanador = new JLabel(ganador + " VENCIÓ A " + perdedor);
        lblGanador.setFont(FUENTE_TITULO);
        lblGanador.setForeground(ACENTO);
        lblGanador.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblPuntos = new JLabel("Has ganado 3 puntos!");
        lblPuntos.setFont(FUENTE_LABEL);
        lblPuntos.setForeground(TEXTO_TENUE);
        lblPuntos.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnOtra = crearBoton("Jugar Otra Partida", BTN_SECUNDARIO, ACENTO);
        JButton btnOk = crearBoton("Volver al Menu", ACENTO, Color.WHITE);

        btnOtra.addActionListener(ev -> {
            turnoRojo = true;
            jugador2 = null;
            piezasCapturadasRojas.clear();
            piezasCapturadasNegras.clear();
            pedirOponente();
        });

        btnOk.addActionListener(ev -> {
         cardLayout.show(cardPanel, "menu");
         cardPanel.revalidate();
         cardPanel.repaint();
        });

        panel.add(lblGanador);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblPuntos);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnOtra);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnOk);

        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
        return;
        }



                    if (tablero[f][c] != null) {
                        tablero[f][c].setFila(f);
                        tablero[f][c].setColumna(c);
                    }


                    turnoRojo = !turnoRojo;
                    if (turnoRojo) {
                        lblTurno.setText("Turno: " + jugador1 + " (Rojas)");
                        lblTurno.setForeground(new Color(200, 80, 80));
                    } else {
                        lblTurno.setText("Turno: " + jugador2 + " (Negras)");
                        lblTurno.setForeground(TEXTO);
                    }
                }catch(Exception ex){
                tablero[filaSeleccionada][colSeleccionada] = tablero[f][c];
                       tablero[f][c] = comidaLocal;
                       JOptionPane.showMessageDialog(PanelTablero.this, "Movimiento inválido");
                   }                


               }
                filaSeleccionada = -1;
                colSeleccionada  = -1;
                movimientosActuales = null;

            } else if (tablero[f][c] != null && tablero[f][c].esRojo() == turnoRojo) {
                filaSeleccionada = f;
                colSeleccionada  = c;
                 if (ayudaActiva) {
            movimientosActuales = tablero[filaSeleccionada][colSeleccionada].getMoveValido(tablero);    }
            }
            repaint();
        }   

        });
        }



        
        //BOTON DE AYUDA ACTIVA


        public void setAyudaActiva(boolean activa) {
        this.ayudaActiva = activa;
        if (activa) {
        if (filaSeleccionada != -1 && colSeleccionada != -1) {
        Pieza p = tablero[filaSeleccionada][colSeleccionada];
        if (p != null) {
            movimientosActuales = p.getMoveValido(tablero);
        }
        }
        } else {
        movimientosActuales = null;
        }
        repaint();
        }
        
        
        //METOODO EN TABLERO

        private boolean enTablero(int f, int c) {
        return f >= 0 && f < 10 && c >= 0 && c < 9;
        }
        
        //PAINT COMPNENTE TABLERO
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            dibujarLineas(g2);
            dibujarRio(g2);
            dibujarPalacios(g2);
            dibujarPiezas(g2);
        }
        
        
        //DIBUJAR LINEAS

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

        
        
        //DIBUJAR RIO
        
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

        
        //DIBUJAR PALACIO
        
        private void dibujarPalacios(Graphics2D g2) {
        g2.setColor(TABLERO_LINEA);
        g2.setStroke(new BasicStroke(2.5f));

        int px1 = MARGEN + 3 * CELDA;
        int py1 = MARGEN;
        int pw  = 2 * CELDA;  // 3 columnas = 2 espacios
        int ph  = 2 * CELDA;  // 3 filas = 2 espacios

        g2.drawRect(px1, py1, pw, ph);
        g2.drawLine(px1,      py1,      px1 + pw, py1 + ph);
        g2.drawLine(px1 + pw, py1,      px1,      py1 + ph);
        g2.drawLine(px1,      py1 + CELDA, px1 + pw, py1 + CELDA);
        g2.drawLine(px1 + CELDA, py1, px1 + CELDA, py1 + ph);

        int px2 = MARGEN + 3 * CELDA;
        int py2 = MARGEN + 7 * CELDA;

        // cuadrado exterior
        g2.drawRect(px2, py2, pw, ph);
        g2.drawLine(px2,      py2,      px2 + pw, py2 + ph);
        g2.drawLine(px2 + pw, py2,      px2,      py2 + ph);
        g2.drawLine(px2,      py2 + CELDA, px2 + pw, py2 + CELDA);
        g2.drawLine(px2 + CELDA, py2, px2 + CELDA, py2 + ph);
        }
        
        //DIBUJAR PIEZAS
        

        private void dibujarPiezas(Graphics2D g2) {
        for (int f = 0; f < 10; f++) {
        for (int c = 0; c < 9; c++) {

        if (ayudaActiva && movimientosActuales != null
        && f < movimientosActuales.length
        && c < movimientosActuales[0].length
        && movimientosActuales[f][c]) {

        int x = MARGEN + c * CELDA;
        int y = MARGEN + f * CELDA;

        g2.setColor(new Color(50,150,255,180));
        g2.fillOval(x-12,y-12,24,24);

        g2.setColor(Color.WHITE);
        g2.fillOval(x-6,y-6,12,12);
        }

        Pieza p = tablero[f][c];
        if (p == null) continue;
        int x = MARGEN + c * CELDA;
        int y = MARGEN + f * CELDA;
        int tam = 48; 

        if (f == filaSeleccionada && c == colSeleccionada) {
            g2.setColor(new Color(255, 255, 0, 120));
            g2.fillOval(x - tam/2 - 3, y - tam/2 - 3, tam + 6, tam + 6);
        }
        Image img = JUGAR.this.cargarImagen(p.getSimbolo());        
        
        if (img != null) {
            g2.drawImage(img, x - 24, y - 24, this); 
        }
        }
        }
        }


        }


 private void actualizarCementerio() {
    panelCementerio.removeAll();
    
    // Título
    JLabel titulo = new JLabel("⚰️ CAPTURADAS ⚰️");
    titulo.setFont(FUENTE_LABEL);
    titulo.setForeground(ACENTO);
    titulo.setAlignmentX(CENTER_ALIGNMENT);
    panelCementerio.add(titulo);
    panelCementerio.add(Box.createVerticalStrut(10));
    
    // Piezas negras capturadas
    if (!piezasCapturadasNegras.isEmpty()) {
        JLabel lblNegras = new JLabel("NEGRAS:");
        lblNegras.setForeground(new Color(200, 80, 80));
        lblNegras.setFont(FUENTE_LABEL);
        lblNegras.setAlignmentX(CENTER_ALIGNMENT);
        panelCementerio.add(lblNegras);
        
        JPanel panelNegras = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelNegras.setBackground(PANEL);
        for (Pieza p : piezasCapturadasNegras) {
Image img = JUGAR.this.cargarImagen(p.getSimbolo());

// esto devuelve Image
            if (img != null) {
                Image imgEscalada = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                JLabel lblImg = new JLabel(new ImageIcon(imgEscalada));
                lblImg.setToolTipText(p.getSimbolo());
                panelNegras.add(lblImg);
            }
        }
        panelCementerio.add(panelNegras);
        panelCementerio.add(Box.createVerticalStrut(10));
    }
    
    // Piezas rojas capturadas
    if (!piezasCapturadasRojas.isEmpty()) {
        JLabel lblRojas = new JLabel("ROJAS:");
        lblRojas.setForeground(new Color(200, 80, 80));
        lblRojas.setFont(FUENTE_LABEL);
        lblRojas.setAlignmentX(CENTER_ALIGNMENT);
        panelCementerio.add(lblRojas);
        
        JPanel panelRojas = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelRojas.setBackground(PANEL);
        for (Pieza p : piezasCapturadasRojas) {
            Image img = cargarImagen(p.getSimbolo());
            if (img != null) {
                Image imgEscalada = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                JLabel lblImg = new JLabel(new ImageIcon(imgEscalada));
                lblImg.setToolTipText(p.getSimbolo());
                panelRojas.add(lblImg);
            }
        }
        panelCementerio.add(panelRojas);
    }
    
    panelCementerio.revalidate();
    panelCementerio.repaint();
}
        
        
        //CREAR BOTON
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
