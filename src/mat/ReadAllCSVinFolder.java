package mat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAllCSVinFolder{	// function that read all the csv files only from any folder

	public static ArrayList<String[]> ReadAllCSVFromFolder(String path) throws FileNotFoundException{

		ArrayList<String[]> CSVList = new ArrayList<String[]>();
		ArrayList<String> models = new ArrayList<String>();
		boolean ok = true;
		int index = 0;
		String[] str1 = {};
		File folder = new File(path);
		String[] files = folder.list();
		for(int i=0; i<files.length; i++){
			if(files[i].contains(".csv")){
				Scanner temp = new Scanner(new File(path + "\\" + files[i]));
				if(ok){
					ok = false;
				}
				else{
					str1 = temp.nextLine().split(",");
					models.add(0 , str1[2]);
					str1 = temp.nextLine().split(",");
				}
				while(temp.hasNextLine()){
					str1 = temp.nextLine().split(",");
					if(models.size()==0)
						models.add(str1[2]);
					str1[2] = models.get(0);
					CSVList.add(index, str1);
					index++;
				}
				temp.close();
			}
		}
		return CSVList;
	}
}

