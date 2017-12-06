package mat;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class WriteCSV {	
	
 
	public static void Write(ArrayList<String[]> CSV){		
		/**
		 * this function output a csv file to original project folder
		 */
		String[] s;
		try {
			FileWriter writer = new FileWriter(new File("finalCSV.csv"));
			for (int i = 0; i < CSV.size(); i++) {
				s = CSV.get(i);	
				for(int j=0; j<s.length; j++){
				if(s[j] == null){
					writer.append(" ");
					writer.append(',');
				}
				else{
				writer.append(s[j]);
					writer.append(',');
				}
				}
				writer.append('\n');
		}
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}	