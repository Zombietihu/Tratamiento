/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codificacion;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author cuatito
 */
public class rCodificacion {
    int nk[][]=new int[1][256];
    double prx[][]=new double [1][256];
    int sk[][] = new int [1][256];
    rCodificacion( BufferedImage imagen){
        Color colorAuxiliar;
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
           // System.out.println(nm);
            int total=0;
            for( int i = 0; i < 256; i++ ){
              
               total =nk[0][i]+total;
                
            }
           // System.out.println(total);
            for( int i = 0; i < 256; i++ ){
                prx[0][i]=(double)nk[0][i]/nm;
                
            }
            //se saca suma acumulada y se sumando a L
            double L = 0;
            double pxL=0;
            for( int i = 0; i < 256; i++ ){
                pxL = (prx[0][i])+pxL;
                if(i<100){
                    L=pxL*2+L;
                }else{
                    L=pxL*3+L;
                }
                
            }
            System.out.println((int)L);
    
    }
     private int calcularMedia(Color color){      
        int mediaColor;
        mediaColor=(int)((color.getRed()+color.getGreen()+color.getBlue())/3);
      //  System.out.println(mediaColor);
        return mediaColor;
    }
    
}
