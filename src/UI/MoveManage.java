package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import SQL.CitizenDao;
import SQL.MovementDao;
import entity.Citizen;
import entity.MoveRecord;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MoveManage {

	private JFrame frame;
	private JTextField textField_Citizen;
	private JTextField textField_PhoneNum;
	private JTextField textField_HeadHouse;
	private JTextField textField_AfterAddress;
	private JTextField textField_CitizenNumber;
	private JTextField textField_BeforeAddress;
	private MoveManage context = this;
	private MovementDao movementDao = new MovementDao();
	private CitizenDao citizenDao = new CitizenDao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoveManage window = new MoveManage(null);
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
	public MoveManage(Main m) {
		initialize(m);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Main m) {
		frame = new JFrame();
		frame.setTitle("\uC804\uC785\uC2E0\uACE0\uC11C");
		frame.setBounds(100, 100, 416, 597);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 379, 538);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField_Citizen = new JTextField();
		textField_Citizen.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		textField_Citizen.setBounds(117, 31, 153, 35);
		panel.add(textField_Citizen);
		textField_Citizen.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		textPane.setText("\uC2E0\uCCAD\uC790 :");
		textPane.setBounds(36, 31, 73, 35);
		panel.add(textPane);
		
		JButton searchBtn = new JButton("\uC870\uD68C");
		searchBtn.setBounds(282, 31, 74, 35);
		panel.add(searchBtn);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		textPane_1.setText("\uC5F0\uB77D\uCC98 :");
		textPane_1.setBounds(36, 121, 73, 35);
		panel.add(textPane_1);
		
		textField_PhoneNum = new JTextField();
		textField_PhoneNum.setBackground(Color.GRAY);
		textField_PhoneNum.setEditable(false);
		textField_PhoneNum.setBounds(117, 121, 239, 35);
		panel.add(textField_PhoneNum);
		textField_PhoneNum.setColumns(10);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBackground(Color.LIGHT_GRAY);
		textPane_2.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		textPane_2.setText("\uC138\uB300\uC8FC :");
		textPane_2.setBounds(36, 166, 73, 35);
		panel.add(textPane_2);
		
		textField_HeadHouse = new JTextField();
		textField_HeadHouse.setBounds(117, 166, 239, 35);
		panel.add(textField_HeadHouse);
		textField_HeadHouse.setColumns(10);
		
		textField_AfterAddress = new JTextField();
		textField_AfterAddress.setBounds(117, 329, 239, 144);
		panel.add(textField_AfterAddress);
		textField_AfterAddress.setColumns(10);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		textPane_3.setBackground(Color.LIGHT_GRAY);
		textPane_3.setText("\uC774\uB3D9 \uC8FC\uC18C :");
		textPane_3.setBounds(12, 329, 97, 35);
		panel.add(textPane_3);
		
		JButton moveBtn = new JButton("\uC804\uC785 \uC2E0\uCCAD");
		moveBtn.setBounds(259, 483, 97, 36);
		panel.add(moveBtn);
		
		textField_CitizenNumber = new JTextField();
		textField_CitizenNumber.setBackground(Color.GRAY);
		textField_CitizenNumber.setEditable(false);
		textField_CitizenNumber.setBounds(117, 76, 239, 35);
		panel.add(textField_CitizenNumber);
		textField_CitizenNumber.setColumns(10);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.LIGHT_GRAY);
		textPane_4.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		textPane_4.setText("\uC8FC\uBBFC \uBC88\uD638 :");
		textPane_4.setBounds(12, 76, 97, 35);
		panel.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setEditable(false);
		textPane_5.setBackground(Color.LIGHT_GRAY);
		textPane_5.setFont(new Font("±º∏≤", Font.PLAIN, 18));
		textPane_5.setText("\uC774\uC804 \uC8FC\uC18C :");
		textPane_5.setBounds(12, 211, 97, 35);
		panel.add(textPane_5);
		
		textField_BeforeAddress = new JTextField();
		textField_BeforeAddress.setBackground(Color.GRAY);
		textField_BeforeAddress.setEditable(false);
		textField_BeforeAddress.setBounds(117, 211, 239, 107);
		panel.add(textField_BeforeAddress);
		textField_BeforeAddress.setColumns(10);
		
		JButton backHomeBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		backHomeBtn.setBounds(12, 483, 97, 36);
		panel.add(backHomeBtn);
		
		searchBtn.addActionListener(new ActionListener() {//¡÷πŒ º±≈√√¢ ∂ÁøÏ±‚
			public void actionPerformed(ActionEvent e) {
				if(textField_Citizen.getText().isEmpty() || textField_Citizen.getText() == null)
					return;
				CitizenSearch cs = new CitizenSearch(context, null,null, textField_Citizen.getText());
				cs.setVisible(true);
			}
		});
		
		moveBtn.addActionListener(new ActionListener() {//Ω≈√ªº≠ Ω≈√ª
			public void actionPerformed(ActionEvent e) {
				if(isBlank())
					return;
				MoveRecord mRecord = new MoveRecord(0,textField_HeadHouse.getText(),textField_Citizen.getText(),textField_CitizenNumber.getText(),textField_BeforeAddress.getText(),textField_AfterAddress.getText(),textField_PhoneNum.getText());
				movementDao.insertMovement(mRecord);
				m.setVisible(true);
				frame.dispose();			
			}
		});
		backHomeBtn.addActionListener(new ActionListener() {//µπæ∆∞°±‚
			public void actionPerformed(ActionEvent e) {
				m.setVisible(true);
				frame.dispose();	
			}
		});
		
	}
	
	public void selectCitizen(String citizenNumber) {
		Citizen citizen = citizenDao.selectCitizen(citizenNumber);
		textField_Citizen.setEditable(false);
		textField_Citizen.setText(citizen.getName());
		textField_Citizen.setBackground(Color.GRAY);
		textField_BeforeAddress.setText(citizen.getAddress());
		textField_CitizenNumber.setText(citizen.getCitizenNumber());
		textField_PhoneNum.setText(citizen.getPhoneNumber());
	}
	
	private boolean isBlank() {
		if(textField_CitizenNumber.getText().isEmpty() || textField_CitizenNumber.getText() == null)
			return true;
		else if(textField_HeadHouse.getText().isEmpty() || textField_HeadHouse.getText() == null)
			return true;
		else if(textField_Citizen.getText().isEmpty() || textField_Citizen.getText() == null)
			return true;
		else if(textField_AfterAddress.getText().isEmpty() || textField_AfterAddress.getText() == null)
			return true;
		else if(textField_BeforeAddress.getText().isEmpty() || textField_BeforeAddress.getText() == null)
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
