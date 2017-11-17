package webapp;
import java.util.List;

public class Lineup {

	private int id;
	private int league;
	private int user;
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
