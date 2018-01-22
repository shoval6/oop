package main.java.IO_Class;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import main.java.WiFi.Point3D;
import main.java.WiFi.WiFi;
import main.java.WiFi.WiFiSub;


public class ReadOrgenizedCSV {

	public ArrayList<WiFi> Reading(File file){
		
		ArrayList<WiFi> CSVList = new ArrayList<WiFi>();
		Scanner temp = null;
		try {
			temp = new Scanner(new File(file.getPath()));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String[] input = {};
		String ID , MAC , SSID , str;
		Date time = null;
		Point3D point = null;
		WiFi wifi = null;
		WiFiSub sub = null;
		int Frequency , Signal;
		double Lat , Lon , Alt;
		input = temp.nextLine().split(",");
		while(temp.hasNext()){
		input = temp.nextLine().split(",");
		str = input[0];
		ID = input[1];
		if(input[2].contains("?")&&input[3].contains("?")&&input[4].contains("?")){
			input[2] = String.valueOf(0);
			input[3] = String.valueOf(0);
			input[4] = String.valueOf(0);
		}
		Lat = Double.parseDouble(input[2]);
		Lon = Double.parseDouble(input[3]);
		Alt = Double.parseDouble(input[4]);
		point = new Point3D(Lat, Lon, Alt);

		try {
			
			if(str.contains("AM") || str.contains("PM"))
				time = new SimpleDateFormat("dd/MM/yy HH:mm a").parse(str);
			
			else if(str.contains("/"))
			time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(str);
			
			else
				time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
			} catch (ParseException e) {
			e.printStackTrace();
		}		
		wifi = new WiFi(time , ID , point , 0);
		for(int i=0; i<input.length; i++){
			if(!((6+(i*4))==input.length)){
			SSID = input[6+(i*4)];
			MAC = input[7+(i*4)];
			Frequency = Integer.parseInt(input[8+(i*4)]);	
			Signal = (int)Double.parseDouble(input[9+(i*4)]);
			sub = new WiFiSub(SSID, MAC, Frequency , Signal);
			wifi.add(sub);
			}			
			else
				i = input.length;

		}
		CSVList.add(wifi);

		}
		
		temp.close();
		return CSVList;
		
		
		
	}
	
	
	public WiFi ReadSample(String[] str){
		double Lat , Lon , Alt;
		WiFi wifi = null;
		WiFiSub sub = null;
		Point3D point = null;
		String MAC;
		int Signal;
		Lat = Double.parseDouble(str[2]);
		Lon = Double.parseDouble(str[3]);
		Alt = Double.parseDouble(str[4]);
		point = new Point3D(Lat, Lon, Alt);

		wifi = new WiFi(null, null , point , 0);
		for(int i=0; i<str.length; i++){
			if(!((6+(i*4))==str.length)){
			MAC = str[7+(i*4)];
			Signal = (int)Double.parseDouble(str[9+(i*4)]);
			sub = new WiFiSub(null, MAC, 0 , Signal);
			wifi.add(sub);
			}			
			else
				i = str.length;

		}
		
		return wifi;
		
	}
	
	
	public ArrayList<WiFi> Merge(ArrayList<WiFi> List , ArrayList<WiFi> temp){
		Set<WiFi> hset = new HashSet<WiFi>();
		ArrayList<WiFi> arr = new ArrayList<WiFi>();
		hset.addAll(List);
		hset.addAll(temp);
		arr.addAll(hset);
		return arr;
	}

	
	
}
