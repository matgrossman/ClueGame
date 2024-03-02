package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
	private int row;
	private int col;
	private boolean isRoom;
	private boolean isOccupied;
	private Set<BoardCell> adjList;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private char initial;
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		adjList = new HashSet<BoardCell>();
	}
	
	
	public void addAdjacency(BoardCell cell) {
		adjList.add(cell);
	}
	
	public Set<BoardCell> getAdjList(){
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


	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return false;
	}


	public DoorDirection getDoorDirection() {
		// TODO Auto-generated method stub
		return doorDirection;
	}


	public boolean isLabel() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isRoomCenter() {
		// TODO Auto-generated method stub
		return false;
	}


	public char getSecretPassage() {
		// TODO Auto-generated method stub
		return 0;
	}
}
