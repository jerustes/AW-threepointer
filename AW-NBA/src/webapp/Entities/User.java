package webapp.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity(name="usuario")
@Table
public class User implements Serializable {
	public enum Role { admin, jugador }
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "nombre")
	private String name;
	@Column(name = "rol")
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(name = "mail")
	private String email;
	@Column(name = "contrasena")
	private String password; 
//	@OneToMany(mappedBy = "user")
//    private Collection<Lineup> userLineup = new ArrayList<Lineup>();
//	@OneToMany(mappedBy = "creator")
//    private Collection<League> creatorLeague = new ArrayList<League>();
//	public void setUserLineup(Collection<Lineup> userLineup) {
//		this.userLineup = userLineup;
//	}
//	public Collection<Lineup> getUserLineup() {
//		return userLineup;
//	}
//	public void setCreatorLeague(Collection<League> creatorLeague) {
//		this.creatorLeague = creatorLeague;
//	}
//	public Collection<League> getCreatorLeague() {
//		return creatorLeague;
//	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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
