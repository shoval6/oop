package mat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FiltrationOfRawCSV {

	public static void newCSV(String fileName, int size) throws FileNotFoundException{
		
		// *** copy csv to matrix:
		
		Scanner file=new Scanner(new File(fileName));
		Scanner file1 = new Scanner(new File(fileName));
		String[][] matrix = new String[size][11];		//end copy ***
											
		while (file1.hasNextLine()) {
			String[] str1 = file1.nextLine().split(",");
			for (int i = 1; i < size; i++) {
				if (i != 0)
					str1 = file1.nextLine().split(",");
				for (int j = 0; j < 11; j++) {
					matrix[i][j] = str1[j];
				}
			}
		}
		file.close();
		file1.close();

		// count numbers of networks from same time to first list, different times to second list
		
		int counter=0;
		String t=matrix[2][3];
		ArrayList<Integer> networksFromSameTime = new ArrayList<Integer>();
		ArrayList<String> time = new ArrayList<String>();
		
		/// *** list of all times
		
		for(int p=2;p<matrix.length;p++){
			if(matrix[p][3].equals(t))
				counter++;
			else{
				time.add(t);
				networksFromSameTime.add(counter);
				t=matrix[p][3];
				counter=1;
			}
		}   	
		networksFromSameTime.add(counter);	
		time.add(t);

		ArrayList <Integer> amp = new ArrayList<>();
		int d=2;
		for(int r=0;r<networksFromSameTime.size();r++){

			if(networksFromSameTime.get(r)>10){
				for(int h=d;h<d+networksFromSameTime.get(r);h++){
					amp.add(Integer.parseInt(matrix[h][5]));
				}
				selectionSort(amp,matrix,d);
				d=d+networksFromSameTime.get(r);
			}
			else{
				d=d+networksFromSameTime.get(r);
			}
			amp.clear();
		}
		lastFile(networksFromSameTime, matrix, time);
	}
	
	public static void selectionSort(ArrayList<Integer> amp,String[][] matrix,int d){
		for (int i = 0; i < amp.size() - 1; i++)
		{
			int index = i;
			for (int j = i + 1; j <  amp.size(); j++)
				if (amp.get(j) >  amp.get(index)) 
					index = j;
			int smallerNumber = amp.get(index);  
			swap(matrix,d+index,d+i);
			amp.set(index, amp.get(i));
			amp.set(i, smallerNumber);
		}
	}
	
	public static void swap(String[][] arr,int index,int h){
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

	public static void lastFile(ArrayList<Integer> networksFromSameTime,String[][] matrix,ArrayList<String> time){
		
	String[][] last=new String[networksFromSameTime.size()+1][46];
	int[] box = {1,0,4,5};
	int counter1=0,counter2=0,stop=1,run=0,g=2,c=6,h=2;
	for(int i=1; i<last.length;i++){
		last[i][0]=time.get(counter1);
		last[i][1]=matrix[0][2];
		last[i][2]=matrix[counter2+1+networksFromSameTime.get(counter1)][6];
		last[i][3]=matrix[counter2+1+networksFromSameTime.get(counter1)][7];
		last[i][4]=matrix[counter2+1+networksFromSameTime.get(counter1)][8];
		counter2 = counter2 + networksFromSameTime.get(counter1);
		if(networksFromSameTime.get(counter1)>10){
			last[i][5]="10";
			stop = 10;
		}
		else{
			last[i][5]=String.valueOf(networksFromSameTime.get(counter1));
			stop = Integer.parseInt(String.valueOf((networksFromSameTime.get(counter1))));
		}
		while(run<stop){
			int f;
			boolean t=true;
			for(f = h; f<matrix.length; f++){
				if(!time.get(counter1).equals(matrix[f][3]))
					g++;
				else{
					for(int m=0; m<4; m++){
						last[i][c] = matrix[g][box[m]];
						c++;
						if(t){
							last[i][1]=matrix[g][2];
							t=false;
						}
					}
					g++;
					run++;
					if(run==stop)
						f=matrix.length;
				}
			}
			t=true;
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

	String arr[][] = FiltrationOfRawCSV.sort(last);
	WriteCSV.Write(matrixToArrayList(arr));

}

	public static ArrayList<String[]> matrixToArrayList(String matrix[][]){
		
		ArrayList<String[]> temp = new ArrayList<>();
		for(int i=0; i<matrix.length; i++)
				temp.add(i, matrix[i]);
		return temp;
	}
	// source - https://stackoverflow.com
	public static String[][] sort(String arr[][]) {
		final String[][] data = arr; {


			Arrays.sort(data, new Comparator<String[]>() {
				@Override
				public int compare(final String[] entry1, final String[] entry2) {
					if(!(entry2[0]==arr[0][0])){
					final String time1 = entry1[0];
					final String time2 = entry2[0];
					return time1.compareTo(time2);

					}
					return 0;
				}
			});
		}
		return data;
	}
}
