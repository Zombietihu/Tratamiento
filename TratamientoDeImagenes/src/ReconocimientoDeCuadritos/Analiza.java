/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReconocimientoDeCuadritos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author cuatito
 */
public class Analiza {
    File path ;
    BufferedImage bmp;
    BufferedImage cuadritoB;
    BufferedImage imagenAnalizada;
    Color colorAuxiliar;
    String imagenEspejo[][];
    public void Analiza(BufferedImage imagen) throws IOException{
        bmp = imagen;
        binariza();
        File imagenSeleccionada= new File("/home/cuatito/NetBeansProjects/TratamientoDeImagenes/Regiones.jpg");
        imagenAnalizada= ImageIO.read(imagenSeleccionada);
        etiqueta();
    }
    //Binarizamos en caso de que no este binarizada
    public void binariza(){
        try{
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
            File file = new File("Regiones.jpg");
            ImageIO.write(cuadritoB, "jpg", file);
    
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo binarizar imagen");
        }
    }
     private int calcularMedia(Color color){
        int mediaColor;
        mediaColor=(int)((color.getRed()+color.getGreen()+color.getBlue())/3);
      //  System.out.println(mediaColor);
        return mediaColor;
    }
     public void etiqueta(){
        /*
            Lo que aremos sera llnear una 
            matriz de tipo String con la inicial del fondo
            
        */
        Color colorAuxiliar;
        imagenEspejo=new String[bmp.getWidth()][ bmp.getHeight()];
        for( int i = 0; i < bmp.getWidth(); i++ ){
            for( int j = 0; j < bmp.getHeight(); j++ ){
               //Se inician las reglas 
               //Obtenemos color del píxel actual
               colorAuxiliar=new Color(imagenAnalizada.getRGB(i, j));
               if(imagenAnalizada.getRGB(i, j)==0){
                   imagenEspejo[i][j]="N";
               }else{
                   if((imagenAnalizada.getRGB(i, j)==1)&&(imagenAnalizada.getRGB(i-1, j)==0)&&(imagenAnalizada.getRGB(i, j-1)==0)){
                       imagenEspejo[i][j]="B";
                   }else{
                       if((imagenAnalizada.getRGB(i, j)==1)&&(imagenAnalizada.getRGB(i-1, j)!=0)&&(imagenAnalizada.getRGB(i, j-1)==0)){
                           imagenEspejo[i][j]=imagenEspejo[i-1][j];
                       }else{
                           if((imagenAnalizada.getRGB(i, j)==1)&&(imagenAnalizada.getRGB(i-1, j)==0)&&(imagenAnalizada.getRGB(i, j-1)!=0)){
                               imagenEspejo[i][j]=imagenEspejo[i][j-1];
                           }else{
                               if((imagenAnalizada.getRGB(i, j)==1)&&(imagenAnalizada.getRGB(i-1, j)!=0)&&(imagenAnalizada.getRGB(i, j-1)!=0)&&(imagenAnalizada.getRGB(i-1, j)==imagenAnalizada.getRGB(i, j-1))){
                                   imagenEspejo[i][j]=imagenEspejo[i-1][j];
                               }else{
                                   if((imagenAnalizada.getRGB(i, j)==1)&&(imagenAnalizada.getRGB(i-1, j)!=0)&&(imagenAnalizada.getRGB(i, j-1)!=0)&&(imagenAnalizada.getRGB(i-1, j)!=imagenAnalizada.getRGB(i, j-1))){
                                       imagenEspejo[i][j]=imagenEspejo[i-1][j];
                                   }
                               }
                           }
                       }
                   }
               }  
            }          
        }
     }
}
