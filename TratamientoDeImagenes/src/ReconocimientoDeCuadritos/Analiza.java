/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReconocimientoDeCuadritos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    int regionesExistentes = 0;
    public void Analiza(BufferedImage imagen) throws IOException{
        bmp = imagen;
        binariza();
        /*Esto se cambia por la path donde se guarda la imagen
        En caso se linux es algo semejante
        En caso de windows es desde C:\\...
        */
        File imagenSeleccionada= new File("/home/cuatito/NetBeansProjects/TratamientoDeImagenes/Regiones.jpg");
        imagenAnalizada= ImageIO.read(imagenSeleccionada);
        etiqueta();
        empiezaBusqueda();
        llenaArchivo();
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
        return mediaColor;
    }
     public void empiezaBusqueda(){
         for(int i=0; i < imagenEspejo.length; i++){
             for(int j=0; j<imagenEspejo[i].length; j++){
                 if(imagenEspejo[i][j].equals("B")){
                     obtenCoordenadas(i,j);
                     break;
                 }
                 
             }
         }
         System.out.println("las Regiones existentes son: "+regionesExistentes);
     }
     public void obtenCoordenadas(int i,int j){
         int x0,x1=0,y0,y1=0;
         int largo, ancho;
         x0=i;
         y0=j;
         //recorreFiulas
         boolean blanco = true;
        while(blanco){
            if (imagenEspejo[x0][y0].equals("B")){
                x0++;
            }else{
                x1=x0;
                blanco = false;
            }
        }
        //recorreColumnas
        x0=i;
        blanco = true;
        while(blanco){
            if (imagenEspejo[x0][y0].equals("B")){
                y0++;
            }else{
                y1=y0;
                blanco = false;
            }
        }
        y0=j;
        //obten distancias
        largo = x1-x0;
        ancho = y1-y0;
        evaluaRegion(x0, y0, x1, y1);
     }
     public void evaluaRegion(int x, int y, int ancho, int largo){
         /*Es la seccion de evaluacion de la region, se compara que toda la region
         se encuentre la etiqueta blanca  si al finalizar todo fue con etiquetado blanco
         aunmenta regionesExistentes.
         Durante el Recorrido se cambia la etiquta blanco a recorrido (R) para 
         no volver a recorrer la region.
         */
         boolean a = true;
         for(int i = x; i<ancho; i++){
             for(int j = y; j<largo;j++){
                 if(imagenEspejo[i][j].equals("B")){
                     imagenEspejo[i][j]="R";
                 }else{
                     imagenEspejo[i][j]="R";
                     a = false;
                 }
             }
         }
         if (a){
             regionesExistentes++;
         }
         
     }
     public void llenaArchivo(){
         /*Esta clase es solo para ver la matriz como queda llenada... 
         el archivo se encuentra al abrir la carpeta contenedora*/
         FileWriter fichero ;
         PrintWriter pw;
         try{fichero = new FileWriter("archivo.txt"); pw = new PrintWriter(fichero);
         for (int x=0; x < imagenEspejo.length; x++) {
            pw.write("\n");
            pw.write("|");
            for (int y=0; y < imagenEspejo[x].length; y++) {
                pw.write(imagenEspejo[x][y]);
            }
          pw.write("|");  
         }
           
        
         fichero.close();
         }catch(Exception e){}
     }
     public void etiqueta(){
        /*
            Lo que aremos sera llnear una 
            matriz de tipo String con la inicial del fondo
            y la inicial de la region
        */
        Color colorp,colorr,colort;
        imagenEspejo=new String[bmp.getWidth()][ bmp.getHeight()];
        for( int i = 0; i < bmp.getWidth(); i++ ){             
            for( int j = 0; j < bmp.getHeight(); j++ ){
               //Se inician las reglas o Algoritmo dado en clase
               //Obtenemos color del píxel actual o sea p y obtenemos su valor
               colorp=new Color(imagenAnalizada.getRGB(i, j));
               int p = calcularMedia(colorp);
               if(p==0){
                   imagenEspejo[i][j]="N";
               }else{
                   //obtenemos el valor se los siguientes pixeles
                   colorr=new Color(imagenAnalizada.getRGB(i-1, j));
                   int r = calcularMedia(colorr);
                   colort=new Color(imagenAnalizada.getRGB(i, j-1));
                   int t = calcularMedia(colort);
                   if((p==255)&&(r==0)&&(t==0)){
                       imagenEspejo[i][j]="B";
                   }else{
                       if((p==255)&&(r==255)&&(t==0)){
                           imagenEspejo[i][j]="B";
                       }else{
                           if((p==255)&&(r==0)&&(t==255)){
                               imagenEspejo[i][j]="B";
                           }else{
                               if((p==255)&&(r==255)&&(t==255)){
                                   imagenEspejo[i][j]="B";
                               }else{
                                   if(p>200){
                                     imagenEspejo[i][j]="B";  
                                   }else{
                                       if(p<200){
                                           imagenEspejo[i][j]="N";
                                       }else{
                                            System.out.println("p= "+p+" r= "+r+" t= "+t);
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
}
