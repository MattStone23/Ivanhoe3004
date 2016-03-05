package gameEntities;

import java.util.*;
public class Engine {
	GameBoard state;
	//tracks which players turn it is
	int turn;
	int numply;
	int playersleft;
	
	public Engine(int players){
		state = new GameBoard( players);
		turn =0;
		numply=players;
	}
	
	
	//ends the turn and switches to the next valid player, then draws card for that player.
	public void endTurn(){
		if(state.getCol()=='N'&& state.hasValidCard(turn)){
			System.out.print("you have a valid card you can play to start the tournament!\n");
			return;
		}
		if(!state.getPlayers()[turn].isWithdrawn()){
			if(state.getCol()!='G'){
				if(turn!= state.highestDisplay()){
					System.out.print("You have not played enough cards to be the highest value display\n");
					return;
				}
			}
			else{
				if(turn!=state.highestDisplayG()){
					System.out.print("You have not played enough cards to be the highest value display\n");
					return;
				}
			}
		}
		do{
			turn= (turn+1)%numply;
		}while(state.getPlayers()[turn].isWithdrawn());
		if(playersleft==1){
			if(state.getCol()=='P'){
				Scanner getCol= new Scanner(System.in);
				String col;
				System.out.print("You won a purple tournament! What colour token do you want?:  ");
				col= getCol.nextLine();
				System.out.print("\n\n");
				state.endTour(turn, col.charAt(0));				
			}
			else state.endTour(turn, state.getCol());
		}
	}
	
	
	public void draw(){
		if(lastLeft()) return;
		state.playerDraw(turn);
	}
	
	public void removeToken(char colour){
		state.getPlayers()[turn].removeColour(colour);
	}
	
	public void playCard( String[] in){
		if(lastLeft()) return;
		char type;
		int val;
		type= in[1].charAt(0);
		val= Integer.parseInt(in[2]);

		if(!state.getPlayers()[turn].getHand().getHandStack().contains(new Card(val, type))){
			throw new IllegalArgumentException();
		}
		if(in[1]=="A"){
			playActionCard(in);
		}
		else{
			if(state.getCol()!= type && type!= 'W' ) throw new IllegalArgumentException();
			state.playCard(new Card(val, type ), turn);
		}
	}
	//TODO make sure cards make it into the discard.
	public void playActionCard(String[] in){
		char type;
		int val;
		val = Integer.parseInt(in[2]);
		type= in[1].charAt(0);
		switch(val){
		//Unhorse card: changes tournament colour to not purple (RBY)
		case 1:
			if (state.getCol() != 'P'){
				throw new IllegalArgumentException();
			}
			if (in[3].charAt(0)=='G' || in[3].charAt(0)=='P'){
				throw new IllegalArgumentException();
			}
			state.changeCol(in[3].charAt(0));
			break;
		//Change weapon// goes from RBY tournament to RBY
		case 2:
			if (state.getCol()=='P' || state.getCol()== 'G'){
				throw new IllegalArgumentException();
			}
			if (in[3].charAt(0)=='G' || in[3].charAt(0)=='P'){
				throw new IllegalArgumentException();
			}
			state.changeCol(in[3].charAt(0));
			break;
			
		//Drop Weapon: Goes from RBY tournament to G tournament.
		case 3:
			if (state.getCol()=='G' || state.getCol()== 'G'){
				throw new IllegalArgumentException();
			}
			state.changeCol('G');
			break;
			
			//Break Lance: makes player indicated by in[3] discard all purple cards.
			//TODO: check if getter will return the object itself, or a copy of the object. could be double danger if it returns a copy. Currently assuming they will return the object itself.
		case 4:
			for(int ply = 0; ply<numply; ply++){
				if(ply != turn && !(state.getPlayers()[ply].isWithdrawn())){
					state.getPlayers()[ply].getHand().DiscardCol('P', state.discard);
				}
			}
			break;
			
		case 5:
			//discards a specific card from the player indicated by arg[3]'s display
			state.getPlayers()[Integer.parseInt(in[3])].displayDisc( Integer.parseInt(in[4])  , state.discard);
			break;
			
			//Retreat: returns card from display to hand.
		case 6:
			state.returnCard(Integer.parseInt(in[3]), turn);
			break;
			
			
		case 7:
			//discards the last card in each players display.
			for(int ply = 0; ply<numply; ply++){
				if(ply != turn){
					state.getPlayers()[ply].displayDisc(state.getPlayers()[ply].displayNum()-1, state.discard);
				}
			}
			break;
			
			// Discards all cards of a lowest value in all players displays
		case 8:
			int minval=10;
			for(int ply = 0; ply<numply; ply++){
				if(state.getPlayers()[ply].displayLowest()<minval)minval= state.getPlayers()[ply].displayLowest();
			}
			for(int ply = 0; ply<numply;ply++){
				state.getPlayers()[ply].displayDiscVal(minval, state.discard);
			}
			break;
			// Discards all cards of a highest value in all players displays
		case 9:
			int maxval=0;
			for(int ply = 0; ply<numply; ply++){
				if(state.getPlayers()[ply].displayHighest()>maxval)maxval= state.getPlayers()[ply].displayHighest();
			}
			break;
			
			//Dishonour: removes all follower (white) cards from all players display
		case 10:
			for(int ply = 0; ply<numply; ply++){
				if(ply != turn && !(state.getPlayers()[ply].isWithdrawn())){
					state.getPlayers()[ply].getHand().DiscardCol('W', state.discard);
				}
			}
			break;
			
			//Adapt: removes all duplicate cards, player chooses
		case 11:
			
			break;
			
			
		case 12:
			
			break;
		case 13:
			state.setShield(turn);
			break;
		case 14:
			
			break;
		case 15:
			
			break;
		case 16:
			
			break;
		case 17:
			
			break;
		default:
			throw new IllegalArgumentException();
		}
		state.playCard(new Card(val, type ), turn);
		
	}
	
