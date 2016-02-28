package base;
import java.util.ArrayList;
public class HandStrength {
	private Card.Rank highCard;
	private Card.Rank lowCard;
	ArrayList<Card> kickers = new ArrayList<Card>();
	private String handStrength;
	private int Score;
	final public static String failedHand="Whiff";
	public HandStrength(Card.Rank highCard, Card.Rank lowCard, ArrayList<Card> kickers, String handStrength,int Score){
		this.highCard=highCard;
		this.lowCard=lowCard;
		this.kickers=kickers;
		this.handStrength=handStrength;
	}
	public HandStrength(){
		this.handStrength=failedHand;
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
	
	public String toString(){
		String tempString="";
		tempString="Hand Type: "+getHandStrength();
		tempString+="\nHigh Card: "+getHighCard();
		tempString+="\nLow Card: "+getLowCard();
		tempString+="\nKickers: "+getKickers();
		return (tempString);
	}
}
