package experiment;

/*
 * TestBoardCell: Stores data for individual cell in TestBoard
 * 
 * Authors: Mathew Grossman, Julian Reyes
 */
import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	private int row;
	private int col;
	private boolean isRoom;
	private boolean occupied;
	Set<TestBoardCell> adjList;
	
	public TestBoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void addAdjacency(TestBoardCell cell) {
		adjList.add(cell);
	}
	public Set<TestBoardCell> getAdjList(){
		adjList = new HashSet <TestBoardCell>();
		return adjList;
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
