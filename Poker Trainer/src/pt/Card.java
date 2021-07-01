package pt;

public class Card {
	private String suit;
	private int rank;
	
	public Card(int rank, String suit) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public String getSuit(){
		return suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	public String toString() {
		String[] rankNames = new String[] {"DEUCE", "TRE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT"
				, "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
		return rankNames[rank-2] + "_of_" + this.suit;
	}

}
