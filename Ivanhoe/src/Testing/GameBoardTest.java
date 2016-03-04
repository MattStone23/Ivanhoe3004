package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import gameEntities.GameBoard;
import gameEntities.Card;

public class GameBoardTest {
	
	
	
	@Test
	public void shuffletst(){
		GameBoard board = new GameBoard(3);
		assertEquals(110,board.getDeck().remaining());
	}
	
	@Test
	public void tournTest(){
		GameBoard board = new GameBoard(3);
		board.startTour('R');
		assertEquals('R',board.getCol());
		board.endTour(1, board.getCol());
		assertEquals(1,board.getPlayers()[1].getStones()[0]);
		board.startTour('P');
		assertEquals('P', board.getCol());
		board.endTour(2, 'R');
		assertEquals(1,board.getPlayers()[2].getStones()[0]);
	}
	/* put this in an engine test
	@Test
	public void playCardTest(){
		GameBoard board = new GameBoard(3);
		board.startTour('R');
		while(! board.getPlayers()[0].getHand().retHandStack().contains(new Card (3, 'R'))){
			board.playerDraw(0);
		}
		board.playCard(new Card(3,'R'), 0);
		while(! board.getPlayers()[0].getHand().retHandStack().contains(new Card (3, 'R'))){
			board.playerDraw(0);
		}
		board.playCard(new Card(3, 'A'), 0);
		assertEquals(1,board.getDiscard().remaining());
	}
	*/
	
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
