package Testing;

import static org.junit.Assert.*;

import gameEntities.Card;
import gameEntities.Engine;
import gameEntities.GameBoard;
import java.util.*;

import org.junit.Test;

public class TournamentTests {
	//mult tourny tests test winning multiple stones, which shouldn't happen, capped at 1
		@Test
		public void testRedtournMult(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[1, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
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
		//Tests red tournaments, only 2 players with only 1 playing a card
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
		//Tests 2 players playing cards
		@Test
		public void testRedoneplaytourn(){
			Engine eng = new Engine(3);
			GameBoard testboard= new GameBoard(3);
			testboard.setGameState("GAMESTATE|N~0~94~0~3~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|R".split("\\|");
			eng.startTour(args);
			eng.printState();
			args="PLAY|R3".split("\\|");
			eng.playCard(args);
			eng.playCard(args);
			eng.endTurn();
			eng.draw();
			eng.playCard(args);
			eng.withdraw();
			eng.endTurn();
			eng.draw();
			eng.withdraw();
			eng.endTurn();
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
			
			
		}
		//Tests with multiple players, with two playing cards and one not.
		@Test
		public void testRedoneplay2cardtourn(){
			Engine eng = new Engine(3);
			GameBoard testboard= new GameBoard(3);
			testboard.setGameState("GAMESTATE|N~0~94~0~3~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,R3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|R".split("\\|");
			eng.startTour(args);
			eng.printState();
			args="PLAY|R3".split("\\|");
			eng.playCard(args);
			eng.playCard(args);
			eng.playCard(args);
			eng.endTurn();
			eng.draw();
			eng.playCard(args);
			eng.playCard(args);
			eng.withdraw();
			eng.endTurn();
			eng.draw();
			eng.withdraw();
			eng.endTurn();
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
			
			
		}
		//Tests multiple players with all playing cards
		@Test
		public void testRedallplaytourn(){
			Engine eng = new Engine(3);
			GameBoard testboard= new GameBoard(3);
			testboard.setGameState("GAMESTATE|N~0~94~0~3~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|R".split("\\|");
			eng.startTour(args);
			eng.printState();
			args="PLAY|R3".split("\\|");
			eng.playCard(args);
			eng.playCard(args);
			eng.endTurn();
			eng.draw();
			eng.playCard(args);
			eng.withdraw();
			eng.endTurn();
			eng.draw();
			eng.playCard(args);
			eng.withdraw();
			eng.endTurn();
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
			
			
		}	
		//Tests multiple players with all playing multiple cards
		@Test
		public void testRedallplayManycardtourn(){
			Engine eng = new Engine(3);
			GameBoard testboard= new GameBoard(3);
			testboard.setGameState("GAMESTATE|N~0~94~0~3~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,R3,R3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,R3,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|R".split("\\|");
			eng.startTour(args);
			eng.printState();
			args="PLAY|R3".split("\\|");
			eng.playCard(args);
			eng.playCard(args);
			eng.playCard(args);
			eng.playCard(args);
			eng.endTurn();
			eng.draw();
			eng.playCard(args);
			eng.playCard(args);
			eng.playCard(args);
			eng.withdraw();
			eng.endTurn();
			eng.draw();
			eng.playCard(args);
			eng.playCard(args);
			eng.withdraw();
			eng.endTurn();
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
			
			
		}	
		//Tests starting tournament with 1 supporter
		@Test
		public void testRedtournSupportStart(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|R".split("\\|");
			eng.startTour(args);
			eng.printState();
			args="PLAY|W6".split("\\|");
			eng.playCard(args);
			eng.endTurn();
			eng.draw();
			eng.withdraw();
			eng.endTurn();
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
			
			
		}			
		//Tests starting tournament with many supporters
		@Test
		public void testRedtournManySupportStart(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W2,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|R".split("\\|");
			eng.startTour(args);
			eng.printState();
			args="PLAY|W6".split("\\|");
			eng.playCard(args);
			args="PLAY|W2".split("\\|");
			eng.playCard(args);
			eng.endTurn();
			eng.draw();
			eng.withdraw();
			eng.endTurn();
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
			
			
		}
		//tests playing 1 supporter than many in the next round
		@Test
		public void testRedtournManySupportmanyround(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W2,W2,W2,W2,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W3,W2,W2,W2,G1$0");
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
			args="PLAY|W3".split("\\|");
			eng.playCard(args);
			eng.endTurn();
			args="PLAY|W2".split("\\|");
			eng.draw();
			eng.playCard(args);
			eng.playCard(args);
			eng.playCard(args);
			eng.endTurn();
			eng.draw();
			eng.playCard(args);
			eng.playCard(args);
			eng.withdraw();
			eng.endTurn();
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[0]);
			
			
		}
		//Tests 
		
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
		public void testGreentournMult(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 1, 0, 0, 0]$false$A1,A16,W6,W6,A11,G1,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
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
		public void testBluetournMult(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 1, 0, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
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
		public void testYellowtournMult(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 1, 0]$false$A1,A16,W6,W6,A11,Y3,R3,B4$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
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
		//tests trying to start a second purple tournament
		@Test
		public void testPurpletournattempt2nd(){
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
			eng.currentState().endTour(eng.currentState().getTurn(), 'G');
			eng.draw();
			args="STARTTOURN|P".split("\\|");
			eng.startTour(args);
			assertEquals('N', eng.currentState().getCol());
		}	
			
		@Test
		public void testPurpletournMult(){
			int colselected=4;
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[1, 1, 1, 1, 1]$false$A1,A16,W6,W6,A11,Y3,P7,B4$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
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
				eng.currentState().endTour(eng.currentState().getTurn(), 'P');
				colselected=4;
				//getCol.close();		
			}
			eng.printState();
			assertEquals(1, eng.currentState().getPlayers()[0].getStones()[colselected]);
		}
		
		//Tests for checking withdrawing with a maiden in different tournaments
		
		@Test
		public void maidenwithdrawY(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|Y~0~94~0~3~true|[1, 1, 0, 0, 0]$false$A10,A16,W6,W6,A11,Y3,R3,B4$W6,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			try{
				boolean temp=eng.withdraw();
				assertTrue(temp);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			
			
		}
		@Test
		public void maidenwithdrawR(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|R~0~94~0~3~true|[1, 1, 0, 0, 0]$false$A10,A16,W6,W6,A11,Y3,R3,B4$W6,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			try{
				boolean temp=eng.withdraw();
				assertTrue(temp);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			
			
		}
		@Test
		public void maidenwithdrawB(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|B~0~94~0~3~true|[1, 1, 0, 0, 0]$false$A10,A16,W6,W6,A11,Y3,R3,B4$W6,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			try{
				boolean temp=eng.withdraw();
				assertTrue(temp);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			
			
		}
		@Test
		public void maidenwithdrawG(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|G~0~94~0~3~true|[1, 1, 0, 0, 0]$false$A10,A16,W6,W6,A11,Y3,R3,B4$W6,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			try{
				boolean temp=eng.withdraw();
				assertTrue(temp);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			
			
		}
		@Test
		public void maidenwithdrawP(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|P~0~94~0~3~true|[1, 1, 0, 0, 0]$false$A10,A16,W6,W6,A11,Y3,R3,B4$W6,Y3|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0|[0, 0, 0, 0, 0]$false$P7,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			try{
				boolean temp=eng.withdraw();
				assertTrue(temp);
			}
			catch(IllegalArgumentException e){
				System.out.println(e.getMessage());
			}
			
			
		}
		
		
		
		//tests playing more than one maiden in tournaments
		@Test
		public void maidentestP(){
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
		public void maidentestR(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,P7,B4$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|R".split("\\|");
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
		public void maidentestB(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,P7,B4$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|B".split("\\|");
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
		public void maidentestY(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,P7,B4$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|Y".split("\\|");
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
		public void maidentestG(){
			Engine eng = new Engine(2);
			GameBoard testboard= new GameBoard(2);
			testboard.setGameState("GAMESTATE|N~0~94~0~2~false|[0, 0, 0, 0, 0]$false$A1,A16,W6,W6,A11,Y3,P7,B4$0|[0, 0, 0, 0, 0]$false$R3,R3,B2,B2,Y2,P5,W2,G1$0");
			eng.setState(testboard);
			eng.printState();
			eng.draw();
			String args[]="STARTTOURN|G".split("\\|");
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
		
}
