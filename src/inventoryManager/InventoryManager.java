package inventoryManager;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;


import salableItem.ArmorAttributes;
import salableItem.HealthItemAttributes;
import salableItem.SalableItem;
import salableItem.WeaponAttributes;
import shoppingCart.ShoppingCart;

/**
 * Represents an inventory management system, maintaining a list of available items for sale.
 * 
 * @author Chennithan
 */
public class InventoryManager {
	
    private List<SalableItem> itemList = new ArrayList<>();
    private int itemId = 0;
    FileSave file = new FileSave();

    /**
     * Adds a generic item to the inventory list and saves the updated list to a file.
     *
     * @param itemName        The name of the item.
     * @param itemDescription A brief description of the item.
     * @param itemPrice       The price of the item.
     * @param itemQuantity    The available quantity of the item in stock.
     */
    public void addItem(String itemName, String itemDescription, double itemPrice, int itemQuantity) {
        itemId += 1;
        SalableItem item = new SalableItem(itemId, itemName, itemDescription, itemPrice, itemQuantity);
        itemList.add(item);
    }
    
    /**
     * Adds a weapon to the inventory list and saves the updated list to a file.
     * 
     * @param itemName        	The name of the item.
     * @param itemDescription 	A brief description of the item.
     * @param itemPrice       	The price of the item.
     * @param itemQuantity    	The available quantity of the item in stock.
     * @param weaponType		The type of weapon.
     * @param weaponRarity		The rarity of the weapon.
     * @param attackDamage		Amount of attack the weapon increases.
     */
    public void addWeapon(String itemName, String itemDescription, 
    		double itemPrice, int itemQuantity, String weaponType, String weaponRarity, int attackDamage) {
    	
        itemId += 1;
        WeaponAttributes item = new WeaponAttributes(itemId, itemName, itemDescription, itemPrice, itemQuantity, weaponType, weaponRarity, attackDamage);
        itemList.add(item);
    }
    
    /**
     * Adds armor to the inventory list and saves the updated list to a file.
     * 
     * @param itemName        	The name of the item.
     * @param itemDescription 	A brief description of the item.
     * @param itemPrice       	The price of the item.
     * @param itemQuantity    	The available quantity of the item in stock.
     * @param armorType			The type of armor.	
     * @param armorRarity		The rarity of the armor.
     * @param defenseStat		Amount of defense the armor adds.
     */
    public void addArmor(String itemName, String itemDescription,
			   double itemPrice, int itemQuantity, String armorType, String armorRarity, int defenseStat) {
    	
        itemId += 1;
        ArmorAttributes item = new ArmorAttributes(itemId, itemName, itemDescription, itemPrice, itemQuantity, armorType, armorRarity, defenseStat);
        itemList.add(item);
    }
    
    /**
     * Adds a health item to the inventory list and saves the updated list to a file.
     * 
     * @param itemName        		The name of the item.
     * @param itemDescription 		A brief description of the item.
     * @param itemPrice       		The price of the item.
     * @param itemQuantity    		The available quantity of the item in stock.
     * @param increaseHealthStat	Amount health will be increased by.
     */
	public void addHealthItem(String itemName, String itemDescription,
				   double itemPrice, int itemQuantity, int increaseHealthStat) {
	 	
	     itemId += 1;
	     HealthItemAttributes item = new HealthItemAttributes(itemId, itemName, itemDescription, itemPrice, itemQuantity, increaseHealthStat);
	     itemList.add(item);
	}

