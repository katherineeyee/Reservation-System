package airline;

import java.io.*;
import java.util.*;  

/**
 * ReservationSystem
 * @author Katherine Yee 
 * @version 1.0 3/4/23 
*/

/**
 * A Reservation System where public users can reserve airline seating and admin users can view reserved seats
 */
public class ReservationSystem {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AirlineReservation.popSeating();
		
        String fileName = args[2];
        File tempF = new File("/Users/katherineyee/Downloads/" + fileName + ".txt"); 
        String file2Name = args[3];
        File tempF2 = new File("/Users/katherineyee/Downloads/" + file2Name + ".txt");
   
		if(!tempF.exists() && !tempF2.exists()) {
			AirlineReservation.createFiles();
			System.out.println("CL34 and Users are now created.");
		} else {
			AirlineReservation.loadFiles();
			System.out.println("Existing Reservations and Users are loaded.");
		}	

		System.out.println("[P]ublic user or [A]dmin?");
		//gets user input to carry out reservation system functions based on public user or admin
		while(scan.hasNext()) {
			String input = scan.nextLine();
			if(input.equals("P")) {
            	String name = AirlineReservation.userInfo();
            	AirlineReservation.userScenario(name);
            	System.out.println("[P]ublic user or [A]dmin?");
            } 
			else if(input.equals("A")) {
				AirlineReservation.adminInfo();
				AirlineReservation.adminScenario();
			}
		}
	}
}
