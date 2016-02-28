package base;

import java.util.*;

public class Hand {
	ArrayList<Card> hand = new ArrayList<Card>();

	public Hand() {
	}

	public void addCard(Card newCard) {
		// Adds a card to the hand
		hand.add(newCard);
	}

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

	public void sortSuite() {
		// Sorts based on suite
		// To sort by both suite and rank, call sortRank and then sortSuite
		int size = this.hand.size();
		boolean swapped;
		do {
			swapped = false;
			for (int index = 0; index < size - 1; index++) {
				if (this.hand.get(index).getSuite().ordinal() < this.hand.get(index + 1).getSuite().ordinal()) {
					Card tempCard = this.hand.get(index);
					this.hand.set(index, this.hand.get(index + 1));
					this.hand.set(index + 1, tempCard);
					swapped = true;
				}
			}
			size--;
		} while (swapped);
	}

	public boolean decending(Hand currentHand) {
		for (int index = 0; index < currentHand.hand.size() - 1; index++) {
			// If the last card is an Ace then stop
			if (index + 1 == currentHand.hand.size() - 1
					&& currentHand.hand.get(index + 1).getRank() == Card.Rank.ACE) {
				break;
			}
			// If the next card has a rank that is equal to the current card
			// return false (Note, hand should already be in order of highest =
			// index 0, to lowest=last index
			else if (currentHand.hand.get(index).getRank().ordinal() > currentHand.hand.get(index + 1).getRank()
					.ordinal()) {
				return false;
			}
		}
		return true;
	}

	public boolean inOrderDecending(Hand currentHand) {
		for (int index = 0; index < currentHand.hand.size() - 1; index++) {
			// If the last card is an Ace then stop
			if (index + 1 == currentHand.hand.size() - 1
					&& currentHand.hand.get(index + 1).getRank() == Card.Rank.ACE) {
				break;
			} else if (currentHand.hand.get(index).getRank()
					.ordinal() != currentHand.hand.get(index + 1).getRank().ordinal() + 1) {
				return false;
			}
		}
		return true;
	}

	public boolean suiteCheck(Hand currentHand) {
		// Checks if all the cards are of the same suite
		for (int index = 0; index < currentHand.hand.size() - 1; index++) {
			if (currentHand.hand.get(index).getSuite() != currentHand.hand.get(index + 1).getSuite()) {
				return false;
			}
		}
		return true;
	}

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

	public HandStrength isRoyalFlush(Hand currentHand) {
		if (currentHand.hand.get(0).getRank() == Card.Rank.KING)
			if (inOrderDecending(currentHand)) {
				HandStrength hs = new HandStrength(Card.Rank.KING, Card.Rank.ACE, new ArrayList<Card>(), "Royal Flush",
						100);
				return hs;
			}
		return (new HandStrength());
	}

	public HandStrength isStraightFlush(Hand currentHand) {
		if (suiteCheck(currentHand) && inOrderDecending(currentHand)) {
			return (new HandStrength(currentHand.hand.get(0).getRank(),
					currentHand.hand.get(currentHand.hand.size() - 1).getRank(), new ArrayList<Card>(),
					"Straight Flush", 90));
		}
		return (new HandStrength());
	}

	public HandStrength isFourOfAKind(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);

		if (duplicates.contains(4)) {
			Card.Rank highCard = numToRank(duplicates.indexOf(4));
			Card.Rank lowCard;
			if (currentHand.hand.get(0).getRank() == highCard) {
				lowCard = currentHand.hand.get(currentHand.hand.size() - 1).getRank();
			} else {
				lowCard = currentHand.hand.get(0).getRank();
			}
			return (new HandStrength(highCard, lowCard, new ArrayList<Card>(), "Four of a Kind", 80));
		}

