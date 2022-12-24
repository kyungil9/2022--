package SQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Citizen;
import entity.Department;
import entity.Employee;
import entity.MoveRecord;


public class EmployeeDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private ResultSet rs = null;
	
	public Object[][] selectDepartmentList(){//��ü �μ��̸� ��ȸ
		String sql ="SELECT �̸� FROM �μ� ORDER BY �μ���ȣ ASC";
		ArrayList<String> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString(1);
				list.add(name);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertObject(list);
	}
	
	public Object[][] selectEmployeeList(String departmentName){//��ü ���� �̸� ��ȸ
		StringBuffer sql =new StringBuffer("SELECT ����.������ȣ,����.�̸� FROM ���� WHERE ����.�μ��� = '");
		sql.append(departmentName);
		sql.append("' ORDER BY ������ȣ ASC");
		ArrayList<Employee> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				String num = rs.getString(1);
				String name = rs.getString(2);
				list.add(new Employee(num,name));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertEmployeeObject(list);
	}
	
	public Object[][] searchEmployeeList(String searchName){//Ư�� ���� �̸����� ��ȸ
		StringBuffer sql = new StringBuffer("SELECT * FROM ���� WHERE �̸� LIKE '%");
		sql.append(searchName);
		sql.append("%' ORDER BY ������ȣ ASC");
		ArrayList<Employee> list = new ArrayList<>();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				list.add(new Employee(id,name));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return convertEmployeeObject(list);
	}
	
	public Employee selectCitizen(String employeeNumber){//���� ���� ��ȸ
		StringBuffer sql = new StringBuffer("SELECT * FROM ���� WHERE ������ȣ = '");
		sql.append(employeeNumber);
		sql.append("'");
		Employee employee = new Employee();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				employee.setEmployNumber(rs.getString(1));
				employee.setName(rs.getString(2));
				employee.setAge(rs.getInt(3));
				employee.setPhoneNumber(rs.getString(4));
				employee.setDepartmentName(rs.getString(5));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return employee;
	}
	
	public Department selectDepartment(String departmentName){//�μ� ���� �ҷ�����
		StringBuffer sql = new StringBuffer("SELECT * FROM �μ� WHERE �̸� = '");
		sql.append(departmentName);
		sql.append("'");
		Department department = new Department();
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				department.setDepartmentNumber(rs.getString(1));
				department.setName(rs.getString(2));
				department.setPhoneNumber(rs.getString(3));
				department.setEmployeeNum(rs.getInt(4));
				department.setManagerNumber(rs.getString(5));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return department;
	}
	
	
	public String selectEmployeeName(String employeeNumber){//�μ���ȣ�� �μ��̸����� ��ȯ
		StringBuffer sql = new StringBuffer("SELECT �̸� FROM ���� WHERE ������ȣ = '");
		sql.append(employeeNumber);
		sql.append("'");
		String string = "";
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				string = rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return string;
	}
	
	
	
	public int selectDepartmentNumber(String department){//�μ��̸��� �μ���ȣ���� ��ȯ
		StringBuffer sql = new StringBuffer("SELECT �μ���ȣ FROM �μ� WHERE �̸� = '");
		sql.append(department);
		sql.append("'");
		int n = 0;
		try {
			conn = factory.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				n = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt ,rs);
		}
		return n;
	}
	
	public void insertEmployee(String department,String name, int age, String phoneNumber) {//���� �߰�
		String sql = "{CALL sp_������û(?, ?, ?, ?)}";
		int row =0;
		try {
			conn = factory.Connect();
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, department);
			cstmt.setString(2, name);
			cstmt.setInt(3, age);
			cstmt.setString(4, phoneNumber);
			row = cstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, cstmt);
		}
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �߰��Ǿ����ϴ�.", "���� �߰�", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"�����߰��� �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public int selectNumDepartment(String department) {//������ ��ȸ ���x
		String sql = "{CALL sp_���������(?, ?)}";
		int num =0;
		try {
			conn = factory.Connect();
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, department);
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.executeUpdate();
			num = cstmt.getInt(2);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, cstmt);
		}
		return num;
	}
	
	public String updateEmployee(Employee em) {//���� ���� ������Ʈ
		String sql5 = "SELECT �μ��� FROM ���� WHERE ������ȣ = ?";
		String sql6 = "SELECT ������ FROM �μ� WHERE �̸� = ?";
		String sql7 = "UPDATE ���� SET  �̸� = ?, ���� = ?,����ó = ?,�μ��� =? WHERE ������ȣ = ?";
		
		String sql1 = "UPDATE ���� SET  �̸� = ?, ���� = ?,����ó = ? WHERE ������ȣ = ?";
		String sql2 = "SELECT ������ȣ FROM (SELECT * FROM ���� WHERE �μ��� = ? ORDER BY ������ȣ DESC) WHERE ROWNUM <= 1";
		String sql3 = "SELECT �μ���ȣ FROM �μ� WHERE �̸� = ?";
		String sql4 = "UPDATE ���� SET ������ȣ = ? WHERE ������ȣ = ?";
		int row =0;
		String employeeNum ="";
		String tempNum="";
		String departNum ="";
		String department = "";
		String head = "";
		try {
			conn = factory.Connect();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql5);//������ �μ���ȣ�� �ٲ������ Ȯ��
			pstmt.setString(1,em.getEmployeeNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				department = rs.getString(1);
			}
			if(!department.equals(em.getDepartmentName())) {
				pstmt = conn.prepareStatement(sql6);//������ �������� �Ǻ�
				pstmt.setString(1,em.getDepartmentName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					head = rs.getString(1);
				}
				if(head.equals(em.getEmployeeNumber()))//�������Ͻ� ����
					return "";
				pstmt = conn.prepareStatement(sql2);//���ο� �μ��� �� �޹�ȣ ��������
				pstmt.setString(1,em.getDepartmentName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					tempNum = rs.getString(1);
				}
				int temp = Integer.parseInt(tempNum.substring(4));
				temp++;
				employeeNum = "-".concat(String.format("%4d", temp));//���ο� ������ȣ ����
				
				pstmt = conn.prepareStatement(sql3);//������ �μ� ��ȣ ��������
				pstmt.setString(1,em.getDepartmentName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					departNum = rs.getString(1);
				}
				departNum = departNum.substring(2).concat(employeeNum);//���ο� ������ȣ ����
				pstmt = conn.prepareStatement(sql4);
				pstmt.setString(1,departNum);
				pstmt.setString(2, em.getEmployeeNumber());
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(sql7);
				pstmt.setString(1,em.getName());
				pstmt.setInt(2, em.getAge());
				pstmt.setString(3, em.getPhoneNumber());
				pstmt.setString(4, em.getDepartmentName());
				pstmt.setString(5, departNum);
				row = pstmt.executeUpdate();
			}else {
				pstmt = conn.prepareStatement(sql1);
				pstmt.setString(1,em.getName());
				pstmt.setInt(2, em.getAge());
				pstmt.setString(3, em.getPhoneNumber());
				pstmt.setString(4, em.getEmployeeNumber());
				row = pstmt.executeUpdate();
			}
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
			factory.close(conn, pstmt,rs);
		}
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �����Ǿ����ϴ�.", "���� ����", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"���������� �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
		return departNum;
	}
	
	public String updateDepartment(Department d) {//�μ� ���� ������Ʈ
		String sql1 = "SELECT �μ���ȣ,������ FROM �μ� WHERE �̸� = ?";
		String sql2 = "SELECT ������ȣ FROM (SELECT * FROM ���� WHERE �μ��� = ? ORDER BY ������ȣ DESC) WHERE ROWNUM <= 1";
		String sql3 = "UPDATE �μ� SET ����ó = ?,������ =? WHERE �̸� = ?";
		String sql4 = "UPDATE ���� SET ������ȣ = ?,�μ��� = ? WHERE ������ȣ = ?";
		String sql5 = "UPDATE �μ� SET ����ó = ? WHERE �̸� = ?";
		String sql6 = "UPDATE �μ� SET ������ = ? WHERE �̸� = ?";
		int row =0;
		String employeeNum ="";
		String tempNum="";
		String departNum ="";
		try {
			
			conn = factory.Connect();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1,d.getName());
			rs = pstmt.executeQuery();
			while(rs.next()) {//�����ڰ� ������ Ȯ��
				departNum = rs.getString(1);
				employeeNum = rs.getString(2);
			}
			if(!employeeNum.equals(d.getEmployNum())) {//�����ڰ� ����ɰ��
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1,d.getName());//�μ� ���ϵ� ��ȣ ��������
				rs = pstmt.executeQuery();
				while(rs.next()) {//�����ڰ� ������ Ȯ��
					tempNum = rs.getString(1);
				}
				int temp = Integer.parseInt(tempNum.substring(4));
				temp++;
				employeeNum = departNum.substring(2).concat("-").concat(String.format("%4d", temp));
				
				pstmt = conn.prepareStatement(sql3);//�μ��� ������ ���ŷ� ���Ἲ ����
				pstmt.setString(1,d.getPhoneNumber());
				pstmt.setString(2,null);
				pstmt.setString(3, d.getName());
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(sql4);//������ȣ�� �μ���ȣ�� �°Բ� ����,�μ��̵�
				pstmt.setString(1,employeeNum);
				pstmt.setString(2, d.getName());
				pstmt.setString(3, d.getManagerNumber());
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(sql6);//���ο� ������ȣ�� �����ڿ� �Ӹ�
				pstmt.setString(1,employeeNum);
				pstmt.setString(2, d.getName());
				row = pstmt.executeUpdate();
			}else {
				pstmt = conn.prepareStatement(sql5);//������ ��������� ����ó�� ������Ʈ
				pstmt.setString(1,d.getPhoneNumber());
				pstmt.setString(2, d.getName());
				row = pstmt.executeUpdate();
			}
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
			factory.close(conn, pstmt,rs);
		}
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �����Ǿ����ϴ�.", "�μ� ����", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"�μ������� �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
		return employeeNum;
	}
	
	public void insertDepartment(Department d) {//�μ� �߰�
		String sql1 = "SELECT �μ���ȣ FROM (SELECT * FROM �μ� ORDER BY �μ���ȣ DESC) WHERE ROWNUM <= 1";
		String sql2 = "UPDATE ���� SET ������ȣ = ? WHERE ������ȣ = ?";
		String sql3 = "INSERT INTO �μ� (�μ���ȣ,�̸�,����ó,������,������) VALUES (?,?,?,?,?)";
		String sql4 = "UPDATE ���� SET �μ��� = ? WHERE ������ȣ = ?";
		int row1 =0;
		int row2 =0;
		String departmentNumber ="";
		try {
			conn = factory.Connect();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next()) {//���� �μ���ȣ ��ȸ
				departmentNumber = rs.getString(1);
			}
			int temp = Integer.parseInt(departmentNumber.substring(2));
			temp += 100;
			departmentNumber = "B-".concat(String.format("%3d", temp));
			String s = String.format("%3d", temp).concat("-1001");
			pstmt = conn.prepareStatement(sql2);//���� ������ȣ ����
			pstmt.setString(1,s );
			pstmt.setString(2, d.getManagerNumber());
			row2 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);//�μ��ȿ� insert
			pstmt.setString(1, departmentNumber);
			pstmt.setString(2, d.getName());
			pstmt.setString(3,d.getPhoneNumber());
			pstmt.setInt(4,0);
			pstmt.setString(5, s);
			row1 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql4);//���� �μ���ȣ ����
			pstmt.setString(1, d.getName());
			pstmt.setString(2, s);
			row2 = pstmt.executeUpdate();
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
		if(row1 > 0 && row2 >0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �����Ǿ����ϴ�.", "�μ� �߰�", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"�μ��߰��� �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Object[][] convertObject(ArrayList<String> list){
		Object[][] temp = new Object[list.size()][1];
		for(int i=0;i<list.size();i++) {
			temp[i][0] = list.get(i);
		}
		return temp;
	}
	
	private Object[][] convertEmployeeObject(ArrayList<Employee> list){
		Object[][] temp = new Object[list.size()][2];
		for(int i=0;i<list.size();i++) {
			temp[i][0] = list.get(i).getEmployeeNumber();
			temp[i][1] = list.get(i).getName();
		}
		return temp;
	}
}
