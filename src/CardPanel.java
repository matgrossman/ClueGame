import java.util.ArrayList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.HumanPlayer;
import clueGame.Player;

public class CardPanel extends JPanel{
	private JPanel personPanel;
	private JPanel roomPanel;
	private JPanel weaponPanel;
	private Board board = Board.getInstance();
	private HumanPlayer player;
	private JLabel handLabel;
	private JLabel seenLabel;
	
	
	ArrayList<Card> hand;
	Set<Card> seen;
	
	public CardPanel() {
		personPanel = new JPanel();
		roomPanel = new JPanel();
		weaponPanel = new JPanel();
		player = (HumanPlayer) board.getPlayers()[0];
		handLabel = new JLabel("In Hand:");
		seenLabel = new JLabel("Seen:");
		personPanel.setName("People");
		weaponPanel.setName("Weapons");
		roomPanel.setName("Rooms");
		hand = player.getHand();
		seen = player.getSeenCards();
		this.setSize(3,1);
		personPanel.setSize(0,1);
		weaponPanel.setSize(0,1);
		roomPanel.setSize(0,1);
//		
//		this.add(personPanel);
//		this.add(roomPanel);
//		this.add(weaponPanel);
		this.updatePanels();

		
		
	}
	public void updatePanels() {
		hand = player.getHand();
		seen = player.getSeenCards();
		this.updatePanel(personPanel, CardType.PERSON);
		this.updatePanel(roomPanel, CardType.ROOM);
		this.updatePanel(weaponPanel, CardType.WEAPON);
		revalidate();
	}
	
	public void updatePanel(JPanel panel, CardType cardType) {
		panel.removeAll();
		panel.add(handLabel);

		
		for(Card c : hand) {
			if(c.getCardType() == cardType) {
				JTextField cardField = new JTextField(c.getCardName());
				cardField.setEditable(false);
				panel.add(cardField);
			}
		}
		
		panel.add(seenLabel);
		
		for(Card c : seen) {
			if(c.getCardType() == cardType) {
				JTextField cardField = new JTextField(c.getCardName());
				cardField.setEditable(false);
				panel.add(cardField);
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
//		
		player.updateHand(personCard);
		player.updateHand(roomCard);
		player.updateHand(weaponCard);
		
//		Test Seen
		Card seenPerson = new Card(CardType.PERSON, "seenPerson");
		Card seenWeapon = new Card(CardType.WEAPON, "seenWeapon");
		Card seenRoom = new Card(CardType.ROOM, "seenRoom");
		
		player.updateSeen(seenPerson);
		player.updateSeen(seenWeapon);
		player.updateSeen(seenRoom);
		
		testPanel.updatePanels();
		testFrame.setContentPane(testPanel);
		testFrame.setSize(180,750);
		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testFrame.setVisible(true);

	}
}
