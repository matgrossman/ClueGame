package clueGame;

import java.awt.Color;

public class Card {
	private CardType cardType;
	private String cardName;
	private Color color;
	
	public Card(CardType cardType, String cardName) {
		super();
		this.cardType = cardType;
		this.cardName = cardName;
		color = Color.WHITE;
	}
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Card)) {
			return false;
		}
		
		Card target = (Card) o;
		if(this.cardType == target.getCardType() && this.cardName.equals(target.getCardName())) {
			return true;
		}
		else return false;
	}

	public CardType getCardType() {
		return cardType;
	}

	public String getCardName() {
		return cardName;
	}
}
