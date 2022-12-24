package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import SQL.ProgramDao;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProgramManage {

	private JFrame frame;
	private JTable progressTable;
	private JTable abolitionTable;
	private String[] header = {"번호","제목","시작기간","마감기간","담당부서","인원수","장소"};
	private ProgramDao programDao = new ProgramDao();
	private ProgramManage context = this;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramManage window = new ProgramManage(null);
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
	public ProgramManage(Main m) {
		initialize(m);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Main m) {
		frame = new JFrame();
		frame.setTitle("\uD504\uB85C\uADF8\uB7A8 \uAD00\uB9AC");
		frame.setBounds(100, 100, 745, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 729, 424);
		frame.getContentPane().add(tabbedPane);
		
		JPanel progressProgram = new JPanel();
		tabbedPane.addTab("\uC9C4\uD589\uC911\uC778 \uD504\uB85C\uADF8\uB7A8", null, progressProgram, null);
		progressProgram.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 700, 329);
		progressProgram.add(scrollPane);
		
		progressTable = new JTable();
		scrollPane.setViewportView(progressTable);
		setProgressTable(programDao.selectProgramList());//프로그램 전체 테이블 가져오기
		
		JButton newProgramBtn = new JButton("\uD504\uB85C\uADF8\uB7A8 \uAC1C\uC124");
		newProgramBtn.setFont(new Font("굴림", Font.PLAIN, 14));
		newProgramBtn.setBounds(577, 349, 135, 36);
		progressProgram.add(newProgramBtn);
		
		JButton backHomeBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backHomeBtn.setBounds(12, 349, 108, 36);
		progressProgram.add(backHomeBtn);
		
		JPanel abolitionProgram = new JPanel();
		tabbedPane.addTab("\uD3D0\uC9C0\uB41C \uD504\uB85C\uADF8\uB7A8", null, abolitionProgram, null);
		abolitionProgram.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 10, 700, 375);
		abolitionProgram.add(scrollPane_1);
		
		abolitionTable = new JTable();
		scrollPane_1.setViewportView(abolitionTable);
		setAbolitionTable(programDao.selectAbolitionProgramList());
		
		newProgramBtn.addActionListener(new ActionListener() {//프로그램 추가 창 띄우기
			public void actionPerformed(ActionEvent e) {
				ProgramRegister pr = new ProgramRegister(context);
				pr.setVisible(true);
			}
		});
		
		backHomeBtn.addActionListener(new ActionListener() {//프로그램 추가 창 띄우기
			public void actionPerformed(ActionEvent e) {
				m.setVisible(true);
				frame.dispose();	
			}
		});
		
		progressTable.addMouseListener(new MouseAdapter() {//ㅍ로그램 정보창으로 이동
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable)e.getSource();
            	if(e.getClickCount()==2) {
            		TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        String num = m.getValueAt(row, 0).toString();
                        ProgramView pv = new ProgramView(num,context);
                        pv.setVisible(true);
                    }
            	}
			}
		});
		
		abolitionTable.addMouseListener(new MouseAdapter() {//폐지된 프로그램 정보확인, 수정불가로 open
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable)e.getSource();
            	if(e.getClickCount()==2) {
            		TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        String num = m.getValueAt(row, 0).toString();
                        ProgramView pv = new ProgramView(num,context);
                        pv.setVisible(true);
                    }
            	}
			}
		});
	}
	public void setupdateProgressTable() {
		setProgressTable(programDao.selectProgramList());//프로그램 전체 테이블 가져오기
	}
	
	public void setupdateAbolitionTable() {
		setAbolitionTable(programDao.selectAbolitionProgramList());
	}
	private void setProgressTable(Object[][] temp) {
		progressTable.setModel(new DefaultTableModel(temp,header) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			}
		);
	}
	
	private void setAbolitionTable(Object[][] temp) {
		abolitionTable.setModel(new DefaultTableModel(temp,header) {
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
