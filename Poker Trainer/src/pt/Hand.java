package pt;

import java.util.Arrays;

public class Hand {
	
	private Deck deck;
	private Card[] myHoleCards = new Card[2], opponentHoleCards = new Card[2], boardCards = new Card[5];
	
	public Hand() {
		deck = new Deck();
		deck.shuffle();
		
		myHoleCards[0] = deck.getNext();
		opponentHoleCards[0] = deck.getNext();
		myHoleCards[1] = deck.getNext();
		opponentHoleCards[1] = deck.getNext();
		
		boardCards[0] = deck.getNext();
		boardCards[1] = deck.getNext();
		boardCards[2] = deck.getNext();
		boardCards[3] = deck.getNext();
		boardCards[4] = deck.getNext();

	}
	
	public Card[] getMyHoleCards() {
		return myHoleCards;
	}
	
	public Card[] getOpponentHoleCards() {
		return opponentHoleCards;
	}
	
	public Card[] getFlop() {
		return Arrays.copyOfRange(boardCards, 0, 3);
	}
	
	public Card getTurn() {
		return boardCards[3];
	}
	
	public Card getRiver() {
		return boardCards[4];
	}
}
