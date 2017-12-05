package webapp.Entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name="plantilla")
@Table
public class Lineup implements Serializable {
	@Id
	@Column(name = "lineup_id")
	private int id;
	@ManyToOne(targetEntity = League.class)
	@JoinColumn(name="liga")
	private int league;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="usuario")
	private int user;
	@Column(name = "saldo")
	private long balance;	//125000 max; Salario de la plantilla de una liga determinada
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
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
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
		return "Lineup [id=" + id + ", league=" + league + ", user=" + user + ", balance=" + balance + ", teamLineup="
				+ teamLineup + ", points=" + points + "]";
	}

	
}
