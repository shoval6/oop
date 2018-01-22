package main.java.IO_Class;
import java.util.ArrayList;
import java.util.Collections;
import main.java.WiFi.WiFi;
import main.java.WiFi.WiFiSub;

/**
 * This function gets arrayList<wifiList>, and unite wifi's that have the same
 * date and coordinates to arraylist of wifi.
 */
public class OrganizedCSV {

	public ArrayList<WiFi> Orgenized(ArrayList<WiFi> arr) {

		ArrayList<WiFi> CSVList = new ArrayList<WiFi>();
		WiFi wifi = null;
		WiFiSub sub = null;
		int j = 0;

		for (int i = 0; i < arr.size(); i++) {
			if (CSVList.size() == 0)
				CSVList.add(arr.get(0));
			else {
				wifi = arr.get(i);
				if (wifi.getTime().equals(CSVList.get(j).getTime())) {
					sub = wifi.getWiFiSub().get(0);
					CSVList.get(j).add(sub);
				} else {
					CSVList.get(j).SignalSort();
					CSVList.get(j).sort10();
					CSVList.add(arr.get(i));
					j = CSVList.size() - 1;
				}
			}
		}

		Collections.sort(CSVList);
		return CSVList;
	}

}
