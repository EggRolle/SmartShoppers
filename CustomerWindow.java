import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;


/**
 * Creates main window for customer, not worth commenting
 * @author Nophil Mehboob
 *
 */

public class CustomerWindow{

	private JFrame frmCustomerInterface;
	private JTextField txtSearchQuery;
	JScrollPane scrollPane_1;
	ShoppersBackend backend;
	private JTextField txtItemName;
	JScrollPane scrollPane;
	private JTextField txtStoreName;
	JScrollPane scrollPane_1_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public CustomerWindow(ShoppersBackend backendi) {
		backend = backendi;
		//backendi.loadUserData();
		//backendi.loadStores();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCustomerInterface = new JFrame();
		frmCustomerInterface.setTitle("Customer Interface");
		frmCustomerInterface.setBounds(100, 100, 865, 613);
		frmCustomerInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCustomerInterface.getContentPane().setLayout(new BorderLayout(0, 0));
		frmCustomerInterface.setVisible(true);
		
		JPanel panel = new JPanel();
		frmCustomerInterface.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Username: " + backend.username);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel("                    ");
		panel.add(lblNewLabel_7);
		
		
		JLabel lblNewLabel_2 = new JLabel("Current Store: " + backend.currentStore.name);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Account settings");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				accountSettings();
		
	}
		});
		
		JLabel lblNewLabel_5 = new JLabel("                                                                                                         ");
		panel.add(lblNewLabel_5);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backend.saveUser();
				backend.saveList();
				listStores();
			}
		});
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		frmCustomerInterface.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Stores List:");
		lblNewLabel_1.setBounds(10, 11, 137, 31);
		panel_1.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 40, 200, 416);
		panel_1.add(scrollPane);
		
		JLabel lblNewLabel_3 = new JLabel("Current Store Item List:");
		lblNewLabel_3.setBounds(239, 19, 145, 14);
		panel_1.add(lblNewLabel_3);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(249, 40, 287, 416);
		panel_1.add(scrollPane_1);
		
		txtSearchQuery = new JTextField();
		txtSearchQuery.setText("Search Query");
		txtSearchQuery.setBounds(249, 467, 95, 20);
		panel_1.add(txtSearchQuery);
		txtSearchQuery.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Name Search");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList searched = backend.nameSearch(txtSearchQuery.getText());
				listItems(searched);
				
			}
		});
		btnNewButton_1.setBounds(354, 466, 95, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cat. Search");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Item> listy = backend.catSearch(txtSearchQuery.getText());
				listItems(listy);
			}
		});
		btnNewButton_1_1.setBounds(462, 467, 89, 23);
		panel_1.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Shopping List:");
		lblNewLabel_4.setBounds(576, 19, 89, 14);
		panel_1.add(lblNewLabel_4);
		
		scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1_1.setBounds(552, 40, 287, 416);
		panel_1.add(scrollPane_1_1);
		
		txtItemName = new JTextField();
		txtItemName.setText("Item Name");
		txtItemName.setBounds(562, 467, 86, 20);
		panel_1.add(txtItemName);
		txtItemName.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Total cost: " + backend.getTotal());
		lblNewLabel_6.setBounds(718, 499, 121, 14);
		panel_1.add(lblNewLabel_6);
		
		JButton btnNewButton_1_2 = new JButton("Remove");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = backend.removeFromList(txtItemName.getText());
				if(!check)
				{
					txtItemName.setText("Failed");
				}
				else
				{
					lblNewLabel_6.setText("Total cost: " + backend.getTotal());
				}
				listList();
			}
		});
		btnNewButton_1_2.setBounds(658, 464, 86, 23);
		panel_1.add(btnNewButton_1_2);
		
		
		
		
		JButton btnNewButton_1_2_1 = new JButton("Add");
		btnNewButton_1_2_1.setBounds(753, 466, 86, 23);
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = backend.addToList(txtItemName.getText());
				if(!check)
				{
					txtItemName.setText("Failed");
				}
				else
				{
					
					lblNewLabel_6.setText("Total cost: " + backend.getTotal());
				}
				listList();
			}
		});
		panel_1.add(btnNewButton_1_2_1);
		
		txtStoreName = new JTextField();
		txtStoreName.setText("Store Name");
		txtStoreName.setColumns(10);
		txtStoreName.setBounds(10, 467, 95, 20);
		panel_1.add(txtStoreName);
		
		JButton btnNewButton_1_3 = new JButton("Select Store");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = backend.changeStore(txtStoreName.getText());
				if(!check)
				{
					txtStoreName.setText("Failed");
				}
				else
				{
					lblNewLabel_2.setText("Current Store: " + backend.currentStore.name);
					listList();
					listItems();
				}
			}
		});
		btnNewButton_1_3.setBounds(115, 467, 95, 46);
		panel_1.add(btnNewButton_1_3);
		
		JButton btnNewButton_3 = new JButton("Sales List");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesWindow sales = new SalesWindow(backend.currentStore);
			}
		});
		
		btnNewButton_3.setBounds(559, 495, 149, 23);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Reccommendations");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecWindow rec = new RecWindow(backend);
			}
		});
		btnNewButton_3_1.setBounds(354, 495, 182, 23);
		panel_1.add(btnNewButton_3_1);
		
		JButton btnNewButton_1_4 = new JButton("Best Order");
		btnNewButton_1_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_1_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				listBest(backend.bestOrder());
		
	}
		});
		btnNewButton_1_4.setBounds(249, 495, 95, 23);
		panel_1.add(btnNewButton_1_4);
		
		
	}
	
	public void listItems()
	{
		JPanel itemsPanel = new JPanel();
		itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));

		for(Item item : backend.currentStore.itemsList)
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(new JLabel("Name: " + item.itemName));
			panel.add(new JLabel("Item count: " + item.itemCount));
			panel.add(new JLabel("Description: " + item.description));
			panel.add(new JLabel("Category: " + item.category));
			panel.add(new JLabel("     "));
			itemsPanel.add(panel);
		}
		scrollPane_1.setViewportView(itemsPanel);
		scrollPane_1.repaint();
		
	}
	
	public void listItems(ArrayList<Item> list)
	{
		JPanel itemsPanel = new JPanel();
		itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));

		for(Item item : list)
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(new JLabel("Name: " + item.itemName));
			panel.add(new JLabel("Item count: " + item.itemCount));
			panel.add(new JLabel("Description: " + item.description));
			panel.add(new JLabel("Category: " + item.category));

			panel.add(new JLabel("     "));
			itemsPanel.add(panel);
		}
		scrollPane_1.setViewportView(itemsPanel);
		scrollPane_1.repaint();
		
	}
	
	public void listBest(ArrayList<Item> list)
	{
		JPanel itemsPanel = new JPanel();
		itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));
		JPanel paneld = new JPanel();
		paneld.setLayout(new BoxLayout(paneld,BoxLayout.Y_AXIS));
		paneld.add(new JLabel("BEST ORDER FOR SHOPPING LIST"));
		itemsPanel.add(paneld);

		for(Item item : list)
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(new JLabel("Name: " + item.itemName));
			panel.add(new JLabel("Item count: " + item.itemCount));
			panel.add(new JLabel("Description: " + item.description));
			panel.add(new JLabel("Category: " + item.category));

			panel.add(new JLabel("     "));
			itemsPanel.add(panel);
		}
		scrollPane_1.setViewportView(itemsPanel);
		scrollPane_1.repaint();
		
	}
	
	public void listStores()
	{
		JPanel storesPanel = new JPanel();
		storesPanel.setLayout(new BoxLayout(storesPanel,BoxLayout.Y_AXIS));
		if(backend.storesList == null)
		{
			return;
		}
		for(Store store : backend.storesList)
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(new JLabel("Name: " + store.name));
			panel.add(new JLabel("Hours: " + store.hours));
			panel.add(new JLabel("Location: " + store.location));
			panel.add(new JLabel("     "));
			storesPanel.add(panel);
		}
		scrollPane.setViewportView(storesPanel);
	}
	
	public void listList()
	{
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel,BoxLayout.Y_AXIS));
		if(backend.list == null)
		{
			return;
		}
		for(Item item : backend.list.list)
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(new JLabel("Name: " + item.itemName));
			panel.add(new JLabel("Count: " + item.itemCount));
			panel.add(new JLabel("Description: " + item.description));
			panel.add(new JLabel("Cost: " + item.price));
			panel.add(new JLabel("     "));
			listPanel.add(panel);
		}
		scrollPane_1_1.setViewportView(listPanel);
		//lblNewLabel_6.update();
	}
	
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
				backend.changeUP(newUser.getText(),newPass.getText());
		
	}
		});
		changeUP.add(newUser);
		changeUP.add(newPass);
		changeUP.add(reset);
		panel.add(changeUP);
		settingsPage.getContentPane().add(panel);
		
		
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
		JLabel info = new JLabel("Personal Info: ");
		JLabel userName = new JLabel("Name: " + backend.username);
		JLabel password = new JLabel("Password: " + backend.password);
		JLabel emailLabel = new JLabel("Email: " + backend.savedEmail);
		personalInfo.add(info);
		personalInfo.add(userName);
		personalInfo.add(password);
		personalInfo.add(emailLabel);
		panel.add(personalInfo);


	}
}
