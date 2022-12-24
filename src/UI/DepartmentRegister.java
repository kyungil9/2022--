package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import SQL.EmployeeDao;
import entity.Department;
import entity.Employee;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class DepartmentRegister {

	private JFrame frame;
	private JTextField textField_departmentName;
	private JTextField textField_phoneNumber;
	private JTextField textField_dName;
	private DepartmentRegister context = this;
	private EmployeeDao employeeDao = new EmployeeDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentRegister window = new DepartmentRegister(null);
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
	public DepartmentRegister(EmployeeManage em) {
		initialize(em);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(EmployeeManage em) {
		frame = new JFrame();
		frame.setTitle("\uBD80\uC11C \uB4F1\uB85D");
		frame.setBounds(100, 100, 371, 228);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 330, 169);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setEditable(false);
		textPane.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane.setText("\uBD80\uC11C\uBA85 :");
		textPane.setBounds(12, 10, 73, 28);
		panel.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setFont(new Font("굴림", Font.PLAIN, 18));
		textPane_1.setText("\uC5F0\uB77D\uCC98 :");
		textPane_1.setBounds(12, 48, 73, 28);
		panel.add(textPane_1);
		
		textField_departmentName = new JTextField();
		textField_departmentName.setBounds(88, 10, 230, 28);
		panel.add(textField_departmentName);
		textField_departmentName.setColumns(10);
		
		textField_phoneNumber = new JTextField();
		textField_phoneNumber.setBounds(88, 48, 230, 28);
		panel.add(textField_phoneNumber);
		textField_phoneNumber.setColumns(10);
		
		textField_dName = new JTextField();
		textField_dName.setBounds(88, 86, 149, 28);
		panel.add(textField_dName);
		textField_dName.setColumns(10);
		
		JButton searchNameBtn = new JButton("\uC870\uD68C");
		searchNameBtn.setBounds(245, 88, 73, 26);
		panel.add(searchNameBtn);
		
		JButton addDepartmentBtn = new JButton("\uB4F1\uB85D");
		addDepartmentBtn.setBounds(116, 124, 97, 37);
		panel.add(addDepartmentBtn);
		
		searchNameBtn.addActionListener(new ActionListener() {//직원 검색
			public void actionPerformed(ActionEvent e) {
				if(textField_dName.getText().isEmpty() || textField_dName.getText() == null)
					return;
				CitizenSearch cs = new CitizenSearch(null, null,null,context, textField_dName.getText());
				cs.setVisible(true);
			}
		});
		
		addDepartmentBtn.addActionListener(new ActionListener() {//부서 추가
			public void actionPerformed(ActionEvent e) {
				if(isBlank()) {
					return;
				}
				employeeDao.insertDepartment(new Department("", textField_departmentName.getText(), textField_phoneNumber.getText(),0, textField_dName.getText()));
				em.refreshDepartment(textField_departmentName.getText());
				frame.dispose();
			}
		});
		
	}
	
	public void selectEmployee(String employeeNumber) {//검색으로 선택된 직원번호로 정보 띄어주기
        textField_dName.setText(employeeNumber);
        textField_dName.setBackground(Color.GRAY);
        textField_dName.setEditable(false);
        
	}
	
	private boolean isBlank() {
		if(textField_departmentName.getText().isEmpty() || textField_departmentName.getText() == null)
			return true;
		else if(textField_dName.getText().isEmpty() || textField_dName.getText() == null)
			return true;
		else if(textField_phoneNumber.getText().isEmpty() || textField_phoneNumber.getText() == null)
			return true;
		else 
			return false;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
