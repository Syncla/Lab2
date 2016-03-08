package base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;

import exceptions.DeckException;

public class Deck_Test {

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
	public void DrawTest() throws DeckException {
		Deck d = new Deck();
		Object o = d.draw();

		if (!(o instanceof Card)) {
			fail("Object drawn from deck isn't card");
		}
	}

	@Test(expected = DeckException.class)
	public void OverDraw() throws Exception {
		Deck d = new Deck();
		Card c = null;
		for (int i = 0; i < 100; i++) {
			c = d.draw();

		}
	}

	@Test
	public void NoramlDeckSizeTest() {
		int iExpectedValue = 51;
		int iActualValue;

		try {
			// Load the Class into 'c'
			Class<?> c = Class.forName("base.Deck");
			// Create a new instance 't' from the no-arg Deck constructor
			Object t = c.newInstance();
			// Load 'mDraw' with the 'Draw' method (no args);
			Method methodDraw = c.getDeclaredMethod("draw", null);
			Method methodCardsLeft = c.getDeclaredMethod("cardsLeft", null);
			// Change the visibilty of 'GetDeckSize' to true *Good Grief!*
			methodCardsLeft.setAccessible(true);

			// invoke 'Draw'
			Object oDraw = methodDraw.invoke(t, null);

			// invoke 'GetDeckSize'
			Object oGetDeckSize = methodCardsLeft.invoke(t, null);

			iActualValue = ((Integer) oGetDeckSize).intValue();

			assertEquals(iExpectedValue, iActualValue);
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}