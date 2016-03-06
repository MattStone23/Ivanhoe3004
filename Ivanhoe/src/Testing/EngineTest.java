package Testing;

import static org.junit.Assert.*;
import gameEntities.Engine;
import gameEntities.GameBoard;
import java.util.*;

import org.junit.Test;

public class EngineTest {

	@Test
	public void test() {
		Engine eng= new Engine(3);
		assertEquals(86, eng.currentState().getDeck().remaining());
		eng.draw();
		eng.startTour('Y');
		String[] args =  "Play|Y|3|".split("\\|");
		eng.printState();
		eng.playCard(args);
		eng.endTurn();
		eng.endTurn();
		eng.printState();
		//if(eng.withdraw())eng.removeToken(colour);
	}
	/*
	@Test
	public void turnTest(){
		Engine eng= new Engine(3);
		for (int i = 0; i<10;i++){
			assertEquals(86-i, eng.currentState().getDeck().remaining());
			eng.draw();
			assertEquals(i%3, eng.turnNum());
			eng.endTurn();
		}
	}*/
	
	@Test
	public void testParse(){
		Engine eng= new Engine(2);
		System.out.println(eng.getGameStateForPlayer(-1));
		eng.startTour('B');
		System.out.println(eng.getGameStateForPlayer(-1));
		eng.draw();
		System.out.println(eng.getGameStateForPlayer(-1));
		String[] test = {"Play","B4"};
		eng.playCard(test);
		eng.withdraw();
		eng.endTurn();
		System.out.println(eng.getGameStateForPlayer(-1));
		eng.currentState().getPlayers()[0].addStone('P');
		System.out.println(eng.getGameStateForPlayer(-1));
	}
	@Test
	public void testParse2(){
		Engine eng= new Engine(2);
		GameBoard testGame = new GameBoard(2);
		GameBoard testGame2 = new GameBoard();
		System.out.println("ENGINE\t\t"+eng.getGameStateForPlayer(-1));
		System.out.println("TESTGAME\t"+testGame.getGameStateForPlayer(-1));
		testGame.setGameState("GAMESTATE|"+eng.getGameStateForPlayer(1));
		testGame2.setGameState("GAMESTATE|"+eng.getGameStateForPlayer(2));
		System.out.println("SETTING GAME STATES");
		System.out.println("ENGINE\t\t"+eng.getGameStateForPlayer(-1));
		System.out.println("TESTGAME\t"+testGame.getGameStateForPlayer(-1));
		System.out.println("TESTGAME2\t"+testGame2.getGameStateForPlayer(-1));
	}
	@Test
	public void GameTest(){
		Engine eng=new Engine(3);
		String in="temp";
		Scanner get = new Scanner(System.in); 
		while(!in.equals("quit")){
			eng.printState();
			in=get.nextLine();
			System.out.println("~"+in+"~");
			String[] args =  in.split("\\|");
			String command = args[0];
			switch (command){
			case "STARTTOURN":
				//startTourn()
				eng.startTour(args[1].charAt(0));
				break;
			case "DRAW":
				eng.draw();
				break;
			case "PLAY":
				//play a card
				System.out.print("Playing!");
				eng.playCard(args);
				break;
			case "WITHDRAW":
				//withdraw from tournament
				eng.withdraw();
				eng.endTurn();
				break;
			case "ENDTURN":
				//end turn
				eng.endTurn();
				break;
			case "IVANHOE":
				//interrupt actioncard
				break;
			case "WINTOKEN":
				//select which token to win if purple tournament won
				break;
			}
		}
		get.close();
	}

}
