package clueGame;

import java.awt.Color;

public abstract class Player {
	private String name;
	private Color color;
	private int row;
	private int column;
	
	private Card[] hand;	
	
	public void updateHand(Card card) {
		return;
	}

}
