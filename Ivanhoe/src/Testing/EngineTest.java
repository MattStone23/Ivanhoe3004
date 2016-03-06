package Testing;

import static org.junit.Assert.*;

import gameEntities.Card;
import gameEntities.Engine;
import gameEntities.GameBoard;
import java.util.*;

import org.junit.Test;

public class EngineTest {

	/*@Test
	public void test() {
		Engine eng= new Engine(3);
		assertEquals(86, eng.currentState().getDeck().remaining());
		eng.draw();
		eng.endTurn();
		eng.startTour("STARTTOURN|R".split("\\|"));
		String[] args =  "Play|W6".split("\\|");
		eng.printState();
		eng.playCard(args);
		eng.endTurn();
		eng.endTurn();
		eng.printState();
		//if(eng.withdraw())eng.removeToken(colour);
	}*/
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

		eng.startTour("STARTTOURN|B".split("\\|"));
		System.out.println(eng.getGameStateForPlayer(-1));
		eng.draw();
		System.out.println(eng.getGameStateForPlayer(-1));
		eng.playCard("PLAY|B4".split("\\|"));
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
		System.out.println("TESTGAME 2\t"+testGame2.getGameStateForPlayer(-1));
	}
	
	
	@Test
	public void testRedtourn(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|R".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|R3".split("\\|");
		eng.playCard(args);
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		eng.printState();
		assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
		
		
	}
	@Test
	public void testGreentourn(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,G1,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|G".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|G1".split("\\|");
		eng.playCard(args);
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		eng.printState();
		assertEquals(1, eng.currentState().getPlayers()[0].getStones()[1]);
	}
	@Test
	public void testBluetourn(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|Y".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|Y3".split("\\|");
		eng.playCard(args);
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		eng.printState();
		assertEquals(1, eng.currentState().getPlayers()[0].getStones()[2]);
	}
	@Test
	public void testYellowtourn(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|B".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|B4".split("\\|");
		eng.playCard(args);
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		eng.printState();
		assertEquals(1, eng.currentState().getPlayers()[0].getStones()[3]);
	}
	@Test
	public void testPurpletourn(){
		int colselected=4;
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,P7,B4$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|P".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|P7".split("\\|");
		eng.playCard(args);
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		if(eng.endTurn()==1){
			Scanner getCol= new Scanner(System.in);
			String col;
			System.out.print("You won a purple tournament! What colour token do you want?:  ");
			col= getCol.nextLine();
			System.out.print("\n\n");
			eng.currentState().endTour(eng.currentState().getTurn(), col.charAt(0));
			switch (col.charAt(0)){
			case 'R':
				colselected=0;
				break;
			case 'G':
				colselected=1;
				break;
			case 'Y':
				colselected=2;
				break;
			case 'B':
				colselected=3;
				break;
			case 'P':
				colselected=4;
				break;
			default:
				getCol.close();
				throw new IllegalArgumentException();			
			}
			getCol.close();
			
		}
		eng.printState();
		assertEquals(1, eng.currentState().getPlayers()[0].getStones()[colselected]);
	}
	
	
	@Test
	public void maidentest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,P7,B4$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|P".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|W6".split("\\|");
		eng.playCard(args);
		try{
			eng.playCard(args);
		}
		catch(IllegalArgumentException e){
			return;
		}
		fail();
	}
	
	@Test
	public void noTournStart(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A1,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.endTurn();
	}
	
	/*
	@Test
	public void GameTest(){
		Engine eng=new Engine(3);
		String in="temp";
		Scanner get = new Scanner(System.in); 
		while(!in.equals("quit")){
			try{
				eng.printState();
				in=get.nextLine();
				String[] args =  in.split("\\|");
				String command = args[0];
				switch (command){
				case "STARTTOURN":
					//startTourn()
					eng.startTour(args);
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
					if(eng.endTurn()==2){
						System.out.println("Player "+eng.currentState().getTurn()+" has won the tournament!");
						
					};
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
				case "DESC":
					System.out.println(new Card(args[1]).getDesc());
					break;
				}
			}
			catch(IllegalArgumentException error){
				System.out.println(error.getMessage());
			}
		}
		get.close();
	}*/

}
