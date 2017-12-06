package oop2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		
		String path = "C:\\Users\\shoval\\workspace\\oop2";
		ArrayList<WiFi> fullCSV	= ReadCSV.Reading(path);
		ArrayList<WiFi> Final = OrgenizedCSV.Orgenized(fullCSV);
		Write.WriteCSV(Final ,"FinalCSV");
		Choose.choose(Final);
		File file = new File("C:\\Users\\shoval\\workspace\\oop2\\FilteredCSV.csv");
		KMLWriter.CSV2KML(file);
		
		
		
			
		
		
		
		
		
		
		
	}

}
