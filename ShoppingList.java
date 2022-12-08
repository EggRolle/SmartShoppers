import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating shopping lists
 * @author Nophil Mehboob
 *
 */

public class ShoppingList {
	
	List<Item> list; //List of items
	
	/*
	 * Default constructor
	 */
	public ShoppingList()
	{
		list = new ArrayList<Item>();
	}

	/**
	 * Add item to list
	 * @param item, the item
	 */
	public void addItem(Item item)
	{
		list.add(item);
	}
	
	/**
	 * Get total cost of items
	 * @return total cost
	 */
	public double totalCost()
	{
		double total = 0;
		for(Item item : list)
		{
			total += item.price*item.itemCount;
		}
		return total;
	}
}
