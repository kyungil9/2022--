package UI;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import SQL.CitizenDao;
import SQL.EmployeeDao;

import javax.swing.JButton;

public class CitizenSearch {

	private JFrame frame;
	private JTable citizenTable;
	private JButton checkBtn;
	private String[] citizenHeader = {"주민번호","이름"};
	private String[] employeeHeader = {"직원번호","이름"};
	private CitizenDao citizenDao = new CitizenDao();
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitizenSearch window = new CitizenSearch(null,null,null,"");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CitizenSearch(MoveManage mm,ProgramView pv,EmployeeManage em, String name) {
		initialize(mm,pv,em,null,name);
	}
	public CitizenSearch(MoveManage mm,ProgramView pv,EmployeeManage em,DepartmentRegister dr, String name) {
		initialize(mm,pv,em,dr,name);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(MoveManage mm,ProgramView pv, EmployeeManage em,DepartmentRegister dr,String name) {
		frame = new JFrame();
		frame.setTitle("\uC8FC\uBBFC \uC870\uD68C");
		frame.setBounds(100, 100, 367, 465);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 327, 366);
		frame.getContentPane().add(scrollPane);
		
		citizenTable = new JTable();
		scrollPane.setViewportView(citizenTable);
		
		checkBtn = new JButton("\uD655\uC778");
		checkBtn.setBounds(126, 386, 97, 30);
		frame.getContentPane().add(checkBtn);
		
		if(mm != null || pv != null) {//시민검색창
			setTable(citizenDao.selectCitizenNameList(name,0),citizenHeader);//검색된 주민 명단 불러오기
		}else {//직원 검색창
			frame.setTitle("직원 조회");
			setTable(employeeDao.searchEmployeeList(name),employeeHeader);//검색된 직원 명단 불러오기
		}
		
		
		checkBtn.addActionListener(new ActionListener() {//확인 버튼 - >창닫기
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//창닫기
			}
		});
		
		citizenTable.addMouseListener(new MouseAdapter() {//해당되는 주민 클릭
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable)e.getSource();
            	if(e.getClickCount()==2) {
            		TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        if(pv != null) {//프로그램 관리쪽에서 넘어온거
                        	String s =  m.getValueAt(row, 0).toString();
                        	pv.selectCitizen(s);
                        	frame.dispose();
                        }else if(mm != null){//전입관리에서 넘어온것
                        	String s =  m.getValueAt(row, 0).toString();
                        	mm.selectCitizen(s);
                        	frame.dispose();
                        }else if(em != null) {//직원 관리에서 넘어온것
                        	String s =  m.getValueAt(row, 0).toString();
                        	em.selectEmployee(s);
                        	frame.dispose();
                        }else {
                        	String s =  m.getValueAt(row, 0).toString();
                        	dr.selectEmployee(s);
                        	frame.dispose();
                        }
                    }
            	}
			}
		});
	}
	
	private void setTable(Object[][] temp, String[] header) {
		citizenTable.setModel(new DefaultTableModel(temp,header) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			}
		);
	}
	

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
