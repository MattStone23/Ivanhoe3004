import static org.junit.Assert.*;

import org.junit.Test;


public class DeckTest {

	@Test
	public void test() {
		Deck testDeck= new Deck();
		assertEquals(true, testDeck.currentDeck.isEmpty());
		Deck testDeck2= new Deck();
		testDeck2.DeckBuild();
		assertEquals(false, testDeck2.currentDeck.isEmpty());
		assertEquals( 110, testDeck2.currentDeck.size());
	}

}
