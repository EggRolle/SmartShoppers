import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Creates reccomendations window, not worth commenting
 * @author Nophil Mehboob
 *
 */


public class RecWindow {

	private JFrame frmSalesWindow;
	ShoppersBackend backend;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public RecWindow(ShoppersBackend stored) {
		backend = stored;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSalesWindow = new JFrame();
		frmSalesWindow.setTitle("Rec Window");
		frmSalesWindow.setBounds(100, 100, 378, 567);
		//frmSalesWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSalesWindow.getContentPane().setLayout(null);
		frmSalesWindow.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 342, 506);
		frmSalesWindow.getContentPane().add(scrollPane);
		
		
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel,BoxLayout.Y_AXIS));
		
		for(Item item : backend.generateReccomendation())
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(new JLabel("Name: " + item.itemName));
			panel.add(new JLabel("Item count: " + item.itemCount));
			panel.add(new JLabel("Description: " + item.description));
			panel.add(new JLabel("Category: " + item.category));
			panel.add(new JLabel("     "));
			listPanel.add(panel);
		}
		scrollPane.setViewportView(listPanel);
		
		
	}
}
