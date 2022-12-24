package SQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import entity.Citizen;
import entity.Program;

public class ProgramDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private ResultSet rs = null;
	
	public Object[][] selectProgramList(){//��ü ���α׷� ��ȸ
		//String sql ="selcet ���α׷���ȣ,���α׷�.�̸�,���۽ð�,�����ð�,��������,�μ�.�̸�,�ο���,��� from ���α׷�,�μ� WHERE ���α׷�.���μ� = �μ�.�μ���ȣ";
		String sql ="SELECT * FROM ���α׷� WHERE �������� = 'F' ORDER BY ���α׷���ȣ ASC";
		ArrayList<Program> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String programNumber = rs.getString(1);
				String title = rs.getString(2);
				Date startDate = rs.getDate(3);
				Date endDate = rs.getDate(4);
				String abolition = rs.getString(5);
				String department = rs.getString(6);
				int participation = rs.getInt(7);
				String location = rs.getString(8);
				list.add(new Program(programNumber,title,startDate,endDate,abolition,department,participation,location));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertObject(list);
	}
	
	public Object[][] selectAbolitionProgramList(){//��ü ���α׷� ��ȸ
		//String sql ="selcet ���α׷���ȣ,���α׷�.�̸�,���۽ð�,�����ð�,��������,�μ�.�̸�,�ο���,��� from ���α׷�,�μ� WHERE ���α׷�.���μ� = �μ�.�μ���ȣ";
		String sql ="SELECT * FROM ���α׷� WHERE ���α׷���ȣ IN (SELECT ���α׷���ȣ FROM �������) ORDER BY ���α׷���ȣ ASC";
		ArrayList<Program> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String programNumber = rs.getString(1);
				String title = rs.getString(2);
				Date startDate = rs.getDate(3);
				Date endDate = rs.getDate(4);
				String abolition = rs.getString(5);
				String department = rs.getString(6);
				int participation = rs.getInt(7);
				String location = rs.getString(8);
				list.add(new Program(programNumber,title,startDate,endDate,abolition,department,participation,location));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertObject(list);
	}
	
	public Program selectProgram(String programNumber){//���α׷� ��ȸ
		StringBuffer sql = new StringBuffer("SELECT * FROM ���α׷� WHERE ���α׷���ȣ = '");
		sql.append(programNumber);
		sql.append("'");
		Program program = new Program();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				program.setProgramNumber(rs.getString(1));
				program.setTitle(rs.getString(2));
				program.setStartDate(rs.getDate(3));
				program.setEndDate(rs.getDate(4));
				program.setAbolition(rs.getString(5));
				program.setDepartment(rs.getString(6));
				program.setParticipation(rs.getInt(7));
				program.setLocation(rs.getString(8));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return program;
	}
	
	public Object[][] selectProgramAttendList(String programNumber){//���α׷� ��û �ο� ��ȸ
		StringBuffer sql =new StringBuffer("SELECT �ֹι�ȣ,�̸�,���� FROM �ֹ� WHERE �ֹι�ȣ IN (SELECT �ֹι�ȣ FROM ���α׷���û WHERE ���α׷���ȣ = '");
		sql.append(programNumber);
		sql.append("') ORDER BY �ֹ�.�̸� ASC");
		ArrayList<Citizen> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				list.add(new Citizen(id,name,age));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertCitizenObject(list);
	}
	
	public void insertAttendCitizen(String programNumber, String citizenNumber) {//���α׷� ���� �ֹ� �߰�
		String sql1 = "SELECT ��û��ȣ FROM (SELECT * FROM ���α׷���û  ORDER BY ��û��ȣ DESC) WHERE ROWNUM <= 1";
		String sql2 = "INSERT INTO ���α׷���û VALUES(?,?,?)";
		int row =0;
		String programNum ="";
		try {
			conn = factory.Connect();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next()) {//���� �μ���ȣ ��ȸ
				programNum = rs.getString(1);
			}
			if(programNum.isEmpty())
				programNum = "C-000001";
			else {
				int temp = Integer.parseInt(programNum.substring(2));
				temp++;
				programNum = "C-".concat(String.format("%06d", temp));
			}
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, programNum);
			pstmt.setString(2, programNumber);
			pstmt.setString(3, citizenNumber);
			row = pstmt.executeUpdate();
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			if ( conn != null) {
				try {
					conn.rollback();
				}catch (SQLException ee) {
					ee.printStackTrace();
				}
			}
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			factory.close(conn,stmt, pstmt,rs);
		}
		
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �߰��Ǿ����ϴ�.", "�ֹ� �߰�", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"�ֹ��߰��� �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void insertProgram(String title, String startDate ,String endDate,String department,String location ) {//���α׷� �߰�
		String sql = "{CALL sp_���α׷����(?, ?, ?, ?, ?)}";
		int row =0;
		try {
			conn = factory.Connect();
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, title);
			cstmt.setDate(2, java.sql.Date.valueOf(startDate));
			cstmt.setDate(3, java.sql.Date.valueOf(endDate));
			cstmt.setString(4, department);
			cstmt.setString(5, location);
			row = cstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, cstmt);
		}
		
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �߰��Ǿ����ϴ�.", "���α׷� ���", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"���α׷���Ͽ� �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void shutdownProgram(String programNumber) {//���α׷� ������ȯ
		String sql = "UPDATE ���α׷� SET �������� = ? WHERE ���α׷���ȣ = ?";
		int row =0;
		try {
			conn = factory.Connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "T");
			pstmt.setString(2, programNumber);
			row = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, pstmt);
		}
		
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �����Ǿ����ϴ�.", "���α׷� ����", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"���α׷� ������ �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateProgram(String programNumber,String title,String endDate,String department,String location) {//���α׷� ���� ������Ʈ
		String sql = "UPDATE ���α׷� SET �̸� = ?, �����ð� = ?, �μ��� = ?,��� = ? WHERE ���α׷���ȣ = ?";
		int row =0;
		try {
			conn = factory.Connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setDate(2,java.sql.Date.valueOf(endDate));
			pstmt.setString(3,department);
			pstmt.setString(4,location);
			pstmt.setString(5, programNumber);
			row = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, pstmt);
		}
		
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �����Ǿ����ϴ�.", "���α׷� ����", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"���α׷� ������ �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Object[][] convertObject(ArrayList<Program> list){
		Object[][] temp = new Object[list.size()][7];
		for(int i=0;i<list.size();i++) {
			temp[i][0] = list.get(i).getProgramNumber();
			temp[i][1] = list.get(i).getTitle();
			temp[i][2] = list.get(i).getStartDate();
			temp[i][3] = list.get(i).getEndDate();
			temp[i][4] = list.get(i).getDepartment();
			temp[i][5] = list.get(i).getParticipation();
			temp[i][6] = list.get(i).getLocation();
		}
		return temp;
	}
	
	private Object[][] convertCitizenObject(ArrayList<Citizen> list){
		Object[][] temp = new Object[list.size()][3];
		for(int i=0;i<list.size();i++) {
			temp[i][0] = list.get(i).getCitizenNumber();
			temp[i][1] = list.get(i).getName();
			temp[i][2] = list.get(i).getAge();
		}
		return temp;
	}
}
