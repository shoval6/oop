package oop2;

import java.util.ArrayList;

public class Algorithm2 {

	Calculating calc = new Calculating();
	
	public ArrayList<WiFi> UserLocation(ArrayList<WiFi> Organized , ArrayList<WiFi> nogps , int num){
		
		WiFi sample = null;
		ArrayList<WiFi> Algo2 = new ArrayList<WiFi>();
		for(int i=0; i<nogps.size(); i++){
			sample = new WiFi(CalcAlgo2(Organized , nogps.get(i) , num ));
			Algo2.add(sample);
		}
		
		return Algo2;
		
	}
	
	public WiFi CalcAlgo2(ArrayList<WiFi> temp , WiFi nogps , int num ){
		
		//ArrayList<WiFi> Organized = new ArrayList<WiFi>();
		ArrayList<WiFi> Organized = new ArrayList<WiFi>(temp);
		CoordinatesPoint point = null;
		calc.PICalc(Organized , nogps);
		calc.Sort(Organized, num);
		point = calc.PointCalc(Organized , "Algo2");
		
		nogps.getPoint().setLat(point.getLat());
		nogps.getPoint().setLon(point.getLon());
		nogps.getPoint().setAlt(point.getAlt());
		
		return nogps;
	}
	
}
