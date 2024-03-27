import java.util.*;

public class Commands{
	
	private static Room[][] Map = new Room[16][9];
	private static int row;
	private static int column;
	private static Room currentRoom;
	private static ArrayList<InventoryItem> inventoryPlayer;
	private static double health = 100;
	private static Food foodItem;
	private static Weapon equippedWeapon = new Weapon();
	private static Armor equippedArmor = new Armor();
	private static Map mapItem;
	private static double carryingCapacity = 75;
	
	public static void main(String[] args) {
		runGame();
	}	

//------------------------------------------------------------------------------------------------------------------
	public static String gameIntro(){
		String intro = "You wake up in an unknown location in Japan, and vaguely remember an extremely important secret mission that you must complete.\n" 
				+ "You must escape from here to learn more and I will show you then. Get some items and make your way to the Courtyard.";
		
		return intro;
	}

//------------------------------------------------------------------------------------------------------------------	
	public static String getFirstWord(String command){
		
		int indexOfCommand = command.indexOf(' ');
		
		if(indexOfCommand > 0){
			String firstWord = command.substring(0, indexOfCommand);
			return firstWord;
		
		} else{
			
		return command;
		}
	}
//------------------------------------------------------------------------------------------------------------------
	public static String getRestOfSentence(String fullPhrase){
		String phrase = fullPhrase;
		
		if(phrase.indexOf(" ") > 0){		
			phrase = phrase.substring((phrase.indexOf(' ') + 1));
		
		return phrase;
		
		}else{
			return "";
		}
	}
	
//------------------------------------------------------------------------------------------------------------------	
	public static String north(){
		if(currentRoom.getCanGoNorth() == true){
		row = row - 1;
		updateRoom();
		return "You moved north.\n" + getcurrentRoom();
		} else{
			return "You can not walk that way.";
		}
		
	}

//------------------------------------------------------------------------------------------------------------------
	public static String south(){
		if(currentRoom.getCanGoSouth() == true){
			row = row + 1;
			updateRoom();
			return "You moved south.\n" + getcurrentRoom();
			} else{
				return "You can not walk that way.";
			}
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String east(){
		if(currentRoom.getCanGoEast() == true){
			column = column + 1;
			updateRoom();
			return "You moved east.\n" + getcurrentRoom();
			} else{
				return "You can not walk that way.";
			}
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String west(){
		if(currentRoom.getCanGoWest() == true){
			column = column - 1;
			updateRoom();
			return "You moved west.\n" + getcurrentRoom();
			} else{
				return "You can not walk that way.";
			}
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String take(String itemName){
		for(InventoryItem i : currentRoom.getInventoryArrayList()){
			if(itemName.equalsIgnoreCase(i.getName())){
				inventoryPlayer.add(i);
				currentRoom.getInventoryArrayList().remove(i);
				return "You took the " + i.getName() + ".";
			}
		}
		return "You could not find it";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String drop(String itemName){
		for(InventoryItem i : inventoryPlayer){
			if(itemName.equalsIgnoreCase(i.getName())){
				inventoryPlayer.remove(i);
				currentRoom.getInventoryArrayList().add(i);
				return "You dropped the " + i.getName() + ".";
			}
		}
		return "You dont't have this item";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String look(){
		return getcurrentRoom() + ", You looked around the room. " + currentRoom.getDescription() 
		+ "\nThese items are around you: " + currentRoom.getNameOfObjectsInRoom()
		+ "These are the enemies around you: " + currentRoom.getNameOfEnemies()
		+ "These are the trades around you: " + currentRoom.getNameOfTrades();
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String inventory(){
		double invWeight = 0;
		String answer = "The current items in your inventory are: \n";
		for(InventoryItem i : inventoryPlayer){
			answer += i.getName() + "\n";
			invWeight += i.getWeight();
		}
		return "Carrying Capacity: " + invWeight + "/" + carryingCapacity + "\n" + answer;
	}

//------------------------------------------------------------------------------------------------------------------
	public static String examine(String itemName){
		for(InventoryItem i : inventoryPlayer){
			if(itemName.equalsIgnoreCase(i.getName())){
				return "Description of " + itemName + ".\n" + i.getDescripition();
			}
		}	
		return "You dont't have this item";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String eat(String itemName){
		for(InventoryItem i : inventoryPlayer){
			if(itemName.equalsIgnoreCase(i.getName())){
				if(i instanceof Food){
					foodItem = (Food) i;
					if(health + foodItem.getHealth() >= 100){
						health = 100;
					} else{
						health = health + foodItem.getHealth();
					}
					inventoryPlayer.remove(i);
					return "You ate the " + i.getName();
				}
			}
		}
		return "You can not do that";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String use(String itemName){
		for(InventoryItem i : inventoryPlayer){
			if(itemName.equalsIgnoreCase(i.getName())){
				if(i instanceof Key){
					Key k = (Key)i;
					if(currentRoom.equals(k.getRoomIWorkIn())){
						k.directionToOpen();
						inventoryPlayer.remove(i);
						updateRoom();
						return "You used the " + itemName + ".";
					}
					return "You are not in the correct room";
				} else if(i instanceof Map){
					mapItem = (Map)i;
					return printMap();
				}
				return "This is not a key or map.";
			}
		}
		return "You don't have this item";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String help(){
		return    "Command		Example		Description \n"
				+ "  North		north		Move in a direction\n"
				+ "  South		south  		Move in a direction\n"
				+ "  East		east		Move in a direction\n"
				+ "  West		west		Move in a direction\n"
				+ "  Take {item}		take katana		You took the katana\n"
				+ "  Drop {item}		drop katana		You dropped the katana\n"
				+ "  Look		look		You looked around the room\n"
				+ "  Inventory		inventory		Displays items in inventory\n"
				+ "  Examine {item}	examine inu		Description of inu\n"
				+ "  Eat {item}		eat mochi		You ate mochi\n"
				+ "  Use {item		use key		You used the key\n"
				+ "  Quit		quit		Used to quit the game\n"
				+ "  Help		help		Help with commands\n"
				+ "  Health		health		Displays health\n"
				+ "  Fight 		fight 		fight enemies\n"
				+ "  Equip {Weapon/Armor}	equip katana		You equipped the katana\n";
	}
		
//------------------------------------------------------------------------------------------------------------------
	public static String quit(){
		
		return "You have decided to quit the game.";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String invalid(){
		return "I do not understand";
	}
	
//------------------------------------------------------------------------------------------------------------------	
	public static String executeCommand(String command, String itemName){
		String comm = command.toLowerCase();
		if(comm.equals("north")){
			return north();
		} else if(comm .equals("south")){
			return south();
		} else if(comm .equals("east")){
			return east();
		} else if(comm .equals("west")){
			return west();
		} else if(comm .equals("take")){
			return take(itemName);
		} else if(comm .equals("drop")){
			return drop(itemName);
		} else if(comm .equals("look")){
			return look();
		} else if(comm .equals("inventory")){
			return inventory();
		} else if(comm .equals("examine")){
			return examine(itemName);
		} else if(comm .equals("eat")){
			return eat(itemName);
		} else if(comm .equals("use")){
			return use(itemName);
		} else if(comm .equals("help")){
			return help();
		} else if(comm .equals("quit")){
			return quit();
		} else if(comm .equals("health")){
			return health();
		} else if(comm .equals("equip")){
			return equip(itemName); 
		} else if(comm .equals("fight")){
			return fight(); 
		}else if(comm .equals("trade")){
			return trade(itemName); 
		}else{
			return invalid();
		}
		
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static Room updateRoom(){
		currentRoom = Map[row][column];
		currentRoom.setHasEntered(true);
		return currentRoom;
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String getcurrentRoom(){
		return currentRoom.getName();
	}

//------------------------------------------------------------------------------------------------------------------	
	//close to finishing
	public static void initializeMap(){	

		inventoryPlayer = new ArrayList<InventoryItem>();
		
		//row 0
		Room ItachiMainStreet = new Room("Itachi Street", false, true, true, false, "You have arrived in the lively streets of Itachi. You can go east or south.");
		Room ItachiBackAlley = new Room("Itachi Alley", true, false, true, false, "There is a stench of rotting garbage and you realize you walked into a back alley of Itachi. You can go north or south.");
		Room ItachiOutskirts = new Room("Itachi Outskirts", true, false, true, false, "You have reached the edge of Itachi, and there is some structure south of here. You can goe north or south.");
		Room AbandonedCottage = new Room("Empty Cottage", true, true, false, false, "The structure seems to be an empty cottage. behind it seems to be a strange door. You can go north or south.");
		Room HiddenStaircase = new Room("Hidden Stairs", true, true, false, false, "Behind the door is a hidden staircase and there is a weird noise at the bottom. You can go east or north.");
		//row 1
		Room GateToItachi = new Room("Gate of Itachi", false, true, false, true, "You have reached the Gate of the city of Itachi. You can go east or west.");
		Room GreatDivide = new Room("Great Divide", false, true, true, false, "You have found a massive river blocking you pass. You can go south and east.");
		Room DesolateValley = new Room("Empty Valley", true, true, false, false, "You have entered an empty valley. You can go north or east.");
		Room HiddenCave = new Room("Hidden Cave", false, false, false, true, "You have slipped into a hidden cave. You can only go west.");
		Room YakuzaHideout = new Room("Yakuza Hideout", false, false, false, true, "You have stumbled upon a Yakuza Hideout. You can only go west.");
		//row 2
		Room CautiousCliff = new Room("Cautious Cliff", false, false, true, true, "You find sheer cliffs and fear that you may plumit to your death. You can go west or south.");
		Room CreekyBridge = new Room("Creeky Bridge", true, false, false, true, "The old swaying bridge creeks each step you take. You can go west or north.");
		Room WesternEntranceOfZou = new Room("West of Zou", false, true, false, true, "You are west of Zou, and can move east or west.");
		//row 3
		Room Bedroom = new Room("Bedroom", false, true, true, false, "You wake up and find yourself in a room with a message on the wall saying kill the Shogun. \nSurrounded with two exits to the south and east.");
		Room WestHall = new Room("West Hall", true, true, true, false, "This hall connects the Bedroom and the Dojo. You can go north, south, or east.");
		Room Dojo = new Room("Dojo", true, true, false, false, "Although it is clean you can see the remnants of blood the wall saying kill the Shogun. You can go north or east.");
		Room NorthWesternZou = new Room("NW Zou", false, true, true, false, "You have reached a corner of the city. You can go east or south.");
		Room WesternZou = new Room("Western Zou", true, true, true, true, "You are in Western Zou, and can move any direction.");
		Room SouthWesternZou = new Room("SW Zou", true, true, false, false, "You have reached a corner of Zou. You can move north or east.");
		Room SecretGrove = new Room("Secret Grove", false, true, false, false, "You have reached a seceret Grove! You can move east.");
		Room KoiPond = new Room("Koi Pond", false, true, true, false, "A beautiful koi pond surrounds the palace. You can go south or East.");
		Room OniPalace = new Room("Oni Palace", true, true, false, false, "Inside a massive hall and can feel the shogun in the next room. You can go north or east.");
		//row 4
		Room NorthHall = new Room("North Hall", false, true, true, true, "This hall connects the Sun Room and the Bedroom. You can go west or east.");
		Room Courtyard = new Room("Courtyard", true, true, true, true, "There is a beautiful courtyard surrounded by the home.");		
		Room SouthHall = new Room("South Hall", true, true, false, true, "This hall connects the Kitchen and the dojo. You can go west or east.");
		Room PathToHouse = new Room("Path to house", true, false, true, false, "You have come to the front of the house, there is a road to the south.");
		Room PathToZou = new Room("Path to Zou", true, true, true, false, "This path connects a home to the town of Zou.");
		Room NorthEntranceOfZou = new Room("North of Zou", true, false, true, false, "You arrive at an entrance of Zou. You can go north or south.");
		Room NorthernZou = new Room("Northern Zou", true, true, true, true, "You arrive at an entrance of Zou. You can go north or south.");
		Room HeartOfZou = new Room("Heart of Zou", true, true, true, true, "You arrive at the Heart of Zou. You can go north, east, south, or west.");
		Room SouthernZou = new Room("Southern Zou", true, true, true, true, "You have reached the southern end of Zou. You can go any direction.");
		Room SouthEntranceOfZou = new Room("South of Zou", true, false, true, false, "You are south of Zou reaching the southern gate. You can go north or south.");
		Room SouthernForest = new Room("South Forest", true, false, false, false, "You are in a dark forest south of Zou. You can go north and south.");
		Room OniEntrance = new Room("Oni Entrance", true, false, true, false, "You have reached the entrance to Oni. You can go north or south.");
		Room OniForest = new Room("Oni Forest", true, true, false, false, "This thick dark forest can be disorieenting. You can go north and east.");
		Room PalaceGate = new Room("Palace Gate", false, true, false, false, "The grand palace gleams in the sun, but if fogged by something sinister. You can go east or west.");
		Room ShogunRoom = new Room("Shogun Room", false, false, false, true, "The room is empty and lacking any sort of life. You can only go west.");
		//row 5
		Room Study = new Room("Study", false, false, true, true, "This room faces the Koi pond with light glimmering in. There are notebooks and pens scatered. You can go west or south.");
		Room EastHall = new Room("East Hall", true, false, true, true, "This hall connects the Sun Room and Kitchen. You can go north or south.");
		Room HiddenLake = new Room("Hidden Lake", false, false, false, true, "You have found a hidden Lake. You can only head west.");
		Room Kitchen = new Room("Kitchen", true, false, false, true, "You see a well kept kitchen. Everything is organized and neatly kept. You can go north and west.");
		Room NorthEasternZou = new Room("NE Zou", false, false, true, true, "You have reached a corner of the city. You can go west or south.");
		Room EasternZou = new Room("Eastern Zou", true, true, true, true, "You are currently in Eastern Zou. You can go any direction.");
		Room SouthEasternZou = new Room("SE Zou", true, false, false, true, "You have reached another corner of Zou. You can go north or west.");
		Room OniStream = new Room("Stream of Oni", false, false, true, true, "You have hit the stream that borders Oni. You can go west or south.");
		Room OniStairs = new Room("Stairs of Oni", true, false, false, true, "You have reached the stairs leading up to the palace. You can go west or north.");
		//row 6
		Room CaptainOffice = new Room("Captain Office", false , true, false, false, "Inside the office of the captain there is an ominous pressence. You can only go east.");
		Room EasternEntranceOfZou = new Room("East of Zou", false, true, false, true, "East of Zou you feel a powerful presence. You can go east or west.");
		//row 7
		Room PoliceStation = new Room("Kiesatsusho", false, true, true, false, "At the police station there are many people working and being busy. You can go east, west, or south.");
		Room NorthUshi = new Room("North Ushi", true, false, false, false, "In northern Ushi there are fruit stands and people shopping. You can only go north.");
		Room UshiEntrance = new Room("Ushi Entrance", false, false, true, true, "At the entrance of Ushi you feel that the shogun has been here. You can go west or south.");
		Room SouthUshi = new Room("South Ushi", true, true, false, false, "You have reached the south end of Ushi. From here you can only move north or east.");
		//row 8
		Room UshiBar = new Room("Ushi Bar", false, false, true, true, "You walk to the outside of the most popular bar of Ushi. You can go south or west.");
		Room UshiStreet = new Room("Ushi Street", true, false, true, false, "The birds fly above as you walk down the famous street of Ushi. You can go north or south.");
		Room UshiTower = new Room("Ushi Tower", true, false, true, false, "You have reached a tall tower that disconnects the city. You can go north or south.");
		Room UshiCafe = new Room("Ushi Cafe", true, false, false, true, "The popular cafe in Ushi has drawn you and other to it. You can go west or north.");
		
		for(int r = 0; r < Map.length; r++){
			for(int c = 0; c < Map[r].length; c++){
				Map[r][c] = new Room();
			}
		}
		

		 																								Map[1][3]= Bedroom;			Map[1][4]= NorthHall;				Map[1][5]= Study;
		 																								Map[2][3]= WestHall;		Map[2][4]= Courtyard;				Map[2][5]= EastHall;
		 																								Map[3][3]= Dojo;			Map[3][4]= SouthHall;				Map[3][5]= Kitchen;
		 																															Map[4][4]= PathToHouse;			 
		 																															Map[5][4]= PathToZou;				Map[5][5]= HiddenLake;
		 Map[6][0]= ItachiMainStreet;	Map[6][1]= GateToItachi;	Map[6][2]= CautiousCliff;										Map[6][4]= NorthEntranceOfZou;										Map[6][6]= CaptainOffice;			Map[6][7]= PoliceStation;	Map[6][8]= UshiBar;
		 Map[7][0]= ItachiBackAlley;	Map[7][1]= GreatDivide;		Map[7][2]= CreekyBridge;			Map[7][3]= NorthWesternZou;	Map[7][4]= NorthernZou;				Map[7][5]= NorthEasternZou;											Map[7][7]= NorthUshi;		Map[7][8]= UshiStreet;
		 Map[8][0]= ItachiOutskirts;	Map[8][1]= DesolateValley;	Map[8][2]= WesternEntranceOfZou;	Map[8][3]= WesternZou;		Map[8][4]= HeartOfZou;				Map[8][5]= EasternZou;			Map[8][6]= EasternEntranceOfZou;	Map[8][7]= UshiEntrance; 	Map[8][8]= UshiTower;
		 Map[9][0]= AbandonedCottage;	Map[9][1]= HiddenCave;											Map[9][3]= SouthWesternZou; Map[9][4]= SouthernZou;				Map[9][5]= SouthEasternZou;											Map[9][7]= SouthUshi;		Map[9][8]= UshiCafe;
		 Map[10][0]= HiddenStaircase;	Map[10][1]= YakuzaHideout;																	Map[10][4]= SouthEntranceOfZou;
																										Map[11][3]= SecretGrove;	Map[11][4]= SouthernForest;
																																	Map[12][4]= OniEntrance;
																																	Map[13][4]= OniForest;				Map[13][5]= OniStream;
																										Map[14][3]= KoiPond;		Map[14][4]= PalaceGate;				Map[14][5]= OniStairs;
																										Map[15][3]= OniPalace;		Map[15][4]= ShogunRoom;
																										
		row = 1;
		column = 3;
		currentRoom = Map[row][column];
		
		
		
		Food mochi = new Food("Mochi", "A warm sticky rice-cake.", 3, 2);
		Food restoreHealth1 = new Food("Restorative Potion","Congrats on defeating this special enemy!", 5, 100);
		Food restoreHealth2 = new Food("Restorative Potion","Congrats on defeating this special enemy!", 5, 100);
		Food restoreHealth0 = new Food("Restorative Potion","Congrats on finding this secret Trade!", 5, 100);
		Food apple = new Food("Apple", "A sweet, juicy, red apple.", 3, 5);
		Food kibiDango = new Food("Kibi Dango", "A stick sweet desert that is delicious while warm.", 3, 10);
		Food matcha = new Food("Matcha", "A hot cup of matcha to cleanse oneself.", 5, 50);
		Food coffee = new Food("Coffee", "A hot cup of rich coffee.", 5, 20);
		Food donut = new Food("Donut", "A warm delicious donut.", 3, 10);
		Food niku = new Food("Niku", "A warm chunk of meat.", 5, 70);
		Food sake = new Food("Sake", "Sweet japanese sake only found in the most elite bars.", 5, 20);
		
		Enemy ronin1 = new Enemy("Ronin Ichiji","Ronin who specializes in one sword combat. He has the key to the house!", 25, 5);
		Enemy ronin2 = new Enemy("Ronin Tokage","A ronin who specializes in camoflauge. He holds the map of Zou", 40, 7.5);
		Enemy ronin3 = new Enemy("Ronin Fujiuji", "A ronin who specializes in kendo.", 50, 10);
		Enemy yakuzaThug = new Enemy("Yakuza Thug", "A yakuza thug who seems extremely skilled with a knife.", 75, 20);
		Enemy yakuzaBoss = new Enemy("Yakuza Boss", "The boss of the yakuza aggressively stares at you.", 100, 30);
		Enemy ronin4 = new Enemy("Ronin Zenitsu", "A bashful ronin who has a mysteriously dangerous presence to him", 100, 50);
		Enemy keisatsuCommander = new Enemy("Kiesatsu Commander", "The commander of the kiesatsu is a skilled officer of the law.", 150, 150);
		Enemy ronin5 = new Enemy("Ronin hachin", "The last ronin sent to kill you, and is by far the most dangerous.", 200, 200);
		Enemy shogunOrochi = new Enemy("Shogun Orochi", "The meanicing presence of Orochi radiates around him.", 500, 500);
		
		Weapon shabbyKatana = new Weapon("Shabby Katana", "A weak katana that has many nicks in it.", 5, 5);
		Weapon rustyKatana = new Weapon("Rusty Katana", "A rusty katana that is still sharp", 5, 10);
		Weapon regularKatana = new Weapon("Regular Katana", "This is a standard katana", 10, 20);
		Weapon kiestsu = new Weapon("Kiestsu", "This powerful blade radiates agony.", 15, 50);
		
		Key houseKey = new Key("House Key", "It unlocks the South Hall.", SouthHall, true, true, true, true);
		Key cottageKey = new Key("Cottage Key","It unlocks someone's cottage, but who's?", AbandonedCottage, true, true, true, false);
		Key policeKey = new Key("Police Key", "It unlocks the Police station.", PoliceStation, false, true, true, true);
		Key oniKey = new Key("Oni Key", "It unlocks the path to Oni", SouthernForest, true, false, true, true);
		Key shogunKey = new Key("Shogun Key", "It unlocks the palace.", PalaceGate, false, true, false, true);
		
		InventoryItem deathNote = new InventoryItem("Death Note", "It seems to be desired by someone", 1);
		InventoryItem nailClipper = new InventoryItem("Nail clipper", "A nail clipper in the shape of a bowling-pin. I wonder if anybody wants this.", .5);
		InventoryItem hand = new InventoryItem("Hand", "this chopped off hand must have come from someone.", 5);
		InventoryItem berries = new InventoryItem("Berries", "This currency is extremely rare, and seems to be from pirates.", 3);
		InventoryItem shogiPiece = new InventoryItem("Shogi piece", "It is a piece from shogi.", .5);
		InventoryItem treasureMap = new InventoryItem("Treasure Map", "It is a treasure map of some far away island.", 1);
		InventoryItem bara = new InventoryItem("Bara Bara no mi", "A strange fruit from far away.", 5);
		
		Armor leatherArmor = new Armor("Leather Armor", "A weak worn leather armor.", 5, 2);
		Armor copperArmor = new Armor("Copper Armor", "A heavy protective armor.", 7.5, 4);
		Armor platedArmor = new Armor("Plated Armor", "Armor made of thick slabs of material.", 15, 25);
		Armor karuta = new Armor("Karuta", "This heavy armor blesses the wearer with extreme defense.", 25, 50);
		

		Map map1 = new Map("Map of house", "It is a map of the house.", 1, 1, 3, 4, 5);
		Map map2 = new Map("Map of Zou", "It is a map of Zou.", 1, 7, 3, 9, 5);
		Map map3 = new Map("Map of Itachi", "It is a map of Itachi.", 1, 6, 0, 10, 2);
		Map map4 = new Map("Map of Ushi", "It is a map of Ushi.", 1, 6, 6, 9, 8);
		Map map5 = new Map("Map of Oni","It is a map of Oni.", 1, 12, 3, 15, 5);

		NPC Shanks = new NPC("Shanks", "Treasure Map");
		NPC Ryuk = new NPC("Ryuk", "Apple");
		NPC LightY = new NPC("Light Y.", "Death Note");
		NPC KiraY = new NPC("Kira Y.", "Nail Clipper");
		NPC Shikamaru = new NPC("Shikamaru", "Shogi piece");
		NPC Buggy = new NPC("Buggy", "Bara Bara no mi");
		NPC Queen = new NPC("Queen", "Berries");
		NPC Katekuri = new NPC("Katekuri", "Donut");
		NPC Zoro = new NPC("Zoro R.", "Sake");
		NPC Ban = new NPC("Ban", "Berries");
		NPC Oni = new NPC("Oni","Hand");
		NPC Kaido = new NPC("Kaido", "Sake");
	
		
//Items in rooms
		Kitchen.getInventoryArrayList().add(mochi);
		Dojo.getInventoryArrayList().add(shabbyKatana);
		Bedroom.getInventoryArrayList().add(map1);
		Dojo.getInventoryArrayList().add(leatherArmor);
		Study.getInventoryArrayList().add(nailClipper);
		PathToHouse.getInventoryArrayList().add(copperArmor);
		PathToHouse.getInventoryArrayList().add(rustyKatana);
		EasternZou.getInventoryArrayList().add(donut);
		GateToItachi.getInventoryArrayList().add(cottageKey);
		AbandonedCottage.getInventoryArrayList().add(matcha);
		NorthUshi.getInventoryArrayList().add(apple);
		UshiBar.getInventoryArrayList().add(shogiPiece);
		UshiTower.getInventoryArrayList().add(bara);
		UshiEntrance.getInventoryArrayList().add(policeKey);
//Enemies to rooms		
		Courtyard.getEnemies().add(ronin1);
		HeartOfZou.getEnemies().add(ronin2);
		CreekyBridge.getEnemies().add(ronin3);
		ItachiBackAlley.getEnemies().add(yakuzaThug);
		YakuzaHideout.getEnemies().add(yakuzaBoss);
		SouthUshi.getEnemies().add(ronin4);
		CaptainOffice.getEnemies().add(keisatsuCommander);
		OniForest.getEnemies().add(ronin5);
		ShogunRoom.getEnemies().add(shogunOrochi);
//Items to Enemies	
		ronin1.getArrayList().add(restoreHealth1);
		ronin1.getArrayList().add(houseKey);
		ronin2.getArrayList().add(map2);
		ronin3.getArrayList().add(berries);
		ronin3.getArrayList().add(map3);
		keisatsuCommander.getArrayList().add(oniKey);
		keisatsuCommander.getArrayList().add(restoreHealth2);
		ronin4.getArrayList().add(map4);
		yakuzaBoss.getArrayList().add(platedArmor);
		yakuzaBoss.getArrayList().add(regularKatana);
//NPC in rooms		
		NorthEasternZou.getNPCsInRoom().add(Ryuk);
		NorthWesternZou.getNPCsInRoom().add(KiraY);
		SouthWesternZou.getNPCsInRoom().add(Buggy);
		SouthEasternZou.getNPCsInRoom().add(Shikamaru);
		ItachiMainStreet.getNPCsInRoom().add(Queen);
		HiddenLake.getNPCsInRoom().add(Shanks);
		HiddenCave.getNPCsInRoom().add(Katekuri);
		UshiCafe.getNPCsInRoom().add(Zoro);
		UshiStreet.getNPCsInRoom().add(Ban);
		PalaceGate.getNPCsInRoom().add(Oni);
		OniPalace.getNPCsInRoom().add(Kaido);
//Trades for NPCS		
		Ryuk.getItems().add(deathNote);
		KiraY.getItems().add(hand);
		LightY.getItems().add(map5);
		Shanks.getItems().add(karuta);
		Buggy.getItems().add(treasureMap);
		Queen.getItems().add(kibiDango);
		Oni.getItems().add(shogunKey);
		Katekuri.getItems().add(restoreHealth0);
		Zoro.getItems().add(kiestsu);
		Ban.getItems().add(coffee);
		Kaido.getItems().add(niku);
		Shikamaru.getItems().add(sake);
		
	}
	
//------------------------------------------------------------------------------------------------------------------	
	public static String printMap(){
		String mapLayout = "";
		String betweenRoom = "| ";
			for(int rows = mapItem.getRowBegin(); rows <= mapItem.getRowEnd(); rows++){
				for(int columns = mapItem.getColBegin(); columns <= mapItem.getColEnd(); columns++){
					if(Map[rows][columns].getHasEntered()){
						mapLayout = mapLayout + betweenRoom + Map[rows][columns].getName() + "\t";
					} else {
						mapLayout = mapLayout + betweenRoom + "\t";
					}
				}
					mapLayout += betweenRoom + "\n"; 
			}
			return mapLayout;
	}
//------------------------------------------------------------------------------------------------------------------

	public static double getHealth(){
		return health;
	}
//------------------------------------------------------------------------------------------------------------------
	public static String health(){
		return "Health is: " + health + "/100";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String updateHealth(){
		if(getHealth() <= 0){
			return "You have died.";
		}
		return "";
	}
	
//------------------------------------------------------------------------------------------------------------------	
	public static String equip(String w){
		for(InventoryItem i : inventoryPlayer){
			if(w.equalsIgnoreCase(i.getName())){
				if(i instanceof Weapon){
				equippedWeapon = (Weapon)i;
				return i.getName() + " has been equipped.";
				
				} else if(i instanceof Armor){
					equippedArmor = (Armor)i;
					return i.getName() + " has been equipped.";
				}
			}

		}
		return "I can not do that";
	}
	
//------------------------------------------------------------------------------------------------------------------	
	public static String fight(){
		if(currentRoom.getEnemies().isEmpty()){
			return "There is no enemies around.";
		} 
		else{
			for(Enemy e : currentRoom.getEnemies()){
				int i = (int)(Math.random() * 4);
					if(i == 0){
						e.setEnemyHealth(e.getEnemyHealth() - equippedWeapon.getDamage());
						return "You struck " + e.getEnemyName() + ". " + updateEnemyHealth() + "His health is " + e.getEnemyHealth() +" and your health is " + getHealth();
					} 
					else if(i == 1){
						e.setEnemyHealth(e.getEnemyHealth() - equippedWeapon.getDamage());
						health = (getHealth() - (e.getEnemyDamage() / equippedArmor.getArmorValue() ) );
						return "You struck " + e.getEnemyName() + ", but it also struck you. "+ updateEnemyHealth() +"His health is " + e.getEnemyHealth() +" and your health is " + getHealth() + " " + updateHealth();		
					} 
					else if(i == 2){
						health = (getHealth() - (e.getEnemyDamage() / equippedArmor.getArmorValue() ) );
						return e.getEnemyName() + " has struck you instead. " + updateEnemyHealth() + "His health is " + e.getEnemyHealth() +" and your health is " + getHealth() + " " + updateHealth();
					} 
					else {
						return "Both you and the enemy have missed. His health is " + e.getEnemyHealth() +" and your health is " + getHealth();
					}
					
			}
		}
		return "";
	}
	
//------------------------------------------------------------------------------------------------------------------
	public static String updateEnemyHealth(){
		if(currentRoom.getEnemies().isEmpty()){
			return "There are no enemies around";
		
		} else {
			for(Enemy e : currentRoom.getEnemies()){
				if(e.getEnemyHealth() <= 0){
					for(InventoryItem i : e.getArrayList()){
						currentRoom.getInventoryArrayList().add(i);
					}
					currentRoom.getEnemies().remove(e);
					return e.getEnemyName() + " has died. ";
				}
			}
		}
		return "";
	}
//------------------------------------------------------------------------------------------------------------------
	public static String trade(String itemName){
		if(currentRoom.getNPCsInRoom().isEmpty()){
			return "There is no one to trade with.";
		} else{
			for(NPC n: currentRoom.getNPCsInRoom()){
				for(InventoryItem i : inventoryPlayer){
					if(i.getName().equalsIgnoreCase(itemName)){
						inventoryPlayer.remove(i);
						for(InventoryItem r: n.getItems()){
							n.getItems().remove(r);
							inventoryPlayer.add(r);
							currentRoom.getNPCsInRoom().remove(n);
							return "You traded " + itemName + " for " + r.getName();
						}
							
					}
				}
				return n.getName() + " does not need this.";
			}
		}
	return "There is no one to trade with";
	}
//------------------------------------------------------------------------------------------------------------------
	public static Room getRoom(int row, int col){
		return Map[row][col];
	}
//------------------------------------------------------------------------------------------------------------------	
	public static void runGame(){
		
		initializeMap();
		Scanner input = new Scanner(System.in);
		System.out.println(gameIntro());
		boolean running = true;
		
		while(running){		
			String totalInput = input.nextLine();
			String command = "";
			String item = "";
			if(totalInput.indexOf(' ') > 1){
				command = getFirstWord(totalInput);
				item = getRestOfSentence(totalInput);
				/* or you can do this
				 * command = totalInput.substring(0, totalInput.indexOf(' '));
				 * item = totalInput.substring(totalInput.indexOf(' ') + 1, totalInput.length());
				 */
			} else{
				command = totalInput;
				updateRoom();
			}

			System.out.println(executeCommand(command, item));
		
			if(totalInput.equals("quit")){
				running = false;
			} else if(health <= 0){
				running = false;
			} else if(Map[15][4].getEnemies().isEmpty()){
				System.out.println("The shogun is dead! You have won!");
				running = false;
			}	else {
				System.out.println("Another input?");
			}
		}
		input.close();

	}


}