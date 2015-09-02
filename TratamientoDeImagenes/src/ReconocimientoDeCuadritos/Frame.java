/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReconocimientoDeCuadritos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author cuatito
 */
public class Frame extends JFrame{
    JButton carga;
    JButton analiza;
    JButton genera;
    JPanel panel;
    JPanel muestraImagen; 
    Analiza ana = new Analiza();
    BufferedImage imagenRegiones;
    public void Frame(){
        init();
        setSize(500,400);
        setTitle("Identifica Cuadritos");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    public void init(){
        panel = new JPanel();
        analiza  = new JButton("Analizar");
        analiza.setBounds(20,50,150,20);
        analiza.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               analizar();
            }
        });  
        genera = new JButton("genera");
        genera.setBounds(20, 80,150,20);
        genera.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               generar();
            }
        });  
        carga = new JButton("Muestra");
        carga.setBounds(20,110,150,20);
        carga.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    cargar();
                    dispose();
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });  
        panel.add(analiza);
        panel.add(genera);
        panel.add(carga);
        add(panel);
    }
    public void analizar(){
        //ana.Analiza();
    }
    public void cargar() throws IOException{
        CargarImagen c = new CargarImagen();
    }
    public void generar(){}
}
