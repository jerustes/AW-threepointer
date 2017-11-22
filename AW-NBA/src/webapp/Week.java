package webapp;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Liga")
public class Week {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "fecha_inicio")
	private Date startDate;
	@Column(name = "fecha_fin")
	private Date endDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
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
