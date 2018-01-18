package GUI;

import java.io.File;
import java.util.ArrayList;

import com.sun.javafx.css.CalculatedValue;

import Algo.Algorithm2;
import Algo.Calculating;
import IO_Class.KMLWriter;
import IO_Class.OrganizedCSV;
import IO_Class.ReadCSV;
import IO_Class.ReadOrgenizedCSV;
import IO_Class.Write;
import WiFi.WiFi;
import WiFi.Point3D;
import WiFi.WiFiSub;
public class Link {

	ArrayList<WiFi> DataBase = new ArrayList<WiFi>();
	ArrayList<WiFi> temp = new ArrayList<WiFi>();

	public void ReadDir(File path){
		ReadCSV read = new ReadCSV();
		OrganizedCSV org = new OrganizedCSV();
		DataBase = read.Reading(path);
		DataBase = org.Orgenized(DataBase);
	}

	public void ReadCSV(File path , boolean flag){
		ReadOrgenizedCSV read = new ReadOrgenizedCSV();
		if(flag){
			temp = read.Reading(path);
			DataBase = read.Merge(DataBase, temp);
		}
		else
			DataBase = read.Reading(path); 
	}


	public void SaveKML(){
		KMLWriter kml = new KMLWriter();
		kml.CSV2KML(DataBase);

	}

	public void SaveCSV(String name){
		Write save = new Write();
		save.WriteCSV(DataBase, name);


	}

	public String[] Algo1(String mac , int num){
		Calculating cal = new Calculating();
		WiFi wifi = cal.MacCompare(DataBase, num, mac);
		String str1 = wifi.getPoint().toString();
		String[] str2 = str1.split(",");
		return str2;
	}

	public String[] Algo2Sample(String[] str){
		ReadOrgenizedCSV rd = new ReadOrgenizedCSV();
		Algorithm2 algo2 = new Algorithm2();
		WiFi wifi = rd.ReadSample(str);
		wifi = algo2.CalcAlgo2(DataBase, wifi, 3);
		String[] res = wifi.getPoint().toString().split(",");
		return res;
	}

	public String[] Algo2Pairs(String[] str){
		Point3D point = new Point3D(0, 0, 0);
		WiFi wifi = new WiFi(null,null,point,0);
		WiFiSub sub1 , sub2 , sub3;

		sub1 = new WiFiSub(null, str[0], 0, Integer.parseInt(str[1]));
		sub2 = new WiFiSub(null, str[2], 0, Integer.parseInt(str[3]));
		sub3 = new WiFiSub(null, str[4], 0, Integer.parseInt(str[5]));
		wifi.add(sub1);
		wifi.add(sub2);
		wifi.add(sub3);
		Algorithm2 algo2 = new Algorithm2();
		wifi = algo2.CalcAlgo2(DataBase, wifi, 3);
		String[] res = wifi.getPoint().toString().split(",");
		return res;
	}

}