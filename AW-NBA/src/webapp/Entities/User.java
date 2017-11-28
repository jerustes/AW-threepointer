package webapp.Entities;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="Usuario")
public class User implements Serializable {
	public enum Role { admin, jugador }
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "rol")
	private Role role;
	@Column(name = "mail")
	private String email;
	@Column(name = "contrasena")
	private String password; //String rly?

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Enumerated(EnumType.STRING)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
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
		return "[" + role + "]" + " " + name + ", " + email + " (" + id + ")";
	}
}
