package webapp.Entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name="plantilla")
@Table
public class Lineup implements Serializable {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "liga")
	private int league;
	@Column(name = "usuario")
	private int user;
	@Column(name = "saldo")
	private long salary;	//125000 max; Salario de la plantilla de una liga determinada
						//no del usuario en general
	@ElementCollection
	private List<Player> teamLineup;
	@Column(name = "puntos")
	private int points;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeague() {
		return league;
	}
	public void setLeague(int league) {
		this.league = league;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public List<Player> getTeamLineup() {
		return teamLineup;
	}
	public void setTeamLineup(List<Player> teamLineup) {
		this.teamLineup = teamLineup;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return "Lineup [id=" + id + ", league=" + league + ", user=" + user + ", salary=" + salary + ", teamLineup="
				+ teamLineup + ", points=" + points + "]";
	}

	
}
