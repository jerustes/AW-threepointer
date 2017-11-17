package webapp;


public class League {
	private String name;
	private String creator;
	private int nMax;	//Max number of players
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCeator() {
		return creator;
	}

	public void setCreator(String creator) {
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

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "[" +creator+ "]" + " " +name+ " (" +id+ ")" + "( max:" +nMax+ ")";
	}
}
