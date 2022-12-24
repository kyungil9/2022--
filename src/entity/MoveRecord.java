package entity;

public class MoveRecord {
	private int moveRecordNumber;
	private String headHousehold;
	private String citizenName;
	private String citizenNumber;
	private String beforeAddress;
	private String afterAddress;
	private String phoneNumber;
	
	public MoveRecord() {};
	public MoveRecord(int moveRecordNumber,String headHousehold,String citizenName,String citizenNumber,String beforeAddress,String afterAddress,String phoneNumber) {
		this.moveRecordNumber = moveRecordNumber;
		this.headHousehold = headHousehold;
		this.citizenName = citizenName;
		this.citizenNumber = citizenNumber;
		this.beforeAddress = beforeAddress;
		this.afterAddress = afterAddress;
		this.phoneNumber = phoneNumber;
	}
	
	public int getMoveRecordNumber() {
		return this.moveRecordNumber;
	}
	public String getHeadHousehold() {
		return this.headHousehold;
	}
	public String getCitizenName() {
		return this.citizenName;
	}
	public String getCitizenNumber() {
		return this.citizenNumber;
	}
	public String getBeforeAddress() {
		return this.beforeAddress;
	}
	public String getAfterAddress() {
		return this.afterAddress;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setMoveRecordNumber(int moveRecordNumber) {
		this.moveRecordNumber = moveRecordNumber;
	}
	public void setHeadHousehold(String headHousehold) {
		this.headHousehold = headHousehold;
	}
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	public void setCitizenNumber(String citizenNumber) {
		this.citizenNumber = citizenNumber;
	}
	public void setBeforeAddress(String beforeAddress) {
		this.beforeAddress = beforeAddress;
	}
	public void setAfterAddress(String afterAddress) {
		this.afterAddress = afterAddress;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
