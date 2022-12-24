package entity;

public class Department {
	private String departmentNumber;
	private String name;
	private String phoneNumber;
	private int employeeNum;
	private String managerNumber;
	
	public Department() {};
	public Department(String departmentNumber,String name,String phoneNumber,int employeeNum,String managerNumber) {
		this.departmentNumber = departmentNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.employeeNum = employeeNum;
		this.managerNumber = managerNumber;
	}
	
	public String getDepartmentNumber() {
		return this.departmentNumber;
	}
	public String getName() {
		return this.name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public int getEmployNum() {
		return this.employeeNum;
	}
	public String getManagerNumber() {
		return this.managerNumber;
	}
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setEmployeeNum(int employeeNum) {
		this.employeeNum = employeeNum;
	}
	public void setManagerNumber(String managerNumber) {
		this.managerNumber = managerNumber;
	}
}
