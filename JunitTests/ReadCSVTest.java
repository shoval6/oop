package test.java;

import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Test;
import main.java.IO_Class.ReadCSV;
import main.java.WiFi.WiFi;


public class ReadCSVTest {

	@Test
	public void testReading() throws FileNotFoundException {
		//String path="C:\\DEV\\workspace\\oop2\\forJunit";
		File f=new File("C:\\Users\\shoval\\Documents\\GitHub\\oop\\new");
		ArrayList<WiFi> fullCSV=ReadCSV.Reading(f);
		assertEquals(15, fullCSV.size());
	}

}
