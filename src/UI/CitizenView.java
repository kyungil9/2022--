package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import SQL.CitizenDao;
import entity.Citizen;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CitizenView {

	private JFrame frame;
	private JTextField textField_Name;
	private JTextField textField_Age;
	private JTextField textField_Job;
	private JTextField textField_Address;
	private JTextField textField_PhoneNum;
	private JTextField textField_CitizenNumber;
	private CitizenDao citizenDao = new CitizenDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitizenView window = new CitizenView("0",null);
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
	public CitizenView(String citizenNumber,CitizenManage cm) {
		initialize(citizenNumber,cm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String citizenNumber,CitizenManage cm) {
		frame = new JFrame();
		frame.setTitle("\uC8FC\uBBFC \uC815\uBCF4");
		frame.setBounds(100, 100, 451, 437);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(23, 20, 385, 321);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setText("\uC8FC\uBBFC\uBC88\uD638 :");
		textPane.setBounds(12, 23, 91, 28);
		panel.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_1.setText("\uC774\uB984 :");
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setBounds(48, 67, 55, 28);
		panel.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_2.setBackground(Color.LIGHT_GRAY);
		textPane_2.setText("\uB098\uC774 :");
		textPane_2.setBounds(48, 112, 55, 28);
		panel.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setText("\uC9C1\uC5C5 :");
		textPane_3.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_3.setBackground(Color.LIGHT_GRAY);
		textPane_3.setBounds(48, 150, 55, 28);
		panel.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.LIGHT_GRAY);
		textPane_4.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_4.setText("\uC8FC\uC18C :");
		textPane_4.setBounds(48, 188, 55, 28);
		panel.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		textPane_5.setBackground(Color.LIGHT_GRAY);
		textPane_5.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_5.setText("\uC5F0\uB77D\uCC98 :");
		textPane_5.setBounds(30, 283, 73, 28);
		panel.add(textPane_5);
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		textField_Name.setBounds(124, 71, 222, 28);
		panel.add(textField_Name);
		textField_Name.setColumns(10);
		
		textField_Age = new JTextField();
		textField_Age.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		textField_Age.setBounds(124, 112, 222, 28);
		panel.add(textField_Age);
		textField_Age.setColumns(10);
		
		textField_Job = new JTextField();
		textField_Job.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		textField_Job.setBounds(124, 150, 222, 28);
		panel.add(textField_Job);
		textField_Job.setColumns(10);
		
		textField_Address = new JTextField();
		textField_Address.setBackground(Color.GRAY);
		textField_Address.setEditable(false);
		textField_Address.setFont(new Font("Gulim", Font.PLAIN, 12));
		textField_Address.setBounds(124, 188, 222, 83);
		panel.add(textField_Address);
		textField_Address.setColumns(10);
		
		textField_PhoneNum = new JTextField();
		textField_PhoneNum.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		textField_PhoneNum.setBounds(124, 283, 222, 28);
		panel.add(textField_PhoneNum);
		textField_PhoneNum.setColumns(10);
		
		textField_CitizenNumber = new JTextField();
		textField_CitizenNumber.setEditable(false);
		textField_CitizenNumber.setBackground(Color.GRAY);
		textField_CitizenNumber.setBounds(124, 23, 222, 31);
		panel.add(textField_CitizenNumber);
		textField_CitizenNumber.setColumns(10);
		
		JButton checkBtn = new JButton("\uD655\uC778");
		checkBtn.setBounds(170, 351, 97, 37);
		frame.getContentPane().add(checkBtn);
		
		JButton updateBtn = new JButton("\uC218\uC815");
		updateBtn.setBounds(311, 351, 97, 37);
		frame.getContentPane().add(updateBtn);
		if(citizenNumber.equals("0")) {//µî·Ï ¸ðµå
			checkBtn.setText("µî·Ï");
			checkBtn.addActionListener(new ActionListener() {//ÁÖ¹Î µî·Ï
				public void actionPerformed(ActionEvent e) {
					if(isBlank())
						return;
					Citizen citizen = new Citizen();
					citizen.setCitizenNumber(textField_CitizenNumber.getText());
					citizen.setName(textField_Name.getText());
					try {
						citizen.setAge(Integer.parseInt(textField_Age.getText()));
					}catch (NumberFormatException ex) {
						ex.printStackTrace();
					}
					citizen.setJob(textField_Job.getText());
					citizen.setAddress(textField_Address.getText());
					citizen.setPhoneNumber(textField_PhoneNum.getText());
					citizenDao.insertCitizen(citizen.getCitizenNumber(),citizen.getName(),citizen.getAge(), citizen.getJob(), citizen.getAddress(), citizen.getPhoneNumber());
					cm.refreshTable();
					frame.dispose();//Ã¢´Ý±â
				}
			});
			textField_CitizenNumber.setBackground(Color.WHITE);
			textField_CitizenNumber.setEditable(true);
			textField_Address.setBackground(Color.WHITE);
			textField_Address.setEditable(true);
			updateBtn.setVisible(false);
		}else {//ÁÖ¹Î ¼öÁ¤ ¸ðµå
			Citizen citizen = citizenDao.selectCitizen(citizenNumber);
			textField_CitizenNumber.setText(citizen.getCitizenNumber());
			textField_Name.setText(citizen.getName());
			textField_Age.setText(Integer.toString(citizen.getAge()));
			textField_Job.setText(citizen.getJob());
			textField_Address.setText(citizen.getAddress());
			textField_PhoneNum.setText(citizen.getPhoneNumber());
			
			checkBtn.addActionListener(new ActionListener() {//È®ÀÎ ¹öÆ° - >Ã¢´Ý±â
				public void actionPerformed(ActionEvent e) {
					frame.dispose();//Ã¢´Ý±â
				}
			});
			
			updateBtn.addActionListener(new ActionListener() {//¼öÁ¤ ¹öÆ°
				public void actionPerformed(ActionEvent e) {
					if(isBlank())
						return;
					Citizen citizen = new Citizen();
					citizen.setCitizenNumber(textField_CitizenNumber.getText());
					citizen.setName(textField_Name.getText());
					try {
						citizen.setAge(Integer.parseInt(textField_Age.getText()));
					}catch (NumberFormatException ex) {
						ex.printStackTrace();
					}
					citizen.setJob(textField_Job.getText());
					citizen.setPhoneNumber(textField_PhoneNum.getText());
					citizenDao.updateCitizen(citizen.getCitizenNumber(),citizen.getName(),citizen.getAge(), citizen.getJob(), citizen.getPhoneNumber());
					cm.refreshTable();
					frame.dispose();//Ã¢´Ý±â
				}
			});
		}
	}
	
	private boolean isBlank() {
		if(textField_CitizenNumber.getText().isEmpty() || textField_CitizenNumber.getText() == null)
			return true;
		else if(textField_Name.getText().isEmpty() || textField_Name.getText() == null)
			return true;
		else if(textField_Age.getText().isEmpty() || textField_Age.getText() == null)
			return true;
		else if(textField_Job.getText().isEmpty() || textField_Job.getText() == null)
			return true;
		else if(textField_Address.getText().isEmpty() || textField_Address.getText() == null)
			return true;
		else if(textField_PhoneNum.getText().isEmpty() || textField_PhoneNum.getText() == null)
			return true;
		else 
			return false;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
