package oop2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * WifiList Class, Holds all the relevant info for each network.
 *  Each WifiList has number of variables: id,date,CoordinatesPoint,and Arraylist of wifi.
 *
 */

public class WiFi implements Comparable<WiFi> {

	private ArrayList<WiFiSub> arr;
	private CoordinatesPoint point;
	private Date time;
	//private int numid;
	private String ID;

	public WiFi(Date time , String ID , CoordinatesPoint point){

		this.time = time;
		this.ID = ID;
		this.point = point;
		//this.numid = numid;
		this.arr = new ArrayList<WiFiSub>();

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

	public WiFiSub getWiFiSub(){
		return arr.get(0);
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

	public CoordinatesPoint getPoint() {
		return point;
	}

	public void setPoint(CoordinatesPoint point) {
		this.point = point;
	}
	
	@Override
	public int compareTo(WiFi other) {
		return this.time.compareTo(time);
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