		return (new HandStrength());
	}

	public HandStrength isFullHouse(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(3) && duplicates.contains(2)) {
			Card.Rank highCard = numToRank(duplicates.indexOf(3));
			Card.Rank lowCard = numToRank(duplicates.indexOf(2));
			return (new HandStrength(highCard, lowCard, new ArrayList<Card>(), "Full House", 70));
		}
		return (new HandStrength());
	}

	public HandStrength isFlush(Hand currentHand) {
		if (suiteCheck(currentHand)) {
			Card.Rank highCard = currentHand.hand.get(0).getRank();
			Card.Rank lowCard = currentHand.hand.get(1).getRank();
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 2; index < currentHand.hand.size(); index++) {
				kickers.add(currentHand.hand.get(index));
			}
			return (new HandStrength(highCard, lowCard, kickers, "Flush", 60));
		}
		return (new HandStrength());
	}

	public HandStrength isStraight(Hand currentHand) {
		if (inOrderDecending(currentHand)) {
			Card.Rank highCard = currentHand.hand.get(0).getRank();
			Card.Rank lowCard = currentHand.hand.get(1).getRank();
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 2; index < currentHand.hand.size(); index++) {
				kickers.add(currentHand.hand.get(index));
			}
			return (new HandStrength(highCard, lowCard, kickers, "Straight", 50));
		}
		return (new HandStrength());
	}

	public HandStrength isThreeOfAKind(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(3)) {
			Card.Rank highCard = numToRank(duplicates.indexOf(3));
			Card.Rank lowCard = Card.Rank.ACE;
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank() != highCard) {
					lowCard = currentHand.hand.get(index).getRank();
					break;
				}
			}
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank()!=highCard && currentHand.hand.get(index).getRank()!=lowCard){
					kickers.add(currentHand.hand.get(index));
				}
			}
			return (new HandStrength(highCard, lowCard,kickers, "Three of a Kind", 40));
		}
		return (new HandStrength());
	}
	public HandStrength isTwoPair(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(2)) {
			int firstIndex=duplicates.indexOf(2);
			int secondIndex=duplicates.lastIndexOf(2);
			if (firstIndex!=secondIndex){
				Card.Rank highCard=numToRank(duplicates.lastIndexOf(2));
				Card.Rank lowCard=numToRank(duplicates.indexOf(2));
				ArrayList<Card> kickers = new ArrayList<Card>();
				for (int index = 0; index < currentHand.hand.size(); index++) {
					if (currentHand.hand.get(index).getRank()!=highCard && currentHand.hand.get(index).getRank()!=lowCard){
						kickers.add(currentHand.hand.get(index));
					}
				}
				return (new HandStrength(highCard, lowCard,kickers, "Two Pair", 30));
			}
		}
		return (new HandStrength());
	}
	public HandStrength isOnePair(Hand currentHand) {
		ArrayList<Integer> duplicates = sets(currentHand);
		if (duplicates.contains(2)) {
			Card.Rank highCard=numToRank(duplicates.indexOf(2));
			//default ACE for lowCard
			Card.Rank lowCard= Card.Rank.ACE;
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank()!=highCard){
					lowCard=currentHand.hand.get(index).getRank();
				}
			}
			ArrayList<Card> kickers = new ArrayList<Card>();
			for (int index = 0; index < currentHand.hand.size(); index++) {
				if (currentHand.hand.get(index).getRank()!=highCard && currentHand.hand.get(index).getRank()!=lowCard){
					kickers.add(currentHand.hand.get(index));
				}
			}
			return (new HandStrength(highCard, lowCard,kickers, "One Pair", 20));
		}
		return (new HandStrength());
	}
	public HandStrength noPair(Hand currentHand) {
		Card.Rank highCard= currentHand.hand.get(0).getRank();
		Card.Rank lowCard= currentHand.hand.get(1).getRank();
		ArrayList<Card> kickers = new ArrayList<Card>();
		for (int index = 2; index < currentHand.hand.size(); index++) {
				kickers.add(currentHand.hand.get(index));
		}
		return (new HandStrength(highCard,lowCard,kickers,"No Pair",10));
	}
	public String toString(){
		String tempString="";
		for (Card card:this.hand){
			tempString+=card.toString()+"\n";
		}
		return tempString;
	}
	public static HandStrength judge(Hand currentHand) {
		HandStrength hs=new HandStrength();
		currentHand.sortRank();
		HandStrength failedHand=new HandStrength();
		if (currentHand.isRoyalFlush(currentHand).getHandStrength()==failedHand.getHandStrength()){
			if (currentHand.isStraightFlush(currentHand).getHandStrength()==failedHand.getHandStrength()){
				if (currentHand.isFourOfAKind(currentHand).getHandStrength()==failedHand.getHandStrength()){
					if (currentHand.isFullHouse(currentHand).getHandStrength()==failedHand.getHandStrength()){
						if (currentHand.isFlush(currentHand).getHandStrength()==failedHand.getHandStrength()){
							if (currentHand.isStraight(currentHand).getHandStrength()==failedHand.getHandStrength()){
								if (currentHand.isThreeOfAKind(currentHand).getHandStrength()==failedHand.getHandStrength()){
									if (currentHand.isTwoPair(currentHand).getHandStrength()==failedHand.getHandStrength()){
										if (currentHand.isOnePair(currentHand).getHandStrength()==failedHand.getHandStrength()){
											hs=currentHand.noPair(currentHand);
										}
										else{
											hs=currentHand.isOnePair(currentHand);
										}
									}
									else{
										hs=currentHand.isTwoPair(currentHand);
									}
								}
								else{
									hs=currentHand.isThreeOfAKind(currentHand);
								}
							}
							else{
								hs=currentHand.isStraight(currentHand);
							}
						}
						else{
							hs=currentHand.isFlush(currentHand);
						}
					}
					else{
						hs=currentHand.isFullHouse(currentHand);
					}
				}
				else{
					hs=currentHand.isFourOfAKind(currentHand);
				}
			}
			else{
				hs=currentHand.isStraightFlush(currentHand);
			}
		}
		else{
			hs=currentHand.isRoyalFlush(currentHand);
		}
		/*
		 * isRoyalFlush;
		 * isStraightFlush; 
		 * isFourOfAKind; 
		 * isFullHouse; 
		 * isFlush;
		 * isStraight; 
		 * isThreeOfAKind; 
		 * ---
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

}