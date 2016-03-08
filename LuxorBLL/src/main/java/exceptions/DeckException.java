package exceptions;
import base.Deck;
public class DeckException extends Exception {
	private Deck deck;
	public DeckException(Deck d){
		super();
		this.deck=deck;
	}
	public Deck getDeck(){
		return deck;
	}
}
