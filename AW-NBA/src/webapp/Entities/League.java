package webapp.Entities;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="Liga")
public class League implements Serializable {
	private enum State { INSCRIPCION, ACTIVA, PREPARADA, FINALIZADA }
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "nombre")
	private String name;
	@Column(name = "creador")
	private int creator;
	@Column(name = "no_usuarios")
	private int nMax;	//Max number of players
	@Column(name = "saldo")
	private int saldo_inicial;
	@Column(name = "estado")
	private State state;
	@Enumerated(EnumType.STRING)
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

	public int getCeator() {
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

	public int getId() {
		return id;
	}

	public void setSaldo(int saldo_inicial) {
		this.saldo_inicial = saldo_inicial;
	}
	
	public int getSaldo() {
		return saldo_inicial;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "[" +creator+ "]" + " " +name+ " (" +id+ ")" + "( max:" +nMax+ ")" + "( state:" +state+ ")";
	}
}
