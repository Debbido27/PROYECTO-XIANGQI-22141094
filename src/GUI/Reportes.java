
package GUI;

import Logic.Login_Manager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author Dell
 */
public class Reportes extends JFrame {
   
    static final Color FONDO          = new Color(18, 18, 24);
    static final Color PANEL          = new Color(28, 28, 38);
    static final Color ACENTO         = new Color(200, 150, 50);
    static final Color TEXTO          = new Color(220, 220, 230);
    static final Color TEXTO_TENUE    = new Color(120, 120, 140);
    static final Color CAMPO_BORDE    = new Color(60, 60, 80);
    static final Color BTN_PRIMARIO   = new Color(200, 150, 50);
    static final Color BTN_SECUNDARIO = new Color(40, 40, 58);

   
    static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  22);
    static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 12);
    static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  13);
    static final Font FUENTE_ITEM   = new Font("SansSerif", Font.PLAIN, 13);

   
    private Login_Manager loginManager;
    
    public Reportes(Login_Manager loginManager){
        this.loginManager=loginManager;
        
        setTitle("Reportes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 420);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(FONDO);
        setLayout(new GridBagLayout());

        add(crearPanel());
        setVisible(true);
    }
    
    
    private JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JLabel titulo = new JLabel("Reportes");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setForeground(CAMPO_BORDE);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));

        JButton btnRanking = crearBoton("Ranking de Jugadores", BTN_PRIMARIO,   Color.WHITE);
        JButton btnLogs    = crearBoton("Mis Últimos Juegos",   BTN_SECUNDARIO, ACENTO);
        JButton btnVolver  = crearBoton("Volver",               BTN_SECUNDARIO, TEXTO_TENUE);

        btnRanking.addActionListener(e -> abrirRanking());
        btnLogs.addActionListener(e    -> abrirLogs());
        btnVolver.addActionListener(e  -> dispose());

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

   
}
