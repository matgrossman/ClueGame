/*
 * Authors: Mathew Grossman, Julian Reyes
 */

package clueGame;

import java.util.HashSet;
import java.util.Set;


public class BoardCell {
	private int row;
	private int col;


	private Set<BoardCell> adjList;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private char initial;
	private boolean isOccupied;
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		doorDirection = DoorDirection.NONE;
		roomLabel = false;
		roomCenter = false;
		isOccupied = false;
		adjList = new HashSet<BoardCell>();
	}
	
	public BoardCell() {
		super();
	}
	
	public void addAdjacency(BoardCell cell) {
		adjList.add(cell);
		return;
	}
	
	public Set<BoardCell> getAdjList(){
		return adjList;
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


	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public boolean isOccupied() {
		return this.isOccupied;
	}


}
