/*
 * Authors: Mathew Grossman Julian Reyes
 * ClueGame: JFrame object that draws and starts game
 */
package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClueGame extends JFrame{
	private Board board = Board.getInstance();
	BoardPanel Boardpanel = new BoardPanel();
	GameControlPanel controlPanel = new GameControlPanel();
	CardPanel cardPanel = new CardPanel();
	public ClueGame() {
		super();
		this.setSize(800,800);
	}
	public void update() {
		controlPanel.updateFields();
		cardPanel.updatePanels();
	}
	
	
//	Update panels
	

public BoardPanel getBoardpanel() {
		return Boardpanel;
	}


	public GameControlPanel getControlPanel() {
		return controlPanel;
	}


	public CardPanel getCardPanel() {
		return cardPanel;
	}


}
