package mat;

import java.io.File;
import java.io.FileNotFoundException;
import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;

public class file {

	public static void main(String[] args) throws FileNotFoundException {
	
		
		Kml kml = KmlFactory.createKml();
		kml.createAndSetPlacemark()
		.withName("Java User Group Hessen - JUGH!")
		   .withVisibility(true)
		   .withOpen(false)
		   .withDescription("die Java User Group Hessen")
		   .withStyleUrl("styles.kml#jugh_style")
		   .createAndSetPoint()
		      .withExtrude(false)
		      .withAltitudeMode(AltitudeMode.CLAMP_TO_GROUND)
		      .addToCoordinates("9.444652669565212,51.30473589438118,0");
			
		kml.marshal(new File("naor.kml"));
		//kml.marshal(System.out);
	
	
	
	}
	
}
