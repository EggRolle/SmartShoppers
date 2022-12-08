import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Loginny {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginny window = new Loginny();
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
	public Loginny() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new  JPanel();
		JPanel usernameP = new JPanel();
		JPanel passwordP = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		usernameP.add(new JLabel("Username: "));
		passwordP.add(new JLabel("Password: "));
		JTextField username = new JTextField("", 20);
		usernameP.add(username);
		JTextField password = new JTextField("",20);
		passwordP.add(password);
		username.setSize(10,20);
		panel.add(usernameP);
		panel.add(passwordP);
		
		JButton loginB = new JButton("Login");
		passwordP.add(loginB);
		loginB.addActionListener(new ActionListener()
				{
		public void actionPerformed(ActionEvent e)
		{
			
			boolean success = true;
			
			try
			{
				File f = new File(username.getText() + password.getText()+".txt");
				//System.out.println(username.getText() + password.getText()+".txt");
				if(!f.exists())
				{
					success = false;
				}
				else
				{
					success = true;
				}
				//loadUserData();
				//success = true;
			}
			catch(Exception ed)
			{
				success = false;
			}
			
			
			
			
		}

		
				});
		frame.getContentPane().add(panel);
		frame.setMinimumSize(new Dimension(400,300));
		frame.setVisible(true);
		
		JButton createButton = new JButton("Create Account");
		panel.add(createButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Create Customer");
		rdbtnNewRadioButton.setSelected(true);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Create Manager");
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Create Admin");
		panel.add(rdbtnNewRadioButton_2);
		
	}
	}


