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
	
	public Object[][] selectDepartmentList(){//전체 부서이름 조회
		String sql ="SELECT 이름 FROM 부서 ORDER BY 부서번호 ASC";
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
	
	public Object[][] selectEmployeeList(String departmentName){//전체 직원 이름 조회
		StringBuffer sql =new StringBuffer("SELECT 직원.직원번호,직원.이름 FROM 직원 WHERE 직원.부서명 = '");
		sql.append(departmentName);
		sql.append("' ORDER BY 직원번호 ASC");
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
	
	public Object[][] searchEmployeeList(String searchName){//특정 직원 이름으로 조회
		StringBuffer sql = new StringBuffer("SELECT * FROM 직원 WHERE 이름 LIKE '%");
		sql.append(searchName);
		sql.append("%' ORDER BY 직원번호 ASC");
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
	
	public Employee selectCitizen(String employeeNumber){//직원 개인 조회
		StringBuffer sql = new StringBuffer("SELECT * FROM 직원 WHERE 직원번호 = '");
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
	
	public Department selectDepartment(String departmentName){//부서 정보 불러오기
		StringBuffer sql = new StringBuffer("SELECT * FROM 부서 WHERE 이름 = '");
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
	
	
	public String selectEmployeeName(String employeeNumber){//부서번호를 부서이름으로 변환
		StringBuffer sql = new StringBuffer("SELECT 이름 FROM 직원 WHERE 직원번호 = '");
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
	
	
	
	public int selectDepartmentNumber(String department){//부서이름를 부서번호으로 변환
		StringBuffer sql = new StringBuffer("SELECT 부서번호 FROM 부서 WHERE 이름 = '");
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
	
	public void insertEmployee(String department,String name, int age, String phoneNumber) {//직원 추가
		String sql = "{CALL sp_직원신청(?, ?, ?, ?)}";
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
					"성공적으로 추가되었습니다.", "직원 추가", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"직원추가에 실패하였습니다.", "sql 처리실패", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public int selectNumDepartment(String department) {//직원수 조회 사용x
		String sql = "{CALL sp_직원수계산(?, ?)}";
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
	
	public String updateEmployee(Employee em) {//직원 내용 업데이트
		String sql5 = "SELECT 부서명 FROM 직원 WHERE 직원번호 = ?";
		String sql6 = "SELECT 관리자 FROM 부서 WHERE 이름 = ?";
		String sql7 = "UPDATE 직원 SET  이름 = ?, 나이 = ?,연락처 = ?,부서명 =? WHERE 직원번호 = ?";
		
		String sql1 = "UPDATE 직원 SET  이름 = ?, 나이 = ?,연락처 = ? WHERE 직원번호 = ?";
		String sql2 = "SELECT 직원번호 FROM (SELECT * FROM 직원 WHERE 부서명 = ? ORDER BY 직원번호 DESC) WHERE ROWNUM <= 1";
		String sql3 = "SELECT 부서번호 FROM 부서 WHERE 이름 = ?";
		String sql4 = "UPDATE 직원 SET 직원번호 = ? WHERE 직원번호 = ?";
		int row =0;
		String employeeNum ="";
		String tempNum="";
		String departNum ="";
		String department = "";
		String head = "";
		try {
			conn = factory.Connect();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql5);//직원의 부서번호가 바뀌었는지 확인
			pstmt.setString(1,em.getEmployeeNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				department = rs.getString(1);
			}
			if(!department.equals(em.getDepartmentName())) {
				pstmt = conn.prepareStatement(sql6);//관리자 계정인지 판별
				pstmt.setString(1,em.getDepartmentName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					head = rs.getString(1);
				}
				if(head.equals(em.getEmployeeNumber()))//관리자일시 종료
					return "";
				pstmt = conn.prepareStatement(sql2);//새로운 부서의 맨 뒷번호 가져오기
				pstmt.setString(1,em.getDepartmentName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					tempNum = rs.getString(1);
				}
				int temp = Integer.parseInt(tempNum.substring(4));
				temp++;
				employeeNum = "-".concat(String.format("%4d", temp));//새로운 직원번호 생성
				
				pstmt = conn.prepareStatement(sql3);//갈곳에 부서 번호 가져오기
				pstmt.setString(1,em.getDepartmentName());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					departNum = rs.getString(1);
				}
				departNum = departNum.substring(2).concat(employeeNum);//새로운 직원번호 결합
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
					"성공적으로 수정되었습니다.", "직원 수정", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"직원수정에 실패하였습니다.", "sql 처리실패", 
					JOptionPane.ERROR_MESSAGE);
		}
		return departNum;
	}
	
	public String updateDepartment(Department d) {//부서 내용 업데이트
		String sql1 = "SELECT 부서번호,관리자 FROM 부서 WHERE 이름 = ?";
		String sql2 = "SELECT 직원번호 FROM (SELECT * FROM 직원 WHERE 부서명 = ? ORDER BY 직원번호 DESC) WHERE ROWNUM <= 1";
		String sql3 = "UPDATE 부서 SET 연락처 = ?,관리자 =? WHERE 이름 = ?";
		String sql4 = "UPDATE 직원 SET 직원번호 = ?,부서명 = ? WHERE 직원번호 = ?";
		String sql5 = "UPDATE 부서 SET 연락처 = ? WHERE 이름 = ?";
		String sql6 = "UPDATE 부서 SET 관리자 = ? WHERE 이름 = ?";
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
			while(rs.next()) {//관리자가 같은지 확인
				departNum = rs.getString(1);
				employeeNum = rs.getString(2);
			}
			if(!employeeNum.equals(d.getEmployNum())) {//관리자가 변경될경우
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1,d.getName());//부서 제일뒤 번호 가져오기
				rs = pstmt.executeQuery();
				while(rs.next()) {//관리자가 같은지 확인
					tempNum = rs.getString(1);
				}
				int temp = Integer.parseInt(tempNum.substring(4));
				temp++;
				employeeNum = departNum.substring(2).concat("-").concat(String.format("%4d", temp));
				
				pstmt = conn.prepareStatement(sql3);//부서에 관리자 제거로 무결성 제거
				pstmt.setString(1,d.getPhoneNumber());
				pstmt.setString(2,null);
				pstmt.setString(3, d.getName());
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(sql4);//직원번호를 부서번호에 맞게끔 개편,부서이동
				pstmt.setString(1,employeeNum);
				pstmt.setString(2, d.getName());
				pstmt.setString(3, d.getManagerNumber());
				pstmt.executeUpdate();
				
				pstmt = conn.prepareStatement(sql6);//새로운 직원번호를 관리자에 임명
				pstmt.setString(1,employeeNum);
				pstmt.setString(2, d.getName());
				row = pstmt.executeUpdate();
			}else {
				pstmt = conn.prepareStatement(sql5);//관리자 변경없을시 연락처만 업데이트
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
					"성공적으로 수정되었습니다.", "부서 수정", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"부서수정에 실패하였습니다.", "sql 처리실패", 
					JOptionPane.ERROR_MESSAGE);
		}
		return employeeNum;
	}
	
	public void insertDepartment(Department d) {//부서 추가
		String sql1 = "SELECT 부서번호 FROM (SELECT * FROM 부서 ORDER BY 부서번호 DESC) WHERE ROWNUM <= 1";
		String sql2 = "UPDATE 직원 SET 직원번호 = ? WHERE 직원번호 = ?";
		String sql3 = "INSERT INTO 부서 (부서번호,이름,연락처,직원수,관리자) VALUES (?,?,?,?,?)";
		String sql4 = "UPDATE 직원 SET 부서명 = ? WHERE 직원번호 = ?";
		int row1 =0;
		int row2 =0;
		String departmentNumber ="";
		try {
			conn = factory.Connect();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while(rs.next()) {//다음 부서번호 조회
				departmentNumber = rs.getString(1);
			}
			int temp = Integer.parseInt(departmentNumber.substring(2));
			temp += 100;
			departmentNumber = "B-".concat(String.format("%3d", temp));
			String s = String.format("%3d", temp).concat("-1001");
			pstmt = conn.prepareStatement(sql2);//직원 직원번호 수정
			pstmt.setString(1,s );
			pstmt.setString(2, d.getManagerNumber());
			row2 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);//부서안에 insert
			pstmt.setString(1, departmentNumber);
			pstmt.setString(2, d.getName());
			pstmt.setString(3,d.getPhoneNumber());
			pstmt.setInt(4,0);
			pstmt.setString(5, s);
			row1 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql4);//직원 부서번호 수정
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
					"성공적으로 수정되었습니다.", "부서 추가", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"부서추가에 실패하였습니다.", "sql 처리실패", 
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
