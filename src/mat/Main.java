package mat;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		// TODO Auto-generated method stub

		String path = "C:\\Users\\shoval\\workspace\\oop";
		ArrayList<String[]> fullCSV	= ReadAllCSVinFolder.ReadAllCSVFromFolder(path);
		WriteCSV.Write(fullCSV);
		int size=fullCSV.size();
		FiltrationOfRawCSV.newCSV("finalCSV.csv",size);
		Filter.choose();
		WriteKML.Write("finalCSV.csv");
	}
}
