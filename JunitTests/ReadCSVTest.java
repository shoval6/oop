import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Test;

public class ReadCSVTest {

	@Test
	public void testReading() throws FileNotFoundException {
		String path="C:\\DEV\\workspace\\oop2\\forJunit";
		ArrayList<WiFi> fullCSV	= ReadCSV.Reading(path);
		assertEquals(40, fullCSV.size());
	}

}
