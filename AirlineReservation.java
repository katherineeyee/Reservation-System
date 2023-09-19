package airline;

import java.io.*;
import java.util.*;

/**
 * AirlineReservation
 * @author Katherine Yee 
 * @version 1.0 3/4/23 
*/

/**
 * Represents an airline reservation where users can make, cancel, and view seating reservations
*/
public class AirlineReservation {
	
	private static ArrayList<User> publicUsers = new ArrayList<User>();
	private static ArrayList<User> admins = new ArrayList<User>();
	private static ArrayList<Seat> seating = new ArrayList<Seat>();
	private static ArrayList<Seat> reservedSeating = new ArrayList<Seat>();
	private static HashMap<String, ArrayList<Seat>> seatReservation = new HashMap<String, ArrayList<Seat>>();
	private static File CL34;
	private static File Users;

	/**
	 * Creates files if doesn't already exist
	*/
	public static void createFiles() {
		CL34 = new File("/Users/katherineyee/Downloads/CL34.txt");
		try {
			CL34.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Users = new File("/Users/katherineyee/Downloads/Users.txt");
		try {
			Users.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads existing files and populate in-memory data
	*/
	public static void loadFiles() {
		CL34 = new File("/Users/katherineyee/Downloads/CL34.txt");
		try {
			Scanner scan = new Scanner(CL34);
			while(scan.hasNextLine()) {
				String reservationInfo = scan.nextLine();
				String[] split = reservationInfo.split(" ");	
				int number = Integer.parseInt(split[0]);
				char letter = split[1].charAt(0);
				String cService = "";
				String name = "";
				if(split.length == 5) {
					cService = split[2];
					name = split[3] + " " + split[4];
				} else {
					cService = split[2] + " " + split[3];
					name = split[4] + " " + split[5];
				}
				
				for(int i = 0; i < seating.size(); i++) {
					Seat currSeat = seating.get(i);
					if(currSeat.getNumber() == number && currSeat.getLetter() == letter) {
						seating.remove(currSeat);
						reservedSeating.add(currSeat);
						if (seatReservation.containsKey(name)) {
							seatReservation.get(name).add(currSeat);
			            } else {
			            	seatReservation.put(name, new ArrayList<Seat>());
			            	seatReservation.get(name).add(currSeat);
			            }
					}
				}	
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Users = new File("/Users/katherineyee/Downloads/Users.txt");
		try {
			Scanner scan = new Scanner(Users);
			while(scan.hasNextLine()) {
				String userInfo = scan.nextLine();
				String[] split = userInfo.split(" ");
				String id = split[0];
				String name = split[1] + " " + split[2];
				String pass = split[3];
				User u = new User(id, name, pass);
				publicUsers.add(u);
			}	
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clearFiles();		
	}
	
	/**
	 * Clear content of files
	*/
	public static void clearFiles() { 
		try {
			FileWriter fw = new FileWriter("/Users/katherineyee/Downloads/CL34.txt", false);
			PrintWriter pw = new PrintWriter(fw, false);
			pw.flush();
			pw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		try {
			FileWriter fw = new FileWriter("/Users/katherineyee/Downloads/Users.txt", false);
			PrintWriter pw = new PrintWriter(fw, false);
			pw.flush();
			pw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	/**
	 * Populates all seats into seating arraylist
	*/
	public static void popSeating() {
		String classService = "";
		for(int i = 1; i <= 10; i++) {
			Character c = null;
			if(i == 1) {
				c = 'A';
			}
			else if(i == 2) {
				c = 'B';
			}
			else if(i == 3) {
				c = 'C';
			}
			else if(i == 4) {
				c = 'D';
			}
			else if(i == 5) {
				c = 'E';
			}
			else if(i == 6) {
				c = 'F';
			}
			else if(i == 7) {
				c = 'G';
			}
			else if(i == 8) {
				c = 'J';
			}
			else if(i == 9) {
				c = 'K';
			}
			else if(i == 10) {
				c = 'L';
			}
			for(int j = 1; j <= 53; j++) {
				if(j >= 1 && j <= 4) {
					if(j == 4 && (c == 'D' || c == 'E' || c == 'F' || c == 'G')) {
						//nothing
					} 
					else if(c == 'C' || c == 'J') {
						//nothing
					} else {
						classService = "First Class";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
				}
				else if(j == 16 && (c == 'D' || c == 'E' || c == 'F' || c == 'G')) {
					classService = "Economy Plus";
					Seat s = new Seat(j, c, classService);
					seating.add(s);
				}
				else if(j >= 17 && j <= 26) {
					if(j == 24 && (c == 'D' || c == 'E' || c == 'F' || c == 'G')) {
						classService = "Economy";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
					else if(j == 25 && (c == 'D' || c == 'E' || c == 'F' || c == 'G')) {
						classService = "Economy";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
					else if(j == 26 && (c == 'D' || c == 'E' || c == 'F' || c == 'G')) {
						classService = "Economy";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					} else {
						classService = "Economy Plus";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
				}
				else if(j >= 27 && j <= 36) {
					classService = "Economy";
					Seat s = new Seat(j, c, classService);
					seating.add(s);
				}
				else if(j == 37 && (c == 'A' || c == 'B' || c == 'K' || c == 'L')) {
					classService = "Economy";
					Seat s = new Seat(j, c, classService);
					seating.add(s);
				}
				else if(j == 39 && (c == 'A' || c == 'B' || c == 'C' || c == 'J' || c == 'K' || c == 'L')) {
					classService = "Economy Plus";
					Seat s = new Seat(j, c, classService);
					seating.add(s);
				}
				else if(j >= 40 && j<= 47) {
					if(j == 40 && (c == 'D' || c == 'E' || c == 'F' || c == 'G')) {
						classService = "Economy Plus";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					} else {
						classService = "Economy";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
				}
				else if(j >= 48 && j<= 51) {
					if(c == 'C' || c == 'J') {
						//nothing
					} else {
						classService = "Economy";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
				}
				else if(j == 52) {
					if(c == 'A' || c == 'B' || c == 'D' || c == 'E' || c == 'F' || c == 'G') {
						classService = "Economy";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
				}
				else if(j == 53) {
					if(c == 'D' || c == 'E' || c == 'F' || c == 'G') {
						classService = "Economy";
						Seat s = new Seat(j, c, classService);
						seating.add(s);
					}
				}
			}
		}
	}
	
	/**
	 * Get public user info
	 * @return the name of user
	*/
	public static String userInfo() {
		Scanner scan = new Scanner(System.in);
		System.out.println("[F]irst-time user or [R]eturning user?");
		while(scan.hasNextLine()) {
			String input = scan.nextLine();
			if(input.equals("F")) {
				System.out.println("Please Sign Up");
				System.out.println("Enter user id: ");
				String id = scan.nextLine();
				boolean idTaken = true;
				while(idTaken) {
					if(publicUsers.size() == 0) {
						break;
					}
					for(User u : publicUsers) {
						if(u.getId().equals(id)) {
							System.out.println("User id taken");
							break;
						} else {
							idTaken = false;
						}
					}
					if(idTaken == false) {
						break;
					}
					System.out.println("Enter new user id: ");
					id = scan.nextLine();
				}
				System.out.println("Enter first and last name: ");
				String name = scan.nextLine();
				System.out.println("Enter password: ");
				String password = scan.nextLine();
				User user = new User(id, name, password);
				publicUsers.add(user);
				System.out.println("Successfully created new account");
				return name;
			}
			else if(input.equals("R")) {
				if(publicUsers.size() == 0) {
					System.out.println("Create new account first");
					userInfo();
					break;
				}
				String name = "";
				System.out.println("Enter user id: ");
				String id = scan.nextLine();
				System.out.println("Enter password: ");
				String password = scan.nextLine();
				boolean match = false;
				
				boolean incorrect = true;
				while(!match) {
					for(User u : publicUsers) {
						if(u.getId().equals(id) && u.getPassword().equals(password)) {
							match = true;
							incorrect = false;
							name = u.getName();
							break;
						} else {
							incorrect = true;
						}
					}
					if(match) {
						break;
					}
					if(incorrect) {
						System.out.println("Incorrect log in, please try again");
					}
					System.out.println("Enter user id: ");
					id = scan.nextLine();
					System.out.println("Enter password: ");
					password = scan.nextLine();
				}
				System.out.println("Successfully logged in");
				return name;
			}
			break;
    	}
		return null;
	}
	
	/**
	 * Print public user main menu options
	*/
	public static void printUserMenu() {
		System.out.println("Select one of the following user menu options: ");
		System.out.println("Check [A]availability, Make [R]eservation, [C]ancel Reservation, [V]iew Reservations, [D]one");
	}
	
	/**
	 * Gets user input to carry out reservation system functions as public user
	 * @param n - the name of user
	*/
	public static void userScenario(String n) {
		String name = n;
		printUserMenu();
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			String input = scan.nextLine();
			if(input.equals("A")) {
				checkAvailability();
				printUserMenu();
            } 
			else if(input.equals("R")) {
				makeReservation(name);
				printUserMenu();
			}
			else if(input.equals("C")) {
				cancelReservation(name);
				printUserMenu();
			}
			else if(input.equals("V")) {
				viewReservations(name);
				printUserMenu();
			}
			else if(input.equals("D")) {
				System.out.println("Transaction is complete");
				break;
			}
		}
	}
	
	/**
	 * Check available seats
	*/
	public static void checkAvailability() {
		System.out.println("Seat Availability");
		System.out.println();
		System.out.println("First Class (price: $1000/seat)");
		boolean first = true;
		for(int i = 1; i <= 4; i++) {
			System.out.print(i + ": ");
			for(Seat s : seating) {
				if(s.getClassService().equals("First Class") && s.getNumber() == i) {
					if(first) {
						System.out.print(s.getLetter());
						first = false;
					} else {
						System.out.print(", " + s.getLetter());
					}
				}
			}
			System.out.println();
			first = true;
		}
		System.out.println("");
		System.out.println("Economy Plus (price: $500/seat)");
		for(int i = 16; i <= 26; i++) {
			System.out.print(i + ": ");
			for(Seat s : seating) {
				if(s.getClassService().equals("Economy Plus") && s.getNumber() == i) {
					if(first) {
						System.out.print(s.getLetter());
						first = false;
					} else {
						System.out.print(", " + s.getLetter());
					}
				}
			}
			System.out.println();
			first = true;
		}
		for(int i = 39; i <= 40; i++) {
			System.out.print(i + ": ");
			for(Seat s : seating) {
				if(s.getClassService().equals("Economy Plus") && s.getNumber() == i) {
					if(first) {
						System.out.print(s.getLetter());
						first = false;
					} else {
						System.out.print(", " + s.getLetter());
					}
				}
			}
			System.out.println();
			first = true;
		}
		System.out.println("");
		System.out.println("Economy (price: $250/seat)");
		
		for(int i = 24; i <= 37; i++) {
			System.out.print(i + ": ");
			for(Seat s : seating) {
				if(s.getClassService().equals("Economy") && s.getNumber() == i) {
					if(first) {
						System.out.print(s.getLetter());
						first = false;
					} else {
						System.out.print(", " + s.getLetter());
					}
				}
			}
			System.out.println();
			first = true;
		}
		for(int i = 40; i <= 53; i++) {
			System.out.print(i + ": ");
			for(Seat s : seating) {
				if(s.getClassService().equals("Economy") && s.getNumber() == i) {
					if(first) {
						System.out.print(s.getLetter());
						first = false;
					} else {
						System.out.print(", " + s.getLetter());
					}
				}
			}
			System.out.println();
			first = true;
		}
	}
	
	/**
	 * Make seating reservations
	 * @param n - the name of user
	*/
	public static void makeReservation(String n) {
		String name = n;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter seat number: ");
		String seat = scan.nextLine();
		String[] split = seat.split("");	
		int number = 0;
		char letter = 'x';
		if(split.length == 2) {
			number = Integer.parseInt(split[0]);
			letter = split[1].charAt(0);
		} else {
			number = Integer.parseInt(split[0] + split[1]);
			letter = split[2].charAt(0);
		}	
		String cService = "";
		Seat seatR = null;
		boolean seatValid = true;
		while(seatValid) {
			for(Seat s : seating) {
				if(s.getNumber() == number && s.getLetter() == letter) {
					System.out.println("Seat is available");
					cService = s.getClassService();
					seatValid = false;
					seatR = s;
					seating.remove(s);
					reservedSeating.add(s);
					break;
				}
			}
			if(seatValid == false) {
				break;
			} else {
				System.out.println("Error: invalid seat number");
			}
			System.out.println("Enter new seat number: ");
			seat = scan.nextLine();
			split = seat.split("");	
			if(split.length == 2) {
				number = Integer.parseInt(split[0]);
				letter = split[1].charAt(0);
			} else {
				number = Integer.parseInt(split[0] + split[1]);
				letter = split[2].charAt(0);
			}
		}	
		String price = "";
		if(cService.equals("First Class")) {
			price = "$1000";
		}
		else if(cService.equals("Economy Plus")) {
			price = "$500";
		}
		else if(cService.equals("Economy")) {
			price = "$250";
		}
		System.out.println("Seat number: " + seat);	
		System.out.println("Class Service: " + cService);
		System.out.println("Price: " + price);	
		Seat s = new Seat(number, letter, cService);
		System.out.println("Please confirm [Y]es or [N]o");
		String confirm = scan.nextLine();
		if(confirm.equals("Y")) {
			if (seatReservation.containsKey(name)) {
				seatReservation.get(name).add(s);
            } else {
            	seatReservation.put(name, new ArrayList<Seat>());
            	seatReservation.get(name).add(s);
            }
			System.out.println("Reservation complete");
		}
		else if(confirm.equals("N")) {
			seating.add(seatR);
			reservedSeating.remove(seatR);
			System.out.println("Reservation request void");
		}
	}
	
	/**
	 * Cancel seating reservations
	 * @param n - the name of user
	*/
	public static void cancelReservation(String n) {
		String name = n;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select one of your following reserved seats: ");
		ArrayList<Seat> seats = seatReservation.get(name);
		for(Seat s : seats) {
			System.out.println(s);
		}
		String seat = scan.nextLine();
		boolean validSeat = true;
		while(validSeat) {
			for(Seat s : seats) {
				String seatStr = s.toString();
				if(seat.equals(seatStr)) {
					System.out.println("Cancelled reservation successfully");
					seating.add(s);
					reservedSeating.remove(s);
					seats.remove(s);
					validSeat = false;
					break;
				}
			}
			if(validSeat == false) {
				break;
			}
			System.out.println("Error, please select again: ");
			for(Seat s : seats) {
				System.out.println(s);
			}
			seat = scan.nextLine();
		}
	}
	
	/**
	 * View seating reservations of user
	 * @param n - the name of user
	*/
	public static void viewReservations(String n) {
		String name = n;
		System.out.println("Name: " + name);
		System.out.println("Seats: ");
		ArrayList<Seat> seats = seatReservation.get(name);	
		if(seats == null || seats.size() == 0) {
			System.out.println("No reserved seats");
			return;
		}
		boolean first = true;
		int total = 0;
		for(Seat s : seats) {
			if(first) {
				System.out.print(s + " ");
				first = false;
			} else {
				System.out.print(", " + s + " ");
			}
			String cService = s.getClassService();
			if(cService.equals("First Class")) {
				System.out.print("$1000");
				total += 1000;
			}
			else if(cService.equals("Economy Plus")) {
				System.out.print("$500");
				total += 500;
			}
			else if(cService.equals("Economy")) {
				System.out.print("$250");
				total += 250;
			}
		}
		System.out.println();
		System.out.println("Total Balance Due: $" + total);
		
	}
	
	/**
	 * Get admin info
	*/
	public static void adminInfo() {
		User adminUser = new User("yee");
		admins.add(adminUser);
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter admin id: ");
		String id = scan.nextLine();
		boolean match = false;
		while(!match) {
			for(User u : admins) {
				if(u.getId().equals(id)) {
					match = true;
					break;
				} else {
					System.out.println("Incorrect admin id, please try again");
				}
			}
			if(match == true) {
				break;
			}
			System.out.println("Enter admin id: ");
			id = scan.nextLine();
		}
		System.out.println("Successfully logged in");
	}
	
	/**
	 * Print admin main menu options
	*/
	public static void printAdminMenu() {
		System.out.println("Select one of the following admin menu options: ");
		System.out.println("Show [M]anifest list, E[X]it");
	}
	
	/**
	 * Gets user input to carry out reservation system functions as public user
	*/
	public static void adminScenario() {
		Scanner scan = new Scanner(System.in);
		printAdminMenu();
		while(scan.hasNext()) {
			String input = scan.nextLine();
			if(input.equals("M")) {
				mainfestList();
				printAdminMenu();
            } 
			else if(input.equals("X")) {
				exit();
				System.out.println("All reservations and registered users saved");
			}
		}
	}
	
	/**
	 * Lists the reserved seats in airline with corresponding passenger name
	*/
	public static void mainfestList() {
		System.out.println("First Class");
		for (Map.Entry<String, ArrayList<Seat>> mapElement : seatReservation.entrySet()) {
            String name = mapElement.getKey();
            String str = "";
            ArrayList<Seat> seats = (mapElement.getValue());
            for(Seat s : seats) {
            	if(s.getClassService().equals("First Class")) {
            		str = s + ": " + name;
    				System.out.println(str);
            	}
			}
        }	
		System.out.println();
		System.out.println("Economy Plus");
		for (Map.Entry<String, ArrayList<Seat>> mapElement : seatReservation.entrySet()) {
            String name = mapElement.getKey();
            String str = "";
 
            ArrayList<Seat> seats = (mapElement.getValue());
 
            for(Seat s : seats) {
            	if(s.getClassService().equals("Economy Plus")) {
            		str = s + ": " + name;
    				System.out.println(str);
            	}
			}
        }	
		System.out.println();
		System.out.println("Economy");
		for (Map.Entry<String, ArrayList<Seat>> mapElement : seatReservation.entrySet()) {
            String name = mapElement.getKey();
            String str = "";
 
            ArrayList<Seat> seats = (mapElement.getValue());
 
            for(Seat s : seats) {
            	if(s.getClassService().equals("Economy")) {
            		str = s + ": " + name;
    				System.out.println(str);
            	}
			}
        }
	}
	
	/**
	 * Add data to files
	*/
	public static void exit() {
		try {
			CL34.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter fWriter = new FileWriter(CL34.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			String str = "";
			for (Map.Entry<String, ArrayList<Seat>> mapElement : seatReservation.entrySet()) {
	            String name = mapElement.getKey();
	            ArrayList<Seat> seats = (mapElement.getValue());
	            for(Seat s : seats) {
					str = s.getNumber() + " " + s.getLetter() + " " + s.getClassService() + " " + name;
					bWriter.write(str);
					bWriter.write("\n");
				}
	        }
			bWriter.close();
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		try {
			Users.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter fWriter = new FileWriter(Users.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			String str = "";
			for(User u :publicUsers) {
				str = u.getId() + " " + u.getName() + " " + u.getPassword();
				bWriter.write(str);
				bWriter.write("\n");
			}
			bWriter.close();
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
}
