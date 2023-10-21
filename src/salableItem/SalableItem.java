package salableItem;

import java.util.Comparator;
/**
 * Represents an item that can be sold, with properties for ID, name, description, price, and quantity.
 * 
 * @author Chennithan
 */

public class SalableItem implements Comparable<SalableItem>{
	
	private int itemId;
	private String itemName;
	private String itemDescription;
	private double itemPrice;
	private int itemQuantity;
 
	/**
	 * 
	 * Default constructor with no args.
	 * 
	 */
	public SalableItem() {
	}
 
	/**
     * Constructs a new SalableItem object with the provided item details.
     *
     * @param itemId          The ID of the item.
     * @param itemName        The name of the item.
     * @param itemDescription The description of the item.
     * @param itemPrice       The price of the item.
     * @param itemQuantity    The quantity of the item.
     */
	public SalableItem (int itemId, String itemName, String itemDescription, double itemPrice, int itemQuantity)
	 {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;

	 }
	
	/**
	 * Compares two SalableItem objects based on their name.
	 */
	@Override
    public int compareTo(SalableItem other) {
        return this.getItemName().compareToIgnoreCase(other.getItemName());
    }
	
	/**
	 * A comparator for comparing {@code SalableItem} objects based on their name in ascending order.
	 */
	public static class NameAscendingComparator implements Comparator<SalableItem> {
			/**
			* Compares two SalableItem objects based on their name in ascending order in a case-insensitive manner.
			* 
			* @param item1 the first item to be compared.
			* @param item2 the second item to be compared.
			* @return a negative integer, zero, or a positive integer if the first item's name is 
			*         less than, equal to, or greater than the second item's name.
			*/
			@Override
	        public int compare(SalableItem item1, SalableItem item2) {
	            return item1.getItemName().compareToIgnoreCase(item2.getItemName());
	        }
	    }
	
	/**
	 * A comparator for comparing {@code SalableItem} objects based on their name in descending order.
	 */
	public static class NameDescendingComparator implements Comparator<SalableItem> {
			/**
			* Compares two SalableItem objects based on their name in descending order in a case-insensitive manner.
			* 
			* @param item1 the first item to be compared.
			* @param item2 the second item to be compared.
			* @return a negative integer, zero, or a positive integer if the first item's name is 
			*         less than, equal to, or greater than the second item's name.
			*/
			@Override
	        public int compare(SalableItem item1, SalableItem item2) {
	            return item2.getItemName().compareToIgnoreCase(item1.getItemName());
	        }
	    }
	
	/**
	 * A comparator for comparing {@code SalableItem} objects based on their price in ascending order.
	 */
	public static class PriceAscendingComparator implements Comparator<SalableItem> {	
			/**
			* Compares two SalableItem objects based on their price.
			* 
			* @param item1 the first item to be compared.
			* @param item2 the second item to be compared.
			* @return a negative integer, zero, or a positive integer if the first item's name is 
			*         less than, equal to, or greater than the second item's name.
			*/
	        @Override
	        public int compare(SalableItem item1, SalableItem item2) {
	            return Double.compare(item1.getItemPrice(), item2.getItemPrice());
	        }
	    }
	
	/**
	 * A comparator for comparing {@code SalableItem} objects based on their price in descending order.
	 */
	public static class PriceDescendingComparator implements Comparator<SalableItem> {
		
			/**
			* Compares two SalableItem objects based on their price.
			* 
			* @param item1 the first item to be compared.
			* @param item2 the second item to be compared.
			* @return a negative integer, zero, or a positive integer if the first item's name is 
			*         less than, equal to, or greater than the second item's name.
			*/
				
	        @Override
	        public int compare(SalableItem item1, SalableItem item2) {
	            return Double.compare(item2.getItemPrice(), item1.getItemPrice());
	        }
	    }

	/**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
	public String getItemName() 
	{
		return itemName;
	}

	/**
     * Sets the name of the item.
     *
     * @param itemName The new name of the item.
     */
	public void setItemName(String itemName) 
	{	
		this.itemName = itemName;
	}

	 /**
     * Gets the description of the item.
     *
     * @return The description of the item.
     */
	public String getItemDescription() 
	{
		return itemDescription;
	}

	/**
     * Sets the description of the item.
     *
     * @param itemDescription The new description of the item.
     */
	public void setItemDescription(String itemDescription) 
	{
		this.itemDescription = itemDescription;
	}

	/**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
	public double getItemPrice() 
	{
		return itemPrice;
	}

	/**
     * Sets the price of the item.
     *
     * @param itemPrice The new price of the item.
     */
	public void setItemPrice(double itemPrice) 
	{
		this.itemPrice = itemPrice;
	}

	 /**
     * Gets the quantity of the item.
     *
     * @return The quantity of the item.
     */
	public int getItemQuantity() 
	{
		return itemQuantity;
	}

	 /**
     * Sets the quantity of the item.
     *
     * @param itemQuantity The new quantity of the item.
     */
	public void setItemQuantity(int itemQuantity) 
	{
		this.itemQuantity = itemQuantity;
	}

	 /**
     * Gets the ID of the item.
     *
     * @return The ID of the item.
     */
	public int getItemId() {
		return itemId;
	}
		 
}
