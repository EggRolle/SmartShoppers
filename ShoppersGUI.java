
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

/**
 * Main GUI class with part of the backend functinality
 * @author Nophil Mehboob
 *
 */

public class ShoppersGUI{
	
	JFrame login; //Login screen
	JTextField username; //user name
	JTextField password; //user passwsord
	ShoppersBackend backend; //Backend for entire application
	String usernameF;
	String passwordF;
	
	JFrame mainScreen;
	
	/**
	 * Default constructor
	 */
	public ShoppersGUI()
	{
		login = new JFrame();
		usernameF = "null";
		passwordF = "null";
		backend = new ShoppersBackend();
		//login.setLayout(new FlowLayout());
		openLogin();
	}
	
	/**
	 * Enables the login screen
	 */
	public void openLogin()
	{
		JPanel panel = new  JPanel();
		JPanel usernameP = new JPanel();
		JPanel passwordP = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		usernameP.add(new JLabel("Username: "));
		passwordP.add(new JLabel("Password: "));
		username = new JTextField("", 20);
		usernameP.add(username);
		password = new JTextField("",20);
		passwordP.add(password);
		username.setSize(10,20);
		panel.add(usernameP);
		panel.add(passwordP);
		login.add(panel);
		login.setMinimumSize(new Dimension(400,300));
		login.setVisible(true);
		
		JButton loginB = new JButton("Login");
		panel.add(loginB);
		loginB.addActionListener(new ActionListener()
				{
		public void actionPerformed(ActionEvent e)
		{
			
			boolean success = true;
			String type = "sas";
			try //Check if user exists
			{
				File f = new File(username.getText() + password.getText()+".txt");
				BufferedReader reader2 = new BufferedReader(new FileReader(new File(username.getText()+password.getText() + ".txt")));
				reader2.readLine();reader2.readLine();
				type = reader2.readLine();
				if(!f.exists())
				{
					success = false;
				}
				else
				{
					success = true;
				}
			
			}
			catch(Exception ed)
			{
				success = false;
			}
			
			
			if(success == false)
			{
				
				loginFailed();
			}
			else
			{ //Login
				if(type.equals("manager"))
				{
					loginSuccessManager();
				}
				else if (type.equals("admin"))
				{
					loginSuccessAdmin();
				}
				else
				{
					loginSuccess();
			
				}
			}
		}

		
				});
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Create Customer");
		rdbtnNewRadioButton.setSelected(true);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Create Manager");
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Create Admin");
		panel.add(rdbtnNewRadioButton_2);
		
		ButtonGroup b = new ButtonGroup();
		b.add(rdbtnNewRadioButton);
		b.add(rdbtnNewRadioButton_1);
		b.add(rdbtnNewRadioButton_2);
		
		JButton createButton = new JButton("Create Account");
		panel.add(createButton);
		createButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(rdbtnNewRadioButton.isSelected())
				{
					backend.createAccount(username.getText(), password.getText());
					loginSuccess();

				}
				else if(rdbtnNewRadioButton_1.isSelected())
				{
					backend.createManager(username.getText(), password.getText());
					loginSuccessManager();
				}

				else if(rdbtnNewRadioButton_2.isSelected())
				{
					backend.createAccountAdmin(username.getText(), password.getText());
					loginSuccessAdmin();
				}
		
	}
		});
		
		
		
		

	}
	
	/**
	 * open Admin screen
	 */
	public void loginSuccessAdmin()
	{
		usernameF = username.getText();
		passwordF = password.getText();
		backend.login(usernameF, passwordF);
		AdminWindow manager = new AdminWindow(backend);
		manager.listStores();
		manager.listItems();
	}
	
	/**
	 * Open Manager screen
	 */
	public void loginSuccessManager()
	{
		usernameF = username.getText();
		passwordF = password.getText();
		backend.login(usernameF, passwordF);
		ManagerWindow manager = new ManagerWindow(backend);
		manager.listStores();
		manager.listItems();
	}
	
	/**
	 * If invalid login
	 */
	public void loginFailed()
	{
		username.setText("Login Failed!");
		password.setText("Invalid username/password");
		
		
	}
	
	/**
	 * Open customer screen
	 */
	public void loginSuccess()
	{
		usernameF = username.getText();
		passwordF = password.getText();
		backend.login(usernameF, passwordF);
		login.setVisible(true);
		mainScreen = new JFrame();
		//mainScreen.setVisible(true);
		mainScreen.setMinimumSize(new Dimension(800,800));
		CustomerWindow custMenu = new CustomerWindow(backend);
		custMenu.listItems();
		custMenu.listStores();
		custMenu.listList();
		//accountSettings();
		
	}
	
	/**
	 * Open account settings screen
	 */
	public void accountSettings()
	{
		JFrame settingsPage = new JFrame("Account Settings");
		settingsPage.setMinimumSize(new Dimension(600,600));
		settingsPage.setVisible(true);
		JPanel panel = new  JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JPanel changeUP = new JPanel();
		JTextField newUser = new JTextField("New username", 20);
		JTextField newPass = new JTextField("New password", 20);
		JButton reset = new JButton("Reset");
		
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(newUser.getText() != null && newPass.getText() != null)
				{
					backend.changeUP(newUser.getText(),newPass.getText());
				}
	}
		});
		changeUP.add(newUser);
		changeUP.add(newPass);
		changeUP.add(reset);
		panel.add(changeUP);
		settingsPage.add(panel);
		
		
		JPanel setEmail = new JPanel();
		JTextField email = new JTextField("Security Email", 20);
		JButton setMailB = new JButton("Set Email");
		setMailB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				backend.setMail(email.getText());
		
	}
		});
		setEmail.add(email);
		setEmail.add(setMailB);
		panel.add(setEmail);
		
		
		JPanel delete = new JPanel();
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				backend.deleteAccount();
		
	}
		});
		delete.add(deleteAccount);
		panel.add(delete);
		
		JPanel personalInfo = new JPanel();
		JLabel userName = new JLabel(usernameF);
		JLabel password = new JLabel(passwordF);
		JLabel emailLabel = new JLabel(backend.savedEmail);
		personalInfo.add(userName);
		personalInfo.add(password);
		personalInfo.add(emailLabel);
		panel.add(personalInfo);


	}

}
