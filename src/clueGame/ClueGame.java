/*
 * Authors: Mathew Grossman Julian Reyes
 * ClueGame: JFrame object that draws and starts game
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClueGame extends JFrame{
	private BoardPanel Boardpanel = new BoardPanel();
	private GameControlPanel controlPanel = new GameControlPanel();
	private CardPanel cardPanel = new CardPanel();
	public ClueGame() {
		super();
		this.setSize(800,800);
	}
//	public void update() {
//		controlPanel.updateFields();
//		cardPanel.updatePanels();
//	}

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
