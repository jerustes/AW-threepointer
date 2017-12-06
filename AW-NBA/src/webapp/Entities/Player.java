package webapp.Entities;

import java.io.*;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="deportista")
@Table
public class Player implements Serializable {
	private enum Position { B, E, A, AP, P }
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "valor")
	private int value;
	@Column(name = "nombre")
	private String name;
	@Column(name = "posicion")
	@Enumerated(EnumType.STRING)
	private Position position;
	@Column(name = "equipo")
	private String team;
	@Column(name = "puntos_semanal")
	private int pointsWeek;	//PER
	@Column(name = "puntos_global")
	private int pointsGlobal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPointsWeek() {
		return pointsWeek;
	}
	public void setPointsWeek(int pointsWeek) {
		this.pointsWeek = pointsWeek;
	}
	public int getPointsGlobal() {
		return pointsGlobal;
	}
	public void setPointsGlobal(int pointsGlobal) {
		this.pointsGlobal = pointsGlobal;
	}
	
	@Override
	public String toString() {
		return "Player [value=" + value + ", name=" + name + ", team=" + team + ", position=" + position + ", pointsWeek=" + pointsWeek + ", pointsGlobal="
				+ pointsGlobal + "]";
	}
	
}
