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
		doorDirection = doorDirection.NONE;
		roomLabel = false;
		roomCenter = false;
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


	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}


	public void setRoomLabel(boolean roomLabel) {
		this.roomLabel = roomLabel;
	}


	public void setRoomCenter(boolean roomCenter) {
		this.roomCenter = roomCenter;
	}


	public void setSecretPassage(char secretPassage) {
		this.secretPassage = secretPassage;
	}


	public boolean isDoorway() {
		if(doorDirection == DoorDirection.NONE) {
			return false;
		}
		return true;
	}


	public DoorDirection getDoorDirection() {
		return doorDirection;
	}


	public boolean isLabel() {
		return roomLabel;
	}


	public boolean isRoomCenter() {
		return roomCenter;
	}


	public char getSecretPassage() {
		return secretPassage;
	}
	
	public char getInitial() {
		return initial;
	}


	public void setInitial(char initial) {
		this.initial = initial;
	}


}
