package inventoryManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import salableItem.SalableItem;

public class Server 
{
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	InventoryManager inventory = new InventoryManager();
	FileSave file = new FileSave();
	
	/**
	 * Start the server and wait for connections on the specified port.
	 * 
	 * @param port
	 * @throws IOException
	 */
	public void start(int port) throws IOException
	{
		//Wait for client connection
		System.out.println("Waiting for client connection..........");
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		
		//If you get here then a client connected to this Server so create an input and output network buffers.
		System.out.println("Recieved client connection on port " + clientSocket.getLocalPort());
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
		
		// Wait for Command (string that is terminated by a line feed character)
		String inputLine;
		boolean exitServer = false;
		
		while(!exitServer && (inputLine = in.readLine()) != null)
		{
			switch (inputLine.split(":")[0])
			{
				case "Generic" :
				{
					 String[] parts = inputLine.split(":");
			            if (parts.length == 5) {  // Check if we have all required parts
			                String itemName = parts[1];
			                String itemDescription = parts[2];
			                double itemPrice = Double.parseDouble(parts[3]);
			                int itemQuantity = Integer.parseInt(parts[4]);

			                inventory.addItem(itemName, itemDescription, itemPrice, itemQuantity);
			                
			                out.println("Item added successfully!");
			                saveInventory();
			                break;
			            } else {
			                out.println("Error: Incorrect command format!");
			            }
			            break;
				}
				case "Weapon" :
				{
					 String[] parts = inputLine.split(":");
			            if (parts.length == 8) {  // Check if we have all required parts
			                String itemName = parts[1];
			                String itemDescription = parts[2];
			                double itemPrice = Double.parseDouble(parts[3]);
			                int itemQuantity = Integer.parseInt(parts[4]);
			                String itemType = parts[5];
			                String itemRarity = parts[6];
			                int itemDamage = Integer.parseInt(parts[7]);

			                inventory.addWeapon(itemName, itemDescription, itemPrice, itemQuantity, itemType, itemRarity, itemDamage);
			                
			                out.println("Item added successfully!\n");
			                saveInventory();
			                break;
			            } else {
			                out.println("Error: Incorrect command format!");
			            }
			            break;
				}
				case "Armor" :
				{
					String[] parts = inputLine.split(":");
		            if (parts.length == 8) {  // Check if we have all required parts
		                String itemName = parts[1];
		                String itemDescription = parts[2];
		                double itemPrice = Double.parseDouble(parts[3]);
		                int itemQuantity = Integer.parseInt(parts[4]);
		                String itemType = parts[5];
		                String itemRarity = parts[6];
		                int itemDefense = Integer.parseInt(parts[7]);

		                inventory.addArmor(itemName, itemDescription, itemPrice, itemQuantity, itemType, itemRarity, itemDefense);
		                
		                out.println("Item added successfully!");
		                saveInventory();
		                break;
		            } else {
		                out.println("Error: Incorrect command format!");
		            }
		            break;
				}
				case "Health" :
				{
					String[] parts = inputLine.split(":");
		            if (parts.length == 6) {  // Check if we have all required parts
		                String itemName = parts[1];
		                String itemDescription = parts[2];
		                double itemPrice = Double.parseDouble(parts[3]);
		                int itemQuantity = Integer.parseInt(parts[4]);
		                int itemHealth = Integer.parseInt(parts[5]);

		                inventory.addHealthItem(itemName, itemDescription, itemPrice, itemQuantity, itemHealth);
		                
		                out.println("Item added successfully!");
		                saveInventory();
		                break;
		            } else {
		                out.println("Error: Incorrect command format!");
		            }
		            break;
				}
				case "Exit" :
				{
					System.out.println("Got a message to shut the server down");
					exitServer = true;
					break;
				}
				case "Inventory" :
				{
					try {
						List<SalableItem> itemList = file.readFromFile("inventory_items.json");
						for(SalableItem item : itemList)
						{
							String text = String.format("%d - %s $%.2f (%d in stock)\n- %s\n",
									item.getItemId(),
			                        item.getItemName(), 
			                        item.getItemPrice(), 
			                        item.getItemQuantity(), 
			                        item.getItemDescription());
							out.println(text);
						}
						
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					out.println("End of Inventory.");
					break;
				}
				default : System.out.println("Invalid choice. Please try again.");
			}
		}
		System.out.println("Server is shut down.");
	}
	
	
	/**
	 * Cleanup logic to close all the network connections.
	 * 
	 * @throws IOException Thrown if anything bad happens from the networking classes.
	 */
	public void cleanup() throws IOException
	{
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}
	 
	private void saveInventory() {
		try 
		{
	        List<SalableItem> items = new ArrayList<>(inventory.getItems()); // Assuming inventory has a getItems method.
	        FileSave.saveToFile(FileSave.INVENTORY_ITEMS, items);
	     } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Entry method for the Server application (for testing only).
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		// Create an instance of this Server
		Server server = new Server();
		
		// Start the Server on port 6666 (Which will not return until the shutdown command is received
		server.start(6666);
		
		// and then exit and clean everything up
		server.cleanup();
	}

	
}
