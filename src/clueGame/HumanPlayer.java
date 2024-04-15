package clueGame;

import java.awt.Color;

public class HumanPlayer extends Player{

	private boolean isTurnFinished;
	public HumanPlayer(String name, Color color, int row, int col) {
		super(name, color, row, col);
	}
	public HumanPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
	}

	
	public boolean isTurnFinished() {
		return isTurnFinished;
	}
	public void setTurnFinished(boolean isTurnFinished) {
		this.isTurnFinished = isTurnFinished;
	}
	
}
