package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import SQL.ProgramDao;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ProgramRegister {

	private JFrame frame;
	private JTextField textField_Title;
	private JTextField textField_StartDate;
	private JTextField textField_EndDate;
	private JTextField textField_Location;
	private JTextField textField_Department;
	private ProgramDao programDao = new ProgramDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramRegister window = new ProgramRegister(null);
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
	public ProgramRegister(ProgramManage pm) {
		initialize(pm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ProgramManage pm) {
		frame = new JFrame();
		frame.setTitle("\uD504\uB85C\uADF8\uB7A8 \uB4F1\uB85D");
		frame.setBounds(100, 100, 357, 375);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 10, 318, 264);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane.setText("\uC774\uB984 :");
		textPane.setBounds(12, 10, 55, 31);
		panel.add(textPane);
		
		textField_Title = new JTextField();
		textField_Title.setBounds(79, 10, 227, 31);
		panel.add(textField_Title);
		textField_Title.setColumns(10);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(Color.LIGHT_GRAY);
		textPane_1.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_1.setText("\uC2DC\uC791 \uC77C\uC790 :");
		textPane_1.setBounds(12, 48, 97, 31);
		panel.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBackground(Color.LIGHT_GRAY);
		textPane_2.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_2.setText("\uB9C8\uAC10 \uC77C\uC790 :");
		textPane_2.setBounds(12, 89, 97, 31);
		panel.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setEditable(false);
		textPane_3.setBackground(Color.LIGHT_GRAY);
		textPane_3.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_3.setText("\uC7A5\uC18C :");
		textPane_3.setBounds(12, 172, 55, 31);
		panel.add(textPane_3);
		
		textField_StartDate = new JTextField();
		textField_StartDate.setBounds(109, 48, 197, 31);
		panel.add(textField_StartDate);
		textField_StartDate.setColumns(10);
		
		textField_EndDate = new JTextField();
		textField_EndDate.setBounds(109, 89, 197, 31);
		panel.add(textField_EndDate);
		textField_EndDate.setColumns(10);
		
		textField_Location = new JTextField();
		textField_Location.setBounds(70, 172, 236, 75);
		panel.add(textField_Location);
		textField_Location.setColumns(10);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBackground(Color.LIGHT_GRAY);
		textPane_4.setFont(new Font("±¼¸²", Font.PLAIN, 18));
		textPane_4.setText("\uB2F4\uB2F9 \uBD80\uC11C :");
		textPane_4.setBounds(12, 131, 97, 31);
		panel.add(textPane_4);
		
		textField_Department = new JTextField();
		textField_Department.setBounds(109, 130, 197, 32);
		panel.add(textField_Department);
		textField_Department.setColumns(10);
		
		JButton addProgramBtn = new JButton("\uC2E0\uCCAD");
		addProgramBtn.setBounds(113, 284, 115, 43);
		frame.getContentPane().add(addProgramBtn);
		
		addProgramBtn.addActionListener(new ActionListener() {//ÇÁ·Î±×·¥ Ãß°¡ Ã¢ ¶ç¿ì±â
			public void actionPerformed(ActionEvent e) {
				if(isBlank())
					return;
				programDao.insertProgram(textField_Title.getText(), textField_StartDate.getText(), textField_EndDate.getText(), textField_Department.getText(),textField_Location.getText());
				pm.setupdateProgressTable();
				frame.dispose();
			}
		});
		
	}
	
	private boolean isBlank() {
		if(textField_Title.getText().isEmpty() || textField_Title.getText() == null)
			return true;
		else if(textField_StartDate.getText().isEmpty() || textField_StartDate.getText() == null)
			return true;
		else if(textField_EndDate.getText().isEmpty() || textField_EndDate.getText() == null)
			return true;
		else if(textField_Department.getText().isEmpty() || textField_Department.getText() == null)
			return true;
		else if(textField_Location.getText().isEmpty() || textField_Location.getText() == null)
			return true;
		else 
			return false;
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
