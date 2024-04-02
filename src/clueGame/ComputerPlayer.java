package clueGame;

import java.util.ArrayList;
import java.util.Collections;

public class ComputerPlayer extends Player{

	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}
	
	public Solution createSuggestion(Room room){
		Board board = Board.getInstance();
		ArrayList<Card> weaponList = board.getWeaponCards();
		ArrayList<Card> peopleList = board.getPeopleCards();
		ArrayList<Card> roomList = board.getRoomCards();
		
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
		Card roomCard = null;
		for (Card c: roomList) {
			if (room.getName().equals(c.getCardName())){
				roomCard = c;
			}
		}
		return new Solution(roomCard, person, weapon);
	}
	
	public BoardCell selectTarget() {
		return new BoardCell();			//PLACEHOLDER
	}

	@Override
	public Card disproveSuggestion(Solution suggestion) {
		return null;
	}

}
