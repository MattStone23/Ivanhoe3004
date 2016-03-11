package Testing;

import static org.junit.Assert.*;

import org.junit.Test;
import gameEntities.*;

public class AItest {

	/*
	@Test
	public void validresponse(){
		AI bot= new AI(0);
		GameBoard state= new GameBoard();
		state.setGameState("GAMESTATE|N~0~94~0~2~false|[1, 0, 0, 0, 0]$true$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		state.printState();
		new Card(bot.handle(state.getGameStateForPlayer(0)));
		
		
	}
	*/
	@Test
	public void DrawResponse(){
		AI bot= new AI(0);
		GameBoard state= new GameBoard();
		state.setGameState("GAMESTATE|N~0~94~0~2~false|[1, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		System.out.println(state.getGameStateForPlayer(1));
		//state.printState();
		assertEquals("DRAW", bot.handle(state.getGameStateForPlayer(1)));
	}

}
