package airline;

/**
 * Seat
 * @author Katherine Yee 
 * @version 1.0 3/4/23 
*/

/**
 * Represents a seat in the airline reservation system
*/
public class Seat {
	private int number;
	private char letter;
	private String classService;
	
	/**
	 * Create public seat object
	 * @param n - number of seat
	 * @param l - letter of seat
	 * @param c - class service of seat
	 */
	public Seat(int n, char l, String c) {
		number = n;
		letter = l;
		classService = c;
	}

	/**
	 * Get number of seat
	 * @return number of seat
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Get letter of seat
	 * @return letter of seat
	 */
	public char getLetter() {
		return letter;
	}
	
	/**
	 * Get class service of seat
	 * @return class service of seat
	 */
	public String getClassService() {
		return classService;
	}
	
	/**
	 * Get a string of seat number and letter
	 * @return seat number and letter in string form
	 */
	public String toString() {
		String str = "" + number + "" + letter;
		return str;
	}
}
