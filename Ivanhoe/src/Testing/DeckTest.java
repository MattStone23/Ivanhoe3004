package Testing;
import static org.junit.Assert.*;

import org.junit.Test;

import gameEntities.Deck;
import gameEntities.Card;

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
	@Test
	public void CardNumtest(){
		Deck CardNum = new Deck();
		CardNum.DeckBuild();
		assertEquals( 110, CardNum.remaining());
		
		Card cardtmp = new Card(3,'p');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
			System.out.println("daf");
		}
		assertEquals(106, CardNum.remaining());
	
	}


}
