package base;

import java.util.ArrayList;
import java.util.Collections;
import exceptions.DeckException;

public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>();

	/*
	 * creates a deck of 52 unique cards
	 */
	public Deck() {
		for (Card.Suit suit : Card.Suit.values()) {
			for (Card.Rank rank : Card.Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
		shuffle(deck);
	}

	private int cardsLeft() {
		return deck.size();
	}

	/*
	 * shuffles the deck
	 */
	public static void shuffle(ArrayList<Card> deck) {
		// Using Fisher Yates shuffle
		int size = deck.size();
		int randomIndex;
		for (int i = 0; i < size; i++) {
			do {
				randomIndex = (int) (Math.random() * size);
			} while (randomIndex == i);
			Card tempCard = deck.get(randomIndex);
			deck.set(randomIndex, deck.get(i));
			deck.set(i, tempCard);
		}
	}

	/**
	 * removes card from deck
	 * returns removed card
	 **/
	public Card draw() throws DeckException {
		if (cardsLeft() == 0) {
			throw new DeckException(this);
		}
		return this.deck.remove(0);
	}
}
