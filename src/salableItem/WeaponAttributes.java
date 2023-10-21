package salableItem;



/**
 * Represents attributes specific to weapons. This includes weapon-specific
 * characteristics such as attack damage, weapon type, and rarity.
 * <p>
 * A weapon attribute is a specialized type of a salable item.
 * </p>
 * 
 * @author Chennithan
 */
public class WeaponAttributes extends SalableItem{

    private int attackDamage;
    private String weaponType;
    private String weaponRarity;

    /**
     * Default constructor, initializing a new weapon with default values.
     */
    public WeaponAttributes() {
        super();
    }

    /**
     * Constructor to initialize a weapon with specified attributes.
     * 
     * @param itemId           The unique identifier of the weapon.
     * @param itemName         The name of the weapon.
     * @param itemDescription  A brief description of the weapon.
     * @param itemPrice        The price of the weapon.
     * @param itemQuantity     The quantity available of the weapon.
     * @param weaponType       The type of the weapon.
     * @param weaponRarity     The rarity of the weapon.
     * @param attackDamage     The attack damage of the weapon.
     */
    public WeaponAttributes(int itemId, String itemName, String itemDescription,
            double itemPrice, int itemQuantity, String weaponType, String weaponRarity, int attackDamage) {
        super(itemId, itemName, itemDescription, itemPrice, itemQuantity);
        this.weaponType = weaponType;
        this.weaponRarity = weaponRarity;
        this.attackDamage = attackDamage;
    }
    

    /**
     * Returns the attack damage of the weapon.
     * 
     * @return The attack damage.
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Sets the attack damage of the weapon.
     * 
     * @param attackDamage The attack damage to be set.
     */
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Returns the type of the weapon.
     * 
     * @return The weapon type.
     */
    public String getWeaponType() {
        return weaponType;
    }

    /**
     * Sets the type of the weapon.
     * 
     * @param weaponType The weapon type to be set.
     */
    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Returns the rarity of the weapon.
     * 
     * @return The weapon rarity.
     */
    public String getWeaponRarity() {
        return weaponRarity;
    }

    /**
     * Sets the rarity of the weapon.
     * 
     * @param weaponRarity The weapon rarity to be set.
     */
    public void setWeaponRarity(String weaponRarity) {
        this.weaponRarity = weaponRarity;
    }
}
