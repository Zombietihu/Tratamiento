/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReconocimientoDeCuadritos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author cuatito
 */
public class GeneraCuadros {
    BufferedImage cuadros;
    //Falta Hcer Generacion de cuadros y probar 
    public void GeneraCuadros(){
        try{
           cuadros = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
            for( int i = 0; i < 50; i++ ){
                
               for( int j = 0; j < 50; j++ ){
                   Color color = new Color(0, 0, 0);
                   cuadros.setRGB(i, j, color.getRGB());
                
                }
            }
            
            //para cuadro de 10x10
            /*
             for( int i = 10; i < 20; i++ ){
                System.out.println("Entre a 10x10");
               for( int j = 10; j < 20; j++ ){
                   Color color = new Color(255, 255, 255);
                   cuadros.setRGB(i, j, color.getRGB());
                
                }
            }
             */
            for( int i = 25; i < 40; i++ ){
                
               for( int j = 25; j < 40; j++ ){
                   Color color = new Color(255, 255, 255);
                   cuadros.setRGB(i, j, color.getRGB());
                
                }
            } 
            
            
            File file = new File("CuadroPrueba.jpg");
            ImageIO.write(cuadros, "jpg", file);
    
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo binarizar imagen");
        }
    }
}
