package clueGame;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	
	public Solution(Card room, Card person, Card weapon) {
		super();
		this.room = room;
		this.person = person;
		this.weapon = weapon;
	}

	public Card getRoom() {
		return room;
	}
	
	public Card getPerson() {
		return person;
	}

	public Card getWeapon() {
		return weapon;
	}
	
	public boolean contains(Card c) {
		if (c.equals(room)|| c.equals(person)|| c.equals(weapon)) {
			return true;
		}
		else {
			return false;
		}
	}
	

	@Override
	public String toString() {
		return "lol";
		//return room.getCardName() + ", " + person.getCardName() + ", " + weapon.getCardName();
	}

}


