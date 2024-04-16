/*
 * Authors: Mathew Grossman, Julian Reyes
 * 
 * BoardPanel : Handles GUI related to drawing game board.
 */

package clueGame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardPanel extends JPanel{
	private Board board;
	private int numRows;
	private int numCols;
	private BoardCell[][] grid;
	private int squareSize;

	public BoardPanel() {
		super();
		board = Board.getInstance();
		numRows = board.getNumRows();
		numCols = board.getNumColumns();
		grid = board.getGrid();
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX()/squareSize;
				int y = e.getY()/squareSize;
				if (x > numCols || y > numRows) {
					return;
				}
				else {
					board.mouseClick(y, x);
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
	}

	/*
	 * paintComponent: Calls each objects draw() function in order of cell, room name, players.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int pageWidth = this.getWidth();
		int pageHeight = this.getHeight();
		
		int squareHeight = pageHeight / numRows;
		int squareWidth = pageWidth / numCols;
		
		squareSize = Math.min(squareHeight, squareWidth);
		
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

//	Test main for Board panel
	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		board.deal();
		JFrame frame = new JFrame();
		BoardPanel panel = new BoardPanel();  // create the panel

		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 750);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
	}
	
	

}
