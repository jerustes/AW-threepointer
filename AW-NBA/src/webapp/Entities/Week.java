package webapp.Entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name="jornada")
@Table
public class Week implements Serializable {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "fecha_inicio")
	private SimpleDateFormat startDate = new SimpleDateFormat("yyyy-MM-dd");
	@Column(name = "fecha_fin")
	private SimpleDateFormat endDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SimpleDateFormat getStartDate() {
		return startDate;
	}
	public void setStartDate(SimpleDateFormat startDate) {
		this.startDate = startDate;
	}
	public SimpleDateFormat getEndDate() {
		return endDate;
	}
	public void setEndDate(SimpleDateFormat endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "Week [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
