/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codificacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;
import javax.swing.JScrollPane;

/**
 *
 * @author cuatito
 */
public class MuestraCodigo extends JFrame {
     JPanel panel;
      JTextArea consola= new JTextArea();
    public MuestraCodigo(){
        setTitle("Codificacion");
        setSize(600, 400);
        init();
       
        add(panel);
        setVisible(true);
       
    }
    
    public void init(){
        panel = new JPanel();
        consola.setFont(new Font("Monospaced",Font.PLAIN,15));
        consola.setBackground(Color.BLACK);
        consola.setForeground(Color.LIGHT_GRAY);
        JScrollPane sc1 = new JScrollPane(consola); 
        sc1.setBounds(new Rectangle(20, 130, 600, 350));
        panel.add(sc1);
        setConsola();
        
    }
    public void setConsola(){
        File archivo= new File("/home/cuatito//NetBeansProjects/TratamientoDeImagenes/codigo.txt");
             try { 
          BufferedReader in = new BufferedReader (new FileReader(archivo)); 
          String linea = ""; 
          linea = in.readLine(); 

          while (linea != null) { 
               consola.append(linea + "\n"); 
               linea = in.readLine(); 
          } 
     } catch (Exception e) { 
          consola.append ("No se encontro el archivo..."); 
     } 

        
    }
}
