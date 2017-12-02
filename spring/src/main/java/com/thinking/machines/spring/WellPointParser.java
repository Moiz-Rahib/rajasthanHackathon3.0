package com.thinking.machines.spring;
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;
public class  WellPointParser
{
private File file;
private RandomAccessFile raf;
private LinkedList<WellPoint> wellPoints;
public WellPointParser(String fileName)
{
try
{
File file = new File(getClass().getResource("/"+fileName).getFile());
raf=new RandomAccessFile(file,"rw");
populateWellPoints();
}catch(IOException io)
{
System.out.println(io.getMessage());
}
}
public LinkedList<WellPoint> getWellPoints()
{
return this.wellPoints;
}
private void populateWellPoints()
{
this.wellPoints=new LinkedList<WellPoint>(); 
WellPoint w;
double latitude,longitude;
String dataString;
try
{
raf.seek(0);
while(raf.getFilePointer()<raf.length())
{
dataString=raf.readLine();
String a[]=dataString.split(",");
latitude=Double.parseDouble(a[0]);
longitude=Double.parseDouble(a[1].substring(0,a[1].length()-1));
w=new WellPoint(latitude,longitude);
wellPoints.add(w);
}
raf.close();
}catch(IOException io)
{
System.out.println(io.getMessage());
}
}

}
