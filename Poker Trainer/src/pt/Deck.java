package pt;

import java.util.Random;

public class Deck {
	
	private Random r = new Random();
	private static Card[] remainingCards = new Card[52];
	private static int currentCard;
	
	//Construct a deck with 52 cards, 13 of each suite 
	//ranging from 2-14 (11, 12, 13, and 14 representing Jack, Queen, King, and Ace)
	public Deck() {
		currentCard = 0;
		int location = 0;
		String[] suits = new String[] {"SPADES", "HEARTS", "DIAMONDS", "CLUBS"};
		for (String suit: suits) {
			for (int i = 2; i <=14; i++) {
				remainingCards[location] = new Card(i, suit);
				location++;
			}
		}
	}
	
	
	//shuffle the deck by swapping the position of each card with a random one in the deck
	public void shuffle() {
		Card tempCard;
		int temp;
		for (int i = 0; i < 52; i++) {
			temp = r.nextInt(51);
			tempCard = remainingCards[temp];
			remainingCards[temp] = remainingCards[i];
			remainingCards[i] = tempCard;			
		}
	}
	
	public Card getNext() {
		return remainingCards[currentCard++];
	}
	
	public void printDeck() {
		for (Card c: remainingCards)
			System.out.println(c);
	}
}
