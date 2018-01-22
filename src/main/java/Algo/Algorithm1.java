package main.java.Algo;

import java.util.ArrayList;

import main.java.WiFi.WiFi;
import main.java.WiFi.WiFiSub;



public class Algorithm1 {

	public ArrayList<WiFi> MacLocation(ArrayList<WiFi> Organized , int num){
		
		Calculating calc = new Calculating();
		
		
		ArrayList<WiFi> Final = new ArrayList<WiFi>();
		WiFi wifi = null;
		WiFiSub sub = null;
		String mac;
		
		for(int i=0; i<Organized.size(); i++){

			for(int j=0; j<Organized.get(i).getWiFiSub().size(); j++){
				sub = Organized.get(i).getWiFiSub().get(j);
				mac = sub.getMAC();
				wifi = calc.MacCompare(Organized, num, mac);
				Final.add(wifi);
				j--;
			}
		
		}
		
		
		return Final;
		
		
		
		
	}
	
	

	
}
