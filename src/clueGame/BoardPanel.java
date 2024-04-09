package clueGame;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	private Board board;
	private int numRows;
	private int numCols;
	private BoardCell[][] grid;

	public BoardPanel() {
		super();
		board = Board.getInstance();
		numRows = board.getNumRows();
		numCols = board.getNumColumns();
		grid = board.getGrid();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int pageWidth = this.getWidth();
		int pageHeight = this.getHeight();
		
		int squareHeight = pageHeight / numRows;
		int squareWidth = pageWidth / numCols;
		
		int squareSize = Math.min(squareHeight, squareWidth);
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				grid[i][j].draw(squareSize, g);
			}
		}
		for (Room r: board.getRooms()) {
			r.draw(g, squareSize);
		}
		for (Player p : board.getPlayers()) {
			p.draw(g, squareSize);
		}
	}
	
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.deal();
		JFrame frame = new JFrame();
		BoardPanel panel = new BoardPanel();  // create the panel
		GameControlPanel controlPanel = new GameControlPanel();
		CardPanel cardPanel = new CardPanel();
		

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		frame.getContentPane().add(cardPanel,BorderLayout.EAST);
//		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 750);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
	}
	
	

}
