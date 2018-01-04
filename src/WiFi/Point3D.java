package WiFi;

/**
 *  This class represent lat lon alt coordinates.
 *
 */
public class Point3D {

	private double Lat , Lon , Alt;
	
	public Point3D(double Lat , double Lon , double Alt){
		
		this.Lat = Lat;
		this.Lon = Lon;
		this.Alt = Alt;
		
		
	}
	
	public boolean equals(Point3D point){
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
