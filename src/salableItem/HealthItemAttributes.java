package salableItem;

/**
 * Represents attributes specific to health items. This includes health item-specific
 * characteristics such as the amount of health increase the item provides.
 * <p>
 * A health item attribute is a specialized type of a salable item.
 * </p>
 * 
 * @author Chennithan
 */
public class HealthItemAttributes extends SalableItem {

    private int increaseHealthStat;

    /**
     * Default constructor, initializing a new health item with default values.
     */
    public HealthItemAttributes() {
        super();
    }

    /**
     * Constructor to initialize a health item with specified attributes.
     * 
     * @param itemId            The unique identifier of the health item.
     * @param itemName          The name of the health item.
     * @param itemDescription   A brief description of the health item.
     * @param itemPrice         The price of the health item.
     * @param itemQuantity      The quantity available of the health item.
     * @param increaseHealthStat The amount of health increase provided by the health item.
     */
    public HealthItemAttributes(int itemId, String itemName, String itemDescription,
            double itemPrice, int itemQuantity, int increaseHealthStat) {
        super(itemId, itemName, itemDescription, itemPrice, itemQuantity);
        this.increaseHealthStat = increaseHealthStat;
    }

    /**
     * Returns the amount of health increase provided by the health item.
     * 
     * @return The increase in health statistic.
     */
    public int getIncreaseHealthStat() {
        return increaseHealthStat;
    }

    /**
     * Sets the amount of health increase provided by the health item.
     * 
     * @param increaseHealthStat The increase in health to be set.
     */
    public void setIncreaseHealthStat(int increaseHealthStat) {
        this.increaseHealthStat = increaseHealthStat;
    }
}
