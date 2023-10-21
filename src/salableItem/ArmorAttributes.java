package salableItem;


/**
 * Represents attributes specific to armors. This includes armor-specific
 * characteristics such as defense stat, armor type, and rarity.
 * <p>
 * An armor attribute is a specialized type of a salable item.
 * </p>
 * 
 * @author Chennithan
 */
public class ArmorAttributes extends SalableItem {

    private int defenseStat;
    private String armorType;
    private String armorRarity;

    /**
     * Default constructor, initializing a new armor with default values.
     */
    public ArmorAttributes() {
        super();
    }

    /**
     * Constructor to initialize armor with specified attributes.
     * 
     * @param itemId           The unique identifier of the armor.
     * @param itemName         The name of the armor.
     * @param itemDescription  A brief description of the armor.
     * @param itemPrice        The price of the armor.
     * @param itemQuantity     The quantity available of the armor.
     * @param armorType        The type of the armor.
     * @param armorRarity      The rarity of the armor.
     * @param defenseStat      The defense statistic of the armor.
     */
    public ArmorAttributes(int itemId, String itemName, String itemDescription,
            double itemPrice, int itemQuantity, String armorType, String armorRarity, int defenseStat) {
        super(itemId, itemName, itemDescription, itemPrice, itemQuantity);
        this.armorType = armorType;
        this.armorRarity = armorRarity;
        this.defenseStat = defenseStat;
    }

    /**
     * Returns the defense statistic of the armor.
     * 
     * @return The defense statistic.
     */
    public int getDefenseStat() {
        return defenseStat;
    }

    /**
     * Sets the defense statistic of the armor.
     * 
     * @param defenseStat The defense statistic to be set.
     */
    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    /**
     * Returns the type of the armor.
     * 
     * @return The armor type.
     */
    public String getArmorType() {
        return armorType;
    }

    /**
     * Sets the type of the armor.
     * 
     * @param armorType The armor type to be set.
     */
    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    /**
     * Returns the rarity of the armor.
     * 
     * @return The armor rarity.
     */
    public String getArmorRarity() {
        return armorRarity;
    }

    /**
     * Sets the rarity of the armor.
     * 
     * @param armorRarity The armor rarity to be set.
     */
    public void setArmorRarity(String armorRarity) {
        this.armorRarity = armorRarity;
    }
}
