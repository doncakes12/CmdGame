
public class Map extends InventoryItem{
	
	private int rowBegin;
	private int rowEnd;
	private int colBegin;
	private int colEnd;

	
	public Map(String name, String description, double weight, int rowBegin, int colBegin, int rowEnd, int colEnd){
		super(name,description,weight);
		this.rowBegin = rowBegin;
		this.colBegin = colBegin;
		this.rowEnd = rowEnd;
		this.colEnd = colEnd;
	}
	
	
	public int getRowBegin(){
		return rowBegin;
	}
	
	public int getRowEnd(){
		return rowEnd;
	}
	
	public int getColBegin(){
		return colBegin;
	}
	
	public int getColEnd(){
		return colEnd;
	}
	
}
