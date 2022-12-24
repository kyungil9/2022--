package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.transform.Templates;

import entity.Citizen;

public class CitizenDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public Object[][] selectCitizenList(){//전체 주민 조회
		String sql ="SELECT * FROM 주민 ORDER BY 이름 ASC" ;
		ArrayList<Citizen> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String job = rs.getString(4);
				String address = rs.getString(5);
				String phoneNumber = rs.getString(6);
				list.add(new Citizen(id,name,age,job,address,phoneNumber));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertObject(list);
	}
	
	public Citizen selectCitizen(String citizenNumber){//주민 개인 조회
		StringBuffer sql = new StringBuffer("SELECT * FROM 주민 WHERE 주민번호 = '");
		sql.append(citizenNumber);
		sql.append("' ORDER BY 이름 ASC");
		Citizen citizen = new Citizen();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				citizen.setCitizenNumber(rs.getString(1));
				citizen.setName(rs.getString(2));
				citizen.setAge(rs.getInt(3));
				citizen.setJob(rs.getString(4));
				citizen.setAddress(rs.getString(5));
				citizen.setPhoneNumber(rs.getString(6));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return citizen;
	}
	
	public Object[][] selectCitizenNameList(String searchName,int mode){//특정 주민 이름으로 조회
		StringBuffer sql = new StringBuffer("SELECT * FROM 주민 WHERE 이름 LIKE '%");
		sql.append(searchName);
		sql.append("%' ORDER BY 이름 ASC");
		ArrayList<Citizen> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String job = rs.getString(4);
				String address = rs.getString(5);
				String phoneNumber = rs.getString(6);
				list.add(new Citizen(id,name,age,job,address,phoneNumber));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		if(mode == 1)
			return convertObject(list);
		else 
			return convertTwoObject(list);
		
	}
	
	public Object[][] selectCitizenNumberList(String citizenNumber){//특정 주민 번호으로 조회
		StringBuffer sql = new StringBuffer("SELECT * FROM 주민 WHERE 주민번호 LIKE '%");
		sql.append(citizenNumber);
		sql.append("%' ORDER BY 이름 ASC");
		ArrayList<Citizen> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String job = rs.getString(4);
				String address = rs.getString(5);
				String phoneNumber = rs.getString(6);
				list.add(new Citizen(id,name,age,job,address,phoneNumber));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertObject(list);
	}
	
	public void insertCitizen(String citizenNumber ,String name, int age, String job, String address, String phoneNumber) {//주민 추가
		String sql = "INSERT INTO 주민 VALUES(?,?,?,?,?,?)";
		int row =0;
		System.out.println(citizenNumber);
		try {
			conn = factory.Connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, citizenNumber);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setString(4, job);
			pstmt.setString(5, address);
			pstmt.setString(6, phoneNumber);
			row = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, pstmt);
		}
		
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"성공적으로 추가되었습니다.", "주민 추가", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"주민추가에 실패하였습니다.", "sql 처리실패", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateCitizen(String citizenNumber ,String name, int age, String job, String phoneNumber) {//주민 정보 업데이트 
		String sql = "UPDATE 주민 SET 이름=?, 나이=? , 직업=?, 연락처=? WHERE 주민번호=?";
		int row = 0;
		try {
			conn = factory.Connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, job);
			pstmt.setString(4, phoneNumber);
			pstmt.setString(5, citizenNumber);
			row = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, pstmt);
		}
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"성공적으로 수정되었습니다.", "주민 수정", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"주민수정에 실패하였습니다.", "sql 처리실패", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Object[][] convertObject(ArrayList<Citizen> list){
		Object[][] temp = new Object[list.size()][6];
		for(int i=0;i<list.size();i++) {
			temp[i][0] = list.get(i).getCitizenNumber();
			temp[i][1] = list.get(i).getName();
			temp[i][2] = list.get(i).getAge();
			temp[i][3] = list.get(i).getJob();
			temp[i][4] = list.get(i).getAddress();
			temp[i][5] = list.get(i).getPhoneNumber();
		}
		return temp;
	}

	private Object[][] convertTwoObject(ArrayList<Citizen> list){
		Object[][] temp = new Object[list.size()][2];
		for(int i=0;i<list.size();i++) {
			temp[i][0] = list.get(i).getCitizenNumber();
			temp[i][1] = list.get(i).getName();
		}
		return temp;
	}
}
