public class DTOArea implements java.io.Serializable,Comparable<DTOArea>
{
private int pinCode;
private String areaName;
private double latitude;
private double longitude;
public void setPinCode(int pinCode)
{
this.pinCode=pinCode;
}
public void setAreaName(String areaName)
{
this.areaName=areaName;
}
public void setLatitude(double latitude)
{
this.latitude=latitude;
}
public void setLongitude(double longitude)
{
this.longitude=longitude;
}
public int getPinCode()
{
return this.pinCode;
}
public String getAreaName()
{
return this.areaName;
}
public double getLatitude()
{
return this.latitude;
}
public double getLongitude()
{
return this.longitude;
}
public int compareTo(DTOArea e)
{
if(this.getLatitude()==e.getLatitude())
{
if(this.getLongitude()-e.getLongitude()>0) return 1;
if(this.getLongitude()==e.getLongitude()) return 0;
return -1;
}
if(this.getLatitude()-e.getLatitude()>0) return 1;
return -1;
}

}