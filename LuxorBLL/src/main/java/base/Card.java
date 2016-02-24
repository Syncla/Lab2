package base;

public class Card {
	public enum Rank {
		ACE(1), TWO(2);
		private final int rankValue;
		
		Rank(int rankValue) {
			this.rankValue = rankValue;
		}
		int rankValue() {
			return rankValue;
		}
	}
public Card(){
	
}
}
