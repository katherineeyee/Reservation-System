package airline;

/**
 * User
 * @author Katherine Yee 
 * @version 1.0 3/4/23 
*/

/**
 * Represents a user in the airline reservation system
*/
public class User {
	private String id;
	private String name;
	private String password;
	
	/**
	 * Create public user object
	 * @param id - user id of public user
	 * @param n - name of public user
	 * @param p - password of public user
	 */
	public User(String id, String n, String p) {
		this.id = id;
		name = n;
		password = p;
	}
	
	/**
	 * Create admin user object
	 * @param id - user id of admin
	 */
	public User(String id) {
		this.id = id;
	}

	/**
	 * Get id of user
	 * @return id of user
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get name of public user
	 * @return name of user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get password of public user
	 * @return password of user
	 */
	public String getPassword() {
		return password;
	}
}
