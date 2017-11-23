# About the Project :ledger:
The project programmed in java , and has two parts .

**The First part :**

The program receives a CSV file that exported from WigleWifi app . The CSV file contains information
about WiFi points , like Time , Coordinates , SSID, MAC address , signal strenght and ect .
The points are sorted for each time and coordinates, by the strength - max 10 strongest .
In the final process for this part , the program creat a new CSV file named as "FinalCSV" that shows in each a row ,
up to 10 strongest points with the following parameters : SSID , MAC address , Frequency , Signal .
Each represent the same time and location.

**The Second part :**

The program gets the "FinalCSV" file , and filters it by 3 kind of sort by : Time , ID , Location (Coordinates).
1. Time - User is prompted to enter start and end time , the program filters the WiFi points by that rang.
2. ID - User is prompted to enter name of device , the program will show Wifi points from the wanted device .
3. Location - User is prompted to enter Lat and lon parameters , the program filters Wifi points according the Coordinates.

- The program will export a KML File after the filteration , and the file can be opened with Google Earth:globe_with_meridians: app. 




