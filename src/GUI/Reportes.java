
package GUI;

import Logic.Login_Manager;
import Logic.Player;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
/**
 *
 * @author Dell
 */
public class Reportes extends JPanel {
   
    static final Color FONDO          = new Color(18, 18, 24);
    static final Color PANEL          = new Color(28, 28, 38);
    static final Color ACENTO         = new Color(200, 150, 50);
    static final Color TEXTO          = new Color(220, 220, 230);
    static final Color TEXTO_TENUE    = new Color(120, 120, 140);
    static final Color CAMPO_BORDE    = new Color(60, 60, 80);
    static final Color BTN_PRIMARIO   = new Color(200, 150, 50);
    static final Color BTN_SECUNDARIO = new Color(40, 40, 58);

   
    static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  28);
    static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 16);
    static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  16);
    static final Font FUENTE_ITEM   = new Font("SansSerif", Font.PLAIN, 15);
    
   
    private Login_Manager loginManager;
    private MENUPRINCIPAL menuPrincipal;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
    
    public Reportes(Login_Manager loginManager, MENUPRINCIPAL menuPrincipal,
                CardLayout cardLayout, JPanel cardPanel) {
    this.loginManager = loginManager;
    this.menuPrincipal = menuPrincipal;
    this.cardLayout = cardLayout;
    this.cardPanel = cardPanel;
    setBackground(FONDO);
    setLayout(new BorderLayout());
    add(crearPanel(),BorderLayout.CENTER);
}
    
    
    private JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));

        JLabel titulo = new JLabel("Reportes");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setForeground(CAMPO_BORDE);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        JButton btnRanking = crearBoton("Ranking de Jugadores", BTN_PRIMARIO,   Color.WHITE);
        btnRanking.setAlignmentX(CENTER_ALIGNMENT);
        JButton btnLogs    = crearBoton("Mis Últimos Juegos",   BTN_SECUNDARIO, ACENTO);
        btnLogs.setAlignmentX(CENTER_ALIGNMENT);
        JButton btnVolver  = crearBoton("Volver",               BTN_SECUNDARIO, TEXTO_TENUE);
        btnVolver.setAlignmentX(CENTER_ALIGNMENT);
        
        btnRanking.addActionListener(e -> abrirRanking());
        btnLogs.addActionListener(e    -> abrirLogs());
        btnVolver.addActionListener(e -> cardLayout.show(cardPanel, "menu"));

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(sep);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnRanking);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnLogs);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnVolver);

        return panel;
    }
    
    private void abrirRanking(){
         removeAll();
    setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACENTO, 1),
                BorderFactory.createEmptyBorder(40, 80, 40, 80)
            ));
        JLabel titulo = new JLabel("Ranking de Jugadores");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));

        try{
            
        Player[] ranking = loginManager.getRankingJugadores();

        if (ranking.length == 0) {
            
            
            JLabel lblVacio = new JLabel("No hay jugadores registrados.");
            lblVacio.setFont(FUENTE_LABEL);
            lblVacio.setForeground(TEXTO_TENUE);
            lblVacio.setAlignmentX(CENTER_ALIGNMENT);
            panel.add(lblVacio);
        } else {
            for (int i = 0; i < ranking.length; i++) {
                Player p = ranking[i];
                if (p != null) {
                    JLabel fila = new JLabel(
                        (i + 1) + ".  " + p.getUsername() + "  —  " + p.getPuntos() + " pts"
                    );
                    fila.setFont(FUENTE_ITEM);
                    fila.setForeground(i == 0 ? ACENTO : TEXTO);
                    fila.setAlignmentX(CENTER_ALIGNMENT);
                    panel.add(fila);
                    panel.add(Box.createVerticalStrut(8));
                }
            }
        }
        }catch(Exception ex){
            JLabel lblError = new JLabel("Error al cargar el ranking");
            lblError.setForeground(new Color(200,60,60));
            lblError.setAlignmentX(CENTER_ALIGNMENT);
            panel.add(lblError);
        }

        panel.add(Box.createVerticalStrut(10));
        JButton btnCerrar = crearBoton("Volver", BTN_SECUNDARIO, ACENTO);
        btnCerrar.setAlignmentX(CENTER_ALIGNMENT);
        btnCerrar.setMaximumSize(new Dimension(200,45));
    btnCerrar.addActionListener(e -> {
        removeAll();
        add(crearPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    });
    panel.add(btnCerrar);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(BorderFactory.createLineBorder(ACENTO, 1));
        scroll.getViewport().setBackground(PANEL);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();

    }
    
    private void abrirLogs(){
    removeAll();
    setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACENTO, 1),
                BorderFactory.createEmptyBorder(40, 80, 40, 80)
            ));
        JLabel titulo = new JLabel("Mis Últimos Juegos");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(15));

        String[] logs = loginManager.cargarPartidas();

        boolean hayLogs = false;
        for (String log : logs) {
            if (log != null && !log.isEmpty()) {
                JLabel lblLog = new JLabel(log);
                lblLog.setFont(FUENTE_ITEM);
                lblLog.setForeground(TEXTO);
                lblLog.setAlignmentX(LEFT_ALIGNMENT);
                panel.add(lblLog);
                panel.add(Box.createVerticalStrut(8));
                hayLogs = true;
            }
        }

        if (!hayLogs) {
            JLabel lblVacio = new JLabel("No hay juegos registrados.");
            lblVacio.setFont(FUENTE_LABEL);
            lblVacio.setForeground(TEXTO_TENUE);
            lblVacio.setAlignmentX(CENTER_ALIGNMENT);
            panel.add(lblVacio);
        }

        panel.add(Box.createVerticalStrut(10));
        JButton btnCerrar = crearBoton("Volver", BTN_SECUNDARIO, ACENTO);
        btnCerrar.setAlignmentX(CENTER_ALIGNMENT);
        btnCerrar.setMaximumSize(new Dimension(200,45));
    btnCerrar.addActionListener(e -> {
        removeAll();
        add(crearPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    });
    panel.add(btnCerrar);

      

    add(panel, BorderLayout.CENTER);
    revalidate();
    repaint();

    }
    
    //helpers
    


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
