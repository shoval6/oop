package mat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class wififinal {
	
	public static void fileNames() throws IOException{
		File folder = new File("C:\\Users\\shoval\\workspace\\matala1oop");
		ArrayList<String> justCsvInFolder = new ArrayList<String>();
		File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	String temp=listOfFiles[i].getName();
		    	String[] splittedFileName = temp.split("\\.");
		    	if(splittedFileName[splittedFileName.length-1].equals("csv")){
		    		System.out.println("File " + listOfFiles[i].getName());
		    		justCsvInFolder.add(temp);
		    	}
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		    System.out.print("Working on: ");
		    System.out.println(justCsvInFolder);
		    readFile(justCsvInFolder);
	}
	
	public static void readFile(ArrayList<String> x) throws IOException{
		for(int m=0;m<x.size();m++){
		String path=x.get(m);
		Scanner file = new Scanner(new File(path));
		int count = 0;
		while (file.hasNextLine()) {		//number of lines in the table
			count++;
			file.nextLine();
		}

		file=new Scanner(new File(path));
		Scanner file1 = new Scanner(new File(path));
		String[][] matrix = new String[count][11];
		String[] temp=file.nextLine().split(",");
		matrix[0][2]=temp[2];						//taking the id from line 0
		while (file1.hasNextLine()) {
			String[] str1 = file1.nextLine().split(",");
			for (int i = 1; i < count; i++) {
				if (i != 0)
					str1 = file1.nextLine().split(",");
				for (int j = 0; j < 11; j++) {
					matrix[i][j] = str1[j];
				}
			}
		}

		int counter=0;
		String t=matrix[2][3];
		ArrayList al = new ArrayList();
		ArrayList time = new ArrayList<String>();
		/// *** list of all times
		for(int p=2;p<matrix.length;p++){
			if(matrix[p][3].equals(t))
				counter++;
			else{
				time.add(t);
				al.add(counter);
				t=matrix[p][3];
				counter=1;
			}
		}   	
		al.add(counter);	
		time.add(t);
		
		//find all amplitudes in constant time:
		ArrayList <Integer> amp = new ArrayList<>();
		for(int r=0;r<al.size();r++){
			if((int)al.get(r)>10){
				for(int h=2;h<(int)al.get(r)+2;h++){
					amp.add(Integer.parseInt(matrix[h][5]));
				}
				selectionSort(amp,matrix,(int)al.get(r));
			}
			amp.clear();
		}
		file1.close();file.close();
		lastFile(al,matrix,time,path);
	}
	}

	public static void selectionSort(ArrayList<Integer> amp,String[][] matrix,int b){
		for (int i = 0; i < amp.size() - 1; i++)
		{
			int index = i;
			for (int j = i + 1; j <  amp.size(); j++)
				if (amp.get(j) >  amp.get(index)) 
					index = j;
			int smallerNumber = amp.get(index);  
			swap(matrix,index,i,b);
			amp.set(index, amp.get(i));
			amp.set(i, smallerNumber);
		}
	}

	public static void swap(String[][] arr,int index,int h,int b){
		index=index+2;
		h=h+2;
		String[] temp=new String[11];
		String[] temp2=new String[11];
		for(int i=0; i<11;i++){
			temp[i]=arr[h][i];
			temp2[i]=arr[index][i];
		}
		for(int i=0; i<11;i++){
			arr[h][i]=temp2[i];
			arr[index][i]=temp[i];
		}
	}

	public static void lastFile(ArrayList al,String[][] matrix,ArrayList<String> time,String path){
		
		String[][] last=new String[al.size()+1][46];
		int[] box = {1,0,4,5}; // index of col to change
		int counter1=0,counter2=0,stop=1,run=0,g=2,c=6,h=2;
		for(int i=1; i<last.length;i++){    // run on rows in "last" matrix 
			last[i][0]=time.get(counter1);  // set time 
			last[i][1]=matrix[0][2];        // set ID 
			last[i][2]=matrix[counter2+1+(int)al.get(counter1)][6];  // set Lat
			last[i][3]=matrix[counter2+1+(int)al.get(counter1)][7];  // set Lon
			last[i][4]=matrix[counter2+1+(int)al.get(counter1)][8];  // set Alt
			counter2 = counter2 +(int)al.get(counter1);
			if((int)al.get(counter1)>10){
				last[i][5]="10"; // set #wifi num
				stop = 10;
			}
			else{
				last[i][5]=String.valueOf(al.get(counter1));  // set #wifi num
				stop = Integer.parseInt(String.valueOf((al.get(counter1)))); 
			}
			while(run<stop){  
				int f;
				for(f = h; f<matrix.length; f++){
					if(!time.get(counter1).equals(matrix[f][3]))
						g++;
					else{
						for(int m=0; m<4; m++){
							last[i][c] = matrix[g][box[m]];
							c++;
						}
						g++;
						run++;
						if(run==stop)
							f=matrix.length;
					}
				}
			}
			counter1++;
			c=6;
			run = 0;
			h = g;
		}

		String line = "Time, ID, Lat, Lon, Alt, #WiFi networks (up to 10), SSID1, MAC1, Frequncy1, Signal1, SSID2, MAC2, Frequncy2, Signal2, SSID3, MAC3, Frequncy3, Signal3, SSID4, MAC4, Frequncy4, Signal4, SSID5, MAC5, Frequncy5, Signal5, SSID6, MAC6, Frequncy6, Signal6, SSID7, MAC7, Frequncy7, Signal7, SSID8, MAC8, Frequncy8, Signal8, SSID9, MAC9, Frequncy9, Signal9, SSID10, MAC10, Frequncy10, Signal10";
		String[] words = line.split(",");
		for(int i=0; i<words.length; i++){
			last[0][i] = words[i];

		}

		try {
			FileWriter writer = new FileWriter("final"+path+".csv");
			for (int i = 0; i < last.length; i++) {
				for (int j = 0; j < 46; j++) {
					writer.append((last[i][j]));
					writer.append(',');
				}
				writer.append('\n');
			}
			writer.close();
			writeKmlFile(last,path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeKmlFile(String[][] matrix,String nat) throws FileNotFoundException{
		String line1="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String line2="<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>";
		try {
			FileWriter writer = new FileWriter("final"+nat+".kml");
			writer.append(line1);
			writer.append('\n');
			writer.append(line2);
			writer.append('\n');
			for(int i=1; i<matrix.length;i++){
				int name=6,time =0, cord=3, amp=9;
					while(matrix[i][name]!=null){
						writer.append("<Placemark>");
						writer.append('\n');
						writer.append("<name>");
						writer.append(matrix[i][name]);
						writer.append("</name>");
						writer.append('\n');
						writer.append("<description>");
						writer.append(matrix[i][time]);
						writer.append("</description><styleUrl>");
						if(Integer.parseInt(matrix[i][amp])>-69)
							writer.append("#green");
						else if((Integer.parseInt(matrix[i][amp])<-69)&&(Integer.parseInt(matrix[i][amp])>-80))
							writer.append("#yellow");
						else if((Integer.parseInt(matrix[i][amp])<-80))
							writer.append("#red");
						writer.append("</styleUrl>");
						writer.append('\n');
						writer.append("<Point>");
						writer.append('\n');
						writer.append("<coordinates>");
						writer.append(matrix[i][cord]);writer.append(",");writer.append(matrix[i][cord-1]);
						writer.append("</coordinates></Point>");
						writer.append('\n');
						writer.append("</Placemark>");
						if(name+4>45||matrix[i][name+4]==null)
							break;
						else{
							name=name+4;
							amp=amp+4;
						}
							
					}
					writer.append('\n');
				}
			writer.append("</Folder>");
			writer.append('\n');
			writer.append("</Document></kml>");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		fileNames();
	}
}
