package Testing;
import static org.junit.Assert.*;

import org.junit.Test;

import gameEntities.Deck;


public class DeckTest {

	@Test
	public void test() {
		Deck testDeck= new Deck();
		assertEquals(true, testDeck.getCurrentDeck().isEmpty());
		Deck testDeck2= new Deck();
		testDeck2.DeckBuild();
		assertEquals(false, testDeck2.getCurrentDeck().isEmpty());
		assertEquals( 110, testDeck2.getCurrentDeck().size());
	}

}
