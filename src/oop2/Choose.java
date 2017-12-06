package oop2;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * This class define the Filter that the user choose to sort the information.
 *
 */

public class Choose {

	static int choice;


	public static void choose(ArrayList<WiFi> WiFi){

		Scanner sc = new Scanner(System.in);
		String start , End , Device;
		Filter filter = null;
		double Lat, Lon,Distance;
		CoordinatesPoint place = null;


		System.out.println("Please choice a Filter to sort your information");
		System.out.println("1.Filter by Time");
		System.out.println("2.Filter by ID (Device)");
		System.out.println("3.Filter by Location");
		System.out.println("4.Don't sort it");

		choice = sc.nextInt();
		sc.nextLine();
		switch(choice){

		case 1: {
			System.out.println("Enter start time (dd/MM/yyyy hh:mm) ");
			start = sc.nextLine();
			System.out.println("Enter end time (dd/MM/yyyy hh:mm) ");
			End = sc.nextLine();
			filter = new Filter("Time", start + ";" + End);
			Choose.SortbyFilter(WiFi, filter, "FilteredCSV");
			break;

		}

		case 2: {
			System.out.println("Enter Device name");
			Device = sc.nextLine();
			filter = new Filter("ID" , Device);
			Choose.SortbyFilter(WiFi, filter, "FilteredCSV");			
			break;

		}

		case 3:{

			System.out.println("Enter your lat coordinate");
			Lat = sc.nextDouble();

			System.out.println("Enter end lon coordinate ");
			Lon = sc.nextDouble();

			System.out.println("Enter distance from your place in km ");
			Distance = sc.nextDouble();

			place = new CoordinatesPoint(Lat, Lon, 0);
			filter = new Filter("Distance", place, Distance);
			Choose.SortbyFilter(WiFi, filter, "FilteredCSV");			
			break;

		}

		case 4:{
			Write.WriteCSV(WiFi, "FilteredCSV");
			break;
		}
		}


	}


	public static void SortbyFilter(ArrayList<WiFi> WiFi , Filter filter , String name ){

		for(int i=0; i<WiFi.size(); i++){
			if(false == filter.check(WiFi.get(i))){
				WiFi.remove(i);
				i--;
			}

		}

		Write.WriteCSV(WiFi , name);

	}

}
