package com.thinking.machines.spring;
import java.util.*;
public class DTODataElement implements java.io.Serializable,Comparable<DTODataElement>
{
private boolean isNaturalWaterbody;
private double latitude;
private double longitude;
public DTODataElement(double latitude,double longitude)
{
this.latitude=latitude;
this.longitude=longitude;
}
public void isNaturalWaterbody(boolean isNaturalWaterbody)
{
this.isNaturalWaterbody=isNaturalWaterbody;
}
public boolean isNaturalWaterbody()
{
return this.isNaturalWaterbody;
}
public void setLatitude(double latitude)
{
this.latitude=latitude;
}
public void setLongitude(double longitude)
{
this.longitude=longitude;
}
public double getLatitude()
{
return this.latitude;
}	
public double getLongitude()
{
return this.longitude;
}
public boolean equals(Object object)
{
System.out.println("equals chali data element ki");
if(!(object instanceof DTODataElement)) return false;
DTODataElement otherDataElement=(DTODataElement)object;
return (this.latitude==otherDataElement.latitude && this.longitude==otherDataElement.longitude);
}
public int compareTo(DTODataElement otherDataElement)
{
if(this.getLatitude()==otherDataElement.getLatitude())
{
if(this.getLongitude()-otherDataElement.getLongitude()>0) return 1;
if(this.getLongitude()==otherDataElement.getLongitude()) return 0;
return -1;
}
if(this.getLatitude()-otherDataElement.getLatitude()>0) return 1;
return -1;
}
public int hashCode()
{
return 0;
}
}
