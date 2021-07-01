package pt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PokerHand {
	private int topRank, bottomRank, handStrength;
	private String handName;
	private Card[] hand = new Card[7];
	private int[] kicker = new int[5];
	private int[] flushRanks = new int[7];
	private int[] handRanks = new int[15];
	
	public PokerHand(Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7) {
		hand[0] = c1;
		hand[1] = c2;
		hand[2] = c3;
		hand[3] = c4;
		hand[4] = c5;
		hand[5] = c6;
		hand[6] = c7;

		//increment corresponding int in handRanks to easily track pairs, 3-of-a-kind, and 4-of-a-kind
		handRanks[c1.getRank()] += 1;
		handRanks[c2.getRank()] += 1;
		handRanks[c3.getRank()] += 1;
		handRanks[c4.getRank()] += 1;
		handRanks[c5.getRank()] += 1;
		handRanks[c6.getRank()] += 1;
		handRanks[c7.getRank()] += 1;

		calculateHandStrength();
	}
	
	public String getHandName() {
		return handName;
	}
	
	public int[] getHandRanks() {
		return handRanks;
	}
	
	private void calculateHandStrength() {
		
		if (isFlush() && sortAndCheckStraight(flushRanks)) {
			handName = "Straight Flush";
			handStrength = 8;
			return;
		}
		
		if (isOfAKind(4) > 0) {
			topRank = isOfAKind(4);
			handName = "Quads";
			handStrength = 7;
			kicker[0] = nextKicker();
			return;
		}
		
		if (isOfAKind(3) > 0) {
			topRank = isOfAKind(3);
			if (isOfAKind(2) > 0) {
				bottomRank = isOfAKind(2);
				handName = "Full House";
				handStrength = 6;
				return;
			} else {
				topRank = 0;
			}
		}
		
		if (isFlush()) {
			handName = "Flush";
			handStrength = 5;
			kicker[0] = nextKicker();
			kicker[1] = nextKicker();
			kicker[2] = nextKicker();
			kicker[3] = nextKicker();
			kicker[4] = nextKicker();
			return;
		}
		
		if (isStraight()) {
			handName = "Straight";
			handStrength = 4;
			return;
		}
		
		if (isOfAKind(3) > 0) {
			topRank = isOfAKind(3);
			kicker[0] = nextKicker();
			kicker[1] = nextKicker();
			handName = "Trips";
			handStrength = 3;
			return;
		}
		
		if (isOfAKind(2) > 0) {
			topRank = isOfAKind(2);
			if (isOfAKind(2) > 0) {
				bottomRank = isOfAKind(2);
				kicker[0] = nextKicker();
				handName = "Two Pair";
				handStrength = 2;
				return;
			} else {
				kicker[0] = nextKicker();
				kicker[1] = nextKicker();
				kicker[2] = nextKicker();
				handName = "Pair";
				handStrength = 1;
				return;
			}
		}
		
		handName = "High Card";
		handStrength = 0;
		kicker[0] = nextKicker();
		kicker[1] = nextKicker();
		kicker[2] = nextKicker();
		kicker[3] = nextKicker();
		kicker[4] = nextKicker();

	}
	
	
	//return true if hand has numCards cards of the same rank, check for 4, 3, or 2 of a kind
	public int isOfAKind(int numCards) {
		
		for (int i=2; i<handRanks.length; i++)
			if (handRanks[i] == numCards && i != topRank) {				
				return i;
			}
		return -1;
	}
	
	//return the rank of the highest card not being used 
	public int nextKicker() {
		for (int i = 14; i >= 2; i--) {
			if (i != topRank && i != bottomRank && Arrays.asList(kicker).contains(i)) {
				return i;
			}
		}
		return -1;
	}
	
	
	//returns true if at least 5 of the 7 cards in the hand have the same suit
	//if true also set kicker to the 5 highest cards of that suit for hand comparison
	public boolean isFlush() {
		ArrayList<Integer> clubs = new ArrayList<Integer>();
		ArrayList<Integer> diamonds = new ArrayList<Integer>();
		ArrayList<Integer> hearts = new ArrayList<Integer>();
		ArrayList<Integer> spades = new ArrayList<Integer>();
		
		//add each card's rank to corresponding ArrayList
		for (Card c: hand) {
			if (c.getSuit().equals("CLUBS"))
				clubs.add(c.getRank());
			if (c.getSuit().equals("DIAMONDS"))
				diamonds.add(c.getRank());
			if (c.getSuit().equals("HEARTS"))
				hearts.add(c.getRank());
			if (c.getSuit().equals("SPADES"))
				spades.add(c.getRank());
		}
		
		//if an ArrayList has 5 or more elements a flush exists, save the ranks for hand comparison and return true
		if (clubs.size() >= 5) {
			saveFlushRanks(clubs);
			return true;
		}
		if (diamonds.size() >= 5) {
			saveFlushRanks(diamonds);
			return true;
		}
		if (hearts.size() >= 5) {
			saveFlushRanks(hearts);
			return true;
		}
		if (spades.size() >= 5) {
			saveFlushRanks(spades);
			return true;
		}
		
		//no flush found
		return false;
		

	}
	
	//helper method for is Flush, saves the rank of cards in the flush for hand comparison, and check for straight flush
	private void saveFlushRanks(ArrayList<Integer> flush) {
		Collections.sort(flush);

		for (int i=0; i<flush.size(); i++) {
		flushRanks[i] = flush.get(flush.size()-(i+1));
		}
		
	}
	
	//returns true if 5 of the 7 cards in the hand have consecutive ranks, ace can be high or low
	//if there is a straight set kicker[0] to the high card of the straight for hand comparison
	public boolean isStraight() {
		
		//store an array of the card ranks
		int[] ranks = new int[] {hand[0].getRank(), hand[1].getRank(), hand[2].getRank(), hand[3].getRank(),
				hand[4].getRank(), hand[5].getRank(), hand[6].getRank()};
		
		if (sortAndCheckStraight(ranks))
			return true;
		
		//convert aces to low and check again
		for (int i=0; i<7; i++) {
			if (ranks[i] == 14)
				ranks[i] -= 13;
		}
		if (sortAndCheckStraight(ranks))
			return true;		
		
		//no straight found
		return false;
	}
	
	//helper method, returns true if 5 cards in the hand have consecutive ranks
	private boolean sortAndCheckStraight(int[] ranks) {
		//set duplicate ranks to -1 so the straight's high card is determined accurately
		for (int i=0; i<ranks.length-1; i++) {
			for (int j=i+1; j<ranks.length; j++) {
				if (ranks[i] == ranks[j])
					ranks[i] = -1;
			}
		}
		Arrays.sort(ranks);
		
		//check if cards at 0-4, 1-5, or 2-6 are consecutive 
		for (int i=2; i>=0; i--) {
			if (ranks[i]+1 == ranks[i+1] && ranks[i+1]+1 == ranks[i+2] 
					&& ranks[i+2]+1 == ranks[i+3] && ranks[i+3]+1 == ranks[i+4]){
				kicker[0] = ranks[i+4]; //save the high card of the straight for hand comparison
				return true;
			}
		}
		return false;		
	}
	
	//Compares 2 poker hands, return 1 if the first hand is stronger, 2 if the second is stronger, 0 if equal
	public static int CompareHands(PokerHand h1, PokerHand h2) {
		if (h1.handStrength > h2.handStrength)
			return 1;
		if (h1.handStrength < h2.handStrength)
			return 2;
		
		if (h1.topRank > h2.topRank)
			return 1;
		if (h1.topRank < h2.topRank)
			return 2;
		
		if (h1.bottomRank > h2.bottomRank)
			return 1;
		if (h1.bottomRank < h2.bottomRank)
			return 2;
		
		// if the hands have equal strength check each kicker
		for (int i=0; i<5; i++) {
			if (h1.kicker[i] > h2.kicker[i])
				return 1;
			if (h1.kicker[i] < h2.kicker[i])
				return 2; 
		}
		
		//both hands have equal strength, top and bottom ranks, and kickers.  they are equal strength
		return 0;
	}

}
