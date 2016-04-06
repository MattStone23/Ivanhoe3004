package Testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameEntities.Card;


public class CardTest {
	Card card1 = new Card(2,'W');
	Card card2 = new Card(1,'G');
	Card card3 = new Card(1,'G');
	@Test
	public void test() {
		assertEquals(card1.getValue(),2);
		assertEquals(card1.getColour(),'W');
		assertEquals(card2.getValue(),1);
		assertEquals(card2.getColour(),'G');
	}
	@Test(expected=IllegalArgumentException.class )
	public void test2() {
		new Card(2, 'F');
	}
	@Test//Tests that equals() is overriden properly in card
	public void test3(){
		assertEquals(true, card2.equals(card3));
	}

}
