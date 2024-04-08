/*
 * Authors: Mathew Grossman, Julian Reyes
 */
package clueGame;

import java.awt.Graphics;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;

	/** 
	 * Parameterized Constructor: Initializes Test Board with name, center cell, and label cell.
	 */	
	public Room(String name, BoardCell centerCell, BoardCell labelCell) {
		super();
		this.name = name;
		this.centerCell = centerCell;
		this.labelCell = labelCell;
	}
	
	/** 
	 * Default Constructor: Initializes a new room.
	 */	
	public Room() {
		super();
	}
	
	public void Draw(Graphics g) {
		g.drawString(name, labelCell.getRow(),labelCell.getCol());
	}
	
	/** 
	 *  setName: sets value to Room name.
	 */	
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	 *  setCenterCell: sets value to Room centerCell.
	 */	
	public void setCenterCell(BoardCell centerCell) {
		this.centerCell = centerCell;
	}
	
	/** 
	 *  setLabelCell: sets value to Room labelCell
	 */	
	public void setLabelCell(BoardCell labelCell) {
		this.labelCell = labelCell;
	}
	
	/** 
	 *  getName: gets value to Room name.
	 */	
	public String getName() {
		return name;
	}
	/** 
	 *  getLabelCell: gets value to Room labelCell
	 */	
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	/** 
	 *  getCenterCell: sets value to Room centerCell.
	 */	
	public BoardCell getCenterCell() {
		return centerCell;
	}



}
