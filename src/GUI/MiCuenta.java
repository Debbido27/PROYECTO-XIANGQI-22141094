/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Logic.Login_Manager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author Dell
 */
public class MiCuenta extends JFrame {
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
}
