package oop2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This function receives a path of folder , takes all the CSV files , and merge them into 
 * an ArrayList. 
 * 
 */
public class ReadCSV {
	public ArrayList<WiFi> Reading(File Folder){
		
		ArrayList<WiFi> CSVList = new ArrayList<WiFi>();
		String[] input = {};
		String[] files = Folder.list();
		String ID;
		String str;
		Date time = null;
		CoordinatesPoint point = null;
		WiFi list = null;
		WiFiSub sub = null;
		double Lat , Lon , Alt;

		for(int i=0; i<files.length; i++){
			if(files[i].contains(".csv")){
				Scanner temp = null;
				try {
					temp = new Scanner(new File(Folder.getPath() + "\\" + files[i]));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				input = temp.nextLine().split(",");
				ID = input[2];
				input = temp.nextLine().split(",");
				while(temp.hasNextLine()){
					input = temp.nextLine().split(",");
					if(input[10].equals("WIFI")){
						str = input[3];

						try {
							if (str.contains("/"))
								time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(str);
							else
								time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						String SSID = input[1];
						String MAC = input[0];
						int Frequncy = Integer.parseInt(input[4]);
						int Signal = Integer.parseInt(input[5]); 
						Lat = Double.parseDouble(input[6]);
						Lon = Double.parseDouble(input[7]);
						Alt = Double.parseDouble(input[8]);
						point = new CoordinatesPoint(Lat, Lon, Alt);
						sub = new WiFiSub(SSID, MAC, Frequncy, Signal);
						list = new WiFi(time , ID , point , 0);
						list.add(sub);
						CSVList.add(list);
					}

				}
				temp.close();
			}
		}
		
		return CSVList;
	
		}

	public static int  MacCount(ArrayList<WiFi> List){

		ArrayList<String> str = new ArrayList<String>();
		String temp;
		for(int i=0; i<List.size(); i++){
			for(int j=0; j<List.get(i).getWiFiSub().size(); j++){
				temp = List.get(i).getWiFiSub().get(j).getMAC();
				if(str.size()==0)
					str.add(temp);
				if(!(str.contains(temp)))
					str.add(temp);
			}
		}

		return str.size();


	}

	
	
	



}


