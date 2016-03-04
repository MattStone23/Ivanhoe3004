package Testing;

import static org.junit.Assert.*;
import gameEntities.Engine;

import org.junit.Test;

public class EngineTest {

	@Test
	public void test() {
		Engine eng= new Engine(3);
		assertEquals(86, eng.currentState().getDeck().remaining());
		eng.draw();
		eng.endTurn();
		eng.startTour('R');
		String[] args =  "Play|R|3|".split("\\|");
		for(int i = 0; i<3;i++){
			System.out.print("Player "+i+"'s hand:\n");
			eng.currentState().getPlayers()[i].getHand().display();
			System.out.print("\nPlayer "+i+"'s display:\n");
			eng.currentState().getPlayers()[i].displayPrint();
			System.out.print("\n");
		}
		eng.playCard(args);
		for(int i = 0; i<3;i++){
			System.out.print("Player "+i+"'s hand:\n");
			eng.currentState().getPlayers()[i].getHand().display();
			System.out.print("\nPlayer "+i+"'s display:\n");
			eng.currentState().getPlayers()[i].displayPrint();
			System.out.print("\n");
		}
		assertEquals(85, eng.currentState().getDeck().remaining());

	}
	
	@Test
	public void turnTest(){
		Engine eng= new Engine(3);
		for (int i = 0; i<10;i++){
			assertEquals(86-i, eng.currentState().getDeck().remaining());
			eng.draw();
			assertEquals(i%3, eng.turnNum());
			eng.endTurn();
		}
	}

}
