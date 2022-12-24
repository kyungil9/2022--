package entity;

import java.util.Date;

public class Program {
	private String programNumber;
	private String title;
	private Date startDate;
	private Date endDate;
	private String abolition;
	private String department;
	private int participation;
	private String location;
	
	public Program() {};
	public Program(String programNumber,String title,Date startDate,Date endDate,String abolition,String department,int participation,String location) {
		this.programNumber = programNumber;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.abolition = abolition;
		this.department = department;
		this.participation = participation;
		this.location = location;
	}
	
	public String getProgramNumber() {
		return this.programNumber;
	}
	public String getTitle() {
		return this.title;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public Date getEndDate() {
		return this.endDate;
	}
	public String getAbolition() {
		return this.abolition;
	}
	public String getDepartment() {
		return this.department;
	}
	public int getParticipation() {
		return this.participation;
	}
	public String getLocation() {
		return this.location;
	}
	
	public void setProgramNumber(String programNumber) {
		this.programNumber = programNumber;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setAbolition(String abolition) {
		this.abolition = abolition;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setParticipation(int participation) {
		this.participation = participation;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
