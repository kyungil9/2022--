package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import SQL.EmployeeDao;
import entity.Department;
import entity.Employee;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class EmployeeManage {

	private JFrame frame;
	private JTextField textField_searchName;
	private JTextField textField_Name;
	private JTextField textField_Department;
	private JTextField textField_Age;
	private JTextField textField_Phonenumber;
	private JTable departmentTable;
	private JTable employeeTable;
	private String[] header1 = {"부서명"};
	private String[] header2 = {"직원번호","직원이름"};
	private EmployeeDao employeeDao = new EmployeeDao();
	private JTextField textField_employeeNumber;
	private EmployeeManage em = this;
	private JTextField textField_DepartmentName;
	private JTextField textField_dPhoneNumber;
	private JTextField textField_dName;
	private JTextField textField_DepartmentNumber;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeManage window = new EmployeeManage(null);
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
	public EmployeeManage(Main m) {
		initialize(m);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Main m) {
		frame = new JFrame();
		frame.setTitle("\uC9C1\uC6D0 \uAD00\uB9AC");
		frame.setBounds(100, 100, 972, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 146, 277);
		frame.getContentPane().add(scrollPane);
		
		departmentTable = new JTable();
		scrollPane.setViewportView(departmentTable);
		setDepartmentTable(employeeDao.selectDepartmentList());
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(433, 21, 215, 320);
		frame.getContentPane().add(scrollPane_1);
		
		employeeTable = new JTable();
		scrollPane_1.setViewportView(employeeTable);
		setEmployeeTable(null);
		
		textField_searchName = new JTextField();
		textField_searchName.setBounds(170, 20, 152, 31);
		frame.getContentPane().add(textField_searchName);
		textField_searchName.setColumns(10);
		
		JButton searchBtn = new JButton("\uAC80\uC0C9");
		searchBtn.setBounds(329, 20, 97, 31);
		frame.getContentPane().add(searchBtn);
		
		JButton addEmployeeBtn = new JButton("\uCD94\uAC00");
		addEmployeeBtn.setBounds(660, 308, 97, 33);
		frame.getContentPane().add(addEmployeeBtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(660, 21, 284, 277);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setText("\uC774\uB984 :");
		textPane.setBounds(12, 108, 55, 35);
		panel.add(textPane);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("굴림", Font.PLAIN, 14));
		textField_Name.setBounds(83, 108, 182, 35);
		panel.add(textField_Name);
		textField_Name.setColumns(10);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_1.setText("\uBD80\uC11C\uC774\uB984 :");
		textPane_1.setBounds(12, 10, 98, 28);
		panel.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBackground(Color.LIGHT_GRAY);
		textPane_2.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_2.setText("\uC9C1\uC6D0\uBC88\uD638 :");
		textPane_2.setBounds(12, 55, 98, 28);
		panel.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.LIGHT_GRAY);
		textPane_3.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_3.setText("\uB098\uC774 :");
		textPane_3.setBounds(12, 153, 55, 35);
		panel.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.LIGHT_GRAY);
		textPane_4.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_4.setText("\uC5F0\uB77D\uCC98 :");
		textPane_4.setBounds(12, 206, 73, 35);
		panel.add(textPane_4);
		
		textField_Department = new JTextField();
		textField_Department.setFont(new Font("굴림", Font.PLAIN, 14));
		textField_Department.setBounds(110, 10, 144, 35);
		panel.add(textField_Department);
		textField_Department.setColumns(10);
		
		textField_Age = new JTextField();
		textField_Age.setFont(new Font("굴림", Font.PLAIN, 14));
		textField_Age.setBounds(83, 153, 182, 35);
		panel.add(textField_Age);
		textField_Age.setColumns(10);
		
		textField_Phonenumber = new JTextField();
		textField_Phonenumber.setFont(new Font("굴림", Font.PLAIN, 14));
		textField_Phonenumber.setBounds(83, 206, 182, 35);
		panel.add(textField_Phonenumber);
		textField_Phonenumber.setColumns(10);
		
		textField_employeeNumber = new JTextField();
		textField_employeeNumber.setBackground(Color.GRAY);
		textField_employeeNumber.setEditable(false);
		textField_employeeNumber.setBounds(110, 55, 144, 35);
		panel.add(textField_employeeNumber);
		textField_employeeNumber.setColumns(10);
		
		JButton backHomeBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backHomeBtn.setBounds(12, 20, 97, 31);
		frame.getContentPane().add(backHomeBtn);
		
		JButton updateEmployeeBtn = new JButton("\uC218\uC815");
		updateEmployeeBtn.setBounds(847, 310, 97, 29);
		frame.getContentPane().add(updateEmployeeBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(170, 64, 251, 191);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBackground(Color.LIGHT_GRAY);
		textPane_5.setText("\uBD80\uC11C\uBA85 :");
		textPane_5.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_5.setEditable(false);
		textPane_5.setBounds(12, 24, 73, 30);
		panel_1.add(textPane_5);
		
		textField_DepartmentName = new JTextField();
		textField_DepartmentName.setEditable(false);
		textField_DepartmentName.setBackground(Color.GRAY);
		textField_DepartmentName.setBounds(86, 24, 140, 30);
		panel_1.add(textField_DepartmentName);
		textField_DepartmentName.setColumns(10);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setEditable(false);
		textPane_6.setBackground(Color.LIGHT_GRAY);
		textPane_6.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_6.setText("\uC5F0\uB77D\uCC98 :");
		textPane_6.setBounds(12, 64, 73, 30);
		panel_1.add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setEditable(false);
		textPane_7.setBackground(Color.LIGHT_GRAY);
		textPane_7.setText("\uAD00\uB9AC\uC790 :");
		textPane_7.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_7.setBounds(12, 104, 73, 30);
		panel_1.add(textPane_7);
		
		textField_dPhoneNumber = new JTextField();
		textField_dPhoneNumber.setBackground(Color.WHITE);
		textField_dPhoneNumber.setBounds(86, 64, 140, 30);
		panel_1.add(textField_dPhoneNumber);
		textField_dPhoneNumber.setColumns(10);
		
		textField_dName = new JTextField();
		textField_dName.setBackground(Color.WHITE);
		textField_dName.setBounds(86, 104, 140, 30);
		panel_1.add(textField_dName);
		textField_dName.setColumns(10);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setEditable(false);
		textPane_8.setBackground(Color.LIGHT_GRAY);
		textPane_8.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_8.setText("\uB4F1\uB85D \uC9C1\uC6D0\uC218 :");
		textPane_8.setBounds(12, 144, 115, 30);
		panel_1.add(textPane_8);
		
		textField_DepartmentNumber = new JTextField();
		textField_DepartmentNumber.setBackground(Color.GRAY);
		textField_DepartmentNumber.setEditable(false);
		textField_DepartmentNumber.setBounds(143, 144, 83, 30);
		panel_1.add(textField_DepartmentNumber);
		textField_DepartmentNumber.setColumns(10);
		
		JButton updateDepartmentBtn = new JButton("\uC218\uC815");
		updateDepartmentBtn.setBounds(324, 265, 97, 33);
		frame.getContentPane().add(updateDepartmentBtn);
		
		JButton addDepartmentBtn = new JButton("\uCD94\uAC00");
		addDepartmentBtn.setBounds(170, 265, 97, 33);
		frame.getContentPane().add(addDepartmentBtn);

		departmentTable.addMouseListener(new MouseAdapter() {//부서 클릭시 해당 정보들 출력
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable)e.getSource();
            	if(e.getClickCount()==2) {
            		TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        String s = m.getValueAt(row, 0).toString();
                        Department department = employeeDao.selectDepartment(s);
                        textField_DepartmentName.setText(department.getName());
                        textField_dPhoneNumber.setText(department.getPhoneNumber());
                        textField_dName.setText(department.getManagerNumber());
                        textField_DepartmentNumber.setText(Integer.toString(department.getEmployNum()));
                        setEmployeeTable(employeeDao.selectEmployeeList(s));
                    }
            	}
			}
		});
		
		employeeTable.addMouseListener(new MouseAdapter() {//직원클릭시 해당 정보 출력
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable)e.getSource();
            	if(e.getClickCount()==2) {
            		TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        String s = m.getValueAt(row, 0).toString();
                        Employee employee = employeeDao.selectCitizen(s);
                        textField_Department.setText(employee.getDepartmentName());
                        textField_employeeNumber.setText(employee.getEmployeeNumber());
                        textField_Name.setText(employee.getName());
                        textField_Age.setText(Integer.toString(employee.getAge()));
                        textField_Phonenumber.setText(employee.getPhoneNumber());
                    }
            	}
			}
		});
		
		updateEmployeeBtn.addActionListener(new ActionListener() {//직원 정보 수정
			public void actionPerformed(ActionEvent e) {
				if(isBlank())
					return;
				Employee employee = new Employee();
				employee.setDepartmentName(textField_Department.getText());
				employee.setEmployNumber(textField_employeeNumber.getText());
				employee.setName(textField_Name.getText());
				employee.setAge(Integer.parseInt(textField_Age.getText()));
				employee.setPhoneNumber(textField_Phonenumber.getText());
				String s = employeeDao.updateEmployee(employee);//업데이트
				if(s.isEmpty())
					return;
				Department department = employeeDao.selectDepartment(textField_Department.getText());
                textField_DepartmentName.setText(department.getName());
                textField_dPhoneNumber.setText(department.getPhoneNumber());
                textField_dName.setText(department.getManagerNumber());
                textField_DepartmentNumber.setText(Integer.toString(department.getEmployNum()));
				setEmployeeTable(employeeDao.selectEmployeeList(textField_Department.getText()));//부서가 변경시 테이블이동
				
				employee = employeeDao.selectCitizen(s);
                textField_Department.setText(employee.getDepartmentName());
                textField_employeeNumber.setText(employee.getEmployeeNumber());
                textField_Name.setText(employee.getName());
                textField_Age.setText(Integer.toString(employee.getAge()));
                textField_Phonenumber.setText(employee.getPhoneNumber());
				
			}
		});
		
		updateDepartmentBtn.addActionListener(new ActionListener() {//부서 정보 수정
			public void actionPerformed(ActionEvent e) {
				if(isDepartmentBlank())
					return;
				Department department = new Department();
				department.setName(textField_DepartmentName.getText());
				department.setPhoneNumber(textField_dPhoneNumber.getText());
				department.setManagerNumber(textField_dName.getText());
				String s = employeeDao.updateDepartment(department);//업데이트
				
				department = employeeDao.selectDepartment(textField_DepartmentName.getText());
                textField_DepartmentName.setText(department.getName());
                textField_dPhoneNumber.setText(department.getPhoneNumber());
                textField_dName.setText(department.getManagerNumber());
                textField_DepartmentNumber.setText(Integer.toString(department.getEmployNum()));
				setEmployeeTable(employeeDao.selectEmployeeList(textField_DepartmentName.getText()));//부서가 변경시 테이블이동
				
				Employee employee = employeeDao.selectCitizen(s);  //직원 영역 화면 업데이트
                textField_Department.setText(employee.getDepartmentName());
                textField_employeeNumber.setText(employee.getEmployeeNumber());
                textField_Name.setText(employee.getName());
                textField_Age.setText(Integer.toString(employee.getAge()));
                textField_Phonenumber.setText(employee.getPhoneNumber());
			}
		});
		
		addEmployeeBtn.addActionListener(new ActionListener() {//직원 추가
			public void actionPerformed(ActionEvent e) {
				EmployeeRegister er = new EmployeeRegister(em);
				er.setVisible(true);
			}
		});
		
		searchBtn.addActionListener(new ActionListener() {//직원 검색
			public void actionPerformed(ActionEvent e) {
				if(textField_searchName.getText().isEmpty() || textField_searchName.getText() == null) {
					return;
				}
				CitizenSearch cs = new CitizenSearch(null, null,em, textField_searchName.getText());
				cs.setVisible(true);
				textField_searchName.setText("");
			}
		});
		
		backHomeBtn.addActionListener(new ActionListener() {//돌아가기
			public void actionPerformed(ActionEvent e) {
				m.setVisible(true);
				frame.dispose();	
			}
		});
		
		addDepartmentBtn.addActionListener(new ActionListener() {//직원 추가
			public void actionPerformed(ActionEvent e) {
				DepartmentRegister dr = new DepartmentRegister(em);
				dr.setVisible(true);
			}
		});
	}
	
	public void refreshDepartment(String departmentName) {
		setDepartmentTable(employeeDao.selectDepartmentList());
		Department department = employeeDao.selectDepartment(departmentName);
        textField_DepartmentName.setText(department.getName());
        textField_dPhoneNumber.setText(department.getPhoneNumber());
        textField_dName.setText(department.getManagerNumber());
        textField_DepartmentNumber.setText(Integer.toString(department.getEmployNum()));
        setEmployeeTable(employeeDao.selectEmployeeList(departmentName));//부서가 변경시 테이블이동
        
        Employee employee = employeeDao.selectCitizen(department.getManagerNumber());
        textField_Department.setText(employee.getDepartmentName());
        textField_employeeNumber.setText(employee.getEmployeeNumber());
        textField_Name.setText(employee.getName());
        textField_Age.setText(Integer.toString(employee.getAge()));
        textField_Phonenumber.setText(employee.getPhoneNumber());
	}
	
	public void refreshEmployee(String s) {
		setEmployeeTable(employeeDao.selectEmployeeList(s));//부서가 변경시 테이블이동
	}
	public void selectEmployee(String employeeNumber) {//검색으로 선택된 직원번호로 정보 띄어주기
		Employee employee = employeeDao.selectCitizen(employeeNumber);
        textField_Department.setText(employee.getDepartmentName());
        textField_employeeNumber.setText(employee.getEmployeeNumber());
        textField_Name.setText(employee.getName());
        textField_Age.setText(Integer.toString(employee.getAge()));
        textField_Phonenumber.setText(employee.getPhoneNumber());
        setEmployeeTable(employeeDao.selectEmployeeList(employee.getDepartmentName()));//검색된 직원에 해당되는 부서테이블로 변경
        
        Department department = employeeDao.selectDepartment(employee.getDepartmentName());
        textField_DepartmentName.setText(department.getName());
        textField_dPhoneNumber.setText(department.getPhoneNumber());
        textField_dName.setText(department.getManagerNumber());
        textField_DepartmentNumber.setText(Integer.toString(department.getEmployNum()));
	}
	
	private void setDepartmentTable(Object[][] temp) {
		departmentTable.setModel(new DefaultTableModel(temp,header1) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			}
		);
	}
	
	private void setEmployeeTable(Object[][] temp) {
		employeeTable.setModel(new DefaultTableModel(temp,header2) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			}
		);
	}
	
	private boolean isBlank() {
		if(textField_Department.getText().isEmpty() || textField_Department.getText() == null)
			return true;
		else if(textField_Name.getText().isEmpty() || textField_Name.getText() == null)
			return true;
		else if(textField_Age.getText().isEmpty() || textField_Age.getText() == null)
			return true;
		else if(textField_employeeNumber.getText().isEmpty() || textField_employeeNumber.getText() == null)
			return true;
		else if(textField_Phonenumber.getText().isEmpty() || textField_Phonenumber.getText() == null)
			return true;
		else 
			return false;
	}
	
	private boolean isDepartmentBlank() {
		if(textField_DepartmentName.getText().isEmpty() || textField_DepartmentName.getText() == null)
			return true;
		else if(textField_dName.getText().isEmpty() || textField_dName.getText() == null)
			return true;
		else if(textField_dPhoneNumber.getText().isEmpty() || textField_dPhoneNumber.getText() == null)
			return true;
		else 
			return false;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
