import java.util.ArrayList;

public class NPC {
	
	private ArrayList<InventoryItem> items;
	private String name;
	private String needs;

	
	public NPC(){
		items = new ArrayList<InventoryItem>();
		name = "No Name";
		needs = "none";
	}
	
	public NPC (String name, String needs){
		items = new ArrayList<InventoryItem>();
		this.name = name;
		this.needs = needs;	
	}
	
	public String getNeeds(){
		return needs;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<InventoryItem> getItems(){
		return items;
	}
}
