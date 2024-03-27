import java.util.ArrayList;
public class Enemy {
	private double health;
	private String name;
	private String description;
	private double damage;
	private ArrayList<InventoryItem> itemsHeld;
	
	public Enemy(){
		this.health = 0;
		this.description = "no Desc";
		this.name = "no Name";
		this.damage = 0;
		this.itemsHeld = new ArrayList<InventoryItem>();
	}
	
	public Enemy(String name, String description, double health, double damage){
		this.health = health;
		this.description = description;
		this.name = name;
		this.damage = damage;
		this.itemsHeld = new ArrayList<InventoryItem>();
	}
	
	public String getEnemyName(){
		return name;
	}
	
	public String getEnemyDescription(){
		return description;
	}
	
	public double getEnemyHealth(){
		return health;
	}
	
	public double getEnemyDamage(){
		return damage;
	}
	
	public void setEnemyHealth(double health){
		this.health = health;
	}
	
	public void setEnemyDamage(double damage){
		this.damage = damage;
	}
	
	public ArrayList<InventoryItem> getArrayList(){
		return itemsHeld;
	}
}
