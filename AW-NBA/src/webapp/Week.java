package webapp;
import java.util.Date;

public class Week {

	private int id;
	private Date startDate;
	private Date endDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getInitialDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "Week [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
