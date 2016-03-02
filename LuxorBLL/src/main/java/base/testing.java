package base;

import java.util.ArrayList;

public class testing {

	public static void main(String[] args) {
		Deck d = new Deck();
		Hand h = new Hand();
		Hand h1 = new Hand();
		Hand h2= new Hand();
		
		for (int i = 0; i < 5; i++) {
			h.addCard(d.draw());
			h1.addCard(d.draw());
			h2.addCard(d.draw());
			
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
