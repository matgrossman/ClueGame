package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {
	private String name;
	private Color color;
	private int row;
	private int col;
	
	protected Board board = Board.getInstance();
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	private Set<Card> seenCards;

	public Player(String name, String color, int row, int col) {
		this.name = name;
		this.color = Board.getColor(color);
		this.row = row;
		this.col = col;
		seenCards = new HashSet<Card>();
	}

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
		if (matchCards.isEmpty()) {
			return null;
		}
		else {
			Collections.shuffle(matchCards);
			return matchCards.get(0);
		}
	}

	/*
	 * Draw(): Makes circle that represents player based on color
	 */
	public void draw(Graphics g, int size) {
		int offset = 0;
		BoardCell cell = board.getCell(row, col);
		if (cell.isRoomCenter()) {
			ArrayList<Player> occupants = board.getRoom(cell).getOccupants();
			for(Player p: board.getPlayers()) {
				if(this.equals(p)) {
					break;
				}
				if (occupants.contains(p)) {
					offset += size/2; 
				}
			}
		}
		g.setColor(color);
		g.fillOval(col*size+offset, row*size, size, size);
		g.setColor(Color.BLACK);
		g.drawOval(col*size+offset, row*size, size, size);
		
	}
	//	Getters for testing purposes
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
