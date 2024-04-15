/*
 * CardPanel: GUI for showing user information about cards seen and in hand
 * Authors: Mathew Grossman, Julian Reyes
 */
package clueGame;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CardPanel extends JPanel{
	private JPanel personPanel;
	private JPanel roomPanel;
	private JPanel weaponPanel;
	private Board board = Board.getInstance();
	private HumanPlayer player;



	ArrayList<Card> hand;
	Set<Card> seen;

	public CardPanel() {

		//		Initialize instance variables
		personPanel = new JPanel();
		roomPanel = new JPanel();
		weaponPanel = new JPanel();
		player = (HumanPlayer) board.getPlayers()[0];


		//		Set Borders for panels
		this.setBorder(BorderFactory.createTitledBorder("Cards:"));
		personPanel.setBorder(BorderFactory.createTitledBorder("People"));
		roomPanel.setBorder(BorderFactory.createTitledBorder("Rooms"));
		weaponPanel.setBorder(BorderFactory.createTitledBorder("Weapons"));

		hand = player.getHand();
		seen = player.getSeenCards();
		this.setLayout(new GridLayout(3,1));
		personPanel.setLayout(new GridLayout(0,1));
		weaponPanel.setLayout(new GridLayout(0,1));
		roomPanel.setLayout(new GridLayout(0,1));
		//		

		this.setName("Cards Known:");

		this.updatePanels();



	}
	public void updatePanels() {
		hand = player.getHand();
		seen = player.getSeenCards();
		this.updatePanel(personPanel, CardType.PERSON);
		this.updatePanel(roomPanel, CardType.ROOM);
		this.updatePanel(weaponPanel, CardType.WEAPON);
	}

	/*
	 * Update Panel: Updates the current panel with correct card types, then adds to
	 * main panel
	 */
	public void updatePanel(JPanel panel, CardType cardType) {
		panel.removeAll();
		panel.add(new JLabel("In Hand:"));


		for(Card c : hand) {
			if(c.getCardType() == cardType) {
				JTextField cardField = new JTextField(c.getCardName());
				cardField.setEditable(false);
				panel.add(cardField);
			}
		}

		panel.add(new JLabel("Seen:"));

		//		Update Seen. Gray for contrast with cards in hand
		if(seen != null) {
		for(Card c : seen) {
			if(c.getCardType() == cardType) {
				JTextField cardField = new JTextField(c.getCardName());
				cardField.setEditable(false);
				cardField.setBackground(Color.GRAY);
				panel.add(cardField);
			}
		}
		}
		this.add(panel);
	}


	public HumanPlayer getPlayer() {
		return player;
	}

	//	Test main for card Panel
	public static void main(String[] args) {
		//		Initialize board values
		Board board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();

		//		Create test panel and frame
		CardPanel testPanel = new CardPanel();
		JFrame testFrame = new JFrame("Card Panel");
		//		Test Player
		HumanPlayer player = testPanel.getPlayer();

		//		Test Hand
		Card personCard = new Card(CardType.PERSON, "TestPerson");
		Card weaponCard = new Card(CardType.WEAPON, "TestWeapon");
		Card roomCard = new Card(CardType.ROOM, "TestRoom");
		Card roomCard2 = new Card(CardType.ROOM, "RoomTest");
		//		
		player.updateHand(personCard);
		player.updateHand(roomCard);
		player.updateHand(weaponCard);
		player.updateHand(roomCard2);

		//		Test Seen
		Card seenPerson = new Card(CardType.PERSON, "seenPerson");
		Card seenWeapon = new Card(CardType.WEAPON, "seenWeapon");
		Card seenWeapon2 = new Card(CardType.WEAPON, "AK47");
		Card seenRoom = new Card(CardType.ROOM, "seenRoom");

		player.updateSeen(seenPerson);
		player.updateSeen(seenWeapon);
		player.updateSeen(seenRoom);
		player.updateSeen(seenWeapon2);

		testPanel.updatePanels();
		testFrame.setContentPane(testPanel);
		testFrame.setSize(400,750);
		testFrame.setVisible(true);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
