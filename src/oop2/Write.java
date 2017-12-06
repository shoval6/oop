package oop2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Write {
	/**
	 * this function output a csv file to original project folder
	 */
	public static void WriteCSV(ArrayList<WiFi> CSV , String name){
		String line = "Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1, SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3, SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5, SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7, SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9, SSID10, MAC10, Frequncy10, Signal10";		
		String path = name+".csv";
		StringBuilder writer = new StringBuilder();
		PrintWriter file = null;
		try {
			file = new PrintWriter(new File(path));
			writer.append(line + "\n");		
			for(int i=0; i<CSV.size(); i++){
				writer.append(CSV.get(i) + "\n");		
			}
			file.write(writer.toString());
			file.close();

		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
}