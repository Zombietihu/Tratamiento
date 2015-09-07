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
    boolean fondo;
    public void Analiza(BufferedImage imagen) throws IOException{
        bmp = imagen;
        //imagenAnalizada = imagen;
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
    //Binarizaba pero ya no y me dio hueva cambiarle el nombre xD ... ahora simplemente hacemos que los 
    //tonos de colore queden mas compactos n.n
    public void binariza(){
        try{
           cuadritoB = new BufferedImage(bmp.getWidth(), bmp.getHeight(), BufferedImage.TYPE_INT_RGB);
            for( int i = 0; i < bmp.getWidth(); i++ ){
                
               for( int j = 0; j < bmp.getHeight(); j++ ){
                  
                   colorAuxiliar=new Color(bmp.getRGB(i, j));
                   if(colorAuxiliar.getRed()>120){
                       Color color = new Color(255, 0, 0);
                       cuadritoB.setRGB(i, j, color.getRGB());
                   }else{
                       if(colorAuxiliar.getGreen()>120){
                           Color color = new Color(0, 255, 0);
                       cuadritoB.setRGB(i, j, color.getRGB());
                       }else{
                           if(colorAuxiliar.getBlue()>120){
                               Color color = new Color(0, 0, 255);
                       cuadritoB.setRGB(i, j, color.getRGB());
                           }else{
                                if ( calcularMedia(colorAuxiliar)<120){
                       Color color = new Color(0, 0, 0);
                       cuadritoB.setRGB(i, j, color.getRGB());
                   }else{
                       Color color = new Color(255, 255, 255);
                       cuadritoB.setRGB(i, j, color.getRGB());
                   }
                           }
                       }
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
                 if(imagenEspejo[i][j].equals("R")){
                     obtenCoordenadas(i,j,"R");
                     break;
                 }else{
                     if(imagenEspejo[i][j].equals("G")){
                        obtenCoordenadas(i,j,"G");
                        break;
                    }else{
                      if(imagenEspejo[i][j].equals("B")){
                        obtenCoordenadas(i,j,"B");
                        break;
                      }    
                    }
                 }
                 
             }
         }
         System.out.println("las Regiones existentes son: "+regionesExistentes);
     }
     public void obtenCoordenadas(int i,int j, String etq){
         int x0,x1=0,y0,y1=0;
         int largo, ancho;
         x0=i;
         y0=j;
         //recorreFiulas
         boolean blanco = true;
        while(blanco){
            if (imagenEspejo[x0][y0].equals(etq)){
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
            if (imagenEspejo[x0][y0].equals(etq)){
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
        evaluaRegion(x0, y0, x1, y1, etq);
     }
     public void evaluaRegion(int x, int y, int ancho, int largo, String etq){
         /*Es la seccion de evaluacion de la region, se compara que toda la region
         se encuentre la etiqueta blanca  si al finalizar todo fue con etiquetado blanco
         aunmenta regionesExistentes.
         Durante el Recorrido se cambia la etiquta blanco a recorrido (R) para 
         no volver a recorrer la region.
         */
         boolean a = true;
         for(int i = x; i<ancho; i++){
             for(int j = y; j<largo;j++){
                 if(imagenEspejo[i][j].equals(etq)){
                     imagenEspejo[i][j]="V";
                 }else{
                     imagenEspejo[i][j]="V";
                     a = false;
                 }
             }
         }
         if (a){
             regionesExistentes++;
             System.out.println(regionesExistentes);
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
               fondo = (colorp.getBlue()<120)&&(colorp.getRed()<120)&&(colorp.getGreen()<120);
               //Se asigna fondo
               if(fondo){
                   imagenEspejo[i][j]="F";
               }else{
                   
                   //obtenemos el valor se los siguientes pixeles
                   colorr=new Color(imagenAnalizada.getRGB(i-1, j));
                   boolean r = (colorr.getBlue()>120)||(colorr.getRed()>120)||(colorr.getGreen()>120);;
                   colort=new Color(imagenAnalizada.getRGB(i, j-1));
                   boolean t = (colorp.getBlue()>120)||(colorp.getRed()>120)||(colorp.getGreen()>120);
                   //Se asigna nueva etiqueta
                   if((!fondo)&&(r)&&(t)){
                       String nuevaEtiqueta = tablaEtiquetas(colorp.getRed(),colorp.getGreen(),colorp.getBlue());
                       imagenEspejo[i][j]=nuevaEtiqueta;
                   }else{
                       if((!fondo)&&(!r)&&(t)){
                           //asigna etiqueta de r
                           imagenEspejo[i][j]=imagenEspejo[i-1][j];
                       }else{
                           //asigna etiqueta de t
                           if((!fondo)&&(r)&&(!t)){
                               imagenEspejo[i][j]=imagenEspejo[i][j-1];
                           }else{
                               if((!fondo)&&(!r)&&(!t)&&(mismoColor(colorr,colort))){
                                   imagenEspejo[i][j]=imagenEspejo[i][j-1];
                               }else{
                                   if((!fondo)&&(!r)&&(!t)&&(!mismoColor(colorr,colort))){
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
     public String tablaEtiquetas(int R, int G, int B){
         if(R>120){return "R";}else{if(G>120){return "G";}else{if(B>120){return "B";}}}
         return "NR";
     }
     public void reEtiqueta(){
     }
     public boolean mismoColor(Color r, Color t){
         if((r.getRed()>120)&&(t.getRed()>120)){
             return true;
         }else{
             if((r.getGreen()>120)&&(t.getGreen()>120)){
                 return true;
             }else{
                 if((r.getBlue()>128)&&(t.getBlue()>120)){
                     return true;
                 }else{return false;}
             }
         }
     }
             
}
