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


	public ArrayList<WiFi> ReadTable(Connecting det) {
		Statement st = null;
		ResultSet rs = null;
		Connection _con = null;
		
		
		ArrayList<WiFi> List = new ArrayList<WiFi>();
		try {  
			_con = DriverManager.getConnection(det.getUrl(), det.getUser(), det.getPass());
			st = _con.createStatement();

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+det.getTableName());
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
		
		for(WiFi s : List)
			System.out.println(s.toString());
		
		
		return List;
	}
}