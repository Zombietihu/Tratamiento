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
      BufferedImage cuadrito;
      private String nombre;
      private String ruta;
      private int alto;
      private int ancho;
      private long peso;
      private Date dat;
      Histograma h = new Histograma();
    public Imagen(int alto, int ancho){
        this.setSize(alto,ancho );
        this.setVisible( true );
        int valor;
        cuadrito = new BufferedImage(alto, ancho, BufferedImage.TYPE_INT_RGB);
       
        
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
                this.ruta = imagenSeleccionada.getPath();
                this.nombre=imagenSeleccionada.getName();
                this.peso = imagenSeleccionada.length();
                dat = new Date(imagenSeleccionada.lastModified());
                this.bmp = ImageIO.read(imagenSeleccionada);
                this.alto=bmp.getHeight();
                this.ancho=bmp.getWidth();
                //System.out.println(bmp.);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al abrir la imagen");
            }
       
    }
   
    public void histogramaImagen(JPanel JPanel_Gris){
         try{  
             //System.out.println("Entre1");
           int[][] histograma =h.histograma((BufferedImage)cuadrito);
         
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
        super.paintComponent(g); //borra el area de dibujo
        Dimension height = getSize();
        ImageIcon Img = new ImageIcon(bmp); 
        g.drawImage(Img.getImage(), 0, 0, height.width, height.height, this);
}

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return (float) (peso/1024);
    }
    
    public Date getDat(){
        return dat;
    }
    
}
