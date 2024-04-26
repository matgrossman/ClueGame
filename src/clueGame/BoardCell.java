/*
 * BoardCell: stores information for each cell of board, including location room, and location
 * Authors: Mathew Grossman, Julian Reyes
 */

package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private Board board = Board.getInstance();
	
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
	
	/*
	 * Draw function for each type of cell (Room, walkway, closet)
	 */
	public void draw(int squareSize,Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(1));
		switch(initial) {
		case 'W':
			g.setColor(Color.CYAN);
			g.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
			g.setColor(Color.BLACK);
			g.drawRect(col*squareSize, row*squareSize, squareSize, squareSize);
			break;
		case 'X' :
			g.setColor(Color.BLACK);
			g.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
			break;
		default: 
			g.setColor(Color.gray);
			g.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
			break;
		}
		
		g.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(3));
		switch(doorDirection) {
		case UP:
			g.drawLine(col*squareSize, row*squareSize-1,(col+1)*squareSize, row*squareSize-1);
			break;
		case DOWN:
			g.drawLine(col*squareSize, (row+1)*squareSize,(col+1)*squareSize, (row+1)*squareSize);
			break;
		case LEFT:
			g.drawLine(col*squareSize-1, row*squareSize,col*squareSize -1, (row+1)*squareSize);
			break;
		case RIGHT:
			g.drawLine((col+1)*squareSize, row*squareSize,(col+1)*squareSize, (row+1)*squareSize);
			break;
		default:
			break;
		}
		
		if(board.getTargets().contains(this) && board.isHumanTurn()) {
			g.setColor(Color.PINK);
			g.fillRect(col*squareSize, row*squareSize, squareSize, squareSize);
		}
		
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
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
