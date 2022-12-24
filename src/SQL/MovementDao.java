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

	
	public void insertMovement(MoveRecord mr) {//전입기록 추가
		String sql = "{CALL sp_전입신청(?, ?, ?, ?)}";
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
					"성공적으로 추가되었습니다.", "전입기록 추가", 
					JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, 
					"전입기록추가에 실패하였습니다.", "sql 처리실패", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
