public class Key extends InventoryItem{
	private Room room;
	private boolean north;
	private boolean east;
	private boolean south;
	private boolean west;

	
	public Key(String name, String description, Room room, boolean n, boolean e, boolean s, boolean w){
		super(name, description, 0.3);
		this.room = room;
		this.north = n;
		this.east = e;
		this.south = s;
		this.west = w;
	}
	
	
	public String getDescription(){
		return getDescription() + ""; 
	}
	
	
	public Room directionToOpen(){
		room.setCanGoNorth(north);
		room.setCanGoEast(east);
		room.setCanGoSouth(south);
		room.setCanGoWest(west);
		return room;
	}

	
	public Room getRoomIWorkIn(){
		return room;
	}
}


 


