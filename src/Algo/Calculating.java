package Algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import WiFi.Point3D;
import WiFi.WiFi;
import WiFi.WiFiSub;

public class Calculating {

	private int  MinDiff = 3 , Norm = 10000 , Power = 2 , DiffNoSignal = 100;
	private double SigDiff = 0.4;


	public WiFi MacCompare(ArrayList<WiFi> Organized , int num , String mac){

		ArrayList<WiFi> temp = new ArrayList<WiFi>();
		WiFi wifi = null;
		WiFiSub sub = null;
		Point3D point = null;


		for(int i=0; i<Organized.size(); i++){
			for(int j=0; j<Organized.get(i).getWiFiSub().size(); j++){
				sub = Organized.get(i).getWiFiSub().get(j);
				if(sub.getMAC().equals(mac)){
					wifi = new WiFi(Organized.get(i));
					wifi.setArr();
					wifi.add(sub);
					wifi.setWeight(1/(Math.pow(sub.getSignal(), 2)));
					temp.add(wifi);
					Organized.get(i).getWiFiSub().remove(j);

				}
			}
		}

		if(temp.size()>1){
			Sort(temp , num);
			point = PointCalc(temp , "Algo1");
			temp.get(0).setPoint(point);
		}


		return temp.get(0);
	}


	public void Sort(ArrayList<WiFi> list , int num){
		Collections.sort(list);
		ArrayList<WiFi> temp = new ArrayList<WiFi>();
		if(num<list.size()){
			for(int i=0; i<list.size() && i<num; i++)
				temp.add(list.get(i));
			list.clear();
			list.addAll(temp);
		
		}

	}



	public Point3D PointCalc(ArrayList<WiFi> temp , String name){

		double wLat = 0 , wLon = 0 , wAlt = 0 , Weight = 0; 
		WiFi wifi = null;

		
		if(Check(temp)&&name.equals("Algo2")){
		wLat = 0;
		wLon = 0;
		wAlt = 0;

		Point3D point = new Point3D(wLat , wLon ,wAlt);

		return point;
		
		}
		
			for(int i=0; i<temp.size(); i++){
				wifi = temp.get(i);
				wLat += (wifi.getPoint().getLat())*(wifi.getWeight());
				wLon += (wifi.getPoint().getLon())*(wifi.getWeight());
				wAlt += (wifi.getPoint().getAlt())*(wifi.getWeight());
				Weight += wifi.getWeight();
			}

			wLat = wLat/Weight;
			wLon = wLon/Weight;
			wAlt = wAlt/Weight;


		Point3D point = new Point3D(wLat , wLon ,wAlt);
		
		return point;
		
	}




	public void PICalc(ArrayList<WiFi> Organized , WiFi  nogps){

		WiFiSub sub1 = null;
		WiFiSub sub2 = null;
		double PI = 1 , Diff = 0 , w;
		String mac;

		for(int i=0; i<Organized.size(); i++){   // run over all database
			for(int j=0; j<nogps.getWiFiSub().size(); j++){  // run over all wifi's in nogps sample 
				sub1 = nogps.getWiFiSub().get(j);
				mac = nogps.getWiFiSub().get(j).getMAC();
				for(int r=0; r<Organized.get(i).getWiFiSub().size(); r++){
					sub2 = Organized.get(i).getWiFiSub().get(r);
					if(mac.equals(sub2.getMAC())){
						Diff = Math.max(Math.abs((sub1.getSignal()) - (sub2.getSignal())),
								this.MinDiff);
						r = Organized.get(i).getWiFiSub().size();
					}
					else if(!mac.equals(sub2.getMAC()) && r == (Organized.get(i).getWiFiSub().size())-1)
						Diff = this.DiffNoSignal;

				}

				w = this.Norm / (Math.pow(Diff, this.SigDiff) * Math.pow(sub1.getSignal(), this.Power));
				PI *= w;

			}

			Organized.get(i).setWeight(PI);
			PI = 1;
		}


	}


	public boolean Check(ArrayList<WiFi> list){

		double x , y = 0;
		for(int i=1; i<list.size(); i++){
			x = list.get(0).getWeight();
			y = list.get(i).getWeight();
			if(x!=y)
				return false;

		}
		return true;

	}


}

