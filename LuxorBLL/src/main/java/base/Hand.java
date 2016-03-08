package base;

import java.util.*;
import java.lang.reflect.Method;
import exceptions.DeckException;
import exceptions.HandException;

public class Hand{
	private ArrayList<Card> hand = new ArrayList<Card>();
	public static enum HandCount{
		FirstCard(0),SecondCard(2),Pair(2),ThreeOfAKind(3),FourOfAKind(4);
		private int handCount;
		private HandCount(int handCount){
			this.handCount=handCount;
		}
	}
	/**
	 * Creates a an array of card objects named hand
	 */
	public Hand() {
	}

	/**
	 * @param deck
	 * @throws DeckException
	 * Adds a card to the hand by drawing from the deck
	 */
	public void addCard(Deck deck) throws DeckException {
		// Adds a card to the hand
		hand.add(deck.draw());
	}

	/**
	 * Sorts the hand based on the rank of each card from highest rank (KING) to 
	 * lowest rank (ACE)
	 */
	public void sortRank() {
		// Sorts based on rank
		int size = this.hand.size();
		boolean swapped;
		do {
			swapped = false;
			for (int index = 0; index < size - 1; index++) {
				if (this.hand.get(index).getRank().ordinal() < this.hand.get(index + 1).getRank().ordinal()) {
					Card tempCard = this.hand.get(index);
					this.hand.set(index, this.hand.get(index + 1));
					this.hand.set(index + 1, tempCard);
					swapped = true;
				}
			}
			size--;
		} while (swapped);
	}

	/**
	 * Sorts the hand based on the suit of each card
	 */
	public void sortSuit() {
		// Sorts based on suit
		// To sort by both suit and rank, call sortRank and then sortSuit
		int size = this.hand.size();
		boolean swapped;
		do {
			swapped = false;
			for (int index = 0; index < size - 1; index++) {
				if (this.hand.get(index).getSuit().ordinal() < this.hand.get(index + 1).getSuit().ordinal()) {
					Card tempCard = this.hand.get(index);
					this.hand.set(index, this.hand.get(index + 1));
					this.hand.set(index + 1, tempCard);
					swapped = true;
				}
			}
			size--;
		} while (swapped);
	}

