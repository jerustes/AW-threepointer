package webapp.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name = "plantilladeportista")
@Table(name = "plantilladeportista")
public class Team implements Serializable {
	@Id
	@Column (name = "id")
	private int id;
	@Column(name = "plantilla")
	private int lineup;
	@Column(name= "deportista")
	private int player;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getLineup() {
		return lineup;
	}

	public void setLineup(int lineup) {
		this.lineup = lineup;
	}

	public String toString() {
		return "[" + id + "]" + lineup + " (" + player + ")";
	}
}
