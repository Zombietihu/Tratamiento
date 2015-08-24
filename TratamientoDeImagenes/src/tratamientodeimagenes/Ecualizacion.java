/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamientodeimagenes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author cuatito
 */
public class Ecualizacion extends JFrame{
    
    int nk[][]=new int[1][256];
    double prx[][]=new double [1][256];
    int sk[][] = new int [1][256];
    
     BufferedImage cuadritoE;
     BufferedImage imagen;
     
    JPanel d_leftPanel;
    JPanel d_rightPanel;
    JPanel histograma ;
    JPanel normal;
    JPanel ecualizado;
     
    private int calcularMedia(Color color){      
        int mediaColor;
        mediaColor=(int)((color.getRed()+color.getGreen()+color.getBlue())/3);
      //  System.out.println(mediaColor);
        return mediaColor;
    }
    
    public  Ecualizacion(){
        setSize(1000,600);
        setTitle("compara");
        setVisible(true);
        Color colorAuxiliar;
        try{
            File imagenSeleccionada= new File("/home/cuatito/NetBeansProjects/TratamientoDeImagenes/cuadrito.jpg");
            this.imagen = ImageIO.read(imagenSeleccionada);
            cuadritoE = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
        //se llena nk
            for( int i = 0; i < imagen.getWidth(); i++ ){
                for( int j = 0; j < imagen.getHeight(); j++ ){       
                    //Obtenemos color del píxel actual
                    colorAuxiliar=new Color(imagen.getRGB(i, j));
                    nk[0][calcularMedia(colorAuxiliar)]+=1;
                }
            }
            // se sca probabilidad
            int nm = imagen.getWidth()*imagen.getHeight();
            System.out.println(nm);
            int total=0;
            for( int i = 0; i < 256; i++ ){
              
               total =nk[0][i]+total;
                
            }
            System.out.println(total);
            for( int i = 0; i < 256; i++ ){
                prx[0][i]=(double)nk[0][i]/nm;
                
            }
            //se saca suma acumulada y se redondea 
            double pxL=0;
            for( int i = 0; i < 256; i++ ){
                pxL = (255*prx[0][i])+pxL;
                sk[0][i]=(int) pxL;
                System.out.println(sk[0][i]);
                
            }
            //se calcula la nueva imagen
            for( int i = 0; i < imagen.getWidth(); i++ ){
                for( int j = 0; j < imagen.getHeight(); j++ ){       
                    colorAuxiliar=new Color(imagen.getRGB(i, j));
                    Color color = new Color(sk[0][calcularMedia(colorAuxiliar)],sk[0][calcularMedia(colorAuxiliar)],sk[0][calcularMedia(colorAuxiliar)]);
                    cuadritoE.setRGB(i,j,color.getRGB());
                }
            }
            //se guarda imagen
            File file = new File("cuadritoE.jpg");
            ImageIO.write(cuadritoE, "jpg", file);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo ecualizar la imagen");
        }
        init();
    }
    public void init(){
    }
}
