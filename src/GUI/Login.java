
package GUI;

import Logic.Login_Manager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    private JTextField mensajeField;
    private Login_Manager manager;
    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private CardLayout cardLayout;
    private JPanel panelPrincipal;
    private JTextField crearUsuarioField;
    private JPasswordField crearPasswordField;
    
    
    public Login(){
        setTitle("Xiangqi - 22141094");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(FONDO);
        setLayout(new BorderLayout());
        manager = new Login_Manager();
        add(crearPanel(),BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel crearPanel() {
    cardLayout = new CardLayout();
    panelPrincipal = new JPanel(cardLayout);
    panelPrincipal.setBackground(PANEL);
    
    // Panel de botones principal
    JPanel panelBotones = new JPanel();
    panelBotones.setBackground(PANEL);
    panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
    panelBotones.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(ACENTO, 1),
        BorderFactory.createEmptyBorder(80, 100, 80, 100)
    ));
    
    JLabel titulo = new JLabel("XIANGQI");
    titulo.setFont(FUENTE_TITULO);
    titulo.setForeground(ACENTO);
    titulo.setAlignmentX(CENTER_ALIGNMENT);
    
    JLabel subtitulo = new JLabel("AJEDREZ CHINO");
    subtitulo.setFont(FUENTE_LABEL);
    subtitulo.setForeground(TEXTO_TENUE);
    subtitulo.setAlignmentX(CENTER_ALIGNMENT);
    
    JSeparator sep = new JSeparator();
    sep.setForeground(CAMPO_BORDE);
    sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    
    JButton btnLogin = crearBoton("Iniciar Sesion", BTN_PRIMARIO, Color.WHITE);
    JButton btnCrear = crearBoton("Crear Jugador", BTN_SECUNDARIO, ACENTO);
    JButton btnSalir = crearBoton("Salir", BTN_SALIR, Color.WHITE);
    
    btnLogin.addActionListener(e -> mostrarPanelLogin());
    btnCrear.addActionListener(e -> mostrarPanelCrear());
    btnSalir.addActionListener(e -> System.exit(0));
    
    panelBotones.add(titulo);
    panelBotones.add(Box.createVerticalStrut(4));
    panelBotones.add(subtitulo);
    panelBotones.add(Box.createVerticalStrut(30));
    panelBotones.add(sep);
    panelBotones.add(Box.createVerticalStrut(40));
    panelBotones.add(btnLogin);
    panelBotones.add(Box.createVerticalStrut(15));
    panelBotones.add(btnCrear);
    panelBotones.add(Box.createVerticalStrut(15));
    panelBotones.add(btnSalir);
    
    panelPrincipal.add(panelBotones, "botones");
    
    return panelPrincipal;
}
    
    
    private void mostrarPanelLogin() {
    JPanel panelLogin = new JPanel();
    panelLogin.setBackground(PANEL);
    panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.Y_AXIS));
    panelLogin.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(ACENTO, 1),
        BorderFactory.createEmptyBorder(40, 80, 40, 80)
    ));
    
    JLabel titulo = new JLabel("INICIAR SESION");
    titulo.setFont(FUENTE_TITULO);
    titulo.setForeground(ACENTO);
    titulo.setAlignmentX(CENTER_ALIGNMENT);
    
    JSeparator sep = new JSeparator();
    sep.setForeground(CAMPO_BORDE);
    sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    
    JLabel lblUser = crearLabel("Usuario");
    campoUsuario = crearCampoTexto();
    
    JLabel lblPass = crearLabel("Contraseña");
    campoPassword = crearCampoPassword();
    
    JTextField mensajeField = new JTextField(" ");
    mensajeField.setEditable(false);
    mensajeField.setFont(FUENTE_LABEL);
    mensajeField.setForeground(PANEL);
    mensajeField.setBackground(PANEL);
    mensajeField.setBorder(BorderFactory.createEmptyBorder()); 
    mensajeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
    mensajeField.setAlignmentX(CENTER_ALIGNMENT);
    
    JButton btnAceptar = crearBoton("Aceptar", BTN_PRIMARIO, Color.WHITE);
    JButton btnVolver = crearBoton("Volver", BTN_SECUNDARIO, ACENTO);
    
    btnAceptar.addActionListener(e -> {
        String user = campoUsuario.getText();
        String pass = new String(campoPassword.getPassword());
        
        if (!manager.usuarioExiste(user)) {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Usuario no existe.");
        } else if (manager.login(user, pass)) {
            setVisible(false);
            new MENUPRINCIPAL(user, manager, this);
        } else {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Contraseña incorrecta.");
        }
    });
    
    btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "botones"));
    
    panelLogin.add(titulo);
    panelLogin.add(Box.createVerticalStrut(20));
    panelLogin.add(sep);
    panelLogin.add(Box.createVerticalStrut(30));
    panelLogin.add(lblUser);
    panelLogin.add(Box.createVerticalStrut(6));
    panelLogin.add(campoUsuario);
    panelLogin.add(Box.createVerticalStrut(15));
    panelLogin.add(lblPass);
    panelLogin.add(Box.createVerticalStrut(6));
    panelLogin.add(campoPassword);
    panelLogin.add(Box.createVerticalStrut(30));
    panelLogin.add(mensajeField);
    panelLogin.add(Box.createVerticalStrut(10));
    panelLogin.add(btnAceptar);
    panelLogin.add(Box.createVerticalStrut(10));
    panelLogin.add(btnVolver);
    
    panelPrincipal.add(panelLogin, "login");
    cardLayout.show(panelPrincipal, "login");
}
    
    
    private void mostrarPanelCrear() {
    JPanel panelCrear = new JPanel();
    panelCrear.setBackground(PANEL);
    panelCrear.setLayout(new BoxLayout(panelCrear, BoxLayout.Y_AXIS));
    panelCrear.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(ACENTO, 1),
        BorderFactory.createEmptyBorder(40, 80, 40, 80)
    ));
    
    JLabel titulo = new JLabel("CREAR JUGADOR");
    titulo.setFont(FUENTE_TITULO);
    titulo.setForeground(ACENTO);
    titulo.setAlignmentX(CENTER_ALIGNMENT);
    
    JSeparator sep = new JSeparator();
    sep.setForeground(CAMPO_BORDE);
    sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
    
    JLabel lblUser = crearLabel("Nuevo Usuario");
    crearUsuarioField = crearCampoTexto();
    
    JLabel lblPass = crearLabel("Contraseña (5 caracteres)");
    crearPasswordField = crearCampoPassword();
    
    JTextField mensajeField = new JTextField(" ");
    mensajeField.setEditable(false);
    mensajeField.setFont(FUENTE_LABEL);
    mensajeField.setForeground(PANEL);
    mensajeField.setBackground(PANEL);
    mensajeField.setBorder(BorderFactory.createEmptyBorder()); 
    mensajeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 26));
    mensajeField.setAlignmentX(CENTER_ALIGNMENT);
    
    JButton btnCrear = crearBoton("Crear", BTN_PRIMARIO, Color.WHITE);
    JButton btnVolver = crearBoton("Volver", BTN_SECUNDARIO, ACENTO);
    
    btnCrear.addActionListener(e -> {
        String user = crearUsuarioField.getText();
        String pass = new String(crearPasswordField.getPassword());
        
        if (manager.usuarioExiste(user)) {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Error: Usuario ya existe.");
        } else if (pass.length() != 5) {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Error: Debe tener 5 caracteres.");
        } else if (!pass.matches(".*[A-Z].*")) {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Error: Debe tener una mayúscula.");
        } else if (!pass.matches(".*[a-z].*")) {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Error: Debe tener una minúscula.");
        } else if (!pass.matches(".*[0-9].*")) {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Error: Debe tener un número.");
        } else if (manager.crearPlayer(user, pass)) {
            mensajeField.setForeground(new Color(80, 180, 80));
            mensajeField.setText("¡Jugador creado exitosamente!");
            crearUsuarioField.setText("");
            crearPasswordField.setText("");
        } else {
            mensajeField.setForeground(new Color(200, 60, 60));
            mensajeField.setText("Error al crear jugador.");
        }
    });
    
    btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "botones"));
    
    panelCrear.add(titulo);
    panelCrear.add(Box.createVerticalStrut(20));
    panelCrear.add(sep);
    panelCrear.add(Box.createVerticalStrut(30));
    panelCrear.add(lblUser);
    panelCrear.add(Box.createVerticalStrut(6));
    panelCrear.add(crearUsuarioField);
    panelCrear.add(Box.createVerticalStrut(15));
    panelCrear.add(lblPass);
    panelCrear.add(Box.createVerticalStrut(6));
    panelCrear.add(crearPasswordField);
    panelCrear.add(Box.createVerticalStrut(30));
    panelCrear.add(mensajeField);
    panelCrear.add(Box.createVerticalStrut(10));
    panelCrear.add(btnCrear);
    panelCrear.add(Box.createVerticalStrut(10));
    panelCrear.add(btnVolver);
    
    panelPrincipal.add(panelCrear, "crear");
    cardLayout.show(panelPrincipal, "crear");
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
        campo.setForeground(FONDO);
        campo.setBackground(TEXTO);
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