    /**
     * Removes an item from the inventory based on its unique ID
     * and saves the updated list to a file.
     *
     * @param itemIdToRemove The unique ID of the item to be removed.
     */
    public void removeItem(int itemIdToRemove) throws IOException {
    	
        itemList = file.readFromFile("inventory_items.json");

        Iterator<SalableItem> iterator = itemList.iterator();
        while (iterator.hasNext()) {
            SalableItem item = iterator.next();
            if (item.getItemId() == itemIdToRemove) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Updates the available quantity of an item by its name
     * and saves the updated list to a file.
     *
     * @param itemName    The name of the item.
     * @param newQuantity The new quantity to be set.
     * @throws IOException 
     */
    public void updateQuantityByName(String itemName, int newQuantity) throws IOException {
    	
        itemList = file.readFromFile("inventory_items.json");
    	
        for (SalableItem item : itemList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                item.setItemQuantity(newQuantity);
                file.saveToFile();
                break;
            }
        }
    }
    
    /**
     * Updates inventory quantities based on items in the shopping cart.
     *
     * @param cart The shopping cart whose items are to be accounted for.
     * @return     true if sufficient stock is available for all items in the cart; false otherwise.
     * @throws IOException 
     */
    public boolean updateQuantitiesFromCart(ShoppingCart cart) throws IOException {
        itemList = file.readFromFile("inventory_items.json");
        List<SalableItem> cartItems = cart.getItems();

        // Use a flag to keep track of whether any quantities were updated.
        boolean quantityUpdated = false;

        for (SalableItem inventoryItem : itemList) {
            int cartItemCount = 0;
            for (SalableItem cartItem : cartItems) {
                if (inventoryItem.getItemName().equalsIgnoreCase(cartItem.getItemName())) {
                    cartItemCount += cartItem.getItemQuantity();
                }
            }

            if (cartItemCount > 0) {
                if (inventoryItem.getItemQuantity() < cartItemCount) {
                    // Not enough stock.
                    return false;
                }
                inventoryItem.setItemQuantity(inventoryItem.getItemQuantity() - cartItemCount);
                quantityUpdated = true;
            }
        }
        
        if (quantityUpdated) {
            // If any quantities were updated, save to file.
            file.saveToFile();
        }

        return true;
    }

    
    /**
     * Checks if a specific item exists in the inventory by its name.
     *
     * @param selectedItem The name of the item to be checked.
     * @return             true if the item exists; false otherwise.
     * @throws IOException 
     */
    public boolean itemExists(String selectedItem) throws IOException {
    	itemList = file.readFromFile(FileSave.INVENTORY_ITEMS);
    	
        for (SalableItem item : itemList) {
            if (item.getItemName().equalsIgnoreCase(selectedItem)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Retrieves a specific item from the inventory based on its name.
     *
     * @param itemName The name of the item to be retrieved.
     * @return         The SalableItem object if found; null otherwise.
     */
    public SalableItem getItemByName(String itemName) {
        for (SalableItem item : itemList) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * Retrieves the list of items in the inventory.
     *
     * @return A list containing all items in the inventory.
     * @throws IOException 
     */
    public List<SalableItem> getItems() throws IOException {
    	itemList = file.readFromFile(FileSave.INVENTORY_ITEMS);

        return new ArrayList<>(itemList);
    }
    
    /**
     * Initializes the store with predefined items.
     * This can be used to seed the inventory with a default set of items.
     */
    public void initializeStore() throws IOException {
        /*	addWeapon("Lightning Sword", "A sword gifted by Zeus, chains lightning to enemies within 5 yards.", 2000, 1, "Sword", "Legendary", 255);
        	addWeapon("Death Cleaver", "This axe was stolen from Death himself, causes 100 points of necrotic damage with each hit.", 5000, 1, "Axe", "Godly", 525);
	        addArmor("Leather Armor", "Simple leather armor", 5, 1, "Armor", "Common", 10);
	        addArmor("Helmet of insight", "A helmet infused with magic, gives the user +15 intellect.", 100, 1, "Helmet", "Rare", 45);
	        addHealthItem("Simple Health Potion", "Replenish 50 health", 1, 20, 50);
	        addItem("zz another test item", "This is a test item to prove that sorting ignores case", 0, 0);
	        addItem("a test item", "This is a test item to prove that sorting works properly and ignores case", 0, 0);
	        
	        saveToFile();
	        */
    	file.readFromFile("inventory_items.json");
    }
    
   
    /**
     * Sorts {@code SalableItem} objects by name in ascending order and saves to JSON file.
     */
    public void sortByNameAscending() {
        Collections.sort(itemList, new SalableItem.NameAscendingComparator());
        file.saveToFile();
    }
    
    /**
     * Sorts {@code SalableItem} objects by name in descending order and saves to JSON file.
     */
    public void sortByNameDescending() {
        Collections.sort(itemList, new SalableItem.NameDescendingComparator());
        file.saveToFile();
    }

    /**
     * Sorts {@code SalableItem} objects by price in ascending order and saves to JSON file.
     */
    public void sortByPriceAscending() {
        Collections.sort(itemList, new SalableItem.PriceAscendingComparator());
        file.saveToFile();
    }

    /**
     * Sorts {@code SalableItem} objects by price in descending order and saves to JSON file.
     */
    public void sortByPriceDescending() {
        Collections.sort(itemList, new SalableItem.PriceDescendingComparator());
        file.saveToFile();
    }
    
    /**
     * Lists all available items in the store inventory.
     */
    public void listInventory() {
        try {
			List<SalableItem> itemList = file.readFromFile("inventory_items.json");
			for(SalableItem item : itemList)
			{
				String text = String.format("%s - $%.2f (%d in stock)\n- %s\n", 
                        item.getItemName(), 
                        item.getItemPrice(), 
                        item.getItemQuantity(), 
                        item.getItemDescription());
				if (item instanceof WeaponAttributes) {
	                WeaponAttributes weapon = (WeaponAttributes) item;
	                text += String.format("- Type: %s, Rarity: %s, Damage: %d\n",
	                        weapon.getWeaponType(),
	                        weapon.getWeaponRarity(),
	                        weapon.getAttackDamage());
	            } else if (item instanceof ArmorAttributes) {
	                ArmorAttributes armor = (ArmorAttributes) item;
	                text += String.format("- Type: %s, Rarity: %s, Defense: %d\n",
	                        armor.getArmorType(),
	                        armor.getArmorRarity(),
	                        armor.getDefenseStat());
	            } else if (item instanceof HealthItemAttributes) {
	            	HealthItemAttributes healthItem = (HealthItemAttributes) item;
	                text += String.format("- Type: %d\n" + healthItem.getIncreaseHealthStat());
	            }
				System.out.println(text);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
