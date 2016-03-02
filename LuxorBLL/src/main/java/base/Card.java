package base;

public class Card {
	private Rank rank;
	private Suit suit;

	public static enum Rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), 
		QUEEN(12), KING(13);
		private int rankValue;

		private Rank(int rankValue) {
			this.rankValue = rankValue;
		}

	}

	public static enum Suit {
		Hearts(1), Spades(2), Clubs(3), Diamonds(4);
		// Int values are for sorting
		private int suitValue;

		private Suit(int suitValue) {
			this.suitValue = suitValue;
		}
	}

	public Card(Card.Rank rank, Card.Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return this.rank;
	}

	public Suit getSuit() {
		return this.suit;
	}

	public String toString() {
		return this.getRank() + " of " + this.getSuit();
	}
}
