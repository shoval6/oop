package oop2;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import uk.me.jstott.jcoord.LatLng;



public class Filter {
	private String filter;
	private String value;
	private CoordinatesPoint place;
	private double distance;

	public Filter (String filter, String value){
		this.filter = filter;
		this.value = value;
	}
	public Filter (String filter, CoordinatesPoint place, double distance){
		this.filter = filter;
		this.place=place;
		this.distance=distance;

	}
	public boolean check(WiFi wifi){

		WiFiSub array=wifi.getWiFiSub().get(0);

		switch(filter){
		case "Time":{ String pattern = "dd/MM/yyyy hh:mm";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date Start = (Date) format.parse(value.substring(0, value.indexOf(';')));
			Date End = (Date) format.parse(value.substring(value.indexOf(';')+1,value.length() ));
			if (Start.before(wifi.getTime())&&End.after(wifi.getTime()))
				return true;
		}
				catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;

			}
	
		case "ID": {
			String ID = wifi.getID();
			if(ID.equals(value))
				return true;
			return false;

		}
		case "Distance": {

			LatLng lld1 = new LatLng(wifi.getPoint().getLat(), wifi.getPoint().getLon());
			LatLng lld2 = new LatLng(place.getLat(), place.getLon());
			Double distance = lld1.distance(lld2);

			if(this.distance>=distance)
				return true;
			return false;

		}

		}
		return false;
	}
}


