/*
 * ComputerPlayer: Player extension that includes basic AI for computer players.
 * Authors: Mathew Grossma, Julian Reyes
 */
package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{


	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}
	
	/*
	 * createSuggestion: Makes suggestion when in room
	 * 
	 */
	public Solution createSuggestion(Room room){
		Board board = Board.getInstance();
		
		Card weapon = suggestWeapon(board);
		Card person = suggestPerson(board);
		Card roomCard = board.getCard(room.getName());

		return new Solution(roomCard, person, weapon);
	}
	
	/*
	 * suggestWeapon: chooses weapon card, prioritizing unseen.
	 */
	public Card suggestWeapon(Board board) {
		ArrayList<Card> weaponList = board.getWeaponCards();
		Card weapon = null;
		Collections.shuffle(weaponList);
		for (Card c: weaponList) {
			if (super.getSeenCards().contains(c) || super.getHand().contains(c)) {
				continue;
			}
			else {
				weapon = c;
				break;
			}
		}
		return weapon;
	}
	
	/*
	 * suggestPerson: suggestPerson: chooses person card, prioritizng unseen.
	 */
	public Card suggestPerson(Board board) {
		ArrayList<Card> peopleList = board.getPeopleCards();
		Card person = null;
		Collections.shuffle(peopleList);
		for (Card c: peopleList) {
			if (super.getSeenCards().contains(c)|| super.getHand().contains(c)) {
				continue;
			}
			else {
				person = c;
				break;
			}
		}
		return person;	
	}
	/*
	 * selectTarget: chooses target cell, prioritizing unseen rooms
	 */
	public BoardCell selectTarget(Set<BoardCell> targets) {
		if(targets.isEmpty()) {
			return board.getCell(getRow(), getCol());
		}
		
		
		Board board = Board.getInstance();
		BoardCell preCell  =  board.getCell(getRow(), getCol());
		if (preCell.isRoomCenter()) {
			Room preRoom = board.getRoom(preCell);
			preRoom.removeOccupant(this);
		}
		else{
			preCell.setOccupied(false);
		}
		
		for(BoardCell cell : targets) {
			if(cell.isRoomCenter()) {
				Card roomCard = board.getCard(board.getRoom(cell).getName());
				if(super.getSeenCards().contains(roomCard)) {
					continue;
				}
				else {
					board.getRoom(cell).addOccupant(this);
					cell.setOccupied(true);
					super.updateSeen(roomCard);
					this.setRow(cell.getRow());
					this.setCol(cell.getCol());
					return cell;
				}
			}
		}
		BoardCell[] cells = targets.toArray(new BoardCell[0]);
		
		Random rng = new Random();
		int randIdx = rng.nextInt(cells.length);
		
		BoardCell cell = cells[randIdx];
		
		this.setRow(cell.getRow());
		this.setCol(cell.getCol());
		cell.setOccupied(true);
		
		return cell;
	}



}
