package Testing;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import gameEntities.Card;
import gameEntities.Engine;
import gameEntities.GameBoard;

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
	public void noTournStart(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A1,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		assertEquals(eng.turnNum(),0);
		eng.endTurn();
		assertEquals(eng.turnNum(),1);
	}
	//checks that trying to play a card before drawing returns an error message.
	@Test
	public void drawTest(){

		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|P~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		try{
		    eng.playCard("PLAY|A1|R".split("\\|"));
		}
		catch(IllegalArgumentException e){
			assertTrue(e.getMessage().equals("Draw a card first"));
		}
	}
		
		
		
	@Test
	public void unhorseTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|P~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A1,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.playCard("PLAY|A1|R".split("\\|"));
		assertEquals('R',eng.currentState().getCol());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void changeweaponTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A2,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.playCard("PLAY|A2|R".split("\\|"));
		assertEquals('R',eng.currentState().getCol());
		
		Engine eng2 = new Engine(2);
		GameBoard testboard2= new GameBoard(2);
		testboard2.setGameState("GAMESTATE|G~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A2,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng2.setState(testboard2);
		try{
			eng2.playCard("PLAY|A2|G".split("\\|"));
		}
		catch(IllegalArgumentException e){};
		
		Engine eng3 = new Engine(2);
		GameBoard testboard3= new GameBoard(2);
		testboard3.setGameState("GAMESTATE|P~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A2,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng3.setState(testboard3);
		try{
			eng2.playCard("PLAY|A2|G".split("\\|"));
		}
		catch(IllegalArgumentException e){};
		Engine eng4 = new Engine(2);
		GameBoard testboard4= new GameBoard(2);
		testboard4.setGameState("GAMESTATE|R~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A2,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng4.setState(testboard4);
		try{
			eng4.playCard("PLAY|A2|G".split("\\|"));
		}
		catch(IllegalArgumentException e){};
		Engine eng5 = new Engine(2);
		GameBoard testboard5= new GameBoard(2);
		testboard5.setGameState("GAMESTATE|R~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A2,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng5.setState(testboard5);
		try{
			eng5.playCard("PLAY|A2|P".split("\\|"));
		}
		catch(IllegalArgumentException e){};
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void dropWeaponTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A3,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.playCard("PLAY|A3".split("\\|"));
		assertEquals('G',eng.currentState().getCol());
		
		Engine eng2 = new Engine(2);
		GameBoard testboard2= new GameBoard(2);
		testboard2.setGameState("GAMESTATE|G~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A3,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng2.setState(testboard2);
		try{
			eng2.playCard("PLAY|A3".split("\\|"));
		}
		catch(IllegalArgumentException e){};
		
		Engine eng3 = new Engine(2);
		GameBoard testboard3= new GameBoard(2);
		testboard3.setGameState("GAMESTATE|P~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A3,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
		eng3.setState(testboard3);
		try{
			eng2.playCard("PLAY|A3".split("\\|"));
		}
		catch(IllegalArgumentException e){};
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void breakLanceTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A4,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$B3,P5,P5,P7");
		eng.setState(testboard);
		eng.playCard("PLAY|A4|1".split("\\|"));
		assertEquals(3,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(4,eng.currentState().getDiscardSize());
	}
	@Test
	public void breakLanceShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A4,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$P5,P5,P7");
		eng.setState(testboard);
		eng.currentState().getPlayers()[1].setShield();
		eng.playCard("PLAY|A4|1".split("\\|"));
		assertEquals(17,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void dodgeTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A5,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$P5,P5,P7");
		eng.setState(testboard);
		eng.playCard("PLAY|A5|1|2".split("\\|"));
		assertEquals(10,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(2,eng.currentState().getDiscardSize());
	}
	@Test
	public void dodgeShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~2~true|[0, 0, 0, 0, 0]$false$A5,A16$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$P5,P5,P7");
		eng.setState(testboard);
		eng.currentState().getPlayers()[1].setShield();
		eng.playCard("PLAY|A5|1|2".split("\\|"));
		assertEquals(17,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void retreatTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A6,A16,W6,W6,A11,Y3,R3,B4$B3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.playCard("PLAY|A6|0".split("\\|"));
		assertEquals(new Card(3,'B'),eng.currentState().getPlayers()[eng.turnNum()].getHand().getHandStack().elementAt(7) );
		assertEquals(0, eng.currentState().getPlayers()[0].displayVal());
		eng.printState();
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void outmanuverTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A7,A16,W6,W6,A11,Y3,R3,B4$B3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$B2,B4|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$B3,B4");
		eng.setState(testboard);
		eng.playCard("PLAY|A7".split("\\|"));
		assertEquals(3,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(2,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(3,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(3,eng.currentState().getDiscardSize());
	}
	@Test
	public void outmanuverShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|B~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A7,A16,W6,W6,A11,Y3,R3,B4$B3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$B4,B3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$B4,B3");
		eng.setState(testboard);
		eng.currentState().getPlayers()[1].setShield();
		eng.playCard("PLAY|A7".split("\\|"));
		assertEquals(7,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(4,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(2,eng.currentState().getDiscardSize());
	}
	@Test
	public void chargeTest(){
		Engine eng = new Engine(3);
		GameBoard testboard= new GameBoard(3);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A8,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,Y2,Y3");
		eng.setState(testboard);
		eng.playCard("PLAY|A8".split("\\|"));
		assertEquals(3,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(2,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(3,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(5,eng.currentState().getDiscardSize());
	}
	@Test
	public void chargeShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A8,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,Y2,Y3");
		eng.setState(testboard);
		eng.currentState().getPlayers()[2].setShield();
		eng.playCard("PLAY|A8".split("\\|"));
		assertEquals(3,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(3,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(7,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(4,eng.currentState().getDiscardSize());
	}
	//tests charge with all green cards
	@Test
	public void chargeGTest(){
		Engine eng = new Engine(3);
		GameBoard testboard= new GameBoard(3);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A8,A16,W6,W6,A11,Y3,R3,B4$G1|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$G1,G1|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$G1,G1,G1");
		eng.setState(testboard);
		eng.playCard("PLAY|A8".split("\\|"));
		assertEquals(1,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(1,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(1,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(4,eng.currentState().getDiscardSize());
	}
	@Test
	public void counterchargeTest(){
		Engine eng = new Engine(3);
		GameBoard testboard= new GameBoard(3);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A9,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y3,Y3,Y3");
		eng.setState(testboard);
		eng.playCard("PLAY|A9".split("\\|"));
		assertEquals(2,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(4,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(3,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(4,eng.currentState().getDiscardSize());
	}
	@Test
	public void counterchargeShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A9,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,Y2,Y3");
		eng.setState(testboard);
		eng.currentState().getPlayers()[2].setShield();
		eng.playCard("PLAY|A9".split("\\|"));
		assertEquals(2,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(4,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(7,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(2,eng.currentState().getDiscardSize());
	}
	@Test
	public void dishonourTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A10,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,W2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W6,Y2,Y2,Y3");
		eng.setState(testboard);
		eng.playCard("PLAY|A10".split("\\|"));
		eng.printState();
		assertEquals(5,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(2,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(7,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(4,eng.currentState().getDiscardSize());
	}
	
	public void dishonourShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A10,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W6,Y2,Y2,Y3");
		eng.setState(testboard);
		eng.currentState().getPlayers()[1].setShield();
		eng.playCard("PLAY|A10".split("\\|"));
		eng.printState();
		assertEquals(5,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(4,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(7,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(3,eng.currentState().getDiscardSize());
	}
	
	@Test
	public void outwitTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A12,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W6,Y2,Y2,Y3");
		eng.setState(testboard);
		eng.playCard("PLAY|A12|1|1|0".split("\\|"));	
		assertEquals(4,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(5,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(16,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	
	@Test
	public void outwitShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A12,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W6,Y2,Y2,Y3");
		eng.setState(testboard);
		eng.currentState().getPlayers()[1].setShield();
		eng.playCard("PLAY|A12|1|1|0".split("\\|"));	
		assertEquals(5,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(4,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(16,eng.currentState().getPlayers()[2].displayVal());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void ShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A13,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.draw();
		eng.playCard("PLAY|A13".split("\\|"));
		assertTrue(eng.currentState().getPlayers()[0].isShielded());
		assertEquals(0, eng.currentState().getDiscardSize());
		eng.playCard("PLAY|Y3".split("\\|"));
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		assertFalse(eng.currentState().getPlayers()[0].isShielded());
		assertEquals(2,eng.currentState().getDiscardSize());
	}
	@Test
	public void StunTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A14,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.draw();
		eng.playCard("PLAY|A14|1".split("\\|"));
		assertTrue(eng.currentState().getPlayers()[1].isStunned());
		assertEquals(0, eng.currentState().getDiscardSize());
		eng.playCard("PLAY|Y3".split("\\|"));
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		assertFalse(eng.currentState().getPlayers()[1].isStunned());
		assertEquals(2,eng.currentState().getDiscardSize());
	}
	@Test
	public void StunactioncardTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A14,A16,W6,W6,A11,Y3,R3,B4$Y3,W6|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,A16,W2,G1$0");
		eng.setState(testboard);
		eng.playCard("PLAY|A14|1".split("\\|"));
		assertTrue(eng.currentState().getPlayers()[1].isStunned());
		assertEquals(0, eng.currentState().getDiscardSize());
		eng.endTurn();
		eng.draw();
		eng.playCard("PLAY|A16|0".split("\\|"));
		assertEquals('Y', eng.currentState().getCol());
		assertEquals(0,eng.turnNum());
		assertTrue(eng.currentState().getPlayers()[1].isStunned());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	//test to make sure a player can possibly stay in the tournament
	@Test
	public void StunstayinTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A14,A16,W6,W6,A11,Y3,R3,B4$Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,A16,W2,W6,G1$0");
		eng.setState(testboard);
		eng.playCard("PLAY|A14|1".split("\\|"));
		assertTrue(eng.currentState().getPlayers()[1].isStunned());
		assertEquals(0, eng.currentState().getDiscardSize());
		eng.endTurn();
		eng.draw();
		eng.playCard("PLAY|W6".split("\\|"));
		assertEquals('Y', eng.currentState().getCol());
		assertEquals(0,eng.turnNum());
		assertTrue(eng.currentState().getPlayers()[1].isStunned());
	}
	@Test
	public void StunEndTurnTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A12,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.currentState().getPlayers()[0].setStun();
		eng.playCard("PLAY|Y3".split("\\|"));
		assertEquals(1,eng.turnNum());
	}
	
	public void StunWithdrawTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A12,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y3,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
		eng.setState(testboard);
		eng.currentState().getPlayers()[0].setStun();
		eng.playCard("PLAY|Y3".split("\\|"));
		assertEquals(1,eng.turnNum());
		assertTrue(eng.currentState().getPlayers()[0].isWithdrawn());
	}
	
	@Test
	public void outwitStunShieldtest(){
		Engine eng = new Engine(3);
		GameBoard testboard= new GameBoard(3);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A12,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W6,Y2,Y2,Y3");
		eng.setState(testboard);
		eng.currentState().getPlayers()[0].setStun();
		eng.currentState().getPlayers()[1].setShield();
		eng.playCard("PLAY|A12|1|-2|-1".split("\\|"));
		eng.printState();
		assertTrue(eng.currentState().getPlayers()[0].isShielded());
		assertTrue(eng.currentState().getPlayers()[1].isStunned());
	}
	//tests whether trying to switch stun and sheild will work if the players don't have those cards
	@Test
	public void outwitinvalidTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A12,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W6,Y2,Y2,Y3");
		eng.setState(testboard);
		try{
			eng.playCard("PLAY|A12|1|-1|0".split("\\|"));	
		}
		catch(IllegalArgumentException e){
			System.out.println(e);
			assertTrue(e.getMessage().equals("You don't have that card"));
		}
		eng.currentState().printState();
		try{
			eng.playCard("PLAY|A12|1|1|-1".split("\\|"));	
		}
		catch(IllegalArgumentException e){
			System.out.println(e);
			assertTrue(e.getMessage().equals("You don't have that card"));
		}
		try{
			eng.playCard("PLAY|A12|1|-2|0".split("\\|"));	
		}
		catch(IllegalArgumentException e){
			System.out.println(e);
			assertTrue(e.getMessage().equals("You don't have that card"));
		}
		try{
			eng.playCard("PLAY|A12|1|1|-2".split("\\|"));	
		}
		catch(IllegalArgumentException e){
			System.out.println(e);
			assertTrue(e.getMessage().equals("You don't have that card"));
		}
		

	}

	@Test
	public void riposteTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A16,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,Y3");
		eng.setState(testboard);
		eng.draw();
		eng.playCard("PLAY|A16|1".split("\\|"));
		assertEquals(3,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(2,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	
	@Test
	public void riposteShieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A16,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,Y3");
		eng.setState(testboard);
		eng.currentState().getPlayers()[1].setShield();
		eng.draw();
		eng.playCard("PLAY|A16|1".split("\\|"));
		assertEquals(0,eng.currentState().getPlayers()[0].displayVal());
		assertEquals(5,eng.currentState().getPlayers()[1].displayVal());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	
	@Test
	public void KnockdownTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A16,A17,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,Y3");
		eng.setState(testboard);
		eng.draw();
		eng.playCard("PLAY|A17|1".split("\\|"));
		assertEquals(8,eng.currentState().getPlayers()[0].getHand().getHandStack().size());
		assertEquals(7,eng.currentState().getPlayers()[1].getHand().getHandStack().size());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	@Test
	public void KnockdownshieldTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|Y~0~94~0~2~true|[1, 0, 0, 0, 0]$false$A16,A17,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$Y2,Y3");
		eng.setState(testboard);
		eng.draw();
		eng.currentState().getPlayers()[1].setShield();
		eng.playCard("PLAY|A17|1".split("\\|"));
		assertEquals(7,eng.currentState().getPlayers()[0].getHand().getHandStack().size());
		assertEquals(8,eng.currentState().getPlayers()[1].getHand().getHandStack().size());
		assertEquals(1,eng.currentState().getDiscardSize());
	}
	
	@Test
	public void notEnoughCard(){
		Engine eng = new Engine(3);
		GameBoard testboard= new GameBoard(3);
		testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 0, 0, 0, 0]$false$A12,A16,W6,W6,A11,Y3,R3,B4$Y2,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W2,Y2|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$W3,W6,Y2,Y2,Y3");
		eng.setState(testboard);
		try{
			//eng.draw();
			eng.endTurn();
		}
		catch(IllegalArgumentException e){
			if(e.equals("You have not played enough cards to be the highest value display\n"))return;
		}
		assert false;
	}

	//tests that getting to the end of the deck doesn't mess things up. discards all cards but 1, then draws that.
	public void EndDeckTest(){
		Engine eng = new Engine(2);
		for(int i =0; i<92;i++){
			eng.currentState().disctop();
		}
		eng.draw();
		assertEquals(93,eng.currentState().getDeckSize());
		assertEquals(9, eng.currentState().getPlayers()[0].getHand().getHandStack().size());
		assertEquals(8, eng.currentState().getPlayers()[1].getHand().getHandStack().size());
		assertEquals(0,eng.currentState().getDiscardSize());
	}
	
	//tests if a winner is decided by engine, decided in endturn function, which will return a 2 if the players who's turn it is now has won.
	@Test
	public void win2plyTest(){
		Engine eng = new Engine(2);
		GameBoard testboard= new GameBoard(2);
		testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 1, 1, 1, 1]$false$A1,A16,W2,W2,W2,W2,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W3,W2,W2,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|R".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|W2".split("\\|");
		eng.playCard(args);
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		int i;
		i=eng.endTurn();
		assertEquals(2, i);
	}
	@Test
	public void win4plyTest(){
		Engine eng = new Engine(4);
		GameBoard testboard= new GameBoard(4);
		testboard.setGameState("GAMESTATE|N~0~94~0~4~false|[0, 1, 1, 1, 0]$false$A1,A16,W2,W2,W2,W2,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W3,W2,W2,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W3,W2,W2,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W3,W2,W2,W2,G1$0");
		eng.setState(testboard);
		eng.printState();
		eng.draw();
		String args[]="STARTTOURN|R".split("\\|");
		eng.startTour(args);
		eng.printState();
		args="PLAY|W2".split("\\|");
		eng.playCard(args);
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		eng.endTurn();
		eng.draw();
		eng.withdraw();
		int i;
		i=eng.endTurn();
		assertEquals(2, i);
	}
	
	/*@Test
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
