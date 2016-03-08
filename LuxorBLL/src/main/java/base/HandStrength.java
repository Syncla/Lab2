package base;
import java.util.ArrayList;
public class HandStrength {
	private Card.Rank highCard;
	private Card.Rank lowCard;
	private Card.Suit suit;
	ArrayList<Card> kickers = new ArrayList<Card>();
	private String handStrength;
	private int Score;
	final public static String failedHand="Whiff";
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
	public HandStrength(Card.Rank highCard, Card.Rank lowCard,Card.Suit suit, ArrayList<Card> kickers, String handStrength,int Score){
		this.highCard=highCard;
		this.lowCard=lowCard;
		this.kickers=kickers;
		this.handStrength=handStrength;
		this.suit=suit;
		this.Score=Score;
	}
	public HandStrength(){
		this.handStrength=failedHand;
		this.Score=0;
	}
	public Card.Rank getHighCard() {
		return highCard;
	}

	public Card.Rank getLowCard() {
		return lowCard;
	}

	public ArrayList<Card> getKickers() {
		return kickers;
	}

	public String getHandStrength() {
		return handStrength;
	}
	public int getScore(){
		return Score;
	}
	public String getFailedHand(){
		return failedHand;
	}
	public Card.Suit getSuit(){
		return suit;
	}
	
	
	@Override
	public String toString(){
		String tempString="";
		tempString="Hand Type: "+getHandStrength();
		tempString+="\nScore: "+getScore();
		tempString+="\nSuit of High Card: "+getSuit();
		tempString+="\nHigh Card: "+getHighCard();
		tempString+="\nLow Card: "+getLowCard();
		tempString+="\nKickers: "+getKickers();
		return (tempString);
	}
	public boolean equals(HandStrength hsToCompare){
		boolean equal=true;
		if (this.getScore()!=hsToCompare.getScore()){
			return false;
		}
		if (this.getScore()==hsToCompare.getScore()){
			if (this.getHighCard().ordinal()!=hsToCompare.getHighCard().ordinal()){
				return false;
			}
			if (this.getHighCard().ordinal()==hsToCompare.getHighCard().ordinal()){
				if (this.getLowCard().ordinal()!=hsToCompare.getLowCard().ordinal()){
					return false;
				}
				if (this.getLowCard().ordinal()==hsToCompare.getLowCard().ordinal()){
					for (int index=0;index<this.getKickers().size();index++){
						if (this.getKickers().get(index).getRank().ordinal()!=hsToCompare.getKickers().get(index).getRank().ordinal()){
							return false;
						}
					}
					if (this.getSuit().ordinal()!=hsToCompare.getSuit().ordinal()){
						return false;
					}
				}
			}
		}
		return equal;
	}
	public boolean equals(Object o){
		HandStrength hs = (HandStrength)o;
		if (this.getScore()!=hs.getScore()){
			return false;
		}
		if (this.getHighCard()!=hs.getHighCard()){
			return false;
		}
		if (this.getLowCard()!=hs.getLowCard()){
			return false;
		}
		if (this.getSuit()!=hs.getSuit()){
			return false;
		}
		if (this.getHandStrength()!=hs.getHandStrength()){
			return false;
		}
		if (this.getKickers().size()!=hs.getKickers().size()){
			return false;
		}
		for (int index=0;index<this.getKickers().size();index++){
			if (this.getKickers().get(index)!=hs.getKickers().get(index)){
				return false;
			}
		}
		return true;
	}
	public HandStrength compareTo(HandStrength hsToCompare){
		if (this.getScore()>hsToCompare.getScore()){
			return hsToCompare;
		}
		if (this.getScore()<hsToCompare.getScore()){
			return this;
		}
		if (this.getScore()==hsToCompare.getScore()){
			if (this.getHighCard().ordinal()>hsToCompare.getHighCard().ordinal()){
				return hsToCompare;
			}
			if (this.getHighCard().ordinal()<hsToCompare.getHighCard().ordinal()){
				return this;
			}
			if (this.getHighCard().ordinal()==hsToCompare.getHighCard().ordinal()){
				if (this.getLowCard().ordinal()>hsToCompare.getLowCard().ordinal()){
					return hsToCompare;
				}
				if (this.getLowCard().ordinal()<hsToCompare.getLowCard().ordinal()){
					return this;
				}
				if (this.getLowCard().ordinal()==hsToCompare.getLowCard().ordinal()){
					for (int index=0;index<this.getKickers().size();index++){
						if (this.getKickers().get(index).getRank().ordinal()>hsToCompare.getKickers().get(index).getRank().ordinal()){
							return hsToCompare;
						}
						if (this.getKickers().get(index).getRank().ordinal()<hsToCompare.getKickers().get(index).getRank().ordinal()){
							return this;
						}
					}
					if (this.getSuit().ordinal()>hsToCompare.getSuit().ordinal()){
						return hsToCompare;
					}
					if (this.getSuit().ordinal()<hsToCompare.getSuit().ordinal()){
						return this;
					}
				}
			}
		}
		return new HandStrength();
	}
}
