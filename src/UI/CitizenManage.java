package UI;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import SQL.CitizenDao;

import javax.swing.JButton;

public class CitizenManage {

	private JFrame frame;
	private JTable citizenTable;
	private JTextField textField_search;
	private String header[] = {"주민번호","이름","나이","직업","주소","연락처"};	
	private CitizenDao citizenDao = new CitizenDao();
	private JButton backHomeBtn;
	private CitizenManage context = this;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitizenManage window = new CitizenManage(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CitizenManage(Main m) {
		initialize(m);
	}

	private void initialize(Main m) {
		frame = new JFrame();
		frame.setTitle("\uC8FC\uBBFC \uAC80\uC0C9");
		frame.setBounds(100, 100, 825, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 63, 781, 370);
		frame.getContentPane().add(scrollPane);
		
		citizenTable = new JTable();
		scrollPane.setViewportView(citizenTable);
		setTable(citizenDao.selectCitizenList());//주민 명단 전체 불러오기
		
		textField_search = new JTextField();
		textField_search.setBounds(499, 23, 185, 30);
		frame.getContentPane().add(textField_search);
		textField_search.setColumns(10);
		
		JButton searchCitizenBtn = new JButton("\uAC80\uC0C9");
		searchCitizenBtn.setBounds(696, 23, 97, 30);
		frame.getContentPane().add(searchCitizenBtn);
		
		JButton addCitizenBtn = new JButton("\uCD94\uAC00");
		addCitizenBtn.setBounds(682, 443, 110, 30);
		frame.getContentPane().add(addCitizenBtn);
		
		backHomeBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backHomeBtn.setBounds(12, 443, 97, 30);
		frame.getContentPane().add(backHomeBtn);
		
		searchCitizenBtn.addActionListener(new ActionListener() {//검색시 주민번호,이름으로 구분
			public void actionPerformed(ActionEvent e) {
				if(textField_search.getText() == null || textField_search.getText().isEmpty()) {
					setTable(citizenDao.selectCitizenList());//주민 명단 전체 불러오기
					return;
				}
					
				String temp = textField_search.getText();
				if(Character.isDigit(temp.charAt(0))) {//숫자일경우 주민번호
					textField_search.setText("");
					setTable(citizenDao.selectCitizenNumberList(temp));
				}else {//아닐 경우 이름검색
					textField_search.setText("");
					setTable(citizenDao.selectCitizenNameList(temp,1));
				}
			}
		});
		
		addCitizenBtn.addActionListener(new ActionListener() {//주민 추가
			public void actionPerformed(ActionEvent e) {
				CitizenView cView = new CitizenView("0",context);
				cView.setVisible(true);
			}
		});
		
		backHomeBtn.addActionListener(new ActionListener() {//프로그램 추가 창 띄우기
			public void actionPerformed(ActionEvent e) {
				m.setVisible(true);
				frame.dispose();	
			}
		});
		
		citizenTable.addMouseListener(new MouseAdapter() {//주민 정보창 띄우기
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable)e.getSource();
            	if(e.getClickCount()==2) {
            		TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        String s =  m.getValueAt(row, 0).toString();
                        //System.out.print(s);
                        CitizenView cView = new CitizenView(s,context);
                        cView.setVisible(true);
                    }
            	}
			}
		});
	}
	public void refreshTable() {
		setTable(citizenDao.selectCitizenList());//주민 명단 전체 불러오기
	}
	
	private void setTable(Object[][] temp) {
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
