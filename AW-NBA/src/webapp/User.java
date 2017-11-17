package webapp;
public class User {
	private char role;
	private String email;
	private String password; //String rly?
	private int id;

	public char getRole() {
		return role;
	}

	public void setRole(char role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "[" + role + "]" + " " + email + " (" + id + ")";
	}
}
