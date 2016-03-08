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
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isRoyalFlush failed", Hand.judge(handToTest).equals(royalFlush));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testStraightFlush() {
		Hand handToTest = new Hand();
		HandStrength straightFlush = new HandStrength(Card.Rank.QUEEN, Card.Rank.JACK, Card.Suit.Clubs,
				new ArrayList<Card>(), "Straight Flush", 90);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.JACK, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.EIGHT, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isStraightFlush failed", Hand.judge(handToTest).equals(straightFlush));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsStraightFlushFail() {
		Hand handToTest = new Hand();
		HandStrength straightFlush = new HandStrength(Card.Rank.QUEEN, Card.Rank.JACK, Card.Suit.Clubs,
				new ArrayList<Card>(), "Straight Flush", 90);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isStraightFlush failed", Hand.judge(handToTest).equals(straightFlush));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsFourOfAKind() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		HandStrength fourOfAKind = new HandStrength(Card.Rank.KING, Card.Rank.ACE, Card.Suit.Diamonds,
				kickers, "Four Of A Kind", 80);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Diamonds));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Spades));
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isFourOfAKind failed", Hand.judge(handToTest).equals(fourOfAKind));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsFourOfAKindFail() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.ACE,Card.Suit.Clubs));
		HandStrength fourOfAKind = new HandStrength(Card.Rank.KING, Card.Rank.ACE, Card.Suit.Clubs,
				kickers, "Four Of A Kind", 80);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isFourOfAKind failed", Hand.judge(handToTest).equals(fourOfAKind));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsFullHouse() {
		Hand handToTest = new Hand();
		HandStrength fullHouse = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN, Card.Suit.Diamonds,
				new ArrayList<Card>(), "Full House", 70);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Diamonds));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Spades));
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Diamonds));
			field.set(handToTest, hand);
			assertTrue("isFullHouse failed", Hand.judge(handToTest).equals(fullHouse));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsFullHouseFail() {
		Hand handToTest = new Hand();
		HandStrength fullHouse = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Full House", 70);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isFullHouse failed", Hand.judge(handToTest).equals(fullHouse));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsFlush() {
		Hand handToTest = new Hand();
		HandStrength flush = new HandStrength(Card.Rank.NINE, Card.Rank.SEVEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Flush", 60);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.THREE, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isFlush failed", Hand.judge(handToTest).equals(flush));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsFlushFail() {
		Hand handToTest = new Hand();
		HandStrength flush = new HandStrength(Card.Rank.NINE, Card.Rank.SEVEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Flush", 60);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isflush failed", Hand.judge(handToTest).equals(flush));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testStraight() {
		Hand handToTest = new Hand();
		HandStrength straight = new HandStrength(Card.Rank.JACK, Card.Rank.TEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Straight", 50);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.EIGHT, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Diamonds));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Spades));
			hand.add(new Card(Card.Rank.JACK, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isStraight failed", Hand.judge(handToTest).equals(straight));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsStraightFail() {
		Hand handToTest = new Hand();
		HandStrength straight = new HandStrength(Card.Rank.JACK, Card.Rank.TEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Straight", 50);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isStraight failed", Hand.judge(handToTest).equals(straight));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsThreeOfAKind() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.NINE,Card.Suit.Clubs));
		HandStrength threeOfAKind = new HandStrength(Card.Rank.KING, Card.Rank.TEN, Card.Suit.Diamonds,
				kickers, "Three of a Kind", 40);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Diamonds));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isThreeOfAKind failed", Hand.judge(handToTest).equals(threeOfAKind));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsThreeOfAKindFail() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.TEN,Card.Suit.Hearts));
		kickers.add(new Card(Card.Rank.NINE,Card.Suit.Clubs));
		HandStrength threeOfAKind = new HandStrength(Card.Rank.KING, Card.Rank.ACE, Card.Suit.Clubs,
				kickers, "Three of a Kind", 80);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isThreeOfAKind failed", Hand.judge(handToTest).equals(threeOfAKind));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsTwoPair() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.NINE,Card.Suit.Clubs));
		HandStrength twoPair = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN, Card.Suit.Clubs,
				kickers, "Two Pair", 30);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isTwoPair failed", Hand.judge(handToTest).equals(twoPair));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsTwoPairFail() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.NINE,Card.Suit.Clubs));
		HandStrength twoPair = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN, Card.Suit.Clubs,
				kickers, "Two Pair", 30);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isTwoPair failed", Hand.judge(handToTest).equals(twoPair));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsOnePair() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.TEN,Card.Suit.Clubs));
		kickers.add(new Card(Card.Rank.NINE,Card.Suit.Clubs));
		HandStrength onePair = new HandStrength(Card.Rank.KING, Card.Rank.JACK, Card.Suit.Clubs,
				kickers, "One Pair", 20);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.JACK, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isOnePair failed", Hand.judge(handToTest).equals(onePair));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsOnePairFail() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.TEN,Card.Suit.Clubs));
		kickers.add(new Card(Card.Rank.NINE,Card.Suit.Clubs));
		HandStrength onePair = new HandStrength(Card.Rank.KING, Card.Rank.JACK, Card.Suit.Clubs,
				kickers, "One Pair", 20);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertFalse("isOnePair failed", Hand.judge(handToTest).equals(onePair));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testIsNoPair() {
		Hand handToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.FOUR,Card.Suit.Hearts));
		kickers.add(new Card(Card.Rank.TWO,Card.Suit.Clubs));
		kickers.add(new Card(Card.Rank.ACE,Card.Suit.Clubs));
		HandStrength noPair = new HandStrength(Card.Rank.KING, Card.Rank.SEVEN, Card.Suit.Clubs,
				kickers, "No Pair", 10);
		try {

			Field field = handToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.ACE, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TWO, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.FOUR, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.SEVEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			field.set(handToTest, hand);
			assertTrue("isNoPair failed", Hand.judge(handToTest).equals(noPair));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Test
	public void testJudgeArray() {
		Hand firstHandToTest = new Hand();
		Hand secondHandToTest = new Hand();
		ArrayList<Card> kickers = new ArrayList<Card>();
		kickers.add(new Card(Card.Rank.TEN,Card.Suit.Clubs));
		kickers.add(new Card(Card.Rank.NINE,Card.Suit.Clubs));
		HandStrength onePair = new HandStrength(Card.Rank.KING, Card.Rank.JACK, Card.Suit.Clubs,
				kickers, "One Pair", 20);
		HandStrength royalFlush = new HandStrength(Card.Rank.KING, Card.Rank.QUEEN, Card.Suit.Clubs,
				new ArrayList<Card>(), "Royal Flush", 100);
		try {

			Field field = secondHandToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.QUEEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.JACK, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			field.set(secondHandToTest, hand);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {

			Field field = firstHandToTest.getClass().getDeclaredField("hand");
			field.setAccessible(true);
			ArrayList<Card> hand = new ArrayList<Card>();
			hand.add(new Card(Card.Rank.KING, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.KING, Card.Suit.Hearts));
			hand.add(new Card(Card.Rank.JACK, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.TEN, Card.Suit.Clubs));
			hand.add(new Card(Card.Rank.NINE, Card.Suit.Clubs));
			field.set(firstHandToTest, hand);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ArrayList<Hand> hands= new ArrayList<Hand>();
		hands.add(firstHandToTest);
		hands.add(secondHandToTest);
		assertTrue("Judge ArrayList failed", Hand.judge(hands).equals(royalFlush));
	}
	@Test
	public void testtoString(){
		Hand testhand = new Hand();
		String tempString = "";
		tempString += "KING of Clubs\n";
		tempString += "QUEEN of Clubs\n";
		tempString += "JACK of Clubs\n";
		tempString += "TEN of Clubs\n";
		tempString += "NINE of Clubs\n";
		
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
			assertTrue("toString failed", handToTest.toString().equals(tempString));
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
