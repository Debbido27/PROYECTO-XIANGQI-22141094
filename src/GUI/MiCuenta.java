
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
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
/**
 *
 * @author Dell
 */
public class MiCuenta extends JPanel {
      // ══════════════════════════════════════════
    //  COLORES
    // ══════════════════════════════════════════
    static final Color FONDO          = new Color(18, 18, 24);
    static final Color PANEL          = new Color(28, 28, 38);
    static final Color ACENTO         = new Color(200, 150, 50);
    static final Color TEXTO          = new Color(220, 220, 230);
    static final Color TEXTO_TENUE    = new Color(120, 120, 140);
    static final Color CAMPO_FONDO    = new Color(12, 12, 18);
    static final Color CAMPO_BORDE    = new Color(60, 60, 80);
    static final Color BTN_PRIMARIO   = new Color(200, 150, 50);
    static final Color BTN_SECUNDARIO = new Color(40, 40, 58);
    static final Color BTN_PELIGRO    = new Color(160, 40, 40);

    // ══════════════════════════════════════════
    //  FUENTES
    // ══════════════════════════════════════════
    static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  22);
    static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 12);
    static final Font FUENTE_CAMPO  = new Font("SansSerif", Font.PLAIN, 13);
    static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  13);

    // ══════════════════════════════════════════
    //  ATRIBUTOS
    // ══════════════════════════════════════════
    private Login_Manager loginManager;
    private MENUPRINCIPAL menuPrincipal;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    
   public MiCuenta(Login_Manager loginManager, MENUPRINCIPAL menuPrincipal,
                CardLayout cardLayout, JPanel cardPanel) {
    this.loginManager = loginManager;
    this.menuPrincipal = menuPrincipal;
    this.cardLayout = cardLayout;
    this.cardPanel = cardPanel;
    setBackground(FONDO);
    setLayout(new BorderLayout());
    add(crearPanel(),BorderLayout.CENTER);
}
    
    
    private JPanel crearPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));
        
        JLabel titulo = new JLabel ("Mi cuenta");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel sub = new JLabel (loginManager.getCurrentUser().getUsername());
        sub.setFont(FUENTE_LABEL);
        sub.setForeground(ACENTO);
        sub.setAlignmentX(CENTER_ALIGNMENT);
        
        JSeparator sep = new JSeparator();
        sep.setForeground(CAMPO_BORDE);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE,1));
        
        JButton btnVerInfo     = crearBoton("Ver mi Información", BTN_SECUNDARIO, ACENTO);
        JButton btnCambiarPass = crearBoton("Cambiar Password",   BTN_PRIMARIO,   Color.WHITE);
        JButton btnEliminar    = crearBoton("Eliminar mi Cuenta", BTN_PELIGRO,    Color.WHITE);
        JButton btnVolver      = crearBoton("Volver al Menú",     BTN_SECUNDARIO, TEXTO_TENUE);

        btnVerInfo.addActionListener(e     -> abrirVerInfo());
        btnCambiarPass.addActionListener(e -> abrirCambiarPassword());
        btnEliminar.addActionListener(e    -> abrirEliminarCuenta());
        btnVolver.addActionListener(e -> cardLayout.show(cardPanel, "menu"));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(4));
        panel.add(sub);
        panel.add(Box.createVerticalStrut(20));
        panel.add(sep);
        panel.add(Box.createVerticalStrut(25));
        panel.add(btnVerInfo);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnCambiarPass);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnEliminar);
        panel.add(Box.createVerticalStrut(12));
        panel.add(btnVolver);

        return panel;
        
        
        
        
    }
    
    
    
     private JPanel crearPanelDialog() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));
    panel.setPreferredSize(new Dimension(900,750));
        return panel;
    }
    
    private JDialog crearDialog(String titulo, int ancho, int alto){
        JDialog dialog = new JDialog(menuPrincipal, titulo, true);   
        dialog.setSize(menuPrincipal.getSize());
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(FONDO);
        dialog.setLayout(new BorderLayout());
        return dialog;
    }
    
     private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FUENTE_LABEL);
        lbl.setForeground(TEXTO_TENUE);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        return lbl;
    }

    private JLabel crearLabelInfo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FUENTE_CAMPO);
        lbl.setForeground(TEXTO);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        return lbl;
    }

    private JPasswordField crearCampoPassword() {
        JPasswordField campo = new JPasswordField();
        campo.setFont(FUENTE_CAMPO);
        campo.setForeground(TEXTO);
        campo.setBackground(CAMPO_FONDO);
        campo.setCaretColor(ACENTO);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CAMPO_BORDE),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
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

        Color hover = fondo.brighter();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(fondo); }
        });
        return btn;
    }
     
     
     private void abrirVerInfo(){
         JPanel panel = crearPanelDialog();
         panel.setBackground(PANEL);
         panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));             
         Player p = loginManager.getCurrentUser();
         
         
         
        JLabel titulo = new JLabel("Mi Información");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblUser   = crearLabelInfo("Usuario: " + p.getUsername());
        JLabel lblPuntos = crearLabelInfo("Puntos:  " + p.getPuntos());
        JLabel lblFecha = crearLabelInfo("Miembro desde: "+p.getFechaIngreso());
        JLabel lblActivo = crearLabelInfo("Activo: "+p.isActivo());
        
        JButton btnCerrar = crearBoton("Cerrar", BTN_SECUNDARIO, ACENTO);
        
 btnCerrar.addActionListener(e -> {
        removeAll();
        add(crearPanel(), BorderLayout.CENTER);
        revalidate();
        repaint();
    });
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblUser);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblPuntos);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblFecha);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblActivo);
        panel.add(Box.createVerticalStrut(25));
        panel.add(btnCerrar);

        removeAll(); setLayout(new BorderLayout()); add(panel, BorderLayout.CENTER); revalidate(); repaint();
     }
     
     
      private void abrirCambiarPassword() {
        JPanel panel   = crearPanelDialog();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));        
        
        JLabel titulo = new JLabel("Cambiar Password");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblNuevo = crearLabel("Nuevo password (exactamente 5 caracteres)");
        JPasswordField campoNuevo = crearCampoPassword();

        JLabel lblMensaje = new JLabel(" ");
        lblMensaje.setFont(FUENTE_LABEL);
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnGuardar  = crearBoton("Guardar",   BTN_PRIMARIO,   Color.WHITE);

        btnGuardar.addActionListener(e -> {
            String nuevoPass = new String(campoNuevo.getPassword());
            String username  = loginManager.getCurrentUser().getUsername();
            String resultado = loginManager.modificarDatos(username, nuevoPass);
            if (resultado.startsWith("Datos modificados")) {
                lblMensaje.setForeground(new Color(80, 180, 80));
                lblMensaje.setText("Password cambiado exitosamente.");
            } else {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText(resultado);
            }
        });

        JButton btnCancelar = crearBoton("Cerrar", BTN_SECUNDARIO, ACENTO);
            btnCancelar.addActionListener(e -> {
                removeAll();
                add(crearPanel(), BorderLayout.CENTER);
                revalidate();
                repaint();
            });
            
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblNuevo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campoNuevo);
        panel.add(Box.createVerticalStrut(14));
        panel.add(lblMensaje);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnGuardar);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnCancelar);

        removeAll(); setLayout(new BorderLayout()); add(panel, BorderLayout.CENTER); revalidate(); repaint();
    }
      
        private void abrirEliminarCuenta() {
        JPanel panel   = crearPanelDialog();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));        
        JLabel titulo = new JLabel("Eliminar Cuenta");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(BTN_PELIGRO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel advertencia = new JLabel("Esta acción no se puede deshacer.");
        advertencia.setFont(FUENTE_LABEL);
        advertencia.setForeground(TEXTO_TENUE);
        advertencia.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lblPass = crearLabel("Ingresa tu password para confirmar");
        JPasswordField campoConfirm = crearCampoPassword();

        JLabel lblMensaje = new JLabel(" ");
        lblMensaje.setFont(FUENTE_LABEL);
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnConfirmar = crearBoton("Eliminar mi Cuenta", BTN_PELIGRO,    Color.WHITE);

        btnConfirmar.addActionListener(e -> {
            String passIngresado = new String(campoConfirm.getPassword());
            String passReal      = loginManager.getCurrentUser().getPassword();

            if (!passIngresado.equals(passReal)) {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText("Password incorrecto.");
                return;
            }

            loginManager.eliminarCuenta();
           menuPrincipal.dispose();
            menuPrincipal.volverAlLogin();
        });

        JButton btnCancelar = crearBoton("Cerrar", BTN_SECUNDARIO, ACENTO);
            btnCancelar.addActionListener(e -> {
                removeAll();
                add(crearPanel(), BorderLayout.CENTER);
                revalidate();
                repaint();
            });
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(advertencia);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblPass);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campoConfirm);
        panel.add(Box.createVerticalStrut(14));
        panel.add(lblMensaje);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnConfirmar);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnCancelar);

        removeAll(); setLayout(new BorderLayout()); add(panel, BorderLayout.CENTER); revalidate(); repaint();
    }

}