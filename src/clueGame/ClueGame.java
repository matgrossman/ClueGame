package clueGame;


import javax.swing.JFrame;

public class ClueGame extends JFrame{
	private BoardPanel Boardpanel = new BoardPanel();
	private GameControlPanel controlPanel = new GameControlPanel();
	private CardPanel cardPanel = new CardPanel();
	public ClueGame() {
		super();
		this.setSize(800,800);
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
