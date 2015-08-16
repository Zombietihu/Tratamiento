/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamientodeimagenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author cuatito
 */
public class Binarizar extends JPanel{
    BufferedImage bmp;
    BufferedImage cuadritoB;
    Color colorAuxiliar;
    
     private int calcularMedia(Color color){
        int mediaColor;
        mediaColor=(int)((color.getRed()+color.getGreen()+color.getBlue())/3);
      //  System.out.println(mediaColor);
        return mediaColor;
    }
    
    public Binarizar(){
        System.out.println("Entre a Binarizar");
        try{
            File imagenSeleccionada= new File("/home/cuatito/NetBeansProjects/TratamientoDeImagenes/cuadrito.jpg");
            this.bmp = ImageIO.read(imagenSeleccionada);
           cuadritoB = new BufferedImage(bmp.getWidth(), bmp.getHeight(), BufferedImage.TYPE_INT_RGB);
            for( int i = 0; i < bmp.getWidth(); i++ ){
                
               for( int j = 0; j < bmp.getHeight(); j++ ){
                   colorAuxiliar=new Color(bmp.getRGB(i, j));
                   if ( calcularMedia(colorAuxiliar)<120){
                       Color color = new Color(0, 0, 0);
                       cuadritoB.setRGB(i, j, color.getRGB());
                   }else{
                       Color color = new Color(255, 255, 255);
                       cuadritoB.setRGB(i, j, color.getRGB());
                   }
              
                
            }
        }
             File file = new File("cuadritoB.jpg");
            ImageIO.write(cuadritoB, "jpg", file);
    
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo binarizar imagen");
        }
    }
    public void paint(Graphics g) {
        super.paintComponent(g); //borra el area de dibujo
        Dimension height = getSize();
        ImageIcon Img = new ImageIcon(cuadritoB); 
        g.drawImage(Img.getImage(), 0, 0, height.width, height.height, this);
}
    
}

