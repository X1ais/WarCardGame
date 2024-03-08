package war;

public class App {
	private static Deck deck;
	private static Player playerOne;
	private static Player playerTwo;
	
	
	
	public static void main(String[] args) {
		deck = new Deck();
		playerOne = new Player("Player One");
		playerTwo = new Player("Player Two");
		
		deck.shuffle();
		
		dealCards();
	}

	
	
	private static void dealCards() {
		for (int i = 0; i < 52; i++) {
			if (i % 2 == 0) {
				playerOne.getHand().add(deck.draw());
			} else {
				playerTwo.getHand().add(deck.draw());
			}
		}		
	}

}
