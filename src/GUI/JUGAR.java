
package GUI;

import Logic.Login_Manager;
import Logic.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
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
 * @author Dell
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
    static final Color PALACIO_COLOR   = new Color(200, 150, 50, 60);// palacio

    // ══════════════════════════════════════════
    //  FUENTES
    // ══════════════════════════════════════════
    static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  18);
    static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 12);
    static final Font FUENTE_CAMPO  = new Font("SansSerif", Font.PLAIN, 13);
    static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  13);
    static final Font FUENTE_RIO    = new Font("Serif",     Font.BOLD,  14);

    // ══════════════════════════════════════════
    //  ATRIBUTOS
    // ══════════════════════════════════════════
    private Login_Manager loginManager;
    private MENUPRINCIPAL menuPrincipal;
    private String jugador1;
    private String jugador2;
    
    
    public JUGAR(Login_Manager loginManager, MENUPRINCIPAL menuPrincipal){
        this.loginManager=loginManager;
        this.menuPrincipal=menuPrincipal;
        this.jugador1=loginManager.getCurrentUser().getUsername();
        
        setTitle("XIANGQI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(FONDO);
        
        
        
        
    }
    
    
    private void pedirOponente(){
        JDialog dialog = new JDialog(this, "Seleccionar oponente",true);
        dialog.setSize(360,280);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(FONDO);
        dialog.setLayout(new GridBagLayout());
        
        JPanel panel = new JPanel();
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
    
    private void mostrarTablero(){
        setLayout(new BorderLayout());
        
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

        JLabel lblJ2 = new JLabel( jugador2 + "  (Rojas)");
        lblJ2.setFont(FUENTE_TITULO);
        lblJ2.setForeground(new Color(200, 80, 80));

        panelTop.add(lblJ1);
        panelTop.add(lblVs);
        panelTop.add(lblJ2);
        panelTop.add(Box.createHorizontalGlue());

        // Panel inferior — botón retirar y volver
        JPanel panelBot = new JPanel();
        panelBot.setBackground(PANEL);
        panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));
        panelBot.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton btnRetirar = crearBoton("Retirarme", BTN_PELIGRO,    Color.WHITE);
        JButton btnVolver  = crearBoton("Volver",    BTN_SECUNDARIO, ACENTO);

        // Acciones temporales — lógica se conecta después
        btnRetirar.setMaximumSize(new Dimension(150, 38));
        btnVolver.setMaximumSize(new Dimension(150, 38));

        btnRetirar.addActionListener(e -> {
            // lógica de retiro pendiente
        });
        btnVolver.addActionListener(e -> {
            dispose();
        });

        panelBot.add(btnRetirar);
        panelBot.add(Box.createHorizontalStrut(12));
        panelBot.add(btnVolver);

        // Tablero central
        PanelTablero panelTablero = new PanelTablero();

        add(panelTop,      BorderLayout.NORTH);
        add(panelTablero,  BorderLayout.CENTER);
        add(panelBot,      BorderLayout.SOUTH);

        setVisible(true);
    }
    
    
    
    
    
    
}
