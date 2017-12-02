import java.io.*;
import java.net.*;
import java.util.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
public class DAOConnection {
private URL url;
private String baseURL;
private URLConnection con;
private OutputStream outputStream;
private InputStream inputStream;
private StringBuilder stringBuilder;
private String responseText;
private ObjectMapper objectMapper;
private ObjectNode objectNode;
private ArrayNode arrayNode;
public DAOConnection()
{ 
this.objectMapper=new ObjectMapper();
this.baseURL="http://localhost:8080/ourApp/";
}       
public LinkedList<DTODataElement> getFavourablePoints(double budget,double labourCost,double stateArea)
{
LinkedList<DTODataElement> favourablePoints=new LinkedList<DTODataElement>();
try{
this.url=new URL(this.baseURL+"getFavourablePoints?budget="+String.valueOf(budget)+"&labour_cost="+String.valueOf(labourCost)+"&stateArea="+String.valueOf(stateArea));
this.con = this.url.openConnection();
this.con.setDoOutput(true); 
this.con.setDoInput(true);
this.con.setConnectTimeout(3000000);
this.outputStream=this.con.getOutputStream();
this.inputStream=this.con.getInputStream();        
this.stringBuilder=new StringBuilder();
int ch;      
while ((ch=this.inputStream.read())!=-1) this.stringBuilder.append((char)ch);
this.inputStream.close();
this.outputStream.close();        
this.responseText="";
this.responseText=this.stringBuilder.toString();
this.arrayNode=this.objectMapper.readValue(this.responseText,ArrayNode.class);
DTODataElement dtoDataElement;
for(int e=0;e<this.arrayNode.size();e++)
{
this.objectNode=(ObjectNode)this.arrayNode.get(e);

dtoDataElement=new DTODataElement(Double.parseDouble(this.objectNode.get("latitude").toString()),Double.parseDouble(this.objectNode.get("longitude").toString()));

dtoDataElement.isNaturalWaterbody(Boolean.parseBoolean(this.objectNode.get("naturalWaterbody").toString()));

favourablePoints.add(dtoDataElement);

}
}catch(Exception e){
System.out.println(e.getMessage()+"node wala ");
}
return favourablePoints;

}
public LinkedList<WellPoint> getWellPoints()
{
LinkedList<WellPoint> wellPoints=new LinkedList<WellPoint>();

try{
this.url=new URL(this.baseURL+"getWellPoints");
this.con=url.openConnection();
this.con.setDoOutput(true); 
this.con.setDoInput(true);
this.con.setConnectTimeout(3000000);
this.outputStream=this.con.getOutputStream();
this.inputStream=this.con.getInputStream();        
this.stringBuilder=new StringBuilder();
int ch;      
while ((ch=this.inputStream.read())!=-1) this.stringBuilder.append((char)ch);
this.outputStream.flush();
this.inputStream.close();
this.outputStream.close();
this.responseText="";        
this.responseText=this.stringBuilder.toString();
this.arrayNode=this.objectMapper.readValue(this.responseText,ArrayNode.class);
WellPoint wellPoint;
for(int e=0;e<this.arrayNode.size();e++)
{
this.objectNode=(ObjectNode)this.arrayNode.get(e);
wellPoint=new WellPoint(Double.parseDouble(this.objectNode.get("latitude").toString()),Double.parseDouble(this.objectNode.get("longitude").toString()));
wellPoints.add(wellPoint);
}

}catch(Exception e)
{
}
return wellPoints;

}
public LinkedList<DistrictDivision> getCityAndDistrictList()
{
LinkedList<DistrictDivision> list=new LinkedList<DistrictDivision>();

try{
this.url=new URL("http://localhost:8080/ourApp/getCityAndDistrictList");
this.con=this.url.openConnection();
this.con.setDoOutput(true); 
this.con.setDoInput(true);
this.con.setConnectTimeout(3000000);
this.outputStream=this.con.getOutputStream();
this.inputStream=this.con.getInputStream();        
this.stringBuilder=new StringBuilder();
int ch;      
while ((ch=inputStream.read())!=-1) this.stringBuilder.append((char)ch);
this.outputStream.flush();
this.inputStream.close();
this.outputStream.close();
this.responseText="";        
this.responseText=this.stringBuilder.toString();
this.arrayNode=this.objectMapper.readValue(this.responseText,ArrayNode.class);
DistrictDivision districtDivision;
ArrayNode areaArrayNode;
DTOArea area;
ObjectNode areaObjectNode;
LinkedList<DTOArea> areas=null;
for(int e=0;e<this.arrayNode.size();e++)
{
this.objectNode=(ObjectNode)this.arrayNode.get(e);
areaArrayNode=this.objectMapper.readValue(this.objectNode.get("areas").toString(),ArrayNode.class);
areas=new LinkedList<DTOArea>();
for(int f=0;f<areaArrayNode.size();f++)
{
areaObjectNode=(ObjectNode)areaArrayNode.get(f);
area=new DTOArea();
area.setPinCode(Integer.parseInt(areaObjectNode.get("pinCode").toString()));
area.setAreaName(areaObjectNode.get("areaName").textValue());
area.setLongitude(Double.parseDouble(areaObjectNode.get("longitude").toString()));
area.setLatitude(Double.parseDouble(areaObjectNode.get("latitude").toString()));
areas.add(area);
}
districtDivision=new DistrictDivision(Integer.parseInt(this.objectNode.get("uniqueId").toString()),areas,this.objectNode.get("districtDivisionName").textValue());
list.add(districtDivision);
}
}catch(Exception e){}
return list;
}
/*public static void main(String gg[])
{
DAOConnection tst=new DAOConnection();
LinkedList<DistrictDivision> l=tst.getCityAndDistrictList();
for(int e=0;e<l.size();e++)
{
System.out.println(l.get(e).getAreas().size());
for(int f=0;f<l.get(e).getAreas().size();f++)
{
System.out.println(l.get(e).getAreas().get(f).getAreaName());
}
}
}*/
}
