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
	private boolean isOccupied;
	public Set<TestBoardCell> adjList;
	
	public TestBoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		adjList = new HashSet<TestBoardCell>();
	}
	
	
	public void addAdjacency(TestBoardCell cell) {
		adjList.add(cell);
	}
	
	public Set<TestBoardCell> getAdjList(){
		return adjList;
	}
	
	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
	
	public boolean isRoom() {
		return isRoom;
	}
	
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}
	
	
}
