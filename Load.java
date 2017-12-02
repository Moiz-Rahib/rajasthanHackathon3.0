import java.io.*;
import java.net.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.border.*;
public class Load extends JFrame{
private ImageIcon imageIcon;
private JLabel labelForLoading,labelForImage;
private BufferedImage img;
private URL url;
private Container c;
private Image dimg; 
public Load()
{
c=this.getContentPane();
imageIcon=new ImageIcon("shreya.gif");
this.labelForImage=new JLabel();
this.labelForImage.setBounds(0,0,400,400);
this.labelForImage.setIcon(imageIcon);

this.setLayout(null);
this.setVisible(true);
this.setSize(400,400);
c.add(labelForImage);
this.setLocation(450,200);
}
public static void main(String gg[])
{
Load l=new Load();
}
}