package base;

import java.util.ArrayList;

import exceptions.DeckException;

public class testing {

	public static void main(String[] args) {
		Deck d = new Deck();
		Deck t = new Deck();
		Hand h = new Hand();
		Hand h1 = new Hand();
		Hand h2= new Hand();
		for (int i=0;t.cardsLeft()>0;i++){
			try {
				System.out.println(t.draw());
			} catch (DeckException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		for (int i = 0; i < 5; i++) {
			try{
				h.addCard(d);
				h1.addCard(d);
				h2.addCard(d);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		ArrayList<Hand> hands = new ArrayList<Hand>();
		hands.add(h1);
		hands.add(h2);
		hands.add(h);
		h.sortSuit();
		h.sortRank();
		// h.sortSuit();
		//System.out.println(hands);
		//System.out.println(Hand.judge(h));
		System.out.println(hands);
		System.out.println(Hand.judge(hands));

	}
}
