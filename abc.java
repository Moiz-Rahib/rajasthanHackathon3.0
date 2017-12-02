import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.*;
public class abc extends JFrame implements MouseListener
{
private Container c;
private JButton toggle;
private JScrollPane sp;
private Graphics2D g2;
private ImageIcon mapImageIcon;
private JPanel panelForImage;
private JLabel labelForImage;
private JLabel labelForMapScale;
private JLabel latLongLabel;
private JLabel infoLabel;
private double upperLat;
private double lowerLat;
private ImageIcon frameIcon;
private double rightLon;
private double leftLon;
private double deltaX;
private double deltaY;
private double xPixelDensity;
private double yPixelDensity;
private JSlider slider;
private BufferedImage img;
private BufferedImage imgBackUp;
private JButton home;
private double initialImageWidth,initialImageHeight;
private test t;
private double currentLat;
private TreeSet<DTOArea> areas;
private double currentLong;
private Dimension d;
private DAOConnection daoConnection;
private LinkedList<DTODataElement> favourablePoints;
private LinkedList<WellPoint> wellPoints;
private LinkedList<DistrictDivision> cityLocations;
private boolean wellPointsUI;
private MasterUiFrame main;
private boolean isMainLoaded;
private MainView mainView;
abc(MainView mainView,LinkedList<DTODataElement> favourablePoints,LinkedList<WellPoint> wellPoints,LinkedList<DistrictDivision> cityLocations)
{
this.main=new MasterUiFrame();
this.mainView=mainView;
this.favourablePoints=favourablePoints;
this.wellPoints=wellPoints;
this.cityLocations=cityLocations;
System.out.println("constructor");
this.t=new test(this,this.cityLocations);
this.wellPointsUI=false;
DTOAreaComparator xyz=new DTOAreaComparator();
this.areas=new TreeSet<DTOArea>(xyz);
c=getContentPane();
this.home=new JButton("HOME");
this.frameIcon=new ImageIcon("rain.png");
for(int i=0;i<cityLocations.size();i++)
{
for(int j=0;j<cityLocations.get(i).getAreas().size();j++){
DTOArea dtoArea=new DTOArea();
dtoArea.setPinCode(cityLocations.get(i).getAreas().get(j).getPinCode());
dtoArea.setAreaName(cityLocations.get(i).getAreas().get(j).getAreaName());
dtoArea.setLatitude(cityLocations.get(i).getAreas().get(j).getLatitude());
dtoArea.setLongitude(cityLocations.get(i).getAreas().get(j).getLongitude());
this.areas.add(dtoArea);
}
}
this.home.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setVisible(false);
mainView.setVisible(true);
}
});
this.upperLat=30.171866;
this.lowerLat=23.067753;
this.leftLon=69.509252;
this.rightLon=78.254369;
this.deltaX=this.upperLat-this.lowerLat;
this.deltaY=this.rightLon-this.leftLon;
this.img = null;
try {
this.img=ImageIO.read(new File("rj1.png"));
this.imgBackUp=ImageIO.read(new File("rj1.png"));
} catch (IOException e){
e.printStackTrace();
}
this.d=Toolkit.getDefaultToolkit().getScreenSize();
this.toggle=new JButton("Go To Well Point View");
this.toggle.addMouseListener(this);
this.labelForImage=new JLabel();
this.labelForImage.addMouseListener(this);
this.panelForImage=new JPanel();
this.labelForImage.setBounds(0,0,(int)d.getWidth()-500,(int)d.getHeight()-200);
this.initialImageWidth=this.labelForImage.getWidth();
this.initialImageHeight=this.labelForImage.getHeight();
this.sp=new JScrollPane(this.labelForImage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
this.infoLabel=new JLabel("INFO INFO ");
fitimage(0);
this.latLongLabel=new JLabel("");
this.latLongLabel.setBounds(380,labelForImage.getHeight()+10,300,30);
this.infoLabel.setBounds(300,labelForImage.getHeight()+40,300,10);
this.sp.setPreferredSize(new Dimension(labelForImage.getWidth(),labelForImage.getHeight()));
this.panelForImage.add(this.sp,BorderLayout.CENTER);
this.panelForImage.setBounds(0,0,labelForImage.getWidth(),labelForImage.getHeight());
this.slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
this.slider.setBounds(20,labelForImage.getHeight()+10,350,50);
this.toggle.setBounds(690,labelForImage.getHeight()+10,200,50);
this.home.setBounds(900,labelForImage.getHeight()+10,100,30);
this.slider.setMinorTickSpacing(2);
this.slider.setMajorTickSpacing(10);
this.slider.setPaintTicks(true);
this.slider.setPaintLabels(true);
slider.addChangeListener(new ChangeListener(){
public void stateChanged(ChangeEvent event)
{
fitimage((double)(slider.getValue()/10));
}
});
this.slider.setLabelTable(slider.createStandardLabels(10));
this.t.setBounds(labelForImage.getWidth()+10,0+10,400,350);
this.main.setBounds(labelForImage.getWidth()+10,351,400,400);
c.add(this.panelForImage);
c.add(this.slider);
c.add(this.latLongLabel);
c.add(this.t);
c.add(this.toggle);
c.add(this.home);
c.add(this.main);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setResizable(false);
this.setLayout(null);
this.xPixelDensity=this.deltaX/this.initialImageWidth;
this.yPixelDensity=this.deltaY/this.initialImageHeight;
setVisible(true);
setTitle("MAP View");
setLocation((int)(d.getWidth()/2)-(int)((d.getWidth()-100)/2),(int)(d.getHeight())/2-(int)((d.getHeight()-100)/2));
setIconImage(frameIcon.getImage());
setSize((int)d.getWidth()-100,(int)d.getHeight()-100);
}
public void mouseClicked(MouseEvent ev)
{
if(ev.getSource()==this.toggle)
{
this.wellPointsUI=!this.wellPointsUI;
if(this.wellPointsUI) this.toggle.setText("Go To Favourable Point View");
else this.toggle.setText("Go To Well Point View");
this.slider.setValue(1);
fitimage(0);
}
else{
double lat=upperLat-((Double.parseDouble(String.valueOf(ev.getY())))*(this.yPixelDensity));
double lon=((Double.parseDouble(String.valueOf(ev.getX())))*(this.xPixelDensity))+(this.leftLon);
this.latLongLabel.setText(lon+","+lat);
DTOArea ar=new DTOArea();
ar.setLatitude(lon);
ar.setLongitude(lat);
DTOArea high=(DTOArea)this.areas.higher(ar);
if(high!=null)
{
this.main.area.setText(high.getAreaName());
this.main.pincodeValue.setText(String.valueOf(high.getPinCode()));
this.main.latitudeValue.setText(String.valueOf(high.getLongitude()));
this.main.longitudeValue.setText(String.valueOf(high.getLatitude()));
}
else
{
high=this.areas.lower(ar);
this.main.area.setText(high.getAreaName());
this.main.pincodeValue.setText(String.valueOf(high.getPinCode()));
this.main.latitudeValue.setText(String.valueOf(high.getLongitude()));
this.main.longitudeValue.setText(String.valueOf(high.getLatitude()));
}
}
}
public void mouseEntered(MouseEvent ev)
{
}
public void mouseExited(MouseEvent ev)
{
}
public void mousePressed(MouseEvent ev)
{
}
public void mouseReleased(MouseEvent ev)
{
}

