package base;

public class Card {
	private Rank rank;
	private Suite suite;
	public static enum Rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
		SEVEN(7), EIGHT(8), NINE(9), TEN(10), JOKER(11),
		QUEEN(12), KING(13);
		private int rankValue;
		private Rank(int rankValue){
			this.rankValue=rankValue;
		}
	}
	public static enum Suite{
		Hearts, Spade, Club, Diamonds;
	}
	public Card(Rank rank,Suite suite){
		this.rank=rank;
		this.suite=suite;
	}
	public Card(){
		
	}
	public String toString(){
		return "Suite "+this.suite+":Rank "+this.rank;
	}
}
