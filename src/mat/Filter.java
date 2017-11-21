package mat;

import java.time.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Filter {

	public static void choose() throws FileNotFoundException, ParseException{
		ArrayList<String[]> temp = new ArrayList<String[]>();
		Scanner console = new Scanner(System.in);  
		Scanner console2 = new Scanner(System.in);  
		boolean ok = true;
		boolean ok2 = true;
		String y = null;
		String x = null;
		intro();
		System.out.println("Press: ");
		int z = console.nextInt();
		switch (z){
		case 1:				
			while(ok){
				System.out.println("Please enter Start Time(HH:mm)");
				ok2 = true;
				x = console2.next();
				if(!check(x)){
					System.out.println("Invalid format , try again!");
					ok2 = false;
				}
				if(ok2){
					System.out.println("Please enter END Time(HH:mm)");
					y = console2.next();
					if(!check(y))
						System.out.println("Invalid format , try again!");
					else
						ok = false;
				}
			}
			temp = filterbyTime("finalCSV.csv", x, y);
			if(temp.size()==0){
				System.out.println("Details not found , try again");
				choose();
			}
			else	{	
				WriteCSV.Write(temp);
				System.out.println("Process done !");}
			break;

		case 2:
			while(ok){
				System.out.println("Please enter Lat (int)");
				ok2 = true;
				x = console2.next();
				if(!check2(x)){
					System.out.println("Invalid format , try again!");
					ok2 = false;
				}
				if(ok2){
					System.out.println("Please enter Lon (int)");
					y = console2.next();
					if(!check2(y))
						System.out.println("Invalid format , try again!");
					else
						ok = false;
				}
			}
			temp = filterbyPlace("finalCSV.csv", x, y);
			if(temp.size()==0){
				System.out.println("Details not found , try again");
				choose();
			}
			else		{
				WriteCSV.Write(temp);
				System.out.println("Process done !");}
			break;

		case 3:
			while(ok){
				System.out.println("Please enter ID");
				x = console2.nextLine();
				ok = false;
			}
			temp = filterbyID("finalCSV.csv", x);
			if(temp.size()==0){
				System.out.println("Details not found , try again");
				choose();
			}
			else		{
				WriteCSV.Write(temp);
				System.out.println("Process done !");}
			break;
		case 4:
			System.out.println("As you wish...");
			break;
		default: System.out.println("Invalid Input , try again" );
				choose();

		}
		console2.close();
		console.close();
	}

	public static ArrayList<String[]> filterbyTime(String path, String start , String end) throws FileNotFoundException, ParseException{

		ArrayList<String[]> temp = new ArrayList<String[]>(); 
		Scanner info = new Scanner(new File(path));
		String[] str = null;
		int index = 0;
		boolean first = true;
		while (info.hasNextLine()){
			if(first){
				str = info.nextLine().split(",");
				first = false;
			}
			str = info.nextLine().split(",");
			if(checkTime(str[0],start,end)){
				temp.add(index , str);
				index++;
			}

		}
		info.close();
		return temp;
	}

	public static boolean checkTime(String time , String start , String end ) throws ParseException{

		String[] str = time.split(" ");
		LocalTime d1 = LocalTime.parse(start);
		LocalTime d2 = LocalTime.parse(end);
		LocalTime t = LocalTime.parse(str[1]);

		if(t.isAfter(d1) && t.isBefore(d2))
			return true;
		else
			return false;
	}

	public static boolean check(String str) throws ParseException{

		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm"); //HH = 24h format
		dateFormat.setLenient(false); //this will not enable 25:67 for example
		try {
			dateFormat.parse(str);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static ArrayList<String[]> filterbyPlace(String path , String lat , String lon) throws FileNotFoundException{

		ArrayList<String[]> temp = new ArrayList<String[]>(); 
		Scanner info = new Scanner(new File(path));
		String[] str = null;
		int index = 0;
		boolean first = true;
		while (info.hasNextLine()){
			if(first){
				str = info.nextLine().split(",");
				first = false;
			}
			str = info.nextLine().split(",");
			if(checkPlace(str[2],lat) && checkPlace(str[3], lon)){
				temp.add(index , str);
				index++;
			}
		}
		info.close();
		return temp;
	}
	
	public static boolean checkPlace(String var , String place){

		String[] str1 = var.split("\\.");
		if(str1[0].equals(place))
			return true;
		return false;
	}

	public static boolean check2(String str){

		for(int i=0; i<str.length(); i++){
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;

	}
	
	public static ArrayList<String[]> filterbyID(String path , String ID) throws FileNotFoundException{
		ArrayList<String[]> temp = new ArrayList<String[]>(); 
		Scanner info = new Scanner(new File(path));
		String[] str = null;
		int index = 0;
		boolean first = true;
		while (info.hasNextLine()){
			if(first){
				str = info.nextLine().split(",");
				first = false;
			}
			str = info.nextLine().split(",");
			if(checkPlaceID(str[1],ID)){
				temp.add(index , str);
				index++;
			}
		}
		info.close();
		return temp;
	}

	public static boolean checkPlaceID(String var , String place){
			String[] str1 = var.split("\\.");
			if(str1[0].equals(place))
				return true;
			return false;
		}
	
	public static void intro(){
		System.out.println("*** CSV final file had been created, let's define sort method for the kml file: \n");
		String leftAlignFormat = "| %-17s | %-5d |%n";
		String[] str = {"Time Sort" , "Coordinates Sort" , "ID Sort", "Don't sort it!"}; 
		System.out.format("+-------------------+-------+%n");
		System.out.format("| Choose a sort     | Press |%n");
		System.out.format("+-------------------+-------+%n");
		for (int i = 0; i < 4; i++) {
		    System.out.format(leftAlignFormat, " " + str[i], i+1);
		}
		System.out.format("+-------------------+-------+%n");
		System.out.println();
	}
}
