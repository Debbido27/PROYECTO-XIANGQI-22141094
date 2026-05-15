
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
import java.text.SimpleDateFormat;

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
        static final Font FUENTE_TITULO = new Font("Serif",     Font.BOLD,  28);
        static final Font FUENTE_LABEL  = new Font("SansSerif", Font.PLAIN, 16);
        static final Font FUENTE_CAMPO  = new Font("SansSerif", Font.PLAIN, 16);
        static final Font FUENTE_BOTON  = new Font("SansSerif", Font.BOLD,  15);

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
            btnVerInfo.setAlignmentX(CENTER_ALIGNMENT);
            JButton btnCambiarPass = crearBoton("Cambiar Password",   BTN_PRIMARIO,   Color.WHITE);
            btnCambiarPass.setAlignmentX(CENTER_ALIGNMENT);
            JButton btnEliminar    = crearBoton("Eliminar mi Cuenta", BTN_PELIGRO,    Color.WHITE);
            btnEliminar.setAlignmentX(CENTER_ALIGNMENT);
            JButton btnVolver      = crearBoton("Volver al Menú",     BTN_SECUNDARIO, TEXTO_TENUE);
            btnVolver.setAlignmentX(CENTER_ALIGNMENT);

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
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         String fechaBonita = formato.format(p.getFechaIngreso());


            JLabel titulo = new JLabel("Mi Información");
            titulo.setFont(FUENTE_TITULO);
            titulo.setForeground(ACENTO);
            titulo.setAlignmentX(CENTER_ALIGNMENT);

            JLabel lblUser   = crearLabelInfo("Usuario: " + p.getUsername());
            lblUser.setAlignmentX(CENTER_ALIGNMENT);
            JLabel lblPuntos = crearLabelInfo("Puntos:  " + p.getPuntos());
            lblPuntos.setAlignmentX(CENTER_ALIGNMENT);
            JLabel lblFecha = crearLabelInfo("Miembro desde: "+fechaBonita);
            lblFecha.setAlignmentX(CENTER_ALIGNMENT);
            JLabel lblActivo = crearLabelInfo("Activo: "+p.isActivo());
            lblActivo.setAlignmentX(CENTER_ALIGNMENT);
            JButton btnCerrar = crearBoton("Cerrar", BTN_SECUNDARIO, ACENTO);
            btnCerrar.setAlignmentX(CENTER_ALIGNMENT);

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


        JPanel panel = crearPanelDialog();
        panel.setBackground(PANEL);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACENTO, 1),
            BorderFactory.createEmptyBorder(40, 80, 40, 80)
        ));

        JLabel titulo = new JLabel("Cambiar Password");
        titulo.setFont(FUENTE_TITULO);
        titulo.setForeground(ACENTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        // Panel para validar contraseña actual
        JLabel lblActual = crearLabel("Ingrese su password actual");
        lblActual.setAlignmentX(CENTER_ALIGNMENT);
        JPasswordField campoActual = crearCampoPassword();
        campoActual.setAlignmentX(CENTER_ALIGNMENT);
        JLabel lblMensaje = new JLabel(" ");
        lblMensaje.setFont(FUENTE_LABEL);
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);
        JButton btnValidar = crearBoton("Validar", BTN_PRIMARIO, Color.WHITE);
        btnValidar.setAlignmentX(CENTER_ALIGNMENT);

        // Panel para nueva contraseña (inicialmente oculto)
        JLabel lblNuevo = crearLabel("Nuevo password (exactamente 5 caracteres)");
        lblNuevo.setAlignmentX(CENTER_ALIGNMENT);
        lblNuevo.setVisible(false);
        JPasswordField campoNuevo = crearCampoPassword();
        campoNuevo.setAlignmentX(CENTER_ALIGNMENT);
        campoNuevo.setVisible(false);
        JLabel lblConfirmar = crearLabel("Confirmar nuevo password");
        lblConfirmar.setAlignmentX(CENTER_ALIGNMENT);
        lblConfirmar.setVisible(false);
        JPasswordField campoConfirmar = crearCampoPassword();
        campoConfirmar.setAlignmentX(CENTER_ALIGNMENT);
        campoConfirmar.setVisible(false);
        JButton btnGuardar = crearBoton("Guardar", BTN_PRIMARIO, Color.WHITE);
        btnGuardar.setAlignmentX(CENTER_ALIGNMENT);
        btnGuardar.setVisible(false);

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblActual);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campoActual);
        panel.add(Box.createVerticalStrut(14));
        panel.add(lblMensaje);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnValidar);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblNuevo);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campoNuevo);
        panel.add(Box.createVerticalStrut(8));
        panel.add(lblConfirmar);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campoConfirmar);
        panel.add(Box.createVerticalStrut(14));
        panel.add(btnGuardar);

        btnValidar.addActionListener(e -> {
            String passActual = new String(campoActual.getPassword());
            String passReal = loginManager.getCurrentUser().getPassword();

            if (!passActual.equals(passReal)) {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText("Password incorrecto");
            } else {
                lblMensaje.setForeground(new Color(80, 180, 80));
                lblMensaje.setText("Validado correctamente. Ingrese nueva contraseña");
                // Mostrar campos de nueva contraseña
                lblNuevo.setVisible(true);
                campoNuevo.setVisible(true);
                lblConfirmar.setVisible(true);
                campoConfirmar.setVisible(true);
                btnGuardar.setVisible(true);
                btnValidar.setEnabled(false);
                campoActual.setEnabled(false);
            }
        });

        btnGuardar.addActionListener(e -> {
            String nuevoPass = new String(campoNuevo.getPassword());
            String confirmarPass = new String(campoConfirmar.getPassword());

            if (nuevoPass.length() != 5) {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText("Error: La contraseña debe tener 5 caracteres");
                return;
            }

            if (!nuevoPass.equals(confirmarPass)) {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText("Error: Las contraseñas no coinciden");
                return;
            }

            String username = loginManager.getCurrentUser().getUsername();
            String resultado = loginManager.modificarDatos(username, nuevoPass);

            if (resultado.startsWith("Datos modificados")) {
                lblMensaje.setForeground(new Color(80, 180, 80));
                lblMensaje.setText("Password cambiado exitosamente.");
                btnGuardar.setEnabled(false);
            } else {
                lblMensaje.setForeground(new Color(200, 60, 60));
                lblMensaje.setText(resultado);
            }
        });

        JButton btnCancelar = crearBoton("Cerrar", BTN_SECUNDARIO, ACENTO);
        btnCancelar.setAlignmentX(CENTER_ALIGNMENT);
        btnCancelar.addActionListener(e -> {
            removeAll();
            add(crearPanel(), BorderLayout.CENTER);
            revalidate();
            repaint();
        });
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnCancelar);

        removeAll();
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
        String passActual = new String(campoActual.getPassword());
    String passReal = loginManager.getCurrentUser().getPassword();


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

            JLabel advertencia = new JLabel("ESTA ACCION NO SE PUEDE DESHACER.");
            advertencia.setFont(FUENTE_LABEL);
            advertencia.setForeground(TEXTO_TENUE);
            advertencia.setAlignmentX(CENTER_ALIGNMENT);

            JLabel lblPass = crearLabel("Ingresa tu password para confirmar");
            lblPass.setAlignmentX(CENTER_ALIGNMENT);
            JPasswordField campoConfirm = crearCampoPassword();
            campoConfirm.setAlignmentX(CENTER_ALIGNMENT);
            JLabel lblMensaje = new JLabel(" ");
            lblMensaje.setFont(FUENTE_LABEL);
            lblMensaje.setAlignmentX(CENTER_ALIGNMENT);

            JButton btnConfirmar = crearBoton("Eliminar mi Cuenta", BTN_PELIGRO,    Color.WHITE);
            btnConfirmar.setAlignmentX(CENTER_ALIGNMENT);
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
            btnCancelar.setAlignmentX(CENTER_ALIGNMENT);
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