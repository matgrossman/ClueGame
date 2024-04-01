package clueGame;

import java.util.ArrayList;

public abstract class Player {
	private String name;
	private String color;
	private int row;
	private int col;
	
	private ArrayList<Card> hand = new ArrayList<Card>();	
	
	public Player(String name, String color, int row, int col) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
//	Getters for testing purposes
	public void updateHand(Card card) {
		return;
	}
	
	public void updateSeen(Card seenCard) {
		return;
	}
	
	public Card disproveSuggestion() {
		return hand.get(0);		//TEMP placeholder
	}

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

}
