import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.filechooser.*;
import java.awt.image.*;
import javax.imageio.*;
public class MainView extends JFrame implements MouseListener,ActionListener
{
private JButton button1,button2,submitButton;
private int returnValue;
private JLabel title,budgetErrorLabel,AreaErrorLabel,labourErrorLabel;
private JLabel imageLabel;
private Font font;
private JFileChooser jFileChooser;
private ImageIcon imageIcon;
private BufferedImage image;
private boolean showEffect;
private ImageIcon frameIcon;
private LinkedList<DTODataElement> favourablePoints;
private LinkedList<WellPoint> wellPoints;
private LinkedList<DistrictDivision> areaList;
private abc a;
private File selectedCityFile,selectedWellFile;
private JTextField selectedCityTextField,selectedWellTextField,budgetTextField,stateAreaTextField,labourCostTextField;
private JLabel budgetLabel,stateAreaLabel,labourCostLabel;
private DAOConnection connection;
MainView()
{
this.jFileChooser=new JFileChooser();
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
this.budgetLabel=new JLabel("Enter Budget(Rs)");
this.stateAreaLabel=new JLabel("Enter Area(sq.km)");
this.labourCostLabel=new JLabel("Labour Cost(Rs/meter)");
this.budgetTextField=new JTextField();
this.stateAreaTextField=new JTextField();
this.submitButton=new JButton("SUBMIT");
this.labourCostTextField=new JTextField();
this.budgetLabel.setBounds(350-155,110,150,30);
this.stateAreaLabel.setBounds(350-155,55+90,150,30);
this.labourCostLabel.setBounds(350-155,90+90,150,30);
this.budgetLabel.setFont(font);
this.stateAreaLabel.setFont(font);
this.labourCostLabel.setFont(font);
this.budgetTextField.setBounds(350+5,20+90,100,30);
this.stateAreaTextField.setBounds(350+5,55+90,100,30);
this.labourCostTextField.setBounds(350+5,90+90,100,30);
this.showEffect=true;
this.font=new Font("BookmanOldStyle",Font.BOLD,20);
this.title=new JLabel();
this.title.setFont(font);
this.frameIcon=new ImageIcon("rain.png");
this.imageLabel=new JLabel();
this.imageLabel.addMouseListener(this);
this.title.setText("Welcome...!!!");
this.title.setBounds(250,10,625,80);
this.button1=new JButton("Enter City Data");
this.button2=new JButton("Enter Well Data");
this.button1.addActionListener(this);
this.button2.addActionListener(this);
this.button1.setFont(this.font);
this.button2.setFont(this.font);
this.submitButton.setFont(this.font);
this.button1.setBounds(200,135+90,200,30);
this.button2.setBounds(200,200+90,200,30);
this.submitButton.setBounds(275,330,50,30);
this.submitButton.addActionListener(this);
this.imageLabel.setBounds(0,0,700,500);
try{
this.image=ImageIO.read(new File("bg.png"));
}catch(Exception e)
{
System.out.println("catch me gaya");
System.out.println(e);
}
this.imageIcon=new ImageIcon(fitImage(this.image,this.imageLabel.getWidth(),this.imageLabel.getHeight(),1.0f));
this.imageLabel.setIcon(this.imageIcon);
setLayout(null);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setContentPane(this.imageLabel);
setResizable(false);
setIconImage(frameIcon.getImage());
setSize(700,500);
setLocation((int)(d.getWidth()/2)-350,(int)(d.getHeight())/2-250);
setTitle("HOME");
setVisible(true);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==this.button1)
{
try
{
this.returnValue=jFileChooser.showDialog(null,"Select File");
if (this.returnValue==JFileChooser.APPROVE_OPTION) {
this.selectedCityFile=this.jFileChooser.getSelectedFile();
this.selectedCityTextField=new JTextField(this.selectedCityFile.getAbsolutePath());
this.button1.setBounds(50,135+90,200,30);
this.selectedCityTextField.setBounds(260,135+90,250,30);
this.add(this.selectedCityTextField);
}
}catch(Exception ex)
{
System.out.println(ex.getMessage());
}
}

if(e.getSource()==this.button2)
{
this.returnValue=jFileChooser.showDialog(null,"Select File");
if (this.returnValue==JFileChooser.APPROVE_OPTION) {
this.selectedWellFile=this.jFileChooser.getSelectedFile();
this.selectedWellTextField=new JTextField(this.selectedWellFile.getAbsolutePath());
this.button2.setBounds(50,200+90,200,30);
this.selectedWellTextField.setBounds(260,200+90,250,30);
this.add(this.selectedWellTextField);
}
}
if(e.getSource()==this.submitButton)
{
this.setVisible(false);
Load load=new Load();
this.connection=new DAOConnection();
this.favourablePoints=connection.getFavourablePoints(50000000,500,343000);
this.wellPoints=connection.getWellPoints();
this.areaList=connection.getCityAndDistrictList();
load.setVisible(false);
this.a=new abc(this,this.favourablePoints,this.wellPoints,this.areaList);
}
}
public void mouseClicked(MouseEvent ev)
{
if(this.showEffect)
{
for(float i=9.0f;i>=6.0f;i--)
{
this.imageIcon=new ImageIcon(fitImage(this.image,this.imageLabel.getWidth(),this.imageLabel.getHeight(),i/10));
this.imageLabel.setIcon(this.imageIcon);
this.setContentPane(this.imageLabel);
}
this.add(this.title);
this.add(budgetLabel);
this.add(budgetTextField);
this.add(stateAreaLabel);
this.add(stateAreaTextField);
this.add(labourCostLabel);
this.add(labourCostTextField);
this.add(this.button1);
this.add(this.button2);
this.add(this.submitButton);
this.showEffect=false;
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
public Image fitImage(BufferedImage image,int width,int height,float x)
{
System.out.println("fit image chali");
BufferedImage resizedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
Graphics2D g2 = (Graphics2D)resizedImage.getGraphics();
g2.setComposite(AlphaComposite.SrcOver.derive(x));    
g2.drawImage(image, 0, 0,width,height,null);
return resizedImage;
}
public static void main(String g[])
{
MainView mv=new MainView();
}
}
