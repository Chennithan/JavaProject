package inventoryManager;

import java.io.IOException;
import java.util.Scanner;

import salableItem.SalableItem;
import shoppingCart.ShoppingCart;

/**
 * Represents a store front interface where customers can view inventory, add items to a shopping cart,
 * remove items from the cart, and complete a checkout process.
 * 
 * @author Chennithan
 * 
 */
public class StoreFront extends Thread {

    private static String userInput;
    private static InventoryManager inventory = new InventoryManager();
    private static ShoppingCart shoppingCart = new ShoppingCart();
    public static Scanner scnr = new Scanner(System.in);
  
    /**
     * Entry point for the application.
     * 
     * @param args The command-line arguments.
     * @throws IOException 
     */
    public static void main(String[] args) throws InterruptedException {
    	System.out.println("Please select User or Admin");
    	while(true) {
    		userInput = scnr.nextLine();
    		if("user".equalsIgnoreCase(userInput)) {
    			new StoreFront().start();
    			return;
    		}
    		else if ("admin".equalsIgnoreCase(userInput)) {
    			ServerThread serverThread = new ServerThread();
    			serverThread.start();
    			
    			while(serverThread.isAlive())
    			{
    				System.out.println("Admin application server started.");
    				InventoryAdministration admin = new InventoryAdministration();
					admin.start();
					return;
    			}
    		}
    		else {
    			System.out.println("Invalid entry, type User or Admin");
    		}
    	}
    	
    }
    
    
    public void run() {
        try {
            inventory.initializeStore();
        } catch (IOException e) {
            System.err.println("Failed to initialize the store. Exiting.");
            return;
        } 

        System.out.println("Welcome to Forge of Fantasies. \nPlease type 'enter' to enter the store, or 'exit' to leave.");

        while (true) {
            userInput = scnr.nextLine();
            
            if ("enter".equalsIgnoreCase(userInput)) {
                try {
					handleStoreOperations();
				} catch (IOException e) {
					e.printStackTrace();
				}
            } else if ("exit".equalsIgnoreCase(userInput)) {
                System.out.println("Gone so soon? Come back again later!");
                return;
            } else {
                System.out.println("Invalid entry, please type 'enter' or 'exit' to continue.");
            }
        }
    }

    private void handleStoreOperations() throws IOException {
        inventory.listInventory();

        while (true) {
            System.out.println("Select an action: 'add', 'remove', 'checkout', 'view inventory', 'view cart', or 'exit'\n"
            		+ "You can also sort the items by name or price, simply type 'name ascending/descending order' or 'price ascending/descending order'");
            String userInput = scnr.nextLine();

            switch (userInput.toLowerCase()) 
            {
                case "add" : selectItem(); 
                	break;
                case "remove" : removeItem();
                	break;
                case "checkout" : checkout();
                	break;
                case "view inventory" : inventory.listInventory();
                	break;
                case "view cart" : shoppingCart.displayCartItems();
                	break;
                case "name ascending order" : 
                {
                	inventory.sortByNameAscending();
                	inventory.listInventory();
    	        	break;
                }
                case "name descending order" : {
                	inventory.sortByNameDescending();
                	inventory.listInventory();
    	        	break;
                }
                case "price ascending order" : {
                	inventory.sortByPriceAscending();
                	inventory.listInventory();
    	        	break;
                }
                case "price descending order" : {
                	inventory.sortByPriceDescending();
                	inventory.listInventory();
                	break;
                }
                case "exit" : {
                    System.out.println("Just browsing? If you would like to shop again please type 'enter' or 'exit' to leave.");
                    return;
                }
                default : System.out.println("Invalid action. Please try again.");
            }
        }
    }
    
   

    
    /**
     * Allows a customer to select an item to add to their shopping cart.
     * @throws IOException 
     */
    public static void selectItem() throws IOException {
        System.out.println("Enter the name of the item you want to purchase:");
        String selectedItemName = scnr.nextLine();

        if (inventory.itemExists(selectedItemName)) {
            SalableItem selectedItem = inventory.getItemByName(selectedItemName);

            System.out.println("Enter the quantity of the items you want to purchase:");
            if (scnr.hasNextInt()) {
                int itemQuantity = scnr.nextInt();
                scnr.nextLine(); 

                if (itemQuantity <= 0) {
                    System.out.println("Invalid quantity. Please enter a positive number.");
                } else if (itemQuantity <= selectedItem.getItemQuantity()) {
                    shoppingCart.addItem(selectedItem, itemQuantity); 
                    System.out.println(itemQuantity + " " + selectedItemName + "(s) added to the cart.");
                } else {
                    System.out.println("Not enough quantity available for " + selectedItemName);
                }
            } else {
                System.out.println("Invalid input. Please enter a valid quantity.");
            }
        } else {
            System.out.println("Item not found in the inventory.");
        }
    }
    
    /**
     * Allows a customer to remove a selected item from their shopping cart.
     */
    public static void removeItem() {
        System.out.println("Enter the name of the item you want to remove from the cart:");
        String itemNameToRemove = scnr.nextLine();

        if (shoppingCart.itemExistsInCart(itemNameToRemove)) {
            SalableItem itemToRemove = shoppingCart.getItemByName(itemNameToRemove);

            System.out.println("Enter the quantity of " + itemNameToRemove + " you want to remove:");
            if (scnr.hasNextInt()) {
                int quantityToRemove = scnr.nextInt();
                scnr.nextLine(); 

                if (quantityToRemove <= 0) {
                    System.out.println("Invalid quantity. Please enter a positive number.");
                } else if (quantityToRemove <= shoppingCart.getItemCountForSpecificItem(itemToRemove)) { 
                    shoppingCart.removeMultipleItems(itemToRemove, quantityToRemove); 

                    System.out.println(quantityToRemove + " " + itemNameToRemove + "(s) have been removed from the cart.");
                } else {
                    System.out.println("You don't have that many of " + itemNameToRemove + " in your cart.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid quantity.");
                scnr.nextLine(); 
            }
        } else {
            System.out.println("Item not found in the cart.");
        }
    }
    
    /**
     * Processes the checkout for items in the shopping cart.
     * The customer is prompted to confirm or cancel the purchase.
     * @throws IOException 
     */
    public static void checkout() throws IOException {
        double total = shoppingCart.getTotalPrice();
        System.out.println("Total price: $" + total);
        System.out.println("Enter 'confirm' to complete the purchase or 'cancel' to cancel.");
        String confirm = scnr.nextLine();

        if (confirm.equalsIgnoreCase("confirm")) {
            if (inventory.updateQuantitiesFromCart(shoppingCart)) {
                shoppingCart.clearCart();
                System.out.println("Purchase complete. Thank you for shopping with us!");
            } else {
                System.out.println("Checkout canceled due to insufficient inventory.");
            }
        } else if (confirm.equalsIgnoreCase("cancel")) {
            System.out.println("Purchase canceled.");
        }
    }
    
}