package webapp.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name = "liga")
@Table
public class League implements Serializable {
	public enum State {
		Inscripcion, Activa, Preparada, Finalizada
	}

	@Id
	@Column(name = "league_id")
	private int id;
	@Column(name = "nombre")
	private String name;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="usuario")
	private int creator;
	@Column(name = "no_usuarios")
	private int nMax; // Max number of players
	@Column(name = "saldo")
	private int initBalance;
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private State state;
	@OneToMany(mappedBy = "league")
	private Collection<League> userLeague = new ArrayList<League>();
	
	public Collection<League> getUserLeague() {
		return userLeague;
	}
	public void setUserLeague(Collection<League> userLeague) {
		this.userLeague = userLeague;
	}
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getNMax() {
		return nMax;
	}

	public void setNMax(int nMax) {
		this.nMax = nMax;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setBalance(int initBalance) {
		this.initBalance = initBalance;
	}

	public int getBalance() {
		return initBalance;
	}

	public String toString() {
		return "[" + creator + "]" + " " + name + " (" + id + ")" + "( max:" + nMax + ")" + "( state:" + state + ")";
	}
}
