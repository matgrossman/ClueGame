package clueGame;

import java.awt.Color;
import java.awt.Graphics;
//import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private int row;
	private int col;
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	private Set<Card> seenCards;
	
	public Player(String name, Color color, int row, int col) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
		seenCards = new HashSet<Card>();
	}
	public Player(String name, String color, int row, int col) {
		this.name = name;
		this.color = Board.getColor(color);
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
	
	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> suggestionCards = new ArrayList<Card>();
		suggestionCards.add(suggestion.getPerson());
		suggestionCards.add(suggestion.getWeapon());
		suggestionCards.add(suggestion.getRoom());
		ArrayList<Card> matchCards = new ArrayList<Card>();
		for (Card c: suggestionCards) {
			if (hand.contains(c)) {
				matchCards.add(c);
			}
		}
		if (matchCards.size() == 0) {
			return null;
		}
		else {
			Collections.shuffle(matchCards);
			return matchCards.get(0);
		}
	}

	public void draw(Graphics g, int size) {
		g.setColor(color);
		g.fillOval(col*size, row*size, size, size);
		g.setColor(Color.BLACK);
		g.drawOval(col*size, row*size, size, size);
	}
	public Color getColor() {
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
