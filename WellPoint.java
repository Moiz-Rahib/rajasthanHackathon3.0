import java.util.*;
public class WellPoint implements java.io.Serializable,Comparable<WellPoint>
{
private double latitude;
private double longitude;
public WellPoint(double latitude,double longitude)
{
this.latitude=latitude;
this.longitude=longitude;
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
if(!(object instanceof WellPoint)) return false;
WellPoint otherDataElement=(WellPoint)object;
return (this.latitude==otherDataElement.latitude && this.longitude==otherDataElement.longitude);
}
public int compareTo(WellPoint otherDataElement)
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
