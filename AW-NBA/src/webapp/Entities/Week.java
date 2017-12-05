package webapp.Entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity(name="jornada")
@Table
public class Week implements Serializable {
	@Id
	@Column(name = "week_id")
	private int id;
	@Column(name = "fecha_inicio")
	private Date startDate = new Date();
	@Column(name = "fecha_fin")
	private Date endDate = new Date();
	
	@OneToMany(mappedBy = "round")
    private Collection<Status> userStatus = new ArrayList<Status>();
	
	public Collection<Status> getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Collection<Status> userStatus) {
		this.userStatus = userStatus;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(startDate);
	}
	public void setStartDate(String startDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		startDate = formatter.format(this.startDate);
	}
	public String getEndDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(endDate);
	}
	public void setEndDate(String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		endDate = formatter.format(this.endDate);
	}
	
	@Override
	public String toString() {
		return "Week [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
