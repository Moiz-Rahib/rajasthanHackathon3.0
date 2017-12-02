package com.thinking.machines.spring;

import java.util.*;

import java.math.*;

public class DataElement implements java.io.Serializable,Comparable<DataElement>

{

private int uniqueId;

private boolean isFavourable;

private boolean isNaturalWaterbody;

private double latitude;

private double longitude;

private double waterLevel;

private double population;

private double populationDensity;

private LinkedList<DataElement> adjacentDataElements;

public DataElement(double latitude,double longitude,double waterLevel,double population)

{

this.latitude=latitude;

this.longitude=longitude;

this.waterLevel=waterLevel;

this.population=population;

this.uniqueId=UUID.randomUUID().toString().hashCode();

}

public void isFavourable(boolean isFavourable)

{

this.isFavourable=isFavourable;

}

public boolean isFavourable()

{

return this.isFavourable;

}

public void isNaturalWaterbody(boolean isNaturalWaterbody)

{

this.isNaturalWaterbody=isNaturalWaterbody;

}

public boolean isNaturalWaterbody()

{

return this.isNaturalWaterbody;

}

public void setPopulation(double population)

{

this.population=population;

}

public double getPopulation()

{

return this.population;

}

public void setLatitude(double latitude)

{

this.latitude=latitude;

}

public void setLongitude(double longitude)

{

this.longitude=longitude;

}

public void setWaterLevel(double waterLevel)

{

this.waterLevel=waterLevel;

}

public void setPopulationDensity(double populationDensity)

{

this.populationDensity=populationDensity;

}

public double getLatitude()

{

return this.latitude;

}	

public double getLongitude()

{

return this.longitude;

}

public double getPopulationDensity()

{

return this.populationDensity;

}

public double getWaterLevel()

{

return this.waterLevel;

}

public LinkedList<DataElement> getAdjacentDataElements()

{

return this.adjacentDataElements;

}

public void setAdjacentDataElements(LinkedList<DataElement> adjacentDataElements)

{

this.adjacentDataElements=adjacentDataElements;

if(adjacentDataElements.size()>0)

{

Iterator iterator=this.adjacentDataElements.iterator();

while(iterator.hasNext()) this.populationDensity=this.populationDensity+((DataElement)iterator.next()).getPopulation();

this.populationDensity=(this.populationDensity)/(this.adjacentDataElements.size());

}

else this.populationDensity=0.00;

}

public boolean equals(Object object)

{

System.out.println("equals chali data element ki");

if(!(object instanceof DataElement)) return false;

DataElement otherDataElement=(DataElement)object;

return (this.latitude==otherDataElement.latitude && this.longitude==otherDataElement.longitude);

}

public int compareTo(DataElement otherDataElement)

{

if(this.populationDensity==otherDataElement.populationDensity)

{

if(otherDataElement.waterLevel-this.waterLevel>0) return 1;

else if(otherDataElement.waterLevel-this.waterLevel<0) return -1;

else {

if(this.getLatitude()==otherDataElement.getLatitude())

{

if(this.getLongitude()-otherDataElement.getLongitude()>0) return 1;

if(this.getLongitude()==otherDataElement.getLongitude()) return 0;

return -1;

}

if(this.getLatitude()-otherDataElement.getLatitude()>0) return 1;

return -1;

}

}

if(this.populationDensity-otherDataElement.populationDensity>0) return 1;

return -1;

}

public int hashCode()

{

System.out.println("hash code chali");

return 0;

}

}
