package inventoryManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class InventoryAdministration extends Thread{
	
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryManager inventoryManager = new InventoryManager();

    public static void main(String[] args) throws IOException  {
    	
    		InventoryAdministration admin = new InventoryAdministration();
			admin.start();
    			 
    }

    public void start(){
    	// Create a Client and connect to the remote Server on the specified IP Address and Port
		InventoryAdministration client = new InventoryAdministration();
		try {
			client.connectToServer("127.0.0.1", 6666);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			client.updateStoreFront();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			client.cleanup();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void connectToServer(String ip, int port) throws UnknownHostException, IOException
    {
    	
    	// Connect to the Remote Server on the specified IP Address and port
    	clientSocket = new Socket(ip, port);
    			
    	// Create some input and output network buffers to communicate back and forth with the Server
    	out = new PrintWriter(clientSocket.getOutputStream(), true);
    	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	System.out.println("Connected to server successfully!");   	
    	
    }
    
    public void updateStoreFront() throws IOException
    {
    	System.out.println("Welcome to the Inventory Administration App");
    	    	
    	while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add a generic item");
            System.out.println("2. Add a weapon");
            System.out.println("3. Add armor");
            System.out.println("4. Add a health item");
            System.out.println("5. Remove an item");
            System.out.println("6. Update item quantity by name");
            System.out.println("7. Display items listed in inventory");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the extra newline

            switch (choice) {
                case 1 : 
                	addGenericItem();
                	break;
                case 2 : 
                	addWeapon();
                	break;
                case 3 : 
                	addArmor();
                	break;
                case 4 : 
                	addHealthItem();
                	break;
                case 5 :
                	out.println("Inventory");
                	String response;
                	while ((response = in.readLine()) != null && !response.equals("End of Inventory.")) {
                        System.out.println(response);
                	}
                	System.out.println("End of inventory\n");
                	removeItem();
                	break;
                case 6 :
                	out.println("Inventory");
                	String response1;
                	while ((response1 = in.readLine()) != null && !response1.equals("End of Inventory.")) {
                        System.out.println(response1);
                	}
                	
                	System.out.println("End of inventory\n");
                	updateItemQuantity();
                	break;
                case 7 : 
                	out.println("Inventory");
                	String response2;
                	while ((response2 = in.readLine()) != null && !response2.equals("End of Inventory.")) {
                	    System.out.println(response2);
                	}
                	System.out.println("End of inventory\n");
                    break;
                case 8 : 
                	System.out.println("Exiting Inventory Admin");
                	out.println("Exit");
                	return;
                default : System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void addGenericItem() throws IOException {
        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        System.out.println("Enter item price:");
        double price = scanner.nextDouble();

        System.out.println("Enter item quantity:");
        int quantity = scanner.nextInt();
        
        String command = "Generic" + ":" + name + ":" + description + ":" + price + ":" + quantity;
        
        out.println(command);
        
        String response = in.readLine();
        System.out.println(response);
    }

    private void addWeapon() throws IOException {
    	System.out.println("Enter item name:");
        String name = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        System.out.println("Enter item price:");
        double price = scanner.nextDouble();

        System.out.println("Enter item quantity:");
        int quantity = scanner.nextInt();
        
        scanner.nextLine();
        
        System.out.println("Enter item type:");
        String type = scanner.nextLine();
        
        System.out.println("Enter item rarity:");
        String rarity = scanner.nextLine();
        
        System.out.println("Enter damage amount:");
        int damage = scanner.nextInt();

        String command =  "Weapon" + ":" + name + ":" + description + ":" + price + ":" + quantity + ":" + type
        		 + ":" + rarity + ":" + damage;
        
        out.println(command);
        
        String response = in.readLine();
        System.out.println(response);
    }
    
    private void addArmor() throws IOException {
    	System.out.println("Enter item name:");
        String name = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        System.out.println("Enter item price:");
        double price = scanner.nextDouble();

        System.out.println("Enter item quantity:");
        int quantity = scanner.nextInt();
        
        scanner.nextLine();
        
        System.out.println("Enter item type:");
        String type = scanner.nextLine();
        
        System.out.println("Enter item rarity:");
        String rarity = scanner.nextLine();
        
        System.out.println("Enter defense amount:");
        int defense = scanner.nextInt();

        String command = "Armor" + ":" + name + ":" + description + ":" + price + ":" + quantity + ":" + type
       		 + ":" + rarity + ":" + defense;
       
        out.println(command);
       
        String response = in.readLine();
        System.out.println(response);
    }
    
    private void addHealthItem() throws IOException{
    	System.out.println("Enter item name:");
        String name = scanner.nextLine();

        System.out.println("Enter item description:");
        String description = scanner.nextLine();

        System.out.println("Enter item price:");
        double price = scanner.nextDouble();

        System.out.println("Enter item quantity:");
        int quantity = scanner.nextInt();
        
        scanner.nextLine();
        
        System.out.println("Enter Health Increase amount");
        int health = scanner.nextInt();

        String command = "Health" + ":" + name + ":" + description + ":" + price + ":" + quantity + ":" + health;
          
           out.println(command);
          
           String response = in.readLine();
           System.out.println(response);
    }

    private void removeItem() throws IOException{
        System.out.println("Enter the item ID to remove:");
        int itemId = scanner.nextInt();

        inventoryManager.removeItem(itemId);
        System.out.println("Item removed successfully!");
    }

    private void updateItemQuantity() throws IOException{
        System.out.println("Enter item name:");
        String name = scanner.nextLine();

        System.out.println("Enter new quantity:");
        int quantity = scanner.nextInt();

        inventoryManager.updateQuantityByName(name, quantity);
        System.out.println("Item quantity updated successfully!");
    }
    
    public void cleanup() throws IOException
	{
		// Close all input and output network buffers and sockets
		in.close();
		out.close();
		clientSocket.close();
	}
}
