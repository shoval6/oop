package oop2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		
		/*

		String path = "C:\\Users\\shoval\\workspace\\oop2";
		ArrayList<WiFi> fullCSV	= ReadCSV.Reading(path);
		ArrayList<WiFi> Final = OrganizedCSV.Orgenized(fullCSV);
		Write.WriteCSV(Final ,"FinalCSV");
		Choose.choose(Final);
		File file = new File("C:\\Users\\shoval\\workspace\\oop2\\FilteredCSV.csv");
		KMLWriter.CSV2KML(file);		
		
		*/
		
		
		//Algo1
		
		Algorithm1 algo1 = new Algorithm1();
		ArrayList<WiFi> comb = ReadOrgenizedCSV.Reading("comb.csv");
		ArrayList<WiFi> Final1 = algo1.MacLocation(comb , 4);
		Write.WriteCSV(Final1, "Algo1");


		
		//Algo2
		
		Algorithm2 algo2 = new Algorithm2();
		ArrayList<WiFi> nogps = ReadOrgenizedCSV.Reading("nogps.csv");
		ArrayList<WiFi> Final2 = algo2.UserLocation(comb, nogps, 4);
		
		Write.WriteCSV(Final1, "Algo2");
		
		
	
	
	
	}

}
