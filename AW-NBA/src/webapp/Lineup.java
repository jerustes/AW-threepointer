package webapp;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Plantilla")
public class Lineup {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "liga")
	private int league;
	@Column(name = "usuario")
	private int user;
	@Column(name = "saldo")
	private long salary;	//125000 max
	
	private List<Player> teamLineup;
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
