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
	private String[] citizenHeader = {"�ֹι�ȣ","�̸�"};
	private String[] employeeHeader = {"������ȣ","�̸�"};
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
		
		if(mm != null || pv != null) {//�ùΰ˻�â
			setTable(citizenDao.selectCitizenNameList(name,0),citizenHeader);//�˻��� �ֹ� ��� �ҷ�����
		}else {//���� �˻�â
			frame.setTitle("���� ��ȸ");
			setTable(employeeDao.searchEmployeeList(name),employeeHeader);//�˻��� ���� ��� �ҷ�����
		}
		
		
		checkBtn.addActionListener(new ActionListener() {//Ȯ�� ��ư - >â�ݱ�
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//â�ݱ�
			}
		});
		
		citizenTable.addMouseListener(new MouseAdapter() {//�ش�Ǵ� �ֹ� Ŭ��
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable)e.getSource();
            	if(e.getClickCount()==2) {
            		TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        if(pv != null) {//���α׷� �����ʿ��� �Ѿ�°�
                        	String s =  m.getValueAt(row, 0).toString();
                        	pv.selectCitizen(s);
                        	frame.dispose();
                        }else if(mm != null){//���԰������� �Ѿ�°�
                        	String s =  m.getValueAt(row, 0).toString();
                        	mm.selectCitizen(s);
                        	frame.dispose();
                        }else if(em != null) {//���� �������� �Ѿ�°�
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
