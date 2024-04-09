package clueGame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClueGame extends JFrame{
	private Board board = Board.getInstance();
	
	public ClueGame() {
		super();
		this.setSize(750,750);
	}
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.deal();
		ClueGame game = new ClueGame();
		BoardPanel panel = new BoardPanel();
		GameControlPanel controlPanel = new GameControlPanel();
		CardPanel cardPanel = new CardPanel();
		game.getContentPane().add(panel, BorderLayout.CENTER);
		game.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		game.getContentPane().add(cardPanel,BorderLayout.EAST);
//		frame.setContentPane(panel); // put the panel in the frame
		game.setSize(750, 750);  // size the frame
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		game.setVisible(true); // make it visible
	}
}
