package Testing;
import static org.junit.Assert.*;

import org.junit.Test;

import gameEntities.Deck;
import gameEntities.Card;
import gameEntities.Hand;

public class DeckTest {

	@Test
	public void test() {
		Deck testDeck= new Deck();
		assertEquals(true, testDeck.getCurrentDeck().isEmpty());
		testDeck.putInto(new Card(3,'p'));
		Deck testDeck2= new Deck();
		testDeck2.DeckBuild();
		assertEquals(false, testDeck2.getCurrentDeck().isEmpty());
		assertEquals( 110, testDeck2.getCurrentDeck().size());
	}
	
	@Test
	public void handtest(){
		Deck testDeck= new Deck();
		testDeck.DeckBuild();
		Hand testhand= new Hand();
		testhand.initialize();
		testhand.DrawCard(testDeck);
		assertEquals(109, testDeck.remaining());	
	}
	
	@Test
	public void CardNumtest(){
		Deck CardNum = new Deck();
		CardNum.DeckBuild();
		CardNum.shuffle();
		assertEquals( 110, CardNum.remaining());
		
		Card cardtmp = new Card(3,'p');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(106, CardNum.remaining());
		
		cardtmp = new Card(4,'p');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(102, CardNum.remaining());
		
		cardtmp = new Card(5,'p');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(98, CardNum.remaining());
		
		cardtmp = new Card(7,'p');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(96, CardNum.remaining());
		
		cardtmp = new Card(3,'r');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(90, CardNum.remaining());
		
		cardtmp = new Card(4,'r');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(84, CardNum.remaining());
		
		cardtmp = new Card(5,'r');		
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(82, CardNum.remaining());
		
		cardtmp = new Card(2,'b');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(78, CardNum.remaining());
		
		cardtmp = new Card(3,'b');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(74, CardNum.remaining());
		
		cardtmp = new Card(4,'b');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(70, CardNum.remaining());
		
		cardtmp = new Card(5,'b');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(68, CardNum.remaining());
		
		cardtmp = new Card(2,'y');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(64, CardNum.remaining());
		
		cardtmp = new Card(3,'y');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(56, CardNum.remaining());
		
		cardtmp = new Card(4,'y');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(54, CardNum.remaining());
		
		cardtmp = new Card(1,'g');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(40, CardNum.remaining());
		
		cardtmp = new Card(2,'w');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(32, CardNum.remaining());
		
		cardtmp = new Card(3,'w');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(24, CardNum.remaining());
		
		cardtmp = new Card(6,'w');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(20, CardNum.remaining());
		

		cardtmp = new Card(16,'a');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(17, CardNum.remaining());
		
		cardtmp = new Card(17,'a');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(15, CardNum.remaining());
		
		for(int i=0; i<=15; i++){
			cardtmp = new Card(i,'a');
			while (CardNum.containsCard(cardtmp)){
				CardNum.CardRemove(cardtmp);
			}
			assertEquals(15-i, CardNum.remaining());
		}
		
	}


}
