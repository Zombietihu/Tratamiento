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
        setSize(500,200);
        setTitle("Identifica Cuadritos");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
    public void init(){
        panel = new JPanel();
        carga = new JButton("Selecciona una Imagen");
        carga.setBounds(20, 80,150,20);
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
        //panel.add(analiza);
        //panel.add(genera);
        panel.add(carga);
        add(panel);
    }
   
    public void cargar() throws IOException{
        CargarImagen c = new CargarImagen();
    }
    
}
