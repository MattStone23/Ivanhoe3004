package gameEntities;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {
	
	
	
	@Test
	public void shuffletst(){
		GameBoard board = new GameBoard(3);
		assertEquals(110,board.inPlay.remaining());
	}
	
	@Test
	public void tournTest(){
		GameBoard board = new GameBoard(3);
		board.startTour('r');
		assertEquals('r',board.getCol());
	}
	
	@Test
	public void test() {
		GameBoard board= new GameBoard(4);
		board.inPlay.shuffle();
		board.playerDraw(1);
		board.playerDraw(0);
		board.playerDraw(2);
		assertEquals(107,board.inPlay.remaining());
		for(int i =0; i<3;i++){
			board.players[i].plyHand.display();
		}
	}

}
