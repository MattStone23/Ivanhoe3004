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
		//eng.playCard("Play|R|3|");
		eng.endTurn();
		eng.startTour('R');
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
