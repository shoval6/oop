package WiFi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * WifiList Class, Holds all the relevant info for each network.
 *  Each WifiList has number of variables: id,date,CoordinatesPoint,and Arraylist of wifi.
 *
 */

public class WiFi implements Comparable<WiFi> {

	static int _id = 0;
	private ArrayList<WiFiSub> arr;
	private Point3D point;
	private Date time;
	//private int numid;
	private String ID;
	private double Weight;

	public WiFi(Date time , String ID , Point3D point , double Weight){

		this.time = time;
		this.ID = ID;
		this.point = point;
		this.arr = new ArrayList<WiFiSub>();
		this.Weight = Weight;
	}

	
	public WiFi(WiFi other){
		this.time = other.time;
		this.ID = other.ID;
		this.point = other.point;
		this.arr = other.arr;
		this.Weight = other.Weight;
	}
	
	
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof WiFi){
			 WiFi ww = (WiFi) obj;
			if(this.time==ww.getTime() && this.ID==ww.getID() && this.point.equals(ww.getPoint()))
				for(int i=0; i<ww.getWiFiSub().size(); i++){
						if(!(this.getWiFiSub().get(i).equals(ww.getWiFiSub().get(i))))
						return false;
				}
				
}
		
		return true;
	}
	
	
	@Override
	 public int hashCode() {
	 int res = 10;
	 res += point.getLat();
	 res += point.getLon();
	 res += point.getAlt();
	 res += this.ID.hashCode();
	 res += this.time.hashCode();
	 
	 
	 for(int i=0; i<arr.size(); i++){
		 res += arr.get(i).getSignal();
		 res += arr.get(i).getMAC().hashCode();
		 res += arr.get(i).getSSID().hashCode();
		 res += arr.get(i).getFrequncy();
		 res += arr.get(i).getSignal();
		 
	 }
	return res;
	}
	 
	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
	}


	public void SignalSort(){
		Collections.sort(arr);

	}


	public void sort10(){
		ArrayList<WiFiSub> temp = new ArrayList<>();
		for(int i=0; i<arr.size() && i<10; i++)
			temp.add(arr.get(i));
		arr = temp;
	}

	public ArrayList<WiFiSub> getWiFiSub(){
		return arr;
	}

	public void setArr(){
		ArrayList<WiFiSub> set = new ArrayList<WiFiSub>();
		this.arr = set;
	}
	public Date getTime(){
		return time;
	}

	public String getID(){
		return ID;
	}

	public void add(WiFiSub sub){
		arr.add(sub);
	}

	public Point3D getPoint() {
		return point;
	}

	public void setPoint(Point3D point) {
		this.point = point;
	}

	/*
	@Override
	public int compareTo(WiFi other) {
		return this.time.compareTo(time);
	}

	 */


	@Override
	public int compareTo(WiFi Other) {
		if(this.getWeight()==Other.getWeight())
			return 0;
		else if(this.getWeight()<Other.getWeight())
			return 1;
		else
			return -1;

	}

	public String toStringAlgo1() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ans = _id++ + "," +arr.get(0).getMAC()+ "," + arr.get(0).getSSID()+ "," + arr.get(0).getFrequncy()+ "," + arr.get(0).getSignal()+ "," + point + "," + format.format(time) + "," +"Approx. w-center algo1" ;
		return ans;
	}


	public String toString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(time) +","+ ID + ","+ point +","+arr.size()+",";
		for (int i = 0; i < arr.size(); i++) {
			str += arr.get(i);
			if (arr.size()-1 != i)
				str+=",";
		}
		return str;
	}



}
