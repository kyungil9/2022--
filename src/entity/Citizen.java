package entity;

public class Citizen {
	private String citizenNumber;
	private String name;
	private int age;
	private String job;
	private String address;
	private String phoneNumber;

	
	public Citizen() {};
	public Citizen(String citizenNumber,String name,int age) {
		this.citizenNumber = citizenNumber;
		this.name = name;
		this.age = age;
		this.job = "";
		this.address = "";
		this.phoneNumber = "";
	}
	public Citizen(String citizenNumber,String name,int age,String job,String address,String phoneNumber) {
		this.citizenNumber = citizenNumber;
		this.name = name;
		this.age = age;
		this.job = job;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public String getCitizenNumber() {
		return this.citizenNumber;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String getJob() {
		return this.job;
	}
	public String getAddress() {
		return this.address;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setCitizenNumber(String citizenNumber) {
		this.citizenNumber = citizenNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
