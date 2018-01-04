package Filters;

import WiFi.WiFi;

/**
 * 
 * @author  source code : Boaz Ben-Moshe
 *
 */

public class ID_Filter implements Filter{

	private String id;
	public ID_Filter(String id) {
		this.id = id;
	}
	

	public boolean test(WiFi wifi) {
		if (wifi != null) {
			if (this.id.equals(wifi.getID())) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return "id";
	}

}