	/**
	 * @param currentHand
	 * @return
	 * Checks to see that the the hand is in order by descending rank. If not, the method returns false
	 */
	public boolean inOrderDescending(Hand currentHand) {
		for (int index = 0; index < currentHand.hand.size() - 1; index++) {
			// If the last card is an Ace then stop
			if (currentHand.hand.get(index).getRank().ordinal() != currentHand.hand.get(index + 1).getRank().ordinal() + 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param currentHand
	 * @return
	 * Checks to see if all the suits in the hand are the same i.e. set up for a flush
	 */
	public boolean suitCheck(Hand currentHand) {
		// Checks if all the cards are of the same suite
		for (int index = 0; index < currentHand.hand.size() - 1; index++) {
			if (currentHand.hand.get(index).getSuit() != currentHand.hand.get(index + 1).getSuit()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param currentHand
	 * @return
	 * Creates an array of integers and uses the ranks of the cards as the index to count how many of each card rank there are. Returns
	 * the array of card ranks
	 */
	public ArrayList<Integer> sets(Hand currentHand) {
		// Returns an array that tells how many cards of
		// each rank are in the hand
		ArrayList<Integer> duplicates = new ArrayList<Integer>();
		for (Card.Rank rank : Card.Rank.values()){
			duplicates.add(0);
		}
		// 1 is Ace, 13 is King
		for (int index = 0; index < currentHand.hand.size(); index++) {
			// Use the value of the current cards
			// rank to increment an array value to
			// find duplicates
			duplicates.set(currentHand.hand.get(index).getRank().ordinal(),
					duplicates.get(currentHand.hand.get(index).getRank().ordinal())+1);
		}
		return duplicates;

	}

	/**
	 * @param rankNum
	 * @returns rank
	 * Converts an integer to a corresponding card rank. Passing in an 11 would return JACK
	 */
	public Card.Rank numToRank(int rankNum) {
		// Returns the rank of a Card given its numerical
		// value
		for (Card.Rank rank : Card.Rank.values()) {
			if (rank.ordinal() == rankNum)
				return rank;
		}
		// Default return if rank is not found
		return Card.Rank.ACE;
	}

	/**
	 * @param currentHand
	 * @returns 100 if true
	 * Tests the hand of cards to see if a Royal Flush condition is met (5 descending cards starting at the highest all with the same suit)
	 */
	public HandStrength isRoyalFlush(Hand currentHand) {
		if (currentHand.hand.get(Hand.HandCount.FirstCard.ordinal()).getRank() == Card.Rank.KING)
			if (inOrderDescending(currentHand) && suitCheck(currentHand)) {
				HandStrength hs = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN,currentHand.hand.get(Hand.HandCount.FirstCard.ordinal()).getSuit(), new ArrayList<Card>(), "Royal Flush",
						100);
				return hs;
			}
		return (new HandStrength());
	}

	/**
	 * @param currentHand
	 * @returns 90 if true
	 * Tests the hand of cards to see if a Straight Flush condition is met (5 descending cards not starting at the highest rank all with the same suit)
	 */
	public HandStrength isStraightFlush(Hand currentHand) {
		if (suitCheck(currentHand) && inOrderDescending(currentHand)) {
			return (new HandStrength(currentHand.hand.get(0).getRank(),
					currentHand.hand.get(1).getRank(),currentHand.hand.get(Hand.HandCount.FirstCard.ordinal()).getSuit(), new ArrayList<Card>(),
					"Straight Flush", 90));
		}
		return (new HandStrength());
	}

	/**
	 * @param currentHand
	 * @returns 80 if true
	 * Tests the hand of cards to see if the Four of a Kind condition is met (4 of one rank and 1 kicker)
	 */
	public HandStrength isFourOfAKind(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);

		if (duplicates.contains(Hand.HandCount.FourOfAKind.ordinal())) {
			Card.Rank highCard = numToRank(duplicates.indexOf(Hand.HandCount.FourOfAKind.ordinal()));
			Card.Rank lowCard = Card.Rank.ACE;
			Card.Suit suit = Card.Suit.Clubs;
			ArrayList<Card> kickers = new ArrayList<Card>();
			boolean foundHighCard=true;
			boolean foundLowCard=true;
			for (int index=0;index<currentHand.hand.size();index++){
				if (currentHand.hand.get(index).getRank() == highCard && foundHighCard) {
					suit = currentHand.hand.get(index).getSuit();
					foundHighCard=false;
				}
				else if (foundLowCard && currentHand.hand.get(index).getRank()!=highCard){
					lowCard=currentHand.hand.get(index).getRank();
					foundLowCard=false;
				}
				else if (currentHand.hand.get(index).getRank()!=highCard && currentHand.hand.get(index).getRank()!=lowCard){
					kickers.add(currentHand.hand.get(index));
				}
			}
			return (new HandStrength(highCard, lowCard,suit, kickers, "Four of a Kind", 80));
		}

		return (new HandStrength());
	}

	/**
	 * @param currentHand
	 * @returns 70 if true
	 * Tests the hand of cards to see if the current hand is a Full House (3 of one rank and 2 of another)
	 */
	public HandStrength isFullHouse(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(Hand.HandCount.ThreeOfAKind.ordinal()) && duplicates.contains(Hand.HandCount.Pair.ordinal())) {
			Card.Rank highCard = numToRank(duplicates.indexOf(3));
			Card.Rank lowCard = numToRank(duplicates.indexOf(2));
			Card.Suit suit = Card.Suit.Clubs;
			ArrayList<Card> kickers = new ArrayList<Card>();
			boolean foundHighCard=true;
			for (int index=0;index<currentHand.hand.size();index++){
				if (currentHand.hand.get(index).getRank() == highCard && foundHighCard) {
					suit = currentHand.hand.get(index).getSuit();
					foundHighCard=false;
				}
			}
			return (new HandStrength(highCard, lowCard,suit, kickers, "Full House", 70));
		}
		return (new HandStrength());
	}

	/**
	 * @param currentHand
	 * @returns 60 if true
	 * Tests to see if the hand of cards is a Flush (all 5 cards have the same suit)
	 */
	public HandStrength isFlush(Hand currentHand) {
		if (suitCheck(currentHand)) {
			Card.Rank highCard = currentHand.hand.get(0).getRank();
			Card.Suit suit = currentHand.hand.get(0).getSuit();
			Card.Rank lowCard = currentHand.hand.get(1).getRank();
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 2; index < currentHand.hand.size(); index++) {
				kickers.add(currentHand.hand.get(index));
			}
			return (new HandStrength(highCard, lowCard,suit, kickers, "Flush", 60));
		}
		return (new HandStrength());
	}

	/**
	 * checks if hand is a straight
	 * returns hand strength of 50 if it's a straight
	 * 
	 */
	public HandStrength isStraight(Hand currentHand) {
		if (inOrderDescending(currentHand)) {
			Card.Rank highCard = currentHand.hand.get(0).getRank();
			Card.Suit suit = currentHand.hand.get(0).getSuit();
			Card.Rank lowCard = currentHand.hand.get(1).getRank();
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 2; index < currentHand.hand.size(); index++) {
				kickers.add(currentHand.hand.get(index));
			}
			return (new HandStrength(highCard, lowCard, suit, kickers, "Straight", 50));
		}
		return (new HandStrength());
	}

	/**
	 * checks to see if hand is three of a kind
	 * returns hand strength of 40 if true
	 * 
	 */
	public HandStrength isThreeOfAKind(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(3)) {
			Card.Rank highCard = numToRank(duplicates.indexOf(3));
			Card.Rank lowCard = Card.Rank.ACE;
			Card.Suit suit = Card.Suit.Clubs;
			boolean foundLow=false;
			boolean foundSuit=false;
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank()==highCard && !foundSuit){
					suit = currentHand.hand.get(index).getSuit();
					foundSuit=true;
				}
				if (currentHand.hand.get(index).getRank() != highCard && !foundLow) {
					lowCard = currentHand.hand.get(index).getRank();
					foundLow=true;
				}
			}
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank()!=highCard && currentHand.hand.get(index).getRank()!=lowCard){
					kickers.add(currentHand.hand.get(index));
				}
			}
			return (new HandStrength(highCard, lowCard, suit, kickers, "Three of a Kind", 40));
		}
		return (new HandStrength());
	}
	
	/**
	 * checks if hand is a two pair
	 * returns hand strength of 30 if true
	 * 
	 */
	public HandStrength isTwoPair(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(2)) {
			int firstIndex=duplicates.indexOf(2);
			int secondIndex=duplicates.lastIndexOf(2);
			if (firstIndex!=secondIndex){
				Card.Rank highCard=numToRank(secondIndex);
				Card.Rank lowCard=numToRank(firstIndex);
				Card.Suit suit = Card.Suit.Clubs;
				boolean foundSuit=false;
				ArrayList<Card> kickers = new ArrayList<Card>();
				for (int index = 0; index < currentHand.hand.size(); index++) {
					if (currentHand.hand.get(index).getRank()==highCard &&!foundSuit){
						suit=currentHand.hand.get(index).getSuit();
						foundSuit=true;
					}
					if (currentHand.hand.get(index).getRank()!=highCard && currentHand.hand.get(index).getRank()!=lowCard){
						kickers.add(currentHand.hand.get(index));
					}
				}
				return (new HandStrength(highCard, lowCard, suit, kickers, "Two Pair", 30));
			}
		}
		return (new HandStrength());
	}
	
	/**
	 * checks if hand contains one pair
	 * returns hand strength of 20 if true
	 * 
	 */
	public HandStrength isOnePair(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(2)) {
			Card.Rank highCard=numToRank(duplicates.indexOf(2));
			//default ACE for lowCard
			Card.Rank lowCard= Card.Rank.ACE;
			Card.Suit suit = Card.Suit.Clubs;
			boolean foundLow=false;
			boolean foundSuit=false;
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank()!=highCard && !foundLow){
					lowCard=currentHand.hand.get(index).getRank();
					foundLow=true;
				}
				if (currentHand.hand.get(index).getRank()==highCard && !foundSuit){
					suit=currentHand.hand.get(index).getSuit();
					foundSuit=true;
				}
				
			}
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank()!=highCard && currentHand.hand.get(index).getRank()!=lowCard){
					kickers.add(currentHand.hand.get(index));
				}
			}
			return (new HandStrength(highCard, lowCard, suit, kickers, "One Pair", 20));
		}
		return (new HandStrength());
	}
	
	/**
	 * catch all hand strength
	 * returns 10 if all other hand strength tests fail
	 * 
	 */
	public HandStrength noPair(Hand currentHand) {
		
		Card.Rank highCard= currentHand.hand.get(0).getRank();
		Card.Suit suit = currentHand.hand.get(0).getSuit();
		Card.Rank lowCard= currentHand.hand.get(1).getRank();
		ArrayList<Card> kickers = new ArrayList<Card>();
		for (int index = 2; index < currentHand.hand.size(); index++) {
				kickers.add(currentHand.hand.get(index));
		}
		return (new HandStrength(highCard,lowCard,suit,kickers,"No Pair",10));
	}
	
	/**
	 * returns string describing each card in hand
	 */
	@Override
	public String toString(){
		String tempString="";
		for (Card card:this.hand){
			tempString+=card.toString()+"\n";
		}
		return tempString;
	}
	
	/**
	 * sorts hand in order of suit and rank
	 * uses hand strength tests to find hand score
	 * returns hand score
	 * 
	 */
	public static HandStrength judge(Hand currentHand){
		if (currentHand.hand.size()!=5){
			//Throw error
			return new HandStrength();
		}
		HandStrength hs=new HandStrength();
		currentHand.sortSuit();
		currentHand.sortRank();
		//System.out.println(currentHand.toString());
		HandStrength failedHand=new HandStrength();
		Class[] judgeArgs = new Class[1];
		judgeArgs[0] =Hand.class;
		try {
			Class judgeHands = Hand.class;
			Object obj = judgeHands.newInstance();
			for (HandStrength.HandScores handName : HandStrength.HandScores.values()){
				Method method = judgeHands.getDeclaredMethod(handName.getHandType(), new Class[]{Hand.class});
				HandStrength handStrength =(HandStrength)method.invoke(obj, currentHand);
				if (!(handStrength.getHandStrength()==failedHand.getHandStrength())){
					return handStrength;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("");
		
		/*
		 * isRoyalFlush;
		 * isStraightFlush; 
		 * isFourOfAKind; 
		 * isFullHouse; 
		 * isFlush;
		 * isStraight; 
		 * isThreeOfAKind; 
		 * isTwoPair; 
		 * isOnePair; 
		 * noPair;
		 */
		// High Card - Highest card
		// Low Card - two pairs or Full House
		// Hand Strength - Royal Flush Down
		// Kickers - Cards that don't matter
		return hs;
	}
	
	/**
	 * finds hand strength of array list of hands
	 * returns hand score of each
	 * 
	 */
	public static HandStrength judge(ArrayList<Hand> currentHands) {
		ArrayList<HandStrength> handStrengths = new ArrayList<HandStrength>();
		for (Hand hand : currentHands){
			handStrengths.add(judge(hand));
		}
		while (handStrengths.size()>1){
			HandStrength lowHand= handStrengths.get(0).compareTo(handStrengths.get(1));
			int index=handStrengths.indexOf(lowHand);
			handStrengths.remove(index);
		}
		HandStrength hs=handStrengths.get(0);
		return hs;
	}	 
}