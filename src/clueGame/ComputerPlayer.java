package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{

	public ComputerPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}
	
	public Solution createSuggestion(Room room){
		Board board = Board.getInstance();
		ArrayList<Card> roomList = board.getRoomCards();
		
		Card weapon = suggestWeapon(board);
		Card person = suggestPerson(board);

		Card roomCard = null;
		for (Card c: roomList) {
			if (room.getName().equals(c.getCardName())){
				roomCard = c;
			}
		}
		return new Solution(roomCard, person, weapon);
	}
	
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
			}
		}
		return weapon;
	}
	
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
			}
		}
		return person;	
	}
	
	public BoardCell selectTarget(Set<BoardCell> targets) {
		Board board = Board.getInstance();
		for(BoardCell cell : targets) {
			if(cell.isRoomCenter()) {
				Card roomCard = board.getCard(board.getRoom(cell).getName());
				if(super.getSeenCards().contains(roomCard)) {
					continue;
				}
				else {
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
		
		return cell;
	}



}
