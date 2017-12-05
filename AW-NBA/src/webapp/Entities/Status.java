package webapp.Entities;

import java.io.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="estado")
@Table
public class Status implements Serializable {
	@Id
	@Column (name = "status_id")
	private int id;
	@ManyToOne(targetEntity = Week.class)
	@JoinColumn(name="jornada")
	private int round;		//Jornada
	@Column(name = "fase")
	private int phase;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public String toString() {
		return "[" +round+ "][" +phase+ "]";
	}
}
