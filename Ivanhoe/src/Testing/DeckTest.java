package Testing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameEntities.Card;
import gameEntities.Deck;
import gameEntities.Hand;

public class DeckTest {

	@Test
	public void test() {
		Deck testDeck= new Deck();
		assertEquals(true, testDeck.getCurrentDeck().isEmpty());
		testDeck.putInto(new Card(3,'P'));
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
		testhand.DrawCard(testDeck);
		assertEquals(109, testDeck.remaining());	
	}
	
	@Test
	public void seededShuffleTest(){
		Deck testDeck1 = new Deck();
		Deck testDeck2 = new Deck();
		testDeck1.DeckBuild();
		testDeck2.DeckBuild();
		testDeck1.seededShuffle();
		testDeck2.seededShuffle();
		Card card1, card2;
		for( int i=0;i<110; i++){
			card1= testDeck1.draw();
			card2= testDeck2.draw();
			assertEquals(card1, card2);
			card1.print();
		}
	}
	
	@Test
	public void CardNumtest(){
		Deck CardNum = new Deck();
		CardNum.DeckBuild();
		CardNum.shuffle();
		assertEquals( 110, CardNum.remaining());
		
		Card cardtmp = new Card(3,'P');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(106, CardNum.remaining());
		
		cardtmp = new Card(4,'P');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(102, CardNum.remaining());
		
		cardtmp = new Card(5,'P');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(98, CardNum.remaining());
		
		cardtmp = new Card(7,'P');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(96, CardNum.remaining());
		
		cardtmp = new Card(3,'R');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(90, CardNum.remaining());
		
		cardtmp = new Card(4,'R');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(84, CardNum.remaining());
		
		cardtmp = new Card(5,'R');		
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(82, CardNum.remaining());
		
		cardtmp = new Card(2,'B');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(78, CardNum.remaining());
		
		cardtmp = new Card(3,'B');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(74, CardNum.remaining());
		
		cardtmp = new Card(4,'B');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(70, CardNum.remaining());
		
		cardtmp = new Card(5,'B');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(68, CardNum.remaining());
		
		cardtmp = new Card(2,'Y');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(64, CardNum.remaining());
		
		cardtmp = new Card(3,'Y');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(56, CardNum.remaining());
		
		cardtmp = new Card(4,'Y');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(54, CardNum.remaining());
		
		cardtmp = new Card(1,'G');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(40, CardNum.remaining());
		
		cardtmp = new Card(2,'W');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(32, CardNum.remaining());
		
		cardtmp = new Card(3,'W');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(24, CardNum.remaining());
		
		cardtmp = new Card(6,'W');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(20, CardNum.remaining());
		

		cardtmp = new Card(16,'A');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(17, CardNum.remaining());
		
		cardtmp = new Card(17,'A');
		while (CardNum.containsCard(cardtmp)){
			CardNum.CardRemove(cardtmp);
		}
		assertEquals(15, CardNum.remaining());
		
		for(int i=0; i<=15; i++){
			cardtmp = new Card(i,'A');
			while (CardNum.containsCard(cardtmp)){
				CardNum.CardRemove(cardtmp);
			}
			assertEquals(15-i, CardNum.remaining());
		}
		
	}


}
