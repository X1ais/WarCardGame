package war;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
	private List<Card> cards = new ArrayList<>();
	
	public Deck() {
		initDeck();
	}
	

	
	private void initDeck() {
		String[] ranks = {"two","three","four","five","six","seven","eight","nine","ten","jack","queen","king","ace"};
		String[] suits = {"spades","diamonds","clubs","hearts"};
				
		for (String s : suits) {
			for (String r : ranks) {
				cards.add(new Card(r,s));
			}
		}
	}



	public List<Card> getCards() {
		return cards;
	}

	
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	
	
	public void shuffle() {
		Random rand = new Random();
		List<Card> tempCards = new ArrayList<>();
		
		while (cards.size() > 0) {
			int i = rand.nextInt(cards.size());
			tempCards.add(cards.remove(i));
		}
		cards = tempCards;
	}
	
	
	
	public Card draw() {
		return cards.remove(0);
	}
	
	
	
	

}
