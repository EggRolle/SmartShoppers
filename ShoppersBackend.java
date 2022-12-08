import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Main backend class for any type of user for the SmartShooppers system
 * @author Nophil Mehboob
 *@date March 20
 */

public class ShoppersBackend {

	
	String username; //Username
	String password; //User password
	String savedEmail;
	String currentStoreName;
	
	Store currentStore; //Currently used store
	ShoppingList list;  //Shoping list for current store
	List<Store> storesList;  //List of all stores
	String userType;		//Type of user, customer admin or manager
	
	List<String> managedStores;  //List of managed stores for managers
	List<Item> recommendations;
	
	
	public ShoppersBackend()
	{
		storesList = new ArrayList<Store>();
		managedStores = new ArrayList<String>();
		list = new ShoppingList();
		storesList.add(currentStore); 
		userType = "cust"; //Default type
		File f = new File("stores.txt");
		if(!f.exists())  //If some error occurs and the stores data is lost
		{
			setup();
		}
		else if(f.length() == 0)
		{
			setup();
		}
		loadStores();
		
		if(currentStore == null)
		{
			currentStore = storesList.get(0);
		}
		recommendations = new ArrayList<Item>();
		
		
	}
	
	/**
	 * Setup, incase the txt files containing informatin on stores is lost
	 */
	public void setup()
	{
		try
		{
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("stores.txt")));
			writer.write("Big Store");
			writer.newLine();
			writer.write("B188 Big Street");
			writer.newLine();
			writer.write("Big Hours");
			writer.newLine();
			writer.close();
		}
		catch(Exception e)
		{
			
		}
		
		

	}
	
	/**
	 * Default customer login
	 * @param user username
	 * @param pass password
	 */
	public void login(String user,String pass)
	{
		username = user;
		password = pass;
		
		
		loadUserData();
	}
	
	/**
	 * Loads saved data about user
	 */
	public void loadUserData()
	{
		loadStores();
		
		File user = new File(username+password+".txt");
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(user));
			String line;
			//Load data from file
			username = reader.readLine();
			password = reader.readLine();
			userType = reader.readLine();
			savedEmail = reader.readLine();
			String current = reader.readLine();
			
			
			if(userType.contains("cust") ) //Customer load
			{
				if(getStore(current) != null)
				{
					currentStore = getStore(current);
					list = new ShoppingList();
				}
			}
			
			
			if(userType.equals("manager") || userType.equals("admin")) //manager and admin load
			{
				if(getStore(current) != null)
				{
					currentStore = getStore(current);
					list = new ShoppingList();
				}
				else
				{
					currentStore  = storesList.get(0);
					list = new ShoppingList();
				}
				String managed;
				while((managed = reader.readLine()) != null)
				{
					managedStores.add(managed);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		//Load other things too
		loadList();
		loadSales();
		
		
	}
	
	/**
	 * Load info on all stores
	 */
	public void loadStores()
	{
		
		storesList = new ArrayList<Store>();
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(new File("stores.txt")));
			String nextStoreN;
			while((nextStoreN = reader.readLine()) != null) //Read through stores save file
			{
				
				Store nextStore = new Store(nextStoreN, reader.readLine(), reader.readLine());
				File thing = new File(nextStore.name + "items.txt");
				if(!thing.exists())
				{
					thing.createNewFile();
				}
				BufferedReader reader2 = new BufferedReader(new FileReader(new File(nextStore.name + "items.txt")));
				String line;
				while((line = reader2.readLine()) != null) //Load store's items
				{
					Item nextItem = new Item(line, Integer.parseInt(reader2.readLine()), reader2.readLine(), Double.parseDouble(reader2.readLine()), reader2.readLine());
					nextStore.addItem(nextItem);
					
				}
				

				storesList.add(nextStore);
			}
			
			
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	/**
	 * Load stores sales
	 */
	public void loadSales()
	{
		try {
			BufferedReader reader2 = new BufferedReader(new FileReader(new File(currentStore.name + "sales.txt")));
			String line;
			while((line = reader2.readLine()) != null)
			{
				currentStore.sales.add(new Sale(line, Integer.parseInt(reader2.readLine())));
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	/**
	 * Create customer account
	 * @param usernamed
	 * @param passwordd
	 */
	public void createAccount(String usernamed, String passwordd)
	{
		username = usernamed;
		password = passwordd;
		userType = "customer";
	}
	
	/**
	 * Create admin account
	 * @param usernamed
	 * @param passwordd
	 */
	public void createAccountAdmin(String usernamed, String passwordd)
	{
		username = usernamed;
		password = passwordd;
		userType = "admin";
	}
	
	/**
	 * Change username and password
	 * @param user
	 * @param pass
	 */
	public void changeUP(String user, String pass)
	{
		username = user;
		password = pass;
	}
	
	/*
	 * Sets new email address
	 */
	public void setMail(String email)
	{
		savedEmail = email;
	}
	
	/**
	 * Comepletely deletes account
	 */
	public void deleteAccount()
	{
		
	}
	
	/**
	 * Saves user information to txt file
	 */
	public void saveUser()
	{
		try
		{
			File user = new File(username+password+".txt");
			
			user.createNewFile();
			FileWriter writer1 = new FileWriter(user);
			BufferedWriter writer = new BufferedWriter(writer1);
			
			writer.write(username);
			writer.newLine();
			writer.write(password);
			writer.newLine();
			
			writer.write(userType);
			writer.newLine();
			if(savedEmail == null)
			{
				writer.write("nomail");
			}
			else
			{
				writer.write(savedEmail);

			}
			writer.newLine();
			writer.write(currentStore.name);
			writer.newLine();
			
			if(userType.equals("manager")) //Save managed stores if applicable
			{
				for(String managed : managedStores)
				{
					writer.write(managed);
					writer.newLine();
				}
			}
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("save fail");
		}
		//Save other info
		
		saveStores();
		saveList();
	}
	
	/**
	 * Save store data for all stores
	 */
	public void saveStores()
	{
		try
		{
		for(Store stored: storesList)
		{
			if(stored.name.equals(currentStore.name))
			{
				stored = currentStore;
			}
		}
		}
		catch(Exception e)
		{
		}
		
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("stores.txt")));
			for(Store current : storesList) //For all stores
			{

				
				writer.write(current.name); //Save data line by line
				writer.newLine();
				writer.write(current.location);
				writer.newLine();
				writer.write(current.hours);
				writer.newLine();
			}
			
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File(currentStore.name + "items.txt")));
			writer.close();
				for(Item item: currentStore.itemsList) //Save data for all items
				{
					writer2.write(item.itemName);
					writer2.newLine();
					writer2.write(String.valueOf(item.itemCount));
					writer2.newLine();
					writer2.write(item.description);
					writer2.newLine();
					writer2.write(String.valueOf(item.price));
					writer2.newLine();
					writer2.write(item.category);
					writer2.newLine();

				}
				writer2.close();
				
				BufferedWriter writer3 = new BufferedWriter(new FileWriter(new File(currentStore.name + "sales.txt")));
				for(Sale sale : currentStore.sales) // Save sales data
				{
					writer3.write(sale.item);
					writer3.newLine();
					writer3.write(String.valueOf(sale.salePercent));
					writer3.newLine();
				}
				writer3.close();
			}
			
		
		catch(Exception e)
		{
		}
		
		
	}
	
	/**
	 * Get store given name
	 * @param name name of store
	 * @return the store object
	 */
	public Store getStore(String name)
	{
		for(Store stored : storesList)
		{
			if (stored.name.equals(name))
			{
				return stored;
			}
		}
		return null;
	}
	
	/**
	 * Adds item to shopping list
	 * @param name name of item
	 * @return true or false successful
	 */
	public boolean addToList(String name)
	{
		for(Item item : currentStore.itemsList)
		{
			if(item.itemName.equals(name)) //Check if store has item
			{
				for(Item item2 : list.list)
				{
					if(item2.itemName.equals(name)) //If already in list, increase count
					{
						item2.addCount();
						return true;
					}
				}
				Item copy = new Item(item.itemName, 1, item.description, item.price, item.category); //If not in list, new item
				list.addItem(copy);
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove item from list
	 * @param name the name of the item
	 * @return true or false if successful
	 */
	public boolean removeFromList(String name)
	{
		
				for(Item item2 : list.list)
				{
					if(item2.itemName.equals(name))
					{
						item2.removeCount();
						if(item2.itemCount == 0)
						{
							list.list.remove(list.list.indexOf(item2));
						}
						return true;
					}
				}
				
				return false;
				
	}
	
	/**
	 * Get total cost of all items in list
	 * @return total cost
	 */
	public double getTotal()
	{
		return list.totalCost();
	}
	
	/**
	 * Save shopping list to txt file
	 */
	public void saveList()
	{
		try
		{
			//System.out.println("wo");
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File(username + "list.txt")));
			for(Item item: list.list)
			{
				writer2.write(item.itemName);
				writer2.newLine();
				writer2.write(String.valueOf(item.itemCount));
				writer2.newLine();
				writer2.write(item.description);
				writer2.newLine();
				writer2.write(String.valueOf(item.price));
				writer2.newLine();
				writer2.write(item.category);
				writer2.newLine();
				
			}
			writer2.close();
		}
		catch(Exception e)
		{
		}
	}
	
	/**
	 * Load shopping list from txt file
	 */
	public void loadList()
	{
		try
		{
			//System.out.println("wo");
			BufferedReader reader2 = new BufferedReader(new FileReader(new File(username+"list.txt")));
			String line;
			while((line = reader2.readLine()) != null)
			{
				Item nextItem = new Item(line, Integer.parseInt(reader2.readLine()), reader2.readLine(), Double.parseDouble(reader2.readLine()), reader2.readLine());
				list.list.add(nextItem);
			}
		}
		catch(Exception e)
		{
			System.out.println("nice");
		}
	}

	public boolean changeStore(String name)
	{
		for(Store store : storesList)
		{
			if(store.name.equals(name))
			{
				currentStore = store;
				list = new ShoppingList();
				loadSales();
				return true;
				
			}
			
		}
		return false;
	}
	
	public ArrayList nameSearch(String name)
	{
		ArrayList<Item> listy = new ArrayList<Item>();
		for(Item item: currentStore.itemsList)
		{
			if(item.itemName.contains(name))
			{
				listy.add(item);
			}
		}
		return listy;
	}
	
	public ArrayList catSearch(String cat)
	{
		ArrayList<Item> listy = new ArrayList<Item>();
		for(Item item : currentStore.itemsList)
		{
			if(item.category.contains(cat))
			{
				listy.add(item);
			}
		}
		return listy;

	}
	public boolean removeItemStore(String name)
	{
		for(Item item: currentStore.itemsList)
		{
			if(item.itemName.equals(name))
			{
				currentStore.itemsList.remove(item);
				return true;
			}
		}
		return false;
	}
	
	public void createManager(String user, String pass)
	{
		username = user;
		password = pass;
		userType = "manager";
		
	}

	public void addManager(String name)
	{
		try
		{
			File f = new File(name+".txt");
			if(!f.exists())
			{
				return;
			}
			
			FileWriter fw = new FileWriter(name + ".txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			
			out.println(currentStore.name);
			out.close();
		}
		catch(Exception e)
		{
		}
	}
	
	public List<Item> generateReccomendation()
	{
		List<Item> recs = new ArrayList<Item>();
		
		if(list.list.size() == 0)
		{
			return recs;
		}
		
		for(Item item : currentStore.itemsList)
		{
			for(Item item2 : list.list)
			{
				if(item.category.equals(item2.category) && recs.contains(item) == false)
				{
					recs.add(item);
				}
			
			}
		}
		
		return recs;
	}
	
	public ArrayList<Item> bestOrder()
	{
		ArrayList<Item> order = new ArrayList<Item>();
		ArrayList<String> categories = new ArrayList<String>();
		for(Item item : list.list)
		{
			if(!categories.contains(item.category))
			{
				categories.add(item.category);
			}
		}
		
		for(String cat : categories)
		{
			for(Item item : list.list)
			{
				if(item.category.equals(cat))
				{
					order.add(item);
				}
			}
		}
		return order;
	}
	
}
