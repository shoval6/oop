package SQL;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.Messaging.SyncScopeHelper;

import WiFi.Point3D;
import WiFi.WiFi;
import WiFi.WiFiSub;
import de.micromata.opengis.kml.v_2_2_0.gx.TourControl;

import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class mySQL {

	private static String _ip = "5.29.193.52";
	private static String _url = "jdbc:mysql://5.29.193.52:3306/oop_course_ariel";
	private static String _user = "oop1";
	private static String _password = "Lambda1();";
	private static Connection _con = null;
	private static String _tableName="";

	public static void main(String[] args) {
		ArrayList<WiFi> _line1 =	test_101("5.29.193.52", "jdbc:mysql://"+"5.29.193.52"+":3306/oop_course_ariel","oop1","Lambda1();","ex4_db");
		//Filter filter = null;
//		filter = new FilterGPS(34, 31, 37, 33);
//		List<Line_Algo2> filteredList = filterList.filterList1(_line1,filter);
//		//Sorting the filteredList by signal (WiFi is implementing Comparable)
//		weightedCenterPoint.Line_Algo2.toCsv(filteredList,"C:/Users/dorle/Desktop");
		
//		filter = new FilterTime("2017-12-03 08:37:10", "2017-12-03 08:37:18");
//		List<Line_Algo2> filteredList = filterList.filterList1(_line1,filter);
//		//Sorting the filteredList by signal (WiFi is implementing Comparable)
//		weightedCenterPoint.Line_Algo2.toCsv(filteredList,"C:/Users/dorle/Desktop");
		
		
		
				for (WiFi wifi : _line1) {	
					System.out.println(wifi.toString());
	}
		
	}


	public static ArrayList<WiFi> test_101(String _ip,String _url,String _user, String _password, String _tableName) {
		Statement st = null;
		ResultSet rs = null;
		Connection _con = null;
		int max_id = -1;
		ArrayList<WiFi> List = new ArrayList<WiFi>();
		try {  
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+_tableName);
			rs = pst.executeQuery();
			ResultSetMetaData rsmd= rs.getMetaData();
			
			while (rs.next()) {
				WiFi wifi = new WiFi();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Point3D point = new Point3D(rs.getDouble(4),rs.getDouble(5),rs.getDouble(6));
				Date time = sdf.parse(rs.getString(2));
				wifi.setTime(time);
				wifi.setID(rs.getString(3));
				wifi.setPoint(point);
				
				int numNetworks= rs.getInt(7);
				for (int i = 0; i < numNetworks; i++) {
					wifi.add((new WiFiSub ("non", rs.getString(8+2*i),0, Integer.parseInt(rs.getString(9+2*i)))));
				}
				
				List.add(wifi);
				
			}
		} catch (SQLException | ParseException ex) {
			System.out.println("eror");
			Logger lgr = Logger.getLogger(mySQL.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {
				System.out.println("eror2");

				Logger lgr = Logger.getLogger(mySQL.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return List;
	}
}