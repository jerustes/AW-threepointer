package webapp.Entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity(name="jornada")
@Table
public class Week implements Serializable {
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
