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
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author cuatito
 */
public class Comparacion extends JPanel {
     
    BufferedImage bmp2;
    Histograma h = new Histograma();
    public void cargaEcualizada() throws IOException{
        this.setVisible( true );
        File imagenSeleccionada= new File("/home/cuatito/NetBeansProjects/TratamientoDeImagenes/cuadritoE.jpg");
        this.bmp2 = ImageIO.read(imagenSeleccionada);
        this.setSize(bmp2.getWidth(),bmp2.getHeight() );
    }
    public void histogramaImagen(JPanel JPanel_Gris){
         try{  
             //System.out.println("Entre1");
           int[][] histograma =h.histograma((BufferedImage)bmp2);
         
            //extraemos un canal del histograma 
            int[] histogramaCanal=new int[256];
            System.arraycopy(histograma[0], 0, histogramaCanal, 0, histograma[0].length);
            //Dibujamos en el panel
            
            h.crearHistograma(histogramaCanal, JPanel_Gris, Color.gray);
             
        
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    public void paint(Graphics g) {
      
             System.out.println("carge ecualizada");
            super.paintComponent(g); //borra el area de dibujo
            Dimension height = getSize();
            ImageIcon Img = new ImageIcon(bmp2); 
            g.drawImage(Img.getImage(), 0, 0, height.width, height.height, this);
        
    }
    
}
