import java.util.ArrayList;

public class Room {



	private boolean canGoNorth; 
	private boolean canGoSouth;
	private boolean canGoEast;
	private boolean canGoWest;
	private String description;
	private String name;
	private ArrayList<InventoryItem> items;
	private ArrayList<Enemy> hasEnemy;
	private ArrayList<NPC> npcInRoom;
	private boolean hasEntered;


	public Room() {
		canGoNorth = false;
		canGoSouth = false;
		canGoEast = false;
		canGoWest = false;
		description = "This is not a room, you shouldn't be here!!!";
		name = "";
		items = new ArrayList<InventoryItem>();
		hasEnemy = new ArrayList<Enemy>();
		npcInRoom = new ArrayList<NPC>();
		hasEntered = false;
	}

//	public Room(boolean n, boolean s, boolean e, boolean w, String desc, String nameOfRoom, ArrayList<InventoryItem> items) {
//		canGoNorth = n;
//		canGoSouth = s;
//		canGoEast = e;
//		canGoWest = w;
//		description = desc;
//		name = nameOfRoom;
//		this.items = items;
//	}

	public Room(String nameOfRoom, boolean n, boolean e, boolean s, boolean w, String desc) {
		canGoNorth = n;
		canGoSouth = s;
		canGoEast = e;
		canGoWest = w;
		description = desc;
		name = nameOfRoom;
		items = new ArrayList<InventoryItem>();
		hasEnemy = new ArrayList<Enemy>();
		npcInRoom = new ArrayList<NPC>();
		hasEntered = false;
	}
	
	
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public boolean getCanGoNorth() {
		return canGoNorth;
	}

	public boolean getCanGoSouth() {
		return canGoSouth;
	}

	public boolean getCanGoEast() {
		return canGoEast;
	}

	public boolean getCanGoWest() {
		return canGoWest;
	}

	public void setCanGoNorth(boolean newNorth) {
		this.canGoNorth = newNorth;
	}

	public void setCanGoSouth(boolean newSouth) {
		this.canGoSouth = newSouth;
	}

	public void setCanGoEast(boolean newEast) {
		this.canGoEast = newEast;
	}

	public void setCanGoWest(boolean newWest) {
		this.canGoWest = newWest;
	}
	
	public String getNameOfObjectsInRoom(){
		String itemslist ="";
		if(items.isEmpty())
			itemslist = "\n";
			for(InventoryItem i : items){
				itemslist = itemslist + (i.getName()) + "\n";
			}
			return itemslist;

	}
	
	public ArrayList<InventoryItem> getInventoryArrayList(){
		return items;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return hasEnemy;
	}
	
	public String getNameOfEnemies(){
		String enemies = "";
		if(hasEnemy.isEmpty())
			enemies = "\n";
		for(Enemy e : hasEnemy){
			enemies = enemies + e.getEnemyName() + "\n";
		}
		return enemies;
	}
	
	public ArrayList<NPC> getNPCsInRoom(){
		return npcInRoom;
	}
	
	public void setHasEntered(boolean entered){
		this.hasEntered = entered;
	}
	
	public boolean getHasEntered(){
		return hasEntered;
	}
	
	public String getNameOfTrades(){
		String tradeList = "";
		for(NPC i: npcInRoom){
			for(InventoryItem item : i.getItems()){
				tradeList = tradeList + i.getName() + " desires " + i.getNeeds() + " for " + item.getName();
				tradeList = tradeList + "\n";
			}
		}
		return tradeList;
	}

	public String toString() {
		return "Name: " + name + "\n" + "Description" + description + "\n" + "canGoNorth: " + canGoNorth + 
				"\n " + "canGoSouth: " + canGoSouth + " \n" + "canGoEast: " + canGoEast + 
				"\n " + "canGoWest: " + canGoWest + "\n ";
				
	}
	
	
}