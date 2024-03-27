
public class Weapon extends InventoryItem{

	private double damage;
	
	public Weapon(){
		super("none", "", 0);
		this.damage = 1;
	}
	
	public Weapon(String name, String description, double weight, double damage){
		super(name, description, weight);
		this.damage = damage;
	}
	
	public double getDamage(){
		return damage;
	}
	
	
	
}