public void zoomLocation(double l1,double l2)
{
//fitimage(0);
this.currentLat=l1;
this.currentLong=l2;
int xCoordinate=(int)((this.currentLong-this.leftLon)/this.xPixelDensity);
int yCoordinate=(int)((this.upperLat-this.currentLat)/this.yPixelDensity);
int a=(int)((d.getWidth()-500)/2);
int b=(int)((d.getHeight()-200)/2);    
                  
double midLat=this.upperLat-(deltaY/2);
double midLong=this.leftLon+(deltaX/2);
                 
Rectangle r=new Rectangle(0,0,0,0);
if(this.currentLat> midLat && this.currentLong >midLong) r=new Rectangle(xCoordinate+a,yCoordinate+b,16,16);
else if(this.currentLat> midLat && this.currentLong <midLong) r=new Rectangle(xCoordinate+a,yCoordinate-b,16,16);
else if(this.currentLat< midLat && this.currentLong >midLong) r=new Rectangle(xCoordinate-a,yCoordinate+b,16,16);
else r=new Rectangle(xCoordinate-a,yCoordinate-b,16,16);
System.out.println("calculation ke baad");
Rectangle r1=new Rectangle(xCoordinate,yCoordinate,16,16);
this.g2.setPaint(Color.RED);
this.g2.fill(r1);
labelForImage.scrollRectToVisible(r);
}

