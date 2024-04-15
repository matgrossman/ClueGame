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
	
	
//	Update panels
	
	//	Main(Test for now)
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.deal();
		ClueGame game = new ClueGame();
		game.setLayout(new BorderLayout());
		BoardPanel panel = game.getBoardpanel();
		GameControlPanel controlPanel = game.getControlPanel();
		CardPanel cardPanel = game.getCardPanel();
		
		controlPanel.setPlayerNameTF(board.getCurPlayer().getName());
		
		game.getContentPane().add(panel, BorderLayout.CENTER);
		game.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		game.getContentPane().add(cardPanel,BorderLayout.EAST);
		game.setSize(750, 750);  // size the frame
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		game.setVisible(true); // make it visible
		
		JOptionPane.showMessageDialog(null, "You are Draymond Green. Can you find the solution before the Computer Players?");
		controlPanel.setPlayerNameTF(board.getCurPlayer().getName());
		
	}
	
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
