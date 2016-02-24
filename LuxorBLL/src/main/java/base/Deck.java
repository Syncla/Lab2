package base;
import java.util.*;
public class Deck extends Card {
	ArrayList<Card> Deck=new ArrayList<Card>();
	public Deck(){
		
	}
	public void shuffle(){
		
	}
	public Card draw(){
		return this.Deck.remove(0);
	}
}
