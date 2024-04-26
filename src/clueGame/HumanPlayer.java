/*
 * HumanPlayer: 
 */
package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player{

	private Board board = Board.getInstance();

	public HumanPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}
	/*
	 * updateOccupancy: Updates occupied status for previous and next boardCells.
	 */
	public void updateOccupancy(BoardCell cell) {
		BoardCell preCell  =  board.getCell(getRow(), getCol());
		if (preCell.isRoomCenter()) {
			Room preRoom = board.getRoom(preCell);
			preRoom.removeOccupant(this);
		}
		else{
			preCell.setOccupied(false);
		}
		
		super.setRow(cell.getRow());
		super.setCol(cell.getCol());
		cell.setOccupied(true);
		if (cell.isRoomCenter()) {
			board.getRoom(cell).addOccupant(this);
		}
	}

	public Solution createSuggestion(Room room) {
		
		return null;
	}

	
	
}
