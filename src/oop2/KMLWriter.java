package oop2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimePrimitive;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
public class KMLWriter {


	/**
	 * this function writes information (network points) to the last kml file
	 */

	public void CSV2KML(ArrayList<WiFi> list){
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		//FileReader fr = new FileReader(csv);
		@SuppressWarnings("resource")
		//BufferedReader br = new BufferedReader(fr);

		/**
		 * Set icons for power signal
		 */
		// Green Icon//
		IconStyle iconStyle = new IconStyle();
		Icon icon = new Icon();
		icon.setHref("http://maps.google.com/mapfiles/kml/paddle/grn-circle.png");
		iconStyle.setScale(1.0);
		iconStyle.setIcon(icon);
		doc.createAndAddStyle().withId("green").setIconStyle(iconStyle);

		// Yellow Icon//
		Icon iconYellow = new Icon();
		iconYellow.setHref("http://maps.google.com/mapfiles/kml/paddle/ylw-circle.png");
		IconStyle iconStyleYellow = new IconStyle();
		iconStyleYellow.setScale(1.0);
		iconStyleYellow.setIcon(iconYellow);
		doc.createAndAddStyle().withId("yellow").setIconStyle(iconStyleYellow);

		// Red Icon
		Icon iconRed = new Icon();
		iconRed.setHref("http://maps.google.com/mapfiles/kml/paddle/red-circle.png");
		IconStyle iconStyleRed = new IconStyle();
		iconStyleRed.setScale(1.0);
		iconStyleRed.setIcon(iconRed);
		doc.createAndAddStyle().withId("red").setIconStyle(iconStyleRed);


		TimeStamp timestamp = new TimeStamp();
		double lat, lon;
		String signal;
		String ssid;
		String mac = "";
		int check = 0;
		int numWifi;

		/*
		 * Copy information from Csv file to Kml file
		 */

		int j=0;
		while (j != list.size()) {
			for(j=1; j<list.size(); j++){
				String str = list.get(j).toString();
				String[] srr = str.split(",");
				timestamp = createTS(srr[0]);
				lat = Double.parseDouble(srr[2]);
				lon = Double.parseDouble(srr[3]);
				numWifi = Integer.parseInt(srr[5]);
				for (int i = 0; i < numWifi; i++) {
					ssid = srr[6 + check];
					signal = (srr[9 + check]);
					mac = (srr[7 + check]);
					doc.createAndAddPlacemark().withTimePrimitive(timestamp)			//add placemarks to kml file!
					.withName(ssid).withStyleUrl(color(signal)).withDescription(mac)
					.createAndSetPoint().addToCoordinates(lon,lat);
					check += 4;

				}
				check = 0;
				//str = br.readLine();

			}
		}

		String KmlFile = "C:\\Users\\shoval\\workspace\\oop2\\KML.kml";
		try {
			kml.marshal(new File(KmlFile));
			System.out.println("Kml created !");

		} catch (Exception e) {

		}

	}

	/**
	 * This function change syntax for kml file and convert to TimeStamp
	 */
	private static TimeStamp createTS(String time) {
		/**
		 * this function creating the TimeStamp in GoogleEarth
		 */
		String str = time;
		String[] wholeStr = str.split(" ");
		String timenew = wholeStr[0] +"T"+wholeStr[1] +"Z"; 
		TimeStamp timeStamp = new TimeStamp ();
		timeStamp.setWhen(timenew);
		return timeStamp;
	}



	/**
	 * This function get power signal and set icon as per value green<yellow<red
	 */
	private static String color(String signal) {
		int a=Integer.parseInt(signal);
		String b="";
		if(a>-80)
			b=b+"#green";
		else if(a<-80&&a>-89)
			b=b+"#yellow";
		else
			b=b+"#red";
		return b;
	}

}

