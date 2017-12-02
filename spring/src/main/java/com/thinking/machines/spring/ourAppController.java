package com.thinking.machines.spring; 
import org.springframework.http.*;
import org.springframework.http.client.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController()
@RequestMapping("/ourApp")
public class ourAppController {
@RequestMapping(value="/getFavourablePoints",params={"labour_cost","budget","stateArea"},produces="application/json")   
public LinkedList<DTODataElement> index(@RequestParam(value="labour_cost",required=true)double labour_cost,@RequestParam(value="budget",required=true)double budget,@RequestParam(value="stateArea",required=true)double stateArea)
{
FavourablePointsCalculator fpc=new FavourablePointsCalculator("Dataset.txt",labour_cost,budget,stateArea);
System.out.println(fpc.getFavourablePoints().size());
return fpc.getFavourablePoints();
}
@RequestMapping(value="/getWellPoints",produces="application/json")
public LinkedList<WellPoint> getWellPoints() {
WellPointParser fpp=new WellPointParser("WellPoints.txt");
System.out.println(fpp.getWellPoints().size());
return fpp.getWellPoints();
}
@RequestMapping(value="/getCityAndDistrictList",produces="application/json")
public LinkedList<DistrictDivision> getCityAndDistrictList() {
LatLongList ll=new LatLongList();
return ll.getCityAndDistrictList();                    
}
@RequestMapping(value="/",produces="application/json")
    public String index() {
        return "Hello!! This is the index Page!!";
    }
}