public void fitimage(double factor)
{
if(this.g2!=null) this.g2.dispose();
int imageWidth=(int)(this.initialImageWidth+this.initialImageWidth*factor);
int imageHeight=(int)(this.initialImageHeight+this.initialImageHeight*factor);
BufferedImage resizedimage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_RGB);
this.g2 = resizedimage.createGraphics();
this.g2.drawImage(this.imgBackUp,0,0,imageWidth,imageHeight,null);    

this.g2.setColor(Color.BLACK);
BasicStroke bs = new BasicStroke(1);
this.g2.setStroke(bs);
int i=(int)this.initialImageWidth/91;       
for(int m=i;m<imageWidth;m=m+i) this.g2.drawLine(m,0,m,imageHeight);
i=(int)this.initialImageHeight/79;
for(int m=i;m<imageHeight;m=m+i) this.g2.drawLine(0,m,imageWidth,m);
this.xPixelDensity=((this.deltaX)/(int)(this.initialImageWidth+this.initialImageWidth*factor));
this.yPixelDensity=((this.deltaY)/(int)(this.initialImageHeight+this.initialImageHeight*factor));
this.infoLabel.setText("Area per Rect: "+factor+"X"+factor+"km-km");
int xCoordinate;
int yCoordinate;
 i=0;
try
{
if(wellPointsUI)
{
System.out.println("well points");
while(i<wellPoints.size())
{
xCoordinate=(int)((wellPoints.get(i).getLongitude()-this.leftLon)/this.xPixelDensity);
yCoordinate=(int)((this.upperLat-wellPoints.get(i).getLatitude())/this.yPixelDensity);
this.g2.setPaint(new Color(1.0f,0.0f,0.0f,0.5f));
this.g2.fillOval(xCoordinate,yCoordinate,((int)factor+1)*3,((int)factor+1)*3);
//this.g2.setPaint(new Color(0.0f,1.0f,0.0f,0.5f));
//this.g2.fillOval(xCoordinate+3,yCoordinate+3,((int)factor+1)*1,((int)factor+1)*1);
i++;
}
}else
{
i=0;
while(i<favourablePoints.size())
{
xCoordinate=(int)((favourablePoints.get(i).getLongitude()-this.leftLon)/this.xPixelDensity);
yCoordinate=(int)((this.upperLat-favourablePoints.get(i).getLatitude())/this.yPixelDensity);
this.g2.setPaint(new Color(0.0f,0.0f,1.0f,0.5f));
this.g2.fillOval(xCoordinate,yCoordinate,((int)factor+1)*3,((int)factor+1)*3);
//this.g2.setPaint(new Color(1.0f,0.0f,0.0f,0.5f));
//this.g2.fillOval(xCoordinate+3,yCoordinate+3,((int)factor+1)*1,((int)factor+1)*1);
i++;
}
}
this.mapImageIcon = new ImageIcon(resizedimage);
this.labelForImage.setIcon(mapImageIcon);

}catch(Exception e)
{
System.out.println(e+"catch mein gaya");
}

}
//public static void main(String gg[])
//{
//abc p=new abc();
//}
}
