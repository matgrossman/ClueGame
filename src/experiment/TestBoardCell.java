package experiment;

import java.util.Set;

public class TestBoardCell {
	private int row;
	private int col;
	private boolean isRoom;
	private boolean occupied;
	Set<TestBoardCell> AdjList;
	
	public TestBoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void addAdjacency(TestBoardCell cell) {
		AdjList.add(cell);
	}
	public Set<TestBoardCell> getAdjList(){
		return AdjList;
	}
	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
	public boolean isRoom() {
		return isRoom;
	}
	
	public void setOccupied(boolean isOccupied) {
		occupied = isOccupied;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
}
