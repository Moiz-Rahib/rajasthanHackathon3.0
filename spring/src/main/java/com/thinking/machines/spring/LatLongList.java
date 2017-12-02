package com.thinking.machines.spring;
import java.util.*;
import java.io.*;
public class LatLongList
{
private LinkedList<Area> areas;
public LinkedList<DistrictDivision> districtDivisions=new LinkedList<DistrictDivision>();
public LatLongList()
{
try
{
File f = new File(getClass().getResource("/latlongwithpin.txt").getFile());
RandomAccessFile raf=new RandomAccessFile(f,"rw");
String line;
int districtUniqueCode;
int e=0;
Area area;
DistrictDivision districtDivision;
while(raf.getFilePointer()<raf.length())
{
line=raf.readLine();
if(line.startsWith("IN"))
{

line=line.substring(3,line.length()-1);
districtUniqueCode=Integer.parseInt(line.substring(0,3));

String g[]=line.split(",");
area=new Area();
area.setPinCode(Integer.parseInt(g[0]));
area.setAreaName(g[1]);
area.setLongitude(Double.parseDouble(g[3]));
area.setLatitude(Double.parseDouble(g[4]));
if(e==0)
{
areas=new LinkedList<Area>();
districtDivision=new DistrictDivision(districtUniqueCode,areas,area.getAreaName());
districtDivisions.add(districtDivision);
e=1;
}
areas.add(area);
}
if(line.startsWith("---------------"))
{
e=0;
}
}
System.out.println("length:"+districtDivisions.size());
raf.close();
}catch(Exception e)
{
System.out.println(e);
}
}
public LinkedList<DistrictDivision> getCityAndDistrictList()
{
return this.districtDivisions;
}
}
