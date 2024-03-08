package war;

public class Card {
	private int value;
	private String rank;
	private String suit;
	
	
	
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
		value = getValue(rank);
	}



	public int getValue() {
		return value;
	}



	public String getName() {
		return suit;
	}



	public void setName(String suit) {
		this.suit = suit;
	}



	public String getRank() {
		return rank;
	}



	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	
	public void describe() {
		System.out.println(rank + " of " + suit);
	}
	
	
	
	private int getValue(String rank) { // Uses a switch statement to check against the valid ranks a card may have and assigns a value associated with that rank.
		switch (rank) {
		case "two":
			return 2;
		case "three":
			return 3;
		case "four":
			return 4;
		case "five":
			return 5;
		case "six":
			return 6;
		case "seven":
			return 7;
		case "eight":
			return 8;
		case "nine":
			return 9;
		case "ten":
			return 10;
		case "jack":
			return 11;
		case "queen":
			return 12;
		case "king":
			return 13;
		case "ace":
			return 14;
		default:
			return -1;
		}
	}
}
