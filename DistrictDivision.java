import java.util.*;
public class DistrictDivision
{
private int uniqueId;
private LinkedList<DTOArea> areas=new LinkedList<DTOArea>();
private String districtDivisionName;
public DistrictDivision(int uniqueId,LinkedList<DTOArea> areas,String districtDivisionName)
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
public LinkedList<DTOArea> getAreas()
{
return this.areas;
}
}
