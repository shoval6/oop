package Filters;

import WiFi.WiFi;

/**
 * 
 * @author  source code : Boaz Ben-Moshe
 *
 */

public class Location_Filter implements Filter{
	Double startLat , startLon , startAlt , endLat , endLon , endAlt;

	public Location_Filter(String startLat , String startLon,String startAlt , String endLat , String endLon, String endAlt ) {
		this.startLat = Double.parseDouble(startLat);
		this.startLon = Double.parseDouble(startLon);
		this.startAlt = Double.parseDouble(startAlt);
		this.endLat = Double.parseDouble(endLat);
		this.endLon = Double.parseDouble(endLon);
		this.endAlt = Double.parseDouble(endAlt);


	}


	public boolean test(WiFi wifi) {
		if (wifi != null) {
			if (((wifi.getPoint().getLon() > this.startLon) && (wifi.getPoint().getLon() < this.endLon))
					&& ((wifi.getPoint().getLat() > this.startLat) && (wifi.getPoint().getLat() < this.endLat))
			&& ((wifi.getPoint().getAlt() > this.startAlt) && (wifi.getPoint().getLat() < this.endAlt))){
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "location";
	}

}