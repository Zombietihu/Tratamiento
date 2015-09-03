/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codificacion;

import ReconocimientoDeCuadritos.Analiza;
import ReconocimientoDeCuadritos.Frame;
import ReconocimientoDeCuadritos.GeneraCuadros;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

/**
 *
 * @author cuatito
 */
public class LoadImage extends JFrame implements ActionListener{
     
    JPanel imagen;
    JScrollPane p = new JScrollPane();
    File path;
    private JMenu menu1;
    private JMenuItem mi1,mi2,mi3;
     BufferedImage bmp2 = null;
    public LoadImage() throws IOException{
        JMenuBar mb = new JMenuBar();
        setTitle("compara");
        setVisible(true);
        
        setSize(500,400);
        setJMenuBar(mb);
        menu1=new JMenu("Opciones");
        mb.add(menu1);
        mi1=new JMenuItem("Abrir Imagen");
        mi1.addActionListener(this);
        menu1.add(mi1);
        mi2=new JMenuItem("Comprecion Imagen");
        mi2.addActionListener(this);
        menu1.add(mi2);
        mi3=new JMenuItem("Codigo Imagen");
        mi3.addActionListener(this);
        menu1.add(mi3); 
       
        
    }
    public void Cargar(){
       
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp=null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector=new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada=selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                this.path = imagenSeleccionada;
                bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            } 
        }
        //Asignamos la imagen cargada a la propiedad imageActual
        bmp2=bmp;
        
        imagen  = new ImagenPanel(bmp2);
        add(imagen);
    }
    public void actionPerformed(ActionEvent e) {
    	Container f=this.getContentPane();
        //Damos la accion a abrir imagen del menu bar
        if (e.getSource()==mi1) {
         
           Cargar();
           
        }
        //damos accion a Analizar Imagen
        if (e.getSource()==mi2) {
              rCodificacion k = new rCodificacion(bmp2);
        }
        //Damos accion al generador de cuadritos (Solo es para el primer caso xD)
        if (e.getSource()==mi3) {
            
        }        
    }
 
}


class ImagenPanel extends JPanel {
          BufferedImage bmp2;
	  public  ImagenPanel(BufferedImage bmp2) {
           this.bmp2= bmp2;
            this.setSize(bmp2.getWidth(),bmp2.getHeight() );
            
          }
    public void paint(Graphics g) {
            
            
            super.paintComponent(g); //borra el area de dibujo
            Dimension height = getSize();
            ImageIcon Img = new ImageIcon(bmp2); 
            g.drawImage(Img.getImage(), 0, 0, height.width, height.height, this);
        
    }
    
    
}
