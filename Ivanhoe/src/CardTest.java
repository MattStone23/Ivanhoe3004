import static org.junit.Assert.*;

import org.junit.Test;


public class CardTest {
	Card card1 = new Card(2,'w');
	Card card2 = new Card(1,'g');
	@Test
	public void test() {
		assertEquals(card1.getValue(),2);
		assertEquals(card1.getColour(),'w');
		assertEquals(card2.getValue(),1);
		assertEquals(card2.getColour(),'g');
	}
	@Test(expected=IllegalArgumentException.class )
	public void test2() {
		Card badCard = new Card(2, 'f');
	}

}
