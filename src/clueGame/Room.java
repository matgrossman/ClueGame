/*
 * Authors: Mathew Grossman, Julian Reyes
 */
package clueGame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	private ArrayList<Player> occupants;

	/** 
	 * Parameterized Constructor: Initializes Test Board with name, center cell, and label cell.
	 */	
	public Room(String name, BoardCell centerCell, BoardCell labelCell) {
		super();
		this.name = name;
		this.centerCell = centerCell;
		this.labelCell = labelCell;
		this.occupants = new ArrayList<Player>();
	}
	
	/** 
	 * Default Constructor: Initializes a new room.
	 */	
	public Room() {
		super();
		this.occupants = new ArrayList<Player>();
	}
	/*
	 * Draw: creates room name text
	 */
	public void draw(Graphics g, int size) {
		Graphics2D g2 = (Graphics2D) g;
		if(labelCell == null) {
			return;
		}
		g2.setFont(new Font("Georgia", Font.PLAIN, size/2));
		g2.getFontMetrics().getStringBounds(name, g2).getWidth();
		g.drawString(name, labelCell.getCol()*size,labelCell.getRow()*size);
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
	
	public ArrayList<Player> getOccupants() {
		return occupants;
	}
	
	public void removeOccupant(Player p) {
		occupants.remove(p);
		if(occupants.isEmpty()) {
			centerCell.setOccupied(false);
		}
		return;
	}
	
	public void addOccupant(Player p) {
		occupants.add(p);
		return;
	}




}
