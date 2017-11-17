package webapp;

import java.io.*;
import javax.persistence.*;

@Entity
@Table(name="Estado")
public class Status implements Serializable {
	// Preguntar cual es la primary key de esta tabla!!
	@Column(name = "jornada")
	private int round;		//Jornada
	@Column(name = "fase")
	private int phase;

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
