package base;

public class Card {
	private Rank rank;
	private Suite suite;
	public static enum Rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
		SEVEN(7), EIGHT(8), NINE(9), TEN(10), JESTER(11),
		QUEEN(12), KING(13);
		private int rankValue;
		private Rank(int rankValue){
			this.rankValue=rankValue;
		}
		
	}
	public static enum Suite{
		Hearts(1), Spade(2), Club(3), Diamonds(4);
		//Int values are for sorting
		private int suiteValue;
		private Suite(int suiteValue){
			this.suiteValue=suiteValue;
		}
	}
	public Card(Rank rank,Suite suite){
		this.rank=rank;
		this.suite=suite;
	}
	public Rank getRank(){
		return rank;
	}
	public Suite getSuite(){
		return suite;
	}
	public String toString(){
		return this.getRank()+" of "+this.getSuite();
	}
}
