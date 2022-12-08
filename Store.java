import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class to create stores
 * @author Nophil Mehboob
 *
 */

public class Store {
	String location; //Location
	List<Item> itemsList; //List of items
	String name;
	String hours;
	List<String> managers; //List of managers
	List<Sale> sales; //List of sales
	
	
	public Store(String namez, String locationz, String hoursz)
	{
		itemsList = new LinkedList<Item>();
		name = namez;
		hours = hoursz;
		location = locationz;
		sales = new LinkedList<Sale>();
		
	}
	

	/**
	 * Add item to store
	 * @param item item to add
	 */
	public void addItem(Item item)
	{
		for(Item items : itemsList)
		{
			if(items.itemName.equals(item.itemName)) //If item already in store, add to count
			{
				items.itemCount += item.itemCount;
				return;
			}
		}
		itemsList.add(item);
	}
}