	//boolean to check if player has a maiden card.
	public boolean withdraw(){
		if(lastLeft()) return false;
		if(state.getCol()=='N'){
			System.out.print("You're trying to withdraw from a tournament that hasn't started. Don't do that.\n");
			return false;
		}
		state.withdraw(turn);
		playersleft--;
		return state.getPlayers()[turn].containsMaiden();
	}
	
	public boolean lastLeft(){
		if(playersleft==1){
			System.out.print("You've won the last tounrnament, please start a new tournament\n");
			return true;
		}
		return false;
	}
	
	public void startTour(char col){
		if(!state.getPlayers()[turn].getHand().containstype(col)){
			System.out.print("YOu don't have that colour of card\n");
			return;
		}
		state.startTour(col);
		playersleft=numply;
	}
	
	public int turnNum(){
		return turn;
	}
	
	public void printState(){
		System.out.print("Card remaining in Deck: "+state.getDeck().remaining()+"\t Cards in discard pile: "+state.getDiscard().remaining()+"\tPlayers left :"+playersleft+"\n");
		System.out.print("Tournament colour: "+state.getCol()+"\t Player "+state.highestDisplay()+"is in the lead\n--------------\n");
		for(int i = 0; i<state.numPlayers;i++){
			//System.out.print("Player "+i+" has ")
			System.out.print("Player "+i+"'s hand:\n");
			state.getPlayers()[i].getHand().display();
			System.out.print("\nPlayer "+i+"'s display:\t value of:"+displayVal(i)+"\n");
			state.getPlayers()[i].displayPrint();
			System.out.print("\n");
		}
		System.out.print("\n\n\n");
		System.out.print("Player "+turn+"'s turn\n\n\n");
		
	}
	
	
	//returns the value of the display, using the number of cards if tournament color is green
	public int displayVal(int player){
		if(state.getCol()!='G'){
			return state.getPlayers()[player].displayVal();
		}
		else{
			return state.getPlayers()[player].displayNum();
		}
	}
	
	
	public void setState(GameBoard inState){
		state= inState;
	}
	
	public GameBoard currentState(){
		return state;
	}
}
