import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.event.*;
public class test extends JPanel implements KeyListener
{
private JList<String> districtDivisionList;
private DefaultListModel<String> listModel;
private JTextField jt=new JTextField(10);
private String text="";
private LinkedList<DistrictDivision> lat;
private JComboBox j;
private JScrollPane pane;
private DTOArea area;
private LinkedList<DTOArea> list=new LinkedList<DTOArea>();

public test(abc A,LinkedList<DistrictDivision> districts){
this.lat=districts;
this.listModel=new DefaultListModel<String>();
this.jt.addKeyListener(this);
districtDivisionList = new JList<String>(listModel);
setLayout(null);
jt.setBounds(0,0,200,20);
add(jt);
j=new JComboBox();
this.j.addItem("<Select>");
this.j.setBounds(200,0,150,20);
add(this.j);
ListSelectionListener listSelectionListener = new ListSelectionListener() {
String g="";
int a=-1;
int b=-1;
int z=0,e;
public void valueChanged(ListSelectionEvent listSelectionEvent) {
if(listModel.size()==1||listModel.size()==0) return;

if(a==listSelectionEvent.getFirstIndex()&&b==listSelectionEvent.getLastIndex()) 
{
z++;
if(z>1)
{

if(e==listSelectionEvent.getFirstIndex()) e=listSelectionEvent.getLastIndex();
else e=listSelectionEvent.getFirstIndex();
g=listModel.getElementAt(e);
z=0;
}

return;
}

if(a==listSelectionEvent.getFirstIndex()||a!=-1&&listSelectionEvent.getFirstIndex()!=listSelectionEvent.getLastIndex())
{
e=listSelectionEvent.getLastIndex();
g=listModel.getElementAt(e);
z=0;
}
if(b==listSelectionEvent.getLastIndex() ||a==listSelectionEvent.getLastIndex()||listSelectionEvent.getFirstIndex()==listSelectionEvent.getLastIndex())
{
e=listSelectionEvent.getFirstIndex();
g=listModel.getElementAt(e);
z=0;
}
j.removeAllItems();
list.clear();
j.addItem("<Select>");

for(DistrictDivision l:lat)
{
if(g.equals(l.getDistrictDivisionName()))
{
for(DTOArea area:l.getAreas())
{
list.add(area);
j.addItem(area.getAreaName());
}

}
}

a=listSelectionEvent.getFirstIndex();
b=listSelectionEvent.getLastIndex();   

}
      
    };
districtDivisionList.addListSelectionListener(listSelectionListener);

j.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent i) {
for(DTOArea a:list)
{
if(a.getAreaName().equals(i.getItem()))
{
A.zoomLocation(a.getLongitude(),a.getLatitude());
}
}
}
});
pane = new JScrollPane(districtDivisionList);      
listModel.addElement("<Select>");
pane.setBounds(0,0,0,0);
add(pane);
setSize(400,400);
setVisible(true);
}
public void keyTyped(KeyEvent e) {
}
public void keyReleased(KeyEvent e)
{
j.removeAllItems();
listModel.clear();
j.addItem("<Select>");
if(e.getKeyChar()==KeyEvent.VK_BACK_SPACE)
{
if(this.text.length()==0) return;

this.text=this.text.substring(0,this.text.length()-1);
if(this.text.length()==0) pane.setBounds(0,0,0,0);
if(this.text.length()<1) return;
}
if(e.getKeyChar()==KeyEvent.VK_DELETE)
{
if(this.text.length()==0) return;

this.text=this.text.substring(1);
if(this.text.length()==0) pane.setBounds(0,0,0,0);

if(this.text.length()<1) return;
}

if(e.getKeyChar()==KeyEvent.VK_ENTER) 
{
pane.setBounds(0,0,0,0);
return;
}
if(e.getKeyChar()!=KeyEvent.VK_DELETE &&e.getKeyChar()!=KeyEvent.VK_BACK_SPACE) this.text=this.text+e.getKeyChar();
if(e.getKeyCode()<=37&&e.getKeyCode()>=40) return;
for(int z=0;z<this.lat.size();z++)
{
if(this.lat.get(z).getDistrictDivisionName().substring(0,this.text.length()).equalsIgnoreCase(this.text)) listModel.addElement(this.lat.get(z).getDistrictDivisionName());
}
pane.setBounds(0,20,200,20*listModel.size());
}
public void keyPressed(KeyEvent e)
{}
public DTOArea getSelectedArea()
{
return this.area;
}
 }
