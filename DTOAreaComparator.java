import java.util.*;
public class DTOAreaComparator implements Comparator<DTOArea>
{
public int compare(DTOArea e1,DTOArea e2)
{
if(e1.getLatitude()==e2.getLatitude())
{
if(e1.getLongitude()-e2.getLongitude()>0) return 1;
if(e1.getLongitude()==e2.getLongitude()) return 0;
return -1;
}
if(e1.getLatitude()-e2.getLatitude()>0) return 1;
return -1;
}
}