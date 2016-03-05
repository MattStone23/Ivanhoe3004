package Testing;

import static org.junit.Assert.*;
import gameEntities.Engine;
import java.util.*;

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
		eng.printState();
		eng.playCard(args);
		eng.printState();
		assertEquals(85, eng.currentState().getDeck().remaining());
		eng.endTurn();
		eng.draw();
		eng.printState();
		eng.withdraw();
		eng.endTurn();
		assertEquals(0, eng.turnNum());
		eng.draw();
		eng.printState();
		eng.endTurn();
		assertEquals(1, eng.turnNum());
		eng.draw();
		eng.printState();
		eng.endTurn();
		assertEquals(0, eng.turnNum());
		eng.draw();
		eng.printState();
		eng.endTurn();
		assertEquals(1, eng.turnNum());
		eng.draw();
		eng.printState();
		//if(eng.withdraw())eng.removeToken(colour);
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
	}

}
