
/**
 * Item class, for creating items
 * @author Nophil Items
 *
 */

public class Item {
	//Relevant item info
	String itemName;
	int itemCount;
	String description;
	double price;
	String category;
	
	/**
	 * Item constructor
	 * @param itemNamed, item name
	 * @param itemCountd item count
	 * @param descriptiond item description
	 * @param priced price of item
	 * @param categoryd category of item
	 */
	public Item(String itemNamed, int itemCountd,String descriptiond, double priced, String categoryd)
	{
		itemName = itemNamed;
		itemCount = itemCountd;
		description= descriptiond;
		price = priced;
		category = categoryd;
	}

	/**
	 * Add count to item
	 */
	public void addCount()
	{
		itemCount++;
	}
	
	/**
	 * Remove count from item
	 */
	public void removeCount()
	{
		itemCount--;
	}
}
