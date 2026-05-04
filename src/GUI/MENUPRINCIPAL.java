
package GUI;

import Logic.Login_Manager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author Dell
 */
public class MENUPRINCIPAL extends JFrame {
  
 //paleta colores
    static final Color FONDO          = new Color(18, 18, 24);
    static final Color PANEL          = new Color(28, 28, 38);
    static final Color ACENTO         = new Color(200, 150, 50);   // dorado
    static final Color TEXTO          = new Color(220, 220, 230);
    static final Color TEXTO_TENUE    = new Color(120, 120, 140);
    static final Color CAMPO_FONDO    = new Color(12, 12, 18);
    static final Color CAMPO_BORDE    = new Color(60, 60, 80);
    static final Color BTN_PRIMARIO   = new Color(200, 150, 50);   // login
    static final Color BTN_SECUNDARIO = new Color(40, 40, 58);     // crear
    static final Color BTN_SALIR      = new Color(160, 40, 40);    // salir

 
    static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  26);
    static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 12);
    static final Font FUENTE_CAMPO  = new Font("SansSerif", Font.PLAIN, 13);
    static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  13);
   
    private Login_Manager loginManager;
    private Login loginWindow;
    private String usernameActual;
    
public MENUPRINCIPAL (String username, Login_Manager loginManager, Login loginWindow) {
    this.usernameActual=username;
        this.loginManager = loginManager;
    this.loginWindow = loginWindow;
        setTitle("Xiangqi - Menu Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(FONDO);
        setLayout(new GridBagLayout());

        add(crearPanel());
        setVisible(true);
    }
    
    private JPanel crearPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(ACENTO,1),BorderFactory.createEmptyBorder(30,40,30,40)));
        
        JLabel titulo = new JLabel("XIANGQI");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel bienvenido = new JLabel("Bienvenido. "+usernameActual);
        bienvenido.setFont(FUENTE_LABEL);
        bienvenido.setForeground(TEXTO_TENUE);
        bienvenido.setAlignmentX(CENTER_ALIGNMENT);
        
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(60,60,80));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE,1));
        
        JButton btnJugar    = crearBoton("Jugar Xiangqi", BTN_PRIMARIO,   Color.WHITE);
        JButton btnCuenta   = crearBoton("Mi Cuenta",     BTN_SECUNDARIO, ACENTO);
        JButton btnReportes = crearBoton("Reportes",      BTN_SECUNDARIO, ACENTO);
        JButton btnLogout   = crearBoton("Log Out",       BTN_SALIR,      Color.WHITE);

        // ── Acciones temporales (lógica se conecta después) ──
        btnJugar.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Jugar — lógica pendiente"));
        btnCuenta.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Mi Cuenta — lógica pendiente"));
        btnReportes.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Reportes — lógica pendiente"));
        btnLogout.addActionListener(e -> {
        loginManager.logout();
        dispose();
        loginWindow.setVisible(true);
    });

        // ── Armar panel ──
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(bienvenido);
        panel.add(Box.createVerticalStrut(20));
        panel.add(sep);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnJugar);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnCuenta);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnReportes);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnLogout);

        return panel;
    }
        
    

 private JButton crearBoton(String texto, Color fondo, Color colorTexto) {
        JButton btn = new JButton(texto);
        btn.setFont(FUENTE_BOTON);
        btn.setForeground(colorTexto);
        btn.setBackground(fondo);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setAlignmentX(LEFT_ALIGNMENT);

        Color hover = fondo.brighter();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(fondo); }
        });
        return btn;
    }
}
