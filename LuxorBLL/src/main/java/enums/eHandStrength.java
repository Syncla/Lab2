package enums;

public class eHandStrength {
	public static enum HandScores{
		 isRoyalFlush("isRoyalFlush"),
		 isStraightFlush("isStraightFlush"), 
		 isFourOfAKind("isFourOfAKind"),
		 isFullHouse("isFullHouse"),
		 isFlush("isFlush"),
		 isStraight("isStraight"),
		 isThreeOfAKind("isThreeOfAKind"), 
		 isTwoPair("isTwoPair"),
		 isOnePair("isOnePair"),
		 noPair("noPair")
		;
		private int score;
		private String handType;
		private HandScores(String handType){
			this.handType=handType;
		}
		public String getHandType(){
			return handType;
		}
		public int getScore(){
			return score;
		}
	}
}
