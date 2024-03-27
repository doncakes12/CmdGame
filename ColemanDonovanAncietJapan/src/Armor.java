
public class Armor extends InventoryItem{

	private double armorValue;
	
	public Armor(){
		super("none", "", 0);
		this.armorValue = 1;
	}

	public Armor(String name, String description, double weight, double armorValue){
		super(name, description, weight);
		this.armorValue = armorValue;
	}
	
	public double getArmorValue(){
		return armorValue;
	}
	
	
}
