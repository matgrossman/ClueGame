/*
 * Authors: Mathew Grossman Julian Reyes
 * ClueGame: JFrame object that draws and starts game
 */
package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class ClueGame extends JFrame{
	private Board board = Board.getInstance();
	
	public ClueGame() {
		super();
		this.setSize(800,800);
	}
	
//	Main(Test for now)
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.deal();
		ClueGame game = new ClueGame();
		game.setLayout(new BorderLayout());
	
		BoardPanel panel = new BoardPanel();
		GameControlPanel controlPanel = new GameControlPanel();
		CardPanel cardPanel = new CardPanel();
		game.getContentPane().add(panel, BorderLayout.CENTER);
		game.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		game.getContentPane().add(cardPanel,BorderLayout.EAST);
		game.setSize(750, 750);  // size the frame
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		game.setVisible(true); // make it visible
	}
}
