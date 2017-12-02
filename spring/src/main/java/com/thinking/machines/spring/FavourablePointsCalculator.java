package com.thinking.machines.spring;

import java.util.*;

import java.io.*;

public class FavourablePointsCalculator

{

private LinkedList<DTODataElement> favourablePoints;

private double threshold,labourCost,budget,stateArea;

private TreeSet<DataElement> dataElementSet;

public FavourablePointsCalculator(String fileName,double labourCost,double budget,double stateArea)

{

this.favourablePoints=new LinkedList<DTODataElement>();

this.labourCost=labourCost;

this.budget=budget;

this.stateArea=stateArea;

//System.out.println(fileName+","+labourCost+","+budget+","+stateArea);

try{

File file = new File(getClass().getResource("/"+fileName).getFile());

DatasetParser datasetParser=new DatasetParser(file);

this.dataElementSet=new TreeSet<DataElement>(datasetParser.getDataset());

}catch(Exception e)

{

//System.out.println("catch me gaya abhi wale");

}

calculateFavourablePoints();

}

private void calculateFavourablePoints()

{

while(this.dataElementSet.size()>8 && this.budget>5*this.labourCost)

{

DataElement d;

DTODataElement dt;

this.threshold=(9*(this.budget))/((this.stateArea)*(this.labourCost));

d=this.dataElementSet.last();

if(d.getAdjacentDataElements().size()<8) d=getMaxPopulationElement(d);

//System.out.println("WaterLevel: "+d.getWaterLevel()+" , "+"Population: "+d.getPopulationDensity());

if(this.threshold>d.getWaterLevel())

{

System.out.println("TRUE");

d.isNaturalWaterbody(true);

for(int e=0;e<d.getAdjacentDataElements().size();e++)

{

for(int f=0;f<d.getAdjacentDataElements().get(e).getAdjacentDataElements().size();f++)

{

this.dataElementSet.remove(d.getAdjacentDataElements().get(e).getAdjacentDataElements().get(f));

}

}

this.stateArea=this.stateArea-9;

this.budget=this.budget-(d.getWaterLevel()*this.labourCost);

}

else

{

d.isNaturalWaterbody(false);

for(int e=0;e<d.getAdjacentDataElements().size();e++)

{

for(int f=0;f<d.getAdjacentDataElements().get(e).getAdjacentDataElements().size();f++)

{

for(int z=0;z<d.getAdjacentDataElements().get(e).getAdjacentDataElements().get(f).getAdjacentDataElements().size();z++)

{

this.dataElementSet.remove(d.getAdjacentDataElements().get(e).getAdjacentDataElements().get(f).getAdjacentDataElements().get(z));

}

this.dataElementSet.remove(d.getAdjacentDataElements().get(e).getAdjacentDataElements().get(f));

}

}

this.stateArea=this.stateArea-25;

this.budget=this.budget-(this.threshold*3*this.labourCost);

}

dt=new DTODataElement(d.getLatitude(),d.getLongitude());

dt.isNaturalWaterbody(d.isNaturalWaterbody());

this.favourablePoints.add(dt);

}

}

public void setfavourablePoints(LinkedList<DTODataElement> favourablePoints)

{

this.favourablePoints=favourablePoints;

}

public LinkedList<DTODataElement> getFavourablePoints()

{

return this.favourablePoints;

} 

private DataElement getMaxPopulationElement(DataElement d)

{

DataElement max=d;

for(int m=0;m<d.getAdjacentDataElements().size();m++)

{

if((max.getPopulationDensity()*max.getAdjacentDataElements().size())<(d.getAdjacentDataElements().get(m).getPopulationDensity()*d.getAdjacentDataElements().get(m).getAdjacentDataElements().size())) max=d.getAdjacentDataElements().get(m);

}

return max;

}

/*public static void main(String g[])

{

FavourablePointsCalculator f=new FavourablePointsCalculator("Dataset.txt",500.0,50000000.0,500000.0);

System.out.println(f.getFavourablePoints().size());

}*/

}
