package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import SQL.ProgramDao;
import entity.Program;

import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class ProgramView {

	private JFrame frame;
	private JTextField textField_Title;
	private JTextField textField_StartDate;
	private JTextField textField_EndDate;
	private JTextField textField_People;
	private JTable citizenTable;
	private JTextField textField_Name;
	private JTextField textField_Location;
	private String[] header = {"주민번호","이름","나이"};
	private ProgramDao programDao = new ProgramDao();
	private String programNumber;
	private ProgramView context = this;
	private JTextField textField_Department;
	private ProgramManage pm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramView window = new ProgramView("",null);
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
	public ProgramView(String programNumber,ProgramManage pm) {
		this.programNumber = programNumber;
		this.pm = pm;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uD504\uB85C\uADF8\uB7A8 \uC815\uBCF4");
		frame.setBounds(100, 100, 614, 424);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 285, 307);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 18));
		textPane.setText("\uC774\uB984 :");
		textPane.setBounds(49, 10, 53, 32);
		panel.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_1.setText("\uC2DC\uC791 \uC77C\uC790 :");
		textPane_1.setBounds(12, 53, 97, 32);
		panel.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBackground(Color.LIGHT_GRAY);
		textPane_2.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_2.setText("\uB9C8\uAC10 \uC77C\uC790 :");
		textPane_2.setBounds(12, 95, 97, 32);
		panel.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.LIGHT_GRAY);
		textPane_3.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_3.setText("\uC778\uC6D0\uC218 :");
		textPane_3.setBounds(36, 182, 73, 32);
		panel.add(textPane_3);
		
		textField_Title = new JTextField();
		textField_Title.setBounds(114, 10, 159, 32);
		panel.add(textField_Title);
		textField_Title.setColumns(10);
		
		textField_StartDate = new JTextField();
		textField_StartDate.setBackground(Color.GRAY);
		textField_StartDate.setEditable(false);
		textField_StartDate.setBounds(114, 53, 159, 32);
		panel.add(textField_StartDate);
		textField_StartDate.setColumns(10);
		
		textField_EndDate = new JTextField();
		textField_EndDate.setBounds(114, 95, 159, 32);
		panel.add(textField_EndDate);
		textField_EndDate.setColumns(10);
		
		textField_People = new JTextField();
		textField_People.setBackground(Color.GRAY);
		textField_People.setEditable(false);
		textField_People.setBounds(114, 181, 159, 33);
		panel.add(textField_People);
		textField_People.setColumns(10);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.LIGHT_GRAY);
		textPane_4.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_4.setText("\uC7A5\uC18C :");
		textPane_4.setBounds(12, 223, 55, 32);
		panel.add(textPane_4);
		
		textField_Location = new JTextField();
		textField_Location.setBounds(76, 224, 197, 73);
		panel.add(textField_Location);
		textField_Location.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(309, 10, 277, 307);
		frame.getContentPane().add(scrollPane);
		
		citizenTable = new JTable();
		scrollPane.setViewportView(citizenTable);
		setTable(programDao.selectProgramAttendList(programNumber));//프로그램 참여자 정보 불러오기
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("굴림", Font.PLAIN, 14));
		textField_Name.setBounds(309, 329, 166, 34);
		frame.getContentPane().add(textField_Name);
		textField_Name.setColumns(10);
		
		JButton registerPeopleBtn = new JButton("\uC2E0\uCCAD");
		registerPeopleBtn.setBounds(489, 328, 97, 34);
		frame.getContentPane().add(registerPeopleBtn);
		
		JButton shutdownProgramBtn = new JButton("\uD504\uB85C\uADF8\uB7A8 \uD3D0\uC9C0");
		shutdownProgramBtn.setBounds(12, 329, 130, 34);
		frame.getContentPane().add(shutdownProgramBtn);
		
		JButton updateProgramBtn = new JButton("\uC218\uC815 \uC0AC\uD56D \uC801\uC6A9");
		updateProgramBtn.setBounds(154, 329, 143, 34);
		frame.getContentPane().add(updateProgramBtn);
		
		textField_Department = new JTextField();
		textField_Department.setBounds(114, 137, 159, 32);
		panel.add(textField_Department);
		textField_Department.setColumns(10);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		textPane_5.setBackground(Color.LIGHT_GRAY);
		textPane_5.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_5.setText("\uB2F4\uB2F9 \uBD80\uC11C :");
		textPane_5.setBounds(12, 137, 97, 35);
		panel.add(textPane_5);
		
		Program program = programDao.selectProgram(programNumber);//해당 프로그램 정보 불러오기
		textField_Title.setText(program.getTitle());
		textField_StartDate.setText(convertDatetoString(program.getStartDate()));
		textField_EndDate.setText(convertDatetoString(program.getEndDate()));
		textField_Department.setText(program.getDepartment());
		textField_People.setText(Integer.toString(program.getParticipation()));
		textField_Location.setText(program.getLocation());
		
		if(program.getAbolition().equals("T")) {//프로그램 폐지상테일시 상태적용
			textField_Title.setEditable(false);
			textField_EndDate.setEditable(false);
			textField_Department.setEditable(false);
			textField_Location.setEditable(false);
			textField_Title.setBackground(Color.GRAY);
			textField_EndDate.setBackground(Color.GRAY);
			textField_Department.setBackground(Color.GRAY);
			textField_Location.setBackground(Color.GRAY);
			shutdownProgramBtn.setVisible(false);
			updateProgramBtn.setVisible(false);
			textField_Name.setVisible(false);
			registerPeopleBtn.setText("확인");
			registerPeopleBtn.addActionListener(new ActionListener() {//창 닫기
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
		}else {
			registerPeopleBtn.addActionListener(new ActionListener() {//신청자 추가를 위한 주민검색리스트 띄우기
				public void actionPerformed(ActionEvent e) {
					if(textField_Name.getText().isEmpty() || textField_Name.getText() == null)
						return;
					CitizenSearch cs = new CitizenSearch(null, context,null, textField_Name.getText());
					cs.setVisible(true);
					textField_Name.setText("");
				}
			});
			
			shutdownProgramBtn.addActionListener(new ActionListener() {//프로그램 강제 폐지
				public void actionPerformed(ActionEvent e) {
					programDao.shutdownProgram(programNumber);
					pm.setupdateProgressTable();
					pm.setupdateAbolitionTable();
					frame.dispose();
				}
			});
		
			updateProgramBtn.addActionListener(new ActionListener() {//프로그램 내용 수정
				public void actionPerformed(ActionEvent e) {
					if(isBlank())
						return;
					programDao.updateProgram(programNumber, textField_Title.getText(), textField_EndDate.getText(),textField_Department.getText(), textField_Location.getText());
					pm.setupdateProgressTable();
					frame.dispose();
				}
			});
		}
	}
	
	public void selectCitizen(String citizenNumber) {//전달된 주민번호를 프로그램 신청 처리,테이블 업데이트,인원수 업데이트
		programDao.insertAttendCitizen(programNumber, citizenNumber);
		setTable(programDao.selectProgramAttendList(programNumber));//프로그램 참여자 정보 불러오기
		pm.setupdateProgressTable();
		textField_People.setText(Integer.toString(Integer.parseInt(textField_People.getText())+1));
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
	
	private String convertDatetoString(Date d) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(d);
		return str;
	}
	
	private boolean isBlank() {
		if(textField_Title.getText().isEmpty() || textField_Title.getText() == null)
			return true;
		else if(textField_Department.getText().isEmpty() || textField_Department.getText() == null)
			return true;
		else if(textField_StartDate.getText().isEmpty() || textField_StartDate.getText() == null)
			return true;
		else if(textField_EndDate.getText().isEmpty() || textField_EndDate.getText() == null)
			return true;
		else if(textField_Department.getText().isEmpty() || textField_Department.getText() == null)
			return true;
		else if(textField_Location.getText().isEmpty() || textField_Location.getText() == null)
			return true;
			return false;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
