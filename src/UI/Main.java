package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	private JFrame frame;
	private Main context = this;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uB3D9\uC0AC\uBB34\uC18C \uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
		frame.setBounds(100, 100, 476, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton employBtn = new JButton("\uC9C1\uC6D0\uAD00\uB9AC");
		employBtn.setFont(new Font("±¼¸²", Font.BOLD, 32));
		employBtn.setBounds(24, 22, 189, 139);
		frame.getContentPane().add(employBtn);
		
		JButton citizenBtn = new JButton("\uC8FC\uBBFC\uAD00\uB9AC");
		citizenBtn.setFont(new Font("±¼¸²", Font.BOLD, 32));
		citizenBtn.setBounds(243, 22, 189, 139);
		frame.getContentPane().add(citizenBtn);
		
		JButton movementBtn = new JButton("\uC804\uC785\uC2E0\uACE0");
		movementBtn.setFont(new Font("±¼¸²", Font.BOLD, 32));
		movementBtn.setBounds(24, 195, 189, 139);
		frame.getContentPane().add(movementBtn);
		
		JButton programBtn = new JButton("<HTML><body><center>\uD504\uB85C\uADF8\uB7A8<br>\r\n\uAD00\uB9AC</center></body></HTML>");
		programBtn.setFont(new Font("±¼¸²", Font.BOLD, 32));
		programBtn.setBounds(243, 195, 189, 139);
		frame.getContentPane().add(programBtn);
		
		programBtn.addActionListener(new ActionListener() {//ÇÁ·Î±×·¥ Ãß°¡ Ã¢ ¶ç¿ì±â
			public void actionPerformed(ActionEvent e) {
				ProgramManage pm = new ProgramManage(context);
				setVisible(false);
				pm.setVisible(true);
			}
		});
		
		citizenBtn.addActionListener(new ActionListener() {//ÇÁ·Î±×·¥ Ãß°¡ Ã¢ ¶ç¿ì±â
			public void actionPerformed(ActionEvent e) {
				CitizenManage cm = new CitizenManage(context);
				setVisible(false);
				cm.setVisible(true);
			}
		});
		
		movementBtn.addActionListener(new ActionListener() {//ÇÁ·Î±×·¥ Ãß°¡ Ã¢ ¶ç¿ì±â
			public void actionPerformed(ActionEvent e) {
				MoveManage mm = new MoveManage(context);
				setVisible(false);
				mm.setVisible(true);
			}
		});
		
		employBtn.addActionListener(new ActionListener() {//ÇÁ·Î±×·¥ Ãß°¡ Ã¢ ¶ç¿ì±â
			public void actionPerformed(ActionEvent e) {
				EmployeeManage em = new EmployeeManage(context);
				setVisible(false);
				em.setVisible(true);
			}
		});
	}
	
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
