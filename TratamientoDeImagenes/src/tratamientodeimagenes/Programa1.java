/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamientodeimagenes;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author cuatito
 */
public class Programa1 extends JFrame {
    
    JLabel ancho;
    JLabel alto;
    JLabel resultado;
    
    JTextArea anchoText;
    JTextArea altoText;
    
    JButton crearCuadrado;
    JButton nuevoCuadrado;
    
    JPanel d_leftPanel;
    JPanel d_rightPanel;
    
    boolean validacion;
    
    String al;
    String an;
    
    int i;
    int j;
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
        pane.setDividerLocation(250);       
        pane.setEnabled(true);
        pane.setOneTouchExpandable(true);
        add(pane);
    }
    public void initLeftPanel(){
       d_leftPanel = new JPanel();
       d_leftPanel.setLayout(null);
       d_leftPanel.setBackground(java.awt.Color.gray);
       ancho = new JLabel("Ancho: ");
       ancho.setBounds(20, 20, 100, 20 );
       anchoText = new JTextArea();
       anchoText.setBounds(100, 20, 50, 20);
       alto = new JLabel("Alto: ");
       alto.setBounds(20, 50, 100, 20);
       altoText = new JTextArea();
       altoText.setBounds(100, 50, 50, 20);
       nuevoCuadrado = new JButton("Nuevo");
       nuevoCuadrado.setBounds(20, 80, 100, 20);
       nuevoCuadrado.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               nuevo();
            }
        });
       crearCuadrado = new JButton("Crear");
       crearCuadrado.setBounds(130, 80, 100, 20);
       crearCuadrado.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               crear();
            }
        });
       d_leftPanel.add(ancho);
       d_leftPanel.add(alto);
       d_leftPanel.add(anchoText);
       d_leftPanel.add(altoText);
       d_leftPanel.add(nuevoCuadrado);
       d_leftPanel.add(crearCuadrado);
    }
    public void initRightPanel(){
       d_rightPanel = new JPanel();
       d_rightPanel.setLayout(null);
       d_rightPanel.setBackground(java.awt.Color.white);
       resultado = new JLabel();
       d_rightPanel.add(resultado);
    }
    public void nuevo(){
        anchoText.enable(true);
        altoText.enable(true);
        crearCuadrado.enable(true);
        nuevoCuadrado.enable(false);
    }
    public void crear(){
        al = altoText.getText();
        an = anchoText.getText();
        validacion = isNumeric(al, an);
        
        if ( validacion == true){
            anchoText.enable(false);
            altoText.enable(false);
            crearCuadrado.enable(false);
            nuevoCuadrado.enable(true);
            System.out.println(i +"+"+ j);
            Imagen cuadro = new Imagen(j, i);
            d_rightPanel.removeAll();
            d_rightPanel.add(cuadro);
            d_rightPanel.repaint();
           
        }else{
            JOptionPane.showMessageDialog(null, "Error, solo se aceptan numeros enteros");
        }
    }
    private  boolean isNumeric(String cadena1, String cadena2){
	try {
		i= Integer.parseInt(cadena1);
                j= Integer.parseInt(cadena2);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}
}
