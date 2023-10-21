package shoppingCart;

import java.util.ArrayList;
import java.util.List;
import salableItem.SalableItem;

/**
 * Represents a shopping cart system, maintaining a list of items selected for purchase.
 * 
 * @author Chennithan
 */
public class ShoppingCart {
	private List<SalableItem> itemsList = new ArrayList<>();
	private int itemCount = 0;
	private double totalPrice = 0.0;
	
	 /**
     * Initializes a new empty shopping cart.
     */
	public ShoppingCart() {
		itemsList = new ArrayList<>();
		itemCount = 0;
		totalPrice = 0.0;
	}
	
	 /**
     * Adds a specified quantity of a given item to the cart.
     *
     * @param selectedItem The item to be added.
     * @param quantity     The quantity of the item to be added.
     */
	public void addItem(SalableItem selectedItem, int quantity) {
	    for (int i = 0; i < quantity; i++) {
	        itemsList.add(selectedItem);
	    }
	    itemCount += quantity;
	    totalPrice += selectedItem.getItemPrice() * quantity;
	}

	/**
     * Removes a specified quantity of a given item from the cart.
     *
     * @param item     The item to be removed.
     * @param quantity The quantity of the item to be removed.
     */
    public void removeMultipleItems(SalableItem item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            itemsList.remove(item);
            itemCount--;
            totalPrice -= item.getItemPrice();
        }
    }
    
    /**
     * Checks if a specific item exists in the cart by its name.
     *
     * @param itemName The name of the item to be checked.
     * @return         true if the item exists in the cart; false otherwise.
     */
    public boolean itemExistsInCart(String itemName) {
        for (SalableItem item : itemsList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a specific item from the cart based on its name.
     *
     * @param itemName The name of the item to be retrieved.
     * @return         The SalableItem object if found in the cart; null otherwise.
     */
    public SalableItem getItemByName(String itemName) {
        for (SalableItem item : itemsList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Counts the occurrences of a specific item in the cart.
     *
     * @param specificItem The specific item whose count is required.
     * @return             The count of occurrences of the specified item in the cart.
     */
    public int getItemCountForSpecificItem(SalableItem specificItem) {
        int count = 0;
        for (SalableItem item : itemsList) {
            if (item.equals(specificItem)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Gets the total number of items in the cart.
     *
     * @return The total count of items in the cart.
     */
	public int getItemCount() {
        return itemCount;
    }

	 /**
     * Gets the total price of all items in the cart.
     *
     * @return The total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    
    /**
     * Retrieves a list of all items in the cart.
     *
     * @return A list of all items in the cart.
     */
    public List<SalableItem> getItems() {
        return itemsList;
    }
    
    /**
     * Clears all items from the cart and resets totals.
     */
    public void clearCart() {
        itemsList.clear();
        itemCount = 0;
        totalPrice = 0.0;
    }
   
    /**
     * Displays a list of all items in the cart and the cart totals.
     */
    public void displayCartItems() {
        System.out.println("Items in your cart:");
        if (itemsList.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        for (SalableItem item : itemsList) {
            System.out.println(item.getItemName() + " - $" + item.getItemPrice());
        }
        System.out.println("Total items: " + getItemCount());
        System.out.println("Total price: $" + getTotalPrice());
    }

}
