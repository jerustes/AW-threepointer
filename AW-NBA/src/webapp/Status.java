package webapp;
public class Status {
	private int round;		//Jornada
	private int points;
	private char phase;

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public char getPhase() {
		return phase;
	}

	public void setPhase(char phase) {
		this.phase = phase;
	}

	public String toString() {
		return "[" +round+ "][" +points+ "][" +phase+ "]";
	}
}
