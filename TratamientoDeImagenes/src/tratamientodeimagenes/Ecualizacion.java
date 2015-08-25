/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamientodeimagenes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.SplitPane;
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
    JPanel histogramaN;
    JPanel histogramaE;
    JPanel normal;
    JPanel ecualizado;
    JTabbedPane pestañasI=new JTabbedPane();
    JTabbedPane pestañasD=new JTabbedPane();
    Comparacion c = new Comparacion();
    Comparacion2 c2 = new Comparacion2();
    
    Imagen i = new Imagen();
    private int calcularMedia(Color color){      
        int mediaColor;
        mediaColor=(int)((color.getRed()+color.getGreen()+color.getBlue())/3);
      //  System.out.println(mediaColor);
        return mediaColor;
    }
    
    public  Ecualizacion() throws IOException{
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
        cargaTodo2();
        cargaTodo();
    }
    public void init(){
        JSplitPane pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        initLeftPanel();
        pane.setLeftComponent(pestañasI);
        initRightPanel();
        pane.setRightComponent(pestañasD);   //cmbiepestañas    
        pane.setContinuousLayout(true);
        pane.setDividerLocation(500);       
        pane.setEnabled(true);
        pane.setOneTouchExpandable(true);
        add(pane);

    }
    public void initLeftPanel(){
       d_leftPanel = new JPanel();
       d_leftPanel.setLayout(null);
       d_leftPanel.setBackground(java.awt.Color.white);
       normal = new JPanel();
       normal.setLayout(null);
       normal.setBackground(java.awt.Color.white);
       pestañasI.addTab("ImagenNormal",normal);
       histogramaN = new JPanel();
       histogramaN.setLayout(null);
       histogramaN.setBackground(java.awt.Color.white);
       pestañasI.addTab("histogramaNormal", histogramaN);
    }
    public void initRightPanel(){
       d_rightPanel = new JPanel();
       d_rightPanel.setLayout(null);
       d_rightPanel.setBackground(java.awt.Color.white);
       pestañasD.addTab("ImagenE", d_rightPanel);
       histogramaE = new JPanel();
       histogramaE.setLayout(null);
       histogramaE.setBackground(java.awt.Color.white);
       pestañasD.addTab("Histograma", histogramaE);
      /* binarizar = new JPanel();
       binarizar.setLayout(null);
       pestañas.addTab("Binarizar", binarizar);*/
    }
    public void cargaTodo2() throws IOException{
        c2.cargaOriginal();
        normal.removeAll();
        normal.add(c2);
        normal.repaint();
        c2.histogramaImagen(histogramaN);
    }
    public void cargaTodo() throws IOException{
        
        c.cargaEcualizada();
        d_rightPanel.removeAll();
        d_rightPanel.add(c);
        d_rightPanel.repaint();
        c.histogramaImagen(histogramaE);
    }
}
