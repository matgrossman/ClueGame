package clueGame;


public abstract class Player {
	private String name;
	private String color;
	private int row;
	private int col;
	
	private Card[] hand;	
	
	public Player(String name, String color, int row, int col) {
		this.name = name;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
//	Getters for testing purposes
	public void updateHand(Card card) {
		return;
	}

	public String getColor() {
		return color;
	}



	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Card[] getHand() {
		return hand;
	}

	public void setHand(Card[] hand) {
		this.hand = hand;
	}

	public String getName() {
		return name;
	}

}
