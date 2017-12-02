package com.thinking.machines.spring;
import java.util.*;
public class DistrictDivision
{
private int uniqueId;
private LinkedList<Area> areas=new LinkedList<Area>();
private String districtDivisionName;
public DistrictDivision(int uniqueId,LinkedList<Area> areas,String districtDivisionName)
{
this.uniqueId=uniqueId;
this.areas=areas;
this.districtDivisionName=districtDivisionName;

}
public String getDistrictDivisionName()
{
return this.districtDivisionName;
}
public int getUniqueId()
{
return this.uniqueId;
}
public LinkedList<Area> getAreas()
{
return this.areas;
}
}
