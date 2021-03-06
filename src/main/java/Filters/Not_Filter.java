package main.java.Filters;

import main.java.WiFi.WiFi;

/**
 * 
 * @author  source code : Boaz Ben-Moshe
 *
 */

@SuppressWarnings("serial")
public class Not_Filter implements Filter{
	
	private Filter _filter;
	
	
	public Not_Filter(Filter f) {
		_filter = f;
	}
	
	public boolean test(WiFi wifi) {
		return !(_filter.test(wifi));
	}

	public String toString() {
		return "not("+_filter+")";
	}
}