
package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;


public class Login extends JFrame {
    
    //Paleta de colores
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

    
    private JTextField campoUsuario;
    private JPasswordField camposuauario;
    
    public Login(){
        setTitle("Xiangqi - 22141094");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
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
      BorderFactory.createLineBorder(ACENTO,1),
      BorderFactory.createEmptyBorder(30,40,30,40)
      ));
      
      JLabel titulo = new JLabel("XIANGQI");
      titulo.setFont(FUENTE_TITULO);
      titulo.setForeground(ACENTO);
      titulo.setAlignmentX(CENTER_ALIGNMENT);
      
      JLabel subtitulo = new JLabel ("AJEDREZ CHINO");
      subtitulo.setFont(FUENTE_LABEL);
      subtitulo.setForeground(TEXTO_TENUE);
      subtitulo.setAlignmentX(CENTER_ALIGNMENT);
      
      JSeparator sep = new JSeparator();
      sep.setForeground(CAMPO_BORDE);
      sep.setMaximumSize(new Dimension(Integer.MAX_VALUE,1));
      
      JLabel lblUser = crearLabel("Usuario");
      campoUsuario = crearCampoTexto();
      
      JLabel lblPass = crearLabel("Contrasena (5 CARACTERES)");
      campoPassword = crearCampoPassword;
      
      JButton btnLogin = crearBoton ("Iniciar Sesion",BTN_PRIMARIO, Color.WHITE);
      JButton btnCrear = crearBoton ("Crear Jugador", BTN_SECUNDARIO, ACENTO);
      JButton btnSalir = crearBoton ("Salir", BTN_SALIR, Color.WHITE);
      
      
      return panel;
      
      
    }
    
    private JLabel crearLabel(String texto){
        JLabel lbl = new JLabel (texto);
        lbl.setFont(FUENTE_LABEL);
        lbl.setForeground(TEXTO_TENUE);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        return lbl;
    }
    
    
    private JTextField crearCampoTexto(){
        JTextField campo = new JTextField();
        campo.setFont(FUENTE_CAMPO);
        campo.setForeground(TEXTO);
        campo.setBackground(CAMPO_FONDO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CAMPO_BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
        campo.setAlignmentX(LEFT_ALIGNMENT);
        return campo;
    }
    
    private JPasswordField crearCampoPassword(){
        JPasswordField campo = new JPasswordField();
        campo.setFont(FUENTE_CAMPO);
        campo.setForeground(CAMPO_FONDO);
        campo.setCaretColor(ACENTO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CAMPO_BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
        campo.setAlignmentX(LEFT_ALIGNMENT);
        return campo;
        
    }
    
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

        // Hover
        Color hover = fondo.brighter();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(fondo); }
        });
        return btn;
    }
    
    
    
    
}
