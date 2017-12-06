package oop2;

import java.util.ArrayList;

/**
 * Wifi class defines a wifi point, representing a network by SSID,Mac,Channel and Date
 *
 */

public class WiFiSub implements Comparable<WiFiSub> {

	private String SSID;
	private String MAC;
	private int Frequncy , Signal;
	
	
	
	public WiFiSub(String SSID , String MAC , int Frequncy , int Signal){
		
		this.SSID = SSID;
		this.MAC = MAC;
		this.Frequncy = Frequncy;
		this.Signal = Signal;
		
	}
 
	
	public String getSSID(){
		return this.SSID; 
	}
	
	
	public WiFiSub(WiFiSub Other){
		this.SSID = Other.SSID;
		this.MAC = Other.MAC;
		this.Frequncy = Other.Frequncy;
		this.Signal = Other.Signal;
	
	}
	@Override
	public int compareTo(WiFiSub Other) {
		if(this.Signal==Other.Signal)
			return 0;
		else if(this.Signal < Other.Signal)
			return 1;
		else
			return -1;
		
	}
	
	@Override
	public String toString(){
		return SSID + "," + MAC + "," + Frequncy + "," + Signal;
	}
}