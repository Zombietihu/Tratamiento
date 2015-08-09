/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamientodeimagenes;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author cuatito
 */
public class Imagen extends JPanel{
      BufferedImage bmp=null;
      String nombre, ruta, alto, ancho;
      long peso;
      
    public Imagen(int alto, int ancho){
        this.setSize(alto,ancho );
        this.setVisible( true );
        int valor;
        BufferedImage cuadrito = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
       
        
        for(int i = 0; i< alto; i++){
            for(int j = 0; j< ancho; j++){
                valor = (int)(Math.random()*255);
                Color color = new Color(valor, valor, valor);
                cuadrito.setRGB(i, j, color.getRGB());
            }
        }
        try{
            File file = new File("cuadrito.jpg");
            ImageIO.write(cuadrito, "jpg", file);
            abrirImagen();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al gurdar la imagen");
            
        }
    }
    
    public void abrirImagen(){
        
        try {
                
                File imagenSeleccionada= new File("/home/cuatito/NetBeansProjects/TratamientoDeImagenes/cuadrito.jpg");
                System.out.println(imagenSeleccionada.getPath());
                System.out.println(imagenSeleccionada.getName());
                System.out.println(imagenSeleccionada.length());
               
                this.bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al abrir la imagen");
            }
       
    }
    
    public void paint(Graphics g) {
        super.paintComponent(g); //borra el area de dibujo
        Dimension height = getSize();
        ImageIcon Img = new ImageIcon(bmp); 
        g.drawImage(Img.getImage(), 0, 0, height.width, height.height, this);
}
    
    
    
}
