package entity;

public class Employee {
	private String employeeNumber;
	private String name;
	private int age;
	private String phoneNumber;
	private String departmentName;
	
	public Employee() {};
	public Employee(String employeeNumber,String name) {
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.age = 0;
		this.phoneNumber = "";
		this.departmentName = "";
	}
	public Employee(String employeeNumber,String name,int age,String phoneNumber,String departmentName) {
		this.employeeNumber = employeeNumber;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.departmentName = departmentName;
	}
	
	public String getEmployeeNumber() {
		return this.employeeNumber;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getDepartmentName() {
		return this.departmentName;
	}
	
	public void setEmployNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
