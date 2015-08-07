/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamientodeimagenes;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author cuatito
 */
public class Programa1 extends JFrame {
    
    JLabel ancho;
    JLabel alto;
    
    JTextArea anchoText;
    JTextArea altoText;
    
    JButton crearCuadro;
    
    JPanel d_leftPanel;
    JPanel d_rightPanel;
    
    public Programa1(){
        inicializar();
        this.setSize(1024, 760);
        this.setTitle("Vil Cuadrito");
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
    public void inicializar(){
        JSplitPane pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        initLeftPanel();
        pane.setLeftComponent(d_leftPanel);
        initRightPanel();
        pane.setRightComponent(d_rightPanel);       
        pane.setContinuousLayout(true);
        pane.setDividerLocation(550);       
        pane.setEnabled(true);
        pane.setOneTouchExpandable(true);
        add(pane);
    }
    public void initLeftPanel(){
       d_leftPanel = new JPanel();
       d_leftPanel.setLayout(null);
       d_leftPanel.setBackground(java.awt.Color.black);
    }
    public void initRightPanel(){
       d_rightPanel = new JPanel();
       d_rightPanel.setLayout(null);
       d_rightPanel.setBackground(java.awt.Color.white);
    }
}
