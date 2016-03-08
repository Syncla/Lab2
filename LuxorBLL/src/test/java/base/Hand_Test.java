package base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.lang.reflect.*;

public class Hand_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsRoyalFlush() {
		Hand handToTest = new Hand();
		HandStrength royalFlush = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Royal Flush", 100);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.JACK, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isRoyalFlush failed", Hand.judge(handToTest).equals(royalFlush));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsRoyalFlushFail() {
		Hand handToTest = new Hand();
		HandStrength royalFlush = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Royal Flush", 100);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.JACK, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isRoyalFlush failed", Hand.judge(handToTest).equals(royalFlush));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/*
	 * Deck d = new Deck() 
	 * Object o = null; 
	 * o=d.Draw(); 
	 * if (!o instanceof Card)){ 
	 * fail("Object drawn from deck isn't a Card")
	 *  }
	 */

}
