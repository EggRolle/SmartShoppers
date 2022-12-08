import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Creates sales window, not worth commenting
 * @author Nophil Mehboob
 *
 */

public class SalesWindow {

	private JFrame frmSalesWindow;
	Store store;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public SalesWindow(Store stored) {
		store = stored;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSalesWindow = new JFrame();
		frmSalesWindow.setTitle("Sales Window");
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
		if(store.sales.size() == 0)
		{
			return;
		}
		for(Sale sale : store.sales)
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(new JLabel("Name: " + sale.item));
			panel.add(new JLabel("Price cut: " + sale.salePercent));
			panel.add(new JLabel("     "));
			listPanel.add(panel);
		}
		scrollPane.setViewportView(listPanel);
		
		
	}
}
