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
    JLabel info;
    JLabel name;
    JLabel type;
    JLabel peso;
    JLabel size;
    JLabel path;
    JLabel fecha;
    JLabel nameT;
    JLabel typeT;
    JLabel pesoT;
    JLabel sizeT;
    JLabel pathT;
    JLabel fechaT;
    
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
    
    Imagen cuadro;
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
          
       info = new JLabel("Informacion de la Imagen");
       info.setBounds(20, 140, 200, 20);
       name = new JLabel("Nombre: ");
       name.setBounds(20, 170, 100, 20);
       nameT = new JLabel();
       nameT.setBounds(120, 170, 100, 20);
       type = new JLabel("Tipo: ");
       type.setBounds(20, 200, 100, 20);
       typeT = new JLabel();
       typeT.setBounds(120, 200, 100, 20);
       size = new JLabel("Tamaño: ");
       size.setBounds(20, 230, 100, 20);
       sizeT = new JLabel();
       sizeT.setBounds(120, 230, 100, 20);
       peso = new JLabel("Peso: ");
       peso.setBounds(20, 260, 100, 20);
       pesoT = new JLabel();
       pesoT.setBounds(120, 260, 100, 20);
       path = new JLabel("Ruta: ");
       path.setBounds(20, 290, 100, 20);
       pathT = new JLabel();
       pathT.setBounds(120, 290, 400, 20);
       fecha = new JLabel("Modificacion:");
       fecha.setBounds(20, 320, 100, 20);
       fechaT = new JLabel();
       fechaT.setBounds(120, 320, 300, 20);
       
       d_leftPanel.add(ancho);
       d_leftPanel.add(alto);
       d_leftPanel.add(anchoText);
       d_leftPanel.add(altoText);
       d_leftPanel.add(nuevoCuadrado);
       d_leftPanel.add(crearCuadrado);
       d_leftPanel.add(info);
       d_leftPanel.add(name);
       d_leftPanel.add(type);
       d_leftPanel.add(size);
       d_leftPanel.add(peso);
       d_leftPanel.add(path);
       d_leftPanel.add(fecha);
       d_leftPanel.add(nameT);
       d_leftPanel.add(typeT);
       d_leftPanel.add(sizeT);
       d_leftPanel.add(pesoT);
       d_leftPanel.add(pathT);
       d_leftPanel.add(fechaT);
       
    }
    public void initRightPanel(){
       d_rightPanel = new JPanel();
       d_rightPanel.setLayout(null);
       d_rightPanel.setBackground(java.awt.Color.white);
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
            crearCuadrado.disable();
            nuevoCuadrado.enable(true);
            cuadro = new Imagen(j, i);
            d_rightPanel.removeAll();
            d_rightPanel.add(cuadro);
            d_rightPanel.repaint();
            datosImagen();
            
           
        }else{
            JOptionPane.showMessageDialog(null, "Error, solo se aceptan numeros enteros");
        }
    }
    
    public void datosImagen(){
        nameT.setText(cuadro.getNombre());
        sizeT.setText(cuadro.getAncho() +"x" +cuadro.getAlto());
        
        pesoT.setText(cuadro.getPeso() + " kB");
        pathT.setText(cuadro.getRuta());
        fechaT.setText(cuadro.getDat() + "");      
        
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
