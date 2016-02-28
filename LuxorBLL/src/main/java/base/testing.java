package base;

public class testing {

	public static void main(String[] args) {
		Deck d =new Deck();
		Hand h=new Hand();
		for (int i=0;i<5;i++){
			h.addCard(d.draw());
		}
		h.sortRank();
		//h.sortSuite(h);
		System.out.println(h);
		System.out.println(Hand.judge(h));
		
	}

}
