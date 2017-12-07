import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinatesPointTest {

	@Test
	public void testCoordinatesPoint() {
		CoordinatesPoint check=new CoordinatesPoint(38.624, 76.151, 54.125);
		assertEquals(38.624, check.getLat(),3);
		assertEquals(76.151, check.getLon(),3);
	}

	@Test
	public void testGetLat() {
		CoordinatesPoint check=new CoordinatesPoint(38.624, 76.151, 54.125);
		double check2=check.getLat();
		assertEquals(38.624, check2,3);
	}

	@Test
	public void testSetLat() {
		CoordinatesPoint check=new CoordinatesPoint(38.624, 76.151, 54.125);
		check.setLat(80.01);
		assertEquals(80.01, check.getLat(),2);
	}

	@Test
	public void testGetLon() {
		CoordinatesPoint check=new CoordinatesPoint(38.624, 76.151, 54.125);
		double check2=check.getLon();
		assertEquals(76.151, check2,3);
	}

	@Test
	public void testSetLon() {
		CoordinatesPoint check=new CoordinatesPoint(38.624, 76.151, 54.125);
		check.setLon(80.01);
		assertEquals(80.01, check.getLon(),2);
	}
}
