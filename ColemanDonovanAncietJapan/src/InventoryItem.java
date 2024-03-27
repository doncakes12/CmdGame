public class InventoryItem{
	private String name;
	private String description;
	private double weight;

	
	public InventoryItem(){
		this.name = "none";
		this.description = "none";
		this.weight = 0;
	}
	
	public InventoryItem(String name, String descripition, double weight){
		this.name = name;
		this.description = descripition;
		this.weight = weight;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDescripition(String descripition){
		this.description = descripition;
	}
	
	public void setWeight(double weight){
		this.weight = weight;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescripition(){
		return description;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public String toString(){
		String returned = "";
		returned = returned + "Name: " + getName();
		returned = returned + "\nDescripition: " + getDescripition();
		returned = returned + "\nWeight: " + getWeight();
		return returned;
	}
}



 
