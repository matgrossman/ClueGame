package clueGame;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	
	public Room(String name, BoardCell centerCell, BoardCell labelCell) {
		super();
		this.name = name;
		this.centerCell = centerCell;
		this.labelCell = labelCell;
	}
	public Room() {
		super();
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public BoardCell getLabelCell() {
		BoardCell labler = new BoardCell(0, 0);
		return labler;
	}
	public BoardCell getCenterCell() {
		BoardCell labler = new BoardCell(0, 0);
		return labler;
	}
	
	

}
