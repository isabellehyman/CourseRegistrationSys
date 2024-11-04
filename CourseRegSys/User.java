
public class User {
	// define member variables 
	protected String username;
	protected String password; 
	protected String fName;
	protected String lName;
	
	// default constructor 
	public User(String username, String password, String fName, String lName) {
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}
	// getters and setters 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	// methods 
	public void viewCourses() {
		
	}

}
