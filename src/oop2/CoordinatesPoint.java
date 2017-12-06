package oop2;

/**
 *  This class represent lat lon alt coordinates.
 *
 */
public class CoordinatesPoint {

	private double Lat , Lon , Alt;
	
	public CoordinatesPoint(double Lat , double Lon , double Alt){
		
		this.Lat = Lat;
		this.Lon = Lon;
		this.Alt = Alt;
		
		
	}
	
	public double getLat() {
		return Lat;
	}

	public void setLat(double lat) {
		Lat = lat;
	}

	public double getLon() {
		return Lon;
	}

	public void setLon(double lon) {
		Lon = lon;
	}

	public String toString(){
		return Lat + "," + Lon + "," + Alt;
	}
}
