package webapp;

public class Player {

	private int value;
	private String name;
	private int pointsWeek;	//PER
	private int pointsGlobal;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
		return "Player [value=" + value + ", name=" + name + ", pointsWeek=" + pointsWeek + ", pointsGlobal="
				+ pointsGlobal + "]";
	}
	
}
