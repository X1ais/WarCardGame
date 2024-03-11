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
		
		for (int i = 0; i < 26; i++) {
			flip();
		}
		
		printWinner();
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
	
	
	private static void flip() {
		int playerOneCard = playerOne.flip().getValue();
		int playerTwoCard = playerTwo.flip().getValue();
		
		if (playerOneCard > playerTwoCard) {
			playerOne.incrementScore();
		} else if (playerOneCard < playerTwoCard) {
			playerTwo.incrementScore();
		}
	}
	
	
	private static void printWinner() {
		String winnerMessage;
		if (playerOne.getScore() > playerTwo.getScore()) {
			winnerMessage = playerOne.getName() + " wins!";
		} else if (playerOne.getScore() < playerTwo.getScore()) {
			winnerMessage = playerTwo.getName() + " wins!";
		} else {
			winnerMessage = "Draw!";
		}
		
		System.out.println(playerOne.getName() + "     " + playerTwo.getName());
		System.out.println("    " + playerOne.getScore() + "             " + playerTwo.getScore());
		System.out.println("\n" + winnerMessage);
	}

}