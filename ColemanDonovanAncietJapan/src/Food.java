public class Food extends InventoryItem{
	private double health;
	
	public Food(String name, String description, double weight, double health){
		super(name, description, weight);
		this.health = health;
		
	}
	
	
	public double getHealth(){
		return health;
	}
	
	public String toString(){
		String returned = "";
		returned = returned + "Name: " + getName();
		returned = returned + "\nDescripition: " + getDescripition();
		returned = returned + "\nWeight: " + getWeight();
		returned = returned + "\nHealth: " + getHealth();
		return returned;
	}
}
