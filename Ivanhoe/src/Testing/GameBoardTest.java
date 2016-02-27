package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import gameEntities.GameBoard;

public class GameBoardTest {
	
	
	
	@Test
	public void shuffletst(){
		GameBoard board = new GameBoard(3);
		assertEquals(110,board.getDeck().remaining());
	}
	
	@Test
	public void tournTest(){
		GameBoard board = new GameBoard(3);
		board.startTour('r');
		assertEquals('r',board.getCol());
		board.endTour(1, board.getCol());
		assertEquals(1,board.getPlayers()[1].getStones()[0]);
		board.startTour('p');
		assertEquals('p', board.getCol());
		board.endTour(2, 'r');
		assertEquals(1,board.getPlayers()[2].getStones()[0]);
	}
	
	@Test
	public void test() {
		GameBoard board= new GameBoard(4);
		board.getDeck().shuffle();
		board.playerDraw(1);
		board.playerDraw(0);
		board.playerDraw(2);
		assertEquals(107,board.getDeck().remaining());
		for(int i =0; i<3;i++){
			board.getPlayers()[i].getHand().display();
		}
	}

}
