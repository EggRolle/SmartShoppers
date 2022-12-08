import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import java.util.List;

/**
 * Testing class
 * @author Nophil Mehboob
 *
 */

public class ShoppersTest {

	
	
	public static void main(String args[])
	{
		try
		{
			ShoppersBackend backend = new ShoppersBackend();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("testclasstestpass.txt")));
	
			writer.write("testclass");
			writer.newLine();
			writer.write("testpass");
			writer.newLine();
			writer.write("customer");
			writer.newLine();
			writer.write("nomail");
			writer.newLine();
			writer.write("test store");
			writer.newLine();
			
			
			backend.createAccount("testclass", "testpass");
			backend.saveUser();
			backend.login("testclass", "testpass");
		
			
			backend.setMail("mailo");
			
			backend.loadUserData();
			
			backend.loadStores();
			
			backend.loadSales();
			
			backend.getStore("Big Store");
			backend.addToList("banana");
			backend.removeFromList("banana");
			
			backend.getTotal();
			backend.loadList();
			backend.nameSearch("");
			backend.catSearch("vegetable");
			backend.removeItemStore("banana");
			backend.addManager("testmantestmanpass");
			backend.generateReccomendation();
			List<Item> yeo = backend.generateReccomendation();
			List<Item> yeo2 = backend.bestOrder();
			
			
			
			//Test part 2
			backend = new ShoppersBackend();
			
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File("testclass2testpass2.txt")));
	
			writer2.write("testclass2");
			writer2.newLine();
			writer2.write("testpass2");
			writer2.newLine();
			writer2.write("manager");
			writer2.newLine();
			writer2.write("nomail");
			writer2.newLine();
			writer2.write("test store");
			writer2.newLine();
			
			backend.createAccountAdmin("testclass2", "testpass2");
			backend.saveUser();
			backend.login("testclass2", "testpass2");
			backend.changeUP("testclass2", "testpass2");
			
		
			
			backend.setMail("mailo");
			
			backend.loadUserData();
			
			backend.loadStores();
			
			backend.loadSales();
			
			backend.getStore("Big Store");
			backend.addToList("banana");
			backend.removeFromList("banana");
			backend.currentStore.sales.add(new Sale("banana", 10));
			backend.getTotal();
			backend.loadList();
			backend.addToList("banana");
			backend.list.addItem(new Item("banana", 7, "yummy", 4.99, "vegetable"));
			backend.nameSearch("");
			backend.catSearch("vegetable");
			backend.removeItemStore("banana");
			backend.saveUser();
			backend.changeStore(backend.storesList.get(0).name);
			backend.list.addItem(new Item("banana", 2, "yummy", 4.99, "vegetable"));
			//System.out.println(backend.list.list.get(0).itemName);
			backend.removeFromList("banana");

			backend.addManager("testclass2testpass2");
			backend.createManager("testclass2", "testpass2");

			backend.generateReccomendation();
			backend.removeFromList("banana");
			backend.currentStore.addItem(new Item("banana", 2, "yummy", 4.99, "vegetable"));
			backend.addToList("banana");
			backend.addToList("banana");
			
			
			
			System.out.println("Test passed");
			
			
		}
		catch(Exception e)
		{
			System.out.println("test failed");
		}
	}
}
