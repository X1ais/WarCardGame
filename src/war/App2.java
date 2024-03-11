package war;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class App2 {
	private static Deck deck;
	private static Player playerOne;
	private static Card playerOneCard;
	
	private static Player playerTwo;
	private static Card playerTwoCard;
	
	private static Player lastWinner;
	
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_HEIGHT = 720;
	
	private static final int CARD_WIDTH = 110;
	private static final int CARD_HEIGHT = 160;
	
	private static JPanel gamePanel;
	private static JButton flipButton;
	
	
	public static void main(String[] args) {
		deck = new Deck();
		playerOne = new Player("Player One");
		playerTwo = new Player("Player Two");
		
		
		deck.shuffle();
		dealCards();
		
		window();

		
		flipButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flip();
				gamePanel.repaint();
			}
			
		});
		
		
//		for (int i = 0; i < 26; i++) {
//			flip();
//		}
		
//		printWinner();
	}



	private static void window() {
		JFrame frame = new JFrame();
		gamePanel = new JPanel() {
			Image deckBack;
			String cardBackFile = "/cards/back.png";
			String winner;
			
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (playerOne.getHand().size() >= 0) {
					try {
						deckBack = ImageIO.read(getClass().getResource(cardBackFile));
					} catch (IOException e) {
						e.printStackTrace();
					}	
					g.drawImage(deckBack,SCREEN_WIDTH - (CARD_WIDTH + 40)*2,SCREEN_HEIGHT - CARD_HEIGHT - 30,CARD_WIDTH,CARD_HEIGHT,null);
					g.drawImage(deckBack,CARD_WIDTH + 80,30,CARD_WIDTH,CARD_HEIGHT,null);
					
					if (playerOneCard != null && playerTwoCard != null) {
						g.drawImage(playerOneCard.getCardImg(),(SCREEN_WIDTH-CARD_WIDTH)/2,SCREEN_HEIGHT/2 - CARD_HEIGHT - 20,CARD_WIDTH,CARD_HEIGHT,null);
						g.drawImage(playerTwoCard.getCardImg(),(SCREEN_WIDTH-CARD_WIDTH)/2,SCREEN_HEIGHT/2 + 20,CARD_WIDTH,CARD_HEIGHT,null);
						
						if (playerOne.getDiscard().size() > 0) {
							g.drawImage(playerOne.getDiscard().get(playerOne.getDiscard().size()-1).getCardImg(),40,30,CARD_WIDTH,CARD_HEIGHT,null);
						}
						if (playerTwo.getDiscard().size() > 0) {
							g.drawImage(playerTwo.getDiscard().get(playerTwo.getDiscard().size()-1).getCardImg(),SCREEN_WIDTH - CARD_WIDTH - 40,SCREEN_HEIGHT - CARD_HEIGHT - 30,CARD_WIDTH,CARD_HEIGHT,null);
						}
						moveCardToDiscard();
					}
					
					g.setFont(new Font("Arial", Font.BOLD, 30));
					g.setColor(Color.white);
					g.drawString(String.valueOf(playerOne.getScore()), SCREEN_WIDTH/2,30);
					g.drawString(String.valueOf(playerTwo.getScore()), SCREEN_WIDTH/2,SCREEN_HEIGHT-10);
					
					if (playerOne.getHand().size() == 0) {
						
						g.drawString(checkWinner(), SCREEN_WIDTH/2-100,SCREEN_HEIGHT/2+10);
					}
				}
			}
			
			
		};
		JPanel buttonPanel = new JPanel();
		flipButton = new JButton("Flip");
		
		frame.setTitle("War: the Card Game");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		gamePanel.setLayout(new BorderLayout());
		gamePanel.setBackground(new Color(53,101,77));
		
		flipButton.setFocusable(false);
		buttonPanel.add(flipButton);
		
		frame.add(gamePanel);
		frame.add(buttonPanel,BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
	


	protected static void moveCardToDiscard() {
		if (lastWinner == null) {
			playerOne.getDiscard().add(playerOneCard);
			playerTwo.getDiscard().add(playerTwoCard);
		} else if (lastWinner == playerOne){
			lastWinner.getDiscard().add(playerTwoCard);
			lastWinner.getDiscard().add(playerOneCard);
		} else {
			lastWinner.getDiscard().add(playerOneCard);
			lastWinner.getDiscard().add(playerTwoCard);
		}
	}

	
	
	private static void flip() {
		playerOneCard = playerOne.flip();
		playerTwoCard = playerTwo.flip();
		
		if (playerOneCard.getValue() > playerTwoCard.getValue()) {
			playerOne.incrementScore();
			lastWinner = playerOne;
		} else if (playerOneCard.getValue() < playerTwoCard.getValue()) {
			playerTwo.incrementScore();
			lastWinner = playerTwo;
		} else {
			lastWinner = null;
		}
		
		if (playerOne.getHand().size() <= 0) {
			flipButton.setEnabled(false);
		}
	}
	
	
	
	
	
	private static String checkWinner() {
		if (playerOne.getScore() > playerTwo.getScore()) {
			return playerOne.getName() + " Wins!";
		} else if (playerOne.getScore() < playerTwo.getScore()) {
			return playerTwo.getName() + " Wins!";
		} else {
			return "TIE";
		}
		
		//System.out.println(playerOne.getName() + "     " + playerTwo.getName());
		//System.out.println("    " + playerOne.getScore() + "             " + playerTwo.getScore());
		//System.out.println("\n" + winner);
	}

}
