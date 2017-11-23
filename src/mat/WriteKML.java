package mat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

public class WriteKML {
	
	public static void informationWriter(String[] arr,Document doc,ArrayList<String> ssid){
		
		/**
		 * this function writes information (network points) to the last kml file
		 */
		
		IconStyle iconStyle =new IconStyle();
        Icon icon = new Icon();
        icon.setHref("http://maps.google.com/mapfiles/kml/paddle/grn-circle.png");
        iconStyle.setScale(1.0);
        iconStyle.setIcon(icon);
        doc.createAndAddStyle().withId("green").setIconStyle(iconStyle);
		
		Icon iconYellow = new Icon();
        iconYellow.setHref("http://maps.google.com/mapfiles/kml/paddle/ylw-circle.png");
        IconStyle iconStyleYellow =new IconStyle();
        iconStyleYellow.setScale(1.0);
        iconStyleYellow.setIcon(iconYellow);
        doc.createAndAddStyle().withId("yellow").setIconStyle(iconStyleYellow);
		
		Icon iconRed = new Icon();
        iconRed.setHref("http://maps.google.com/mapfiles/kml/paddle/red-circle.png");
        IconStyle iconStyleRed =new IconStyle();
        iconStyleRed.setScale(1.0);
        iconStyleRed.setIcon(iconRed);
        doc.createAndAddStyle().withId("red").setIconStyle(iconStyleRed);

		if(ssid.size()>1){
			int amp=9,mac=7;
			for(int i=0;i<ssid.size();i++){	
				doc.createAndAddPlacemark().withTimePrimitive(createTS(arr[0]))			//add placemarks to kml file!
		        .withName(ssid.get(i)).withStyleUrl(color(arr[amp])).withDescription(arr[mac])
		        .createAndSetPoint().addToCoordinates(Double.parseDouble(arr[3]),Double.parseDouble(arr[2]));
				amp+=4;mac+=4;
			}
		}
		else{
			doc.createAndAddPlacemark().withTimePrimitive(createTS(arr[0]))	
	        .withName(arr[6]).withStyleUrl(color(arr[9])).withDescription(arr[7])
	        .createAndSetPoint().addToCoordinates(Double.parseDouble(arr[3]),Double.parseDouble(arr[2]));
		}
	}

	public static String color(String amplitude){
		/**
		 * this function decided which color is suitable for each network on the map, by its amplitude
		 */
		int a=Integer.parseInt(amplitude);
		String b="";
		if(a>-80)
			b=b+"#green";
		else if(a<-80&&a>-89)
			b=b+"#yellow";
		else
			b=b+"#red";
		return b;
	}
	
	public static TimeStamp createTS(String time) {
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

	public static void Write(String file) throws FileNotFoundException {
		/**
		 * this function creates the last kml file in the folder
		 */
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument().withName("oopShovalNaor");
		Scanner sc = new Scanner(new File(file));
		String[] line = {};
		String temp;
		ArrayList<String> ssid=new ArrayList<String>();
		sc.nextLine();			//skip first line
		while(sc.hasNextLine()){
			temp=sc.nextLine();
			line=temp.split(",");
				for(int i=6; i<line.length;i+=4){
				if(line[i].equals(" "))
					break;			//in case of end of line
				ssid.add(line[i]);
			}
			informationWriter(line, doc,ssid);
			ssid.clear();
		}
		try {
            kml.marshal(new File("FinalKML.kml"));
        } catch (FileNotFoundException ex) {
        	ex.printStackTrace();
        }         
		sc.close();
	}
}
