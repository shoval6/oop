package main.java.Filters;

import java.text.SimpleDateFormat;

import main.java.WiFi.WiFi;

/**
 * 
 * @author  source code : Boaz Ben-Moshe
 *
 */

@SuppressWarnings("serial")
public class Time_Filter implements Filter{
		
	private String _start, _end;
	
	public Time_Filter(String start, String end) {
			_start = start;
			_end = end;
		}


		public boolean test(WiFi wifi) {
		boolean ans = false;
		if(wifi!=null) {
			SimpleDateFormat fr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String t = fr.format(wifi.getTime());
			String[] str = t.split(" ");
			int ts = _start.compareTo(str[1]);
			int te = _end.compareTo(str[1]);
			//		System.out.println(_start+"  <  "+t+"   <  "+_end+"  "+ts+"  "+te);
			// t0.compareTo(t)<0 && t1.compareTo(t)>0
			if(ts<=0 && te>0) {ans = true;}
		}
		return ans;
		}

		public String toString() {
			return ""+this.getClass().getName()+" ["+this._start+","+_end+"]";
		}

		
	}

