import java.awt.*;
import javax.swing.*;
public class MasterUiFrame extends JPanel
{
private JLabel areaName,latitude,longitude,pincode;
public JLabel area;
public JLabel latitudeValue;
public JLabel longitudeValue;
public JLabel pincodeValue;

public MasterUiFrame()
{
areaName=new JLabel("Area:");
latitude=new JLabel("Latitude:");
longitude=new JLabel("Longitude:");
pincode=new JLabel("Pincode:");
areaName.setBounds(0,0,50,50);
latitude.setBounds(0,51,50,50);
longitude.setBounds(0,102,70,50);
pincode.setBounds(0,153,50,50);

this.area=new JLabel("");
this.latitudeValue=new JLabel("");
this.longitudeValue=new JLabel("");
this.pincodeValue=new JLabel("");
area.setBounds(51,0,200,50);
latitudeValue.setBounds(51,51,100,50);
longitudeValue.setBounds(71,102,100,50);
pincodeValue.setBounds(51,153,100,50);
setLayout(null);
add(areaName);
add(latitude);
add(longitude);
add(pincode);
add(area);
add(latitudeValue);
add(longitudeValue);
add(pincodeValue);
setSize(400,250);
setLocation(100,100);
setVisible(true);

}
public static void main(String gg[])
{
MasterUiFrame m=new MasterUiFrame();
}
}