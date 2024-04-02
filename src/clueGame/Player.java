package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private int row;
	private int col;
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	private Set<Card> seenCards;
	
	public Player(String name, String color, int row, int col) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		seenCards = new HashSet<Card>();
	}
	

	//	Getters for testing purposes
	public void updateHand(Card card) {
		hand.add(card);
		return;
	}
	
	public void updateSeen(Card seenCard) {
		seenCards.add(seenCard);
		return;
	}
	
	public abstract Card disproveSuggestion(Solution suggestion);

	public String getColor() {
		return color;
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

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public String getName() {
		return name;
	}

	public Set<Card> getSeenCards() {
		return seenCards;
	}

}
