package war;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Card> hand = new ArrayList<>();
	private List<Card> discard = new ArrayList<>();
	private int score = 0;
	private String name;
	
	
	
	public Player(String name) {
		this.name = name;
	}



	public List<Card> getHand() {
		return hand;
	}



	public void setHand(List<Card> hand) {
		this.hand = hand;
	}



	public int getScore() {
		return score;
	}



	public void setScore(int score) {
		this.score = score;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	
	
	public void describe() {
		for (int i = 0; i < hand.size(); i++) {
			hand.get(i).describe();
		}
	}
	
	
	
	public Card flip() {
		return hand.remove(0);
	}
	
	
	
	public void draw(Deck deck) {
		hand.add(deck.draw());
	}
	
	
	
	public void incrementScore() {
		score++;
	}



	public List<Card> getDiscard() {
		return discard;
	}



	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
}
