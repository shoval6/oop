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
	
	public boolean equals(CoordinatesPoint point){
		if(this.Lat == point.getLat() && this.Lon == point.getLon() && this.Alt == point.getAlt())
			return true;
		return false;
	}
	
	public double getLat() {
		return Lat;
	}

	public void setLat(double lat) {
		this.Lat = lat;
	}

	public double getLon() {
		return Lon;
	}

	public void setLon(double lon) {
		this.Lon = lon;
	}

	public double getAlt() {
		return Alt;
	}

	public void setAlt(double Alt) {
		this.Alt = Alt;
	}
	public String toString(){
		return Lat + "," + Lon + "," + Alt;
	}
}
