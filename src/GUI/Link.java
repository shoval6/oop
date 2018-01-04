package GUI;

import java.io.File;
import java.util.ArrayList;

import IO_Class.KMLWriter;
import IO_Class.OrganizedCSV;
import IO_Class.ReadCSV;
import IO_Class.ReadOrgenizedCSV;
import IO_Class.Write;
import WiFi.WiFi;
import oop2.*;
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

}