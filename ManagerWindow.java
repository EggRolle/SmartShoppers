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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


/**
 * Creates main window for manager, not worth commenting
 * @author Nophil Mehboob
 *
 */
public class ManagerWindow{

	private JFrame frmCustomerInterface;
	private JTextField txtSearchQuery;
	JScrollPane scrollPane_1;
	ShoppersBackend backend;
	private JTextField txtItemName;
	JScrollPane scrollPane;
	private JTextField txtStoreName;
	private JTextField txtDescription;
	private JTextField txtPrice;
	private JTextField textField;
	private JTextField txtStoreName_1;
	private JTextField txtStoreHours;
	private JTextField txtStoreLocation;
	private JTextField txtNumber;
	private JTextField textField_1;
	private JTextField txtSalePercentint;
	private JTextField txtCategory;




	/**
	 * Create the application.
	 */
	public ManagerWindow(ShoppersBackend backendi) {
		backend = backendi;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCustomerInterface = new JFrame();
		frmCustomerInterface.setTitle("Manager Interface");
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
		
		JLabel lblNewLabel_2 = new JLabel("Current Store: " );
		
		try {
			lblNewLabel_2.setText("Current Store: " + backend.currentStore.name);
			panel.add(lblNewLabel_2);
		}
		catch(Exception e)
		{
			
		}
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
		
		JLabel lblNewLabel_1 = new JLabel("Managed Stores List:");
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
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList searched = backend.nameSearch(txtSearchQuery.getText());
				listItems(searched);
				
			}
		});
		btnNewButton_1.setBounds(354, 466, 95, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Cat. Search");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList listy = backend.catSearch(txtSearchQuery.getText());
				listItems(listy);
			}
		});
		btnNewButton_1_1.setBounds(462, 467, 89, 23);
		panel_1.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Add Item to Store:");
		lblNewLabel_4.setBounds(561, 28, 161, 14);
		panel_1.add(lblNewLabel_4);
		
		txtItemName = new JTextField();
		txtItemName.setText("Item Name");
		txtItemName.setBounds(561, 50, 86, 20);
		panel_1.add(txtItemName);
		txtItemName.setColumns(10);
		
		JButton btnNewButton_1_2 = new JButton("Remove");
		
		
		
		
		
		JButton btnNewButton_1_2_1 = new JButton("Add to Store");
		btnNewButton_1_2_1.setBounds(675, 80, 121, 23);
		
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
		btnNewButton_1_3.setBounds(115, 467, 95, 23);
		panel_1.add(btnNewButton_1_3);
		
		txtDescription = new JTextField();
		txtDescription.setText("Description");
		txtDescription.setColumns(10);
		txtDescription.setBounds(561, 81, 104, 20);
		panel_1.add(txtDescription);
		
		txtPrice = new JTextField();
		txtPrice.setText("Price");
		txtPrice.setColumns(10);
		txtPrice.setBounds(561, 112, 86, 20);
		panel_1.add(txtPrice);
		
		textField = new JTextField();
		textField.setText("Item Name");
		textField.setColumns(10);
		textField.setBounds(561, 212, 86, 20);
		panel_1.add(textField);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = backend.removeItemStore(textField.getText());
				if(!check)
				{
					textField.setText("Failed");
				}
				else
				{
					listItems();
				}
			}
		});
		btnNewButton_1_2.setBounds(675, 211, 86, 23);
		panel_1.add(btnNewButton_1_2);
		
		JLabel lblNewLabel_4_1 = new JLabel("Remove Item from Store");
		lblNewLabel_4_1.setBounds(561, 186, 161, 14);
		panel_1.add(lblNewLabel_4_1);
		
		txtStoreName_1 = new JTextField();
		txtStoreName_1.setText("Store Name");
		txtStoreName_1.setColumns(10);
		txtStoreName_1.setBounds(561, 274, 86, 20);
		panel_1.add(txtStoreName_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Change Current Store Details:");
		lblNewLabel_4_1_1.setBounds(561, 249, 161, 14);
		panel_1.add(lblNewLabel_4_1_1);
		
		txtStoreHours = new JTextField();
		txtStoreHours.setText("Store Hours");
		txtStoreHours.setColumns(10);
		txtStoreHours.setBounds(561, 306, 86, 20);
		panel_1.add(txtStoreHours);
		
		txtStoreLocation = new JTextField();
		txtStoreLocation.setText("Store Location");
		txtStoreLocation.setColumns(10);
		txtStoreLocation.setBounds(561, 337, 86, 20);
		panel_1.add(txtStoreLocation);
		
		JButton btnNewButton_1_2_2 = new JButton("Change");
		btnNewButton_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if(backend.currentStore.name.equals(txtStoreName.getText()))
				//{
				//	return;
				//}
				
				String old = backend.currentStore.name;
				try
				{
					for(Store store : backend.storesList)
					{
						if(store.name.equals(backend.currentStore.name))
						{
							store.name = txtStoreName_1.getText();
							
						}
					}
					backend.currentStore.name = txtStoreName_1.getText();
					backend.managedStores.add(txtStoreName_1.getText());
					
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(new File(old + "items.txt")));
					writer.write("new");
					writer.newLine();
					writer.write(txtStoreName_1.getText());
					writer.newLine();
					writer.close();
					listStores();
				}
				catch(Exception exce)
				{
					
				}
			}
		});
		
		btnNewButton_1_2_2.setBounds(675, 273, 86, 23);
		panel_1.add(btnNewButton_1_2_2);
		
		JButton btnNewButton_1_2_2_1 = new JButton("Change");
		btnNewButton_1_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					for(Store store : backend.storesList)
					{
						if(store.name.equals(backend.currentStore.name))
						{
							
							store.hours = txtStoreHours.getText();
							
						}
					}
					backend.currentStore.hours = txtStoreHours.getText();
					listStores();
				}
				catch(Exception exce)
				{
					
				}
			}
		});
		btnNewButton_1_2_2_1.setBounds(675, 305, 86, 23);
		panel_1.add(btnNewButton_1_2_2_1);
		
		JButton btnNewButton_1_2_2_1_1 = new JButton("Change");
		
		btnNewButton_1_2_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					for(Store store : backend.storesList)
					{
						if(store.name.equals(backend.currentStore.name))
						{
							
							store.location = txtStoreLocation.getText();
							
						}
					}
					backend.currentStore.location = txtStoreLocation.getText();
					listStores();
				}
				catch(Exception exce)
				{
					
				}
			}
		});
		
		
		btnNewButton_1_2_2_1_1.setBounds(675, 336, 86, 23);
		panel_1.add(btnNewButton_1_2_2_1_1);
		
		
		txtCategory = new JTextField();
		txtCategory.setText("Category");
		txtCategory.setColumns(10);
		txtCategory.setBounds(657, 143, 86, 20);
		panel_1.add(txtCategory);
		
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					backend.currentStore.addItem(new Item(txtItemName.getText(),Integer.parseInt(txtNumber.getText()), txtDescription.getText(), Double.parseDouble(txtPrice.getText()), txtCategory.getText()));
				}
				catch(Exception ed)
				{
					
				}
				listItems();
			}
		});
		
		
		txtNumber = new JTextField();
		txtNumber.setText("Number");
		txtNumber.setColumns(10);
		txtNumber.setBounds(561, 143, 86, 20);
		panel_1.add(txtNumber);
		
		
		JButton btnNewButton_3 = new JButton("Sales List");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesWindow sales = new SalesWindow(backend.currentStore);
			}
		});
		
		btnNewButton_3.setBounds(559, 495, 89, 23);
		panel_1.add(btnNewButton_3);
		
		textField_1 = new JTextField();
		textField_1.setText("Item Name");
		textField_1.setColumns(10);
		textField_1.setBounds(561, 400, 86, 20);
		panel_1.add(textField_1);
		
		txtSalePercentint = new JTextField();
		txtSalePercentint.setText("Sale Cut(int)");
		txtSalePercentint.setColumns(10);
		txtSalePercentint.setBounds(561, 431, 86, 20);
		panel_1.add(txtSalePercentint);
		
		JButton btnNewButton_1_2_2_1_1_1 = new JButton("Add Sale");
		btnNewButton_1_2_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					backend.currentStore.sales.add(new Sale(textField_1.getText(), Integer.parseInt(txtSalePercentint.getText())));
				}
				catch(Exception ed)
				{
					
				}
				}
		});
		btnNewButton_1_2_2_1_1_1.setBounds(657, 412, 86, 23);
		panel_1.add(btnNewButton_1_2_2_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Add Sale");
		lblNewLabel_4_1_1_1.setBounds(561, 375, 161, 14);
		panel_1.add(lblNewLabel_4_1_1_1);
		
		
	}
	
	
	public void listItems()
	{
		JPanel itemsPanel = new JPanel();
		itemsPanel.setLayout(new BoxLayout(itemsPanel,BoxLayout.Y_AXIS));
		
		if(backend.currentStore == null)
		{
			return;
		}

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
			if(backend.managedStores.contains(store.name))
			{
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
				panel.add(new JLabel("Name: " + store.name));
				panel.add(new JLabel("Hours: " + store.hours));
				panel.add(new JLabel("Location: " + store.location));
				panel.add(new JLabel("     "));
				storesPanel.add(panel);
			}
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
