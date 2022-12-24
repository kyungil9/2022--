package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import SQL.EmployeeDao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class EmployeeRegister {

	private JFrame frame;
	private JTextField textField_Department;
	private JTextField textField_Name;
	private JTextField textField_Age;
	private JTextField textField_PhoneNumber;
	private EmployeeDao employeeDao = new EmployeeDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeRegister window = new EmployeeRegister(null);
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
	public EmployeeRegister(EmployeeManage em) {
		initialize(em);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(EmployeeManage em) {
		frame = new JFrame();
		frame.setTitle("\uC9C1\uC6D0 \uB4F1\uB85D");
		frame.setBounds(100, 100, 357, 287);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 316, 188);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane.setText("\uBD80\uC11C \uC774\uB984 :");
		textPane.setBounds(12, 10, 97, 32);
		panel.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_1.setText("\uC774\uB984 :");
		textPane_1.setBounds(54, 52, 55, 32);
		panel.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_2.setBackground(Color.LIGHT_GRAY);
		textPane_2.setText("\uB098\uC774 :");
		textPane_2.setBounds(54, 94, 55, 32);
		panel.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.LIGHT_GRAY);
		textPane_3.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_3.setText("\uC5F0\uB77D\uCC98 :");
		textPane_3.setBounds(36, 136, 73, 32);
		panel.add(textPane_3);
		
		textField_Department = new JTextField();
		textField_Department.setBounds(115, 10, 189, 32);
		panel.add(textField_Department);
		textField_Department.setColumns(10);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(115, 53, 189, 32);
		panel.add(textField_Name);
		textField_Name.setColumns(10);
		
		textField_Age = new JTextField();
		textField_Age.setBounds(115, 95, 189, 32);
		panel.add(textField_Age);
		textField_Age.setColumns(10);
		
		textField_PhoneNumber = new JTextField();
		textField_PhoneNumber.setBounds(115, 136, 189, 32);
		panel.add(textField_PhoneNumber);
		textField_PhoneNumber.setColumns(10);
		
		JButton addBtn = new JButton("\uB4F1\uB85D");
		addBtn.setBounds(123, 208, 97, 32);
		frame.getContentPane().add(addBtn);
		
		
		addBtn.addActionListener(new ActionListener() {//ÀÔ·ÂµÈ Á¤º¸ µî·Ï
			public void actionPerformed(ActionEvent e) {
				if(isBlank())
					return;
				employeeDao.insertEmployee(textField_Department.getText(), textField_Name.getText(), Integer.parseInt(textField_Age.getText()), textField_PhoneNumber.getText());
				em.refreshEmployee(textField_Department.getText());
				frame.dispose();//Ã¢´Ý±â
			}
		});
	}
	
	private boolean isBlank() {
		if(textField_Department.getText().isEmpty() || textField_Department.getText() == null)
			return true;
		else if(textField_Name.getText().isEmpty() || textField_Name.getText() == null)
			return true;
		else if(textField_Age.getText().isEmpty() || textField_Age.getText() == null)
			return true;
		else if(textField_PhoneNumber.getText().isEmpty() || textField_PhoneNumber.getText() == null)
			return true;
		else 
			return false;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
