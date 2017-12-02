package com.thinking.machines.spring;
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;
public class  DatasetParser
{
private File file;
private RandomAccessFile raf;
private List<DataElement> dataset;
private TreeSet<DataElement> datasetTreeSet;
private TreeSet<Double> latitudes;
private TreeSet<Double> longitudes;
public DatasetParser(File fileName)

{
latitudes=new TreeSet<Double>();
longitudes=new TreeSet<Double>();

try

{
this.file=fileName;
//file=new File(fileName);

this.raf=new RandomAccessFile(file,"rw");

populateDataset();

}catch(IOException io)
{
//System.out.println("catch me gaya");
System.out.println(io.getMessage());
}

}

public List<DataElement> getDataset()

{

return this.dataset;

}

private void populateDataset()

{
//System.out.println("populate Data Set chali");
this.dataset=new LinkedList<DataElement>(); 
this.datasetTreeSet=new TreeSet<DataElement>(new DataElementComparator());
DataElement d;

double latitude,longitude,population,waterLevel;

String dataString;

try
{
//System.out.println("try me gaya");
//System.out.println(raf.length());
this.raf.seek(0);
while(this.raf.getFilePointer()<this.raf.length())
{
//System.out.println("while chala");
dataString=this.raf.readLine();
//System.out.println(dataString);

String a[]=dataString.split(":");

String b[]=a[0].split(",");

latitude=Double.parseDouble(b[0]);

longitude=Double.parseDouble(b[1]);

latitudes.add(latitude);
longitudes.add(longitude);
b=a[1].split(",");
population=Double.parseDouble(b[0]);
waterLevel=Double.parseDouble(b[1].substring(0,b[1].length()-1));
d=new DataElement(latitude,longitude,waterLevel,population);
datasetTreeSet.add(d);

}
this.raf.close();
}catch(IOException io)
{

//System.out.println("catch me gaya");
System.out.println(io.getMessage());

}

Iterator iterator=datasetTreeSet.iterator();
DataElement dataElement,left,right,upper,upperLeftMost,lower,lowerLeftMost,upperRightMost,lowerRightMost;
double upperLat=0,lowerLat=0,rightLong=0,leftLong=0;
LinkedList<DataElement> adjacentDataElements;
while(iterator.hasNext())
{
adjacentDataElements=new LinkedList<DataElement>();
dataElement=(DataElement)iterator.next();
try{
leftLong=longitudes.lower(dataElement.getLongitude());
lowerLat=latitudes.lower(dataElement.getLatitude());
upperLat=latitudes.higher(dataElement.getLatitude());
rightLong=longitudes.higher(dataElement.getLongitude());
}catch(Exception e){
leftLong=0;
rightLong=0;
upperLat=0;
lowerLat=0;
}
try{
left=datasetTreeSet.lower(dataElement);
if(left.getLongitude()==leftLong) adjacentDataElements.add(left);
else leftLong=0;
right=datasetTreeSet.higher(dataElement);
if(right.getLongitude()==rightLong) adjacentDataElements.add(right);
else rightLong=0;
if(leftLong!=0) {
upperLeftMost=new DataElement(upperLat,leftLong,0.0,0.0);
upperLeftMost=datasetTreeSet.ceiling(upperLeftMost);
if(upperLeftMost.getLongitude()==leftLong) adjacentDataElements.add(upperLeftMost);
lowerLeftMost=new DataElement(lowerLat,leftLong,0.0,0.0);
lowerLeftMost=datasetTreeSet.ceiling(lowerLeftMost);
if(lowerLeftMost.getLongitude()==leftLong) adjacentDataElements.add(lowerLeftMost);
}
if(rightLong!=0) {
upperRightMost=new DataElement(upperLat,rightLong,0.0,0.0);
upperRightMost=datasetTreeSet.ceiling(upperRightMost);
if(upperRightMost.getLongitude()==rightLong) adjacentDataElements.add(upperRightMost);
lowerRightMost=new DataElement(lowerLat,rightLong,0.0,0.0);
lowerRightMost=datasetTreeSet.ceiling(lowerRightMost);
if(lowerRightMost.getLongitude()==rightLong) adjacentDataElements.add(lowerRightMost);
}
upper=new DataElement(upperLat,dataElement.getLongitude(),0.0,0.0);
lower=new DataElement(lowerLat,dataElement.getLongitude(),0.0,0.0);
upper=datasetTreeSet.ceiling(upper);
lower=datasetTreeSet.ceiling(lower);
if(upper.getLongitude()==dataElement.getLongitude()) adjacentDataElements.add(upper);
if(lower.getLongitude()==dataElement.getLongitude()) adjacentDataElements.add(lower);
}catch(Exception e){}
dataElement.setAdjacentDataElements(adjacentDataElements);
dataset.add(dataElement);
//System.out.println(adjacentDataElements.size());
}
}
/*public static void main(String g[])
{
DatasetParser dp=new DatasetParser("Dataset.txt");
LinkedList<DataElement> a=(LinkedList<DataElement>)dp.getDataset();
Iterator i=a.iterator();
while(i.hasNext())
{
System.out.println(((DataElement)i.next()).getPopulationDensity());
}
}*/

}
