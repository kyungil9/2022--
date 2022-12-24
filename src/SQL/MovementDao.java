package SQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import entity.MoveRecord;

public class MovementDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private Connection conn = null;
	private CallableStatement cstmt = null;

	
	public void insertMovement(MoveRecord mr) {//���Ա�� �߰�
		String sql = "{CALL sp_���Խ�û(?, ?, ?, ?)}";
		int row =0;
		try {
			conn = factory.Connect();
			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, mr.getCitizenNumber());
			cstmt.setString(2, mr.getHeadHousehold());
			cstmt.setString(3, mr.getCitizenName());
			cstmt.setString(4, mr.getAfterAddress());
			row = cstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			factory.close(conn, cstmt);
		}
		if(row > 0) {
			JOptionPane.showMessageDialog(null, 
					"���������� �߰��Ǿ����ϴ�.", "���Ա�� �߰�", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"���Ա���߰��� �����Ͽ����ϴ�.", "sql ó������", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
