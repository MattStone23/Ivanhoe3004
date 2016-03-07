package gameEntities;

import java.util.*;
public class Engine {
	GameBoard state;
	//tracks which player last drew a card
	
	
	
	public Engine(int players){
		state = new GameBoard( players);
	}
	
	
	//ends the turn and switches to the next valid player, then draws card for that player.
	public int endTurn(){
		if(!state.hasDrawn){

			System.out.print("YOu didn't draw a card this turn\n");
			throw new IllegalArgumentException("You didn't draw a card this turn\n");
		}
		if(state.getCol()=='N'&& state.hasValidCard(state.getTurn())){
			System.out.print("you have a valid card you can play to start the tournament!\n");
			throw new IllegalArgumentException("you have a valid card you can play to start the tournament!\n");
		}

		if(!state.getPlayers()[state.getTurn()].isWithdrawn()&&state.getCol()!='N'){
			if(state.getCol()=='G'){
				if(state.getTurn()!= state.highestDisplayG()){
					System.out.print("You have not played enough cards to be the highest value display\n");
					throw new IllegalArgumentException("You have not played enough cards to be the highest value display\n");
				}
			}
			else{
				if(state.getTurn()!=state.highestDisplay()){
					System.out.print("You have not played enough cards to be the highest value display\n");
					throw new IllegalArgumentException("You have not played enough cards to be the highest value display\n");
				}
			}
		}
		do{
			state.setTurn((state.getTurn()+1)%state.getNumPlayers());
		}while(state.getPlayers()[state.getTurn()].isWithdrawn());
		state.hasDrawn=false;
		if(state.getPlayersleft()==1){
			for(Player ply: state.getPlayers()){
				ply.enterTour();
			}
			if(state.getCol()=='P'){
				/*Scanner getCol= new Scanner(System.in);
				String col;
				System.out.print("You won a purple tournament! What colour token do you want?:  ");
				col= getCol.nextLine();
				System.out.print("\n\n");
				state.endTour(state.getTurn(), col.charAt(0));
				getCol.close();*/
				state.getPlayers()[state.getTurn()].win(state.getDiscard());
				return 1;
			}
			else state.endTour(state.getTurn(), state.getCol());
			state.getPlayers()[state.getTurn()].win(state.getDiscard());
			if(state.getPlayers()[state.getTurn()].isWinner(state.getNumPlayers())) return 2;			
		}
		return 0;
	}
	
	

	public Card draw(){
		if(lastLeft()) return null;
		return state.playerDraw(state.getTurn());
	}
	
	public void removeToken(String[] in){
		if(in.length!=2){
			throw new IllegalArgumentException("Illegal argument");
		}		
		state.getPlayers()[state.getTurn()].removeColour(in[1].charAt(0));
	}
	
	public void playCard( String[] in){
		if(lastLeft()) return;
		Card c = new Card(in[1]);

		if(!state.getPlayers()[state.getTurn()].getHand().getHandStack().contains(c)){
			throw new IllegalArgumentException();
		}
		if(c.getColour()=='A'){
			playActionCard(in);
		}
		else{
			if(state.getCol()!= c.getColour() && c.getColour()!= 'W' ) throw new IllegalArgumentException();
			if(state.getPlayers()[state.getTurn()].containsMaiden()) throw new IllegalArgumentException();
			state.playCard(c, state.getTurn());
		}
	}
	public void playActionCard(String[] in){
		Card c = new Card(in[1]);
		char type = c.getColour();
		int val = c.getValue();
		
		switch(val){
		//Unhorse card: changes tournament colour to not purple (RBY)
		case 1:
			if (state.getCol() != 'P'){
				throw new IllegalArgumentException();
			}
			if (in[2].charAt(0)=='G' || in[2].charAt(0)=='P'){
				throw new IllegalArgumentException();
			}
			state.changeCol(in[2].charAt(0));
			break;
		//Change weapon// goes from RBY tournament to RBY
		case 2:
			if (state.getCol()=='P' || state.getCol()== 'G'){
				throw new IllegalArgumentException();
			}
			if (in[2].charAt(0)=='G' || in[2].charAt(0)=='P'){
				throw new IllegalArgumentException();
			}
			state.changeCol(in[2].charAt(0));
			break;
			
		//Drop Weapon: Goes from RBY tournament to G tournament.
		case 3:
			if (state.getCol()=='G' || state.getCol()== 'G'){
				throw new IllegalArgumentException();
			}
			state.changeCol('G');
			break;
			
			//Break Lance: makes player indicated by in[2] discard all purple cards.
		case 4:
			state.getPlayers()[Integer.parseInt(in[2])].displayDiscCol('P', state.getDiscard());
			break;
			
		case 5:
			//Dodge:discards a specific card from the player indicated by arg[2]'s display
			state.getPlayers()[Integer.parseInt(in[2])].displayDisc( Integer.parseInt(in[3])  , state.discard);
			break;
			
			//Retreat: returns card from display to hand.
		case 6:
			state.returnCard(Integer.parseInt(in[2]), state.getTurn());
			break;
			
			
		case 7:
			//Outmanuver:discards the last card in each opponents display.
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(ply != state.getTurn()){
					state.getPlayers()[ply].displayDisc(state.getPlayers()[ply].displayNum()-1, state.discard);
				}
			}
			break;
			
			//Charge Discards all cards of a lowest value in all players displays
		case 8:
			int minval=10;
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(state.getPlayers()[ply].displayLowest()<minval)minval= state.getPlayers()[ply].displayLowest();
			}
			for(int ply = 0; ply<state.getNumPlayers();ply++){
				state.getPlayers()[ply].displayDiscVal(minval, state.discard);
			}
			break;
			// Counter charge Discards all cards of a highest value in all players displays
		case 9:
			int maxval=0;
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(state.getPlayers()[ply].displayHighest()>maxval)maxval= state.getPlayers()[ply].displayHighest();
			}
			for(int ply = 0; ply<state.getNumPlayers();ply++){
				state.getPlayers()[ply].displayDiscVal(maxval, state.discard);
			}
			break;
			
			//Dishonour: removes all follower (white) cards from all players display
		case 10:
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
					state.getPlayers()[ply].displayDiscCol('W', state.discard);	
			}
			break;
			
			//Adapt: removes all duplicate cards, player chooses
		case 11:
			
			break;
			
			
		case 12:
			
			break;
		case 13:
			state.setShield(state.getTurn());
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
		state.playCard(new Card(val, type ), state.getTurn());
		
	}
	//TODO: write prompt for maiden withdraw in server. returns true if display contains maiden
	//boolean to check if player has a maiden card.
	public boolean withdraw(){
		if(!state.hasDrawn){
			System.out.print("YOu didn't draw a card this turn\n");
			return false;
		}
		if(lastLeft()) return false;
		if(state.getCol()=='N'){
			System.out.print("You're trying to withdraw from a tournament that hasn't started. Don't do that.\n");
			return false;
		}
		if(state.highestDisplay()==-1&&state.getPlayers()[state.getTurn()].displayVal()==0){
			System.out.print("You haven't played a card yet!");
			return false;
		}
		boolean temp =state.getPlayers()[state.getTurn()].containsMaiden();
		state.withdraw(state.getTurn());
		state.setPlayersleft(state.getPlayersleft()-1);
		return temp;
	}
	
	public void addstone(String[] in){
		if(in.length!=2){
			throw new IllegalArgumentException("Illegal argument");
		}		
		state.endTour(state.getTurn(), (in[1].charAt(0)));
	}
	
	public boolean lastLeft(){
		if(state.getPlayersleft()==1){
			System.out.print("You've won the last tounrnament, please start a new tournament\n");
			return true;
		}
		return false;
	}
	

	public void startTour(String[] args){
		//check args
		char[] colours={'R','P','G','Y','B'};
		if (args.length!=2){
			throw new IllegalArgumentException("Improper arguments");
		}
		
		char col = args[1].charAt(0);
		boolean check=false;
		for(char colour:colours){
			if(col==colour){
				check=true;
			}
		}
		if(!check)throw new IllegalArgumentException("Improper colour argument");
		if(state.getCol()!='N'){
			System.out.print("No changing colours midtournament");
			return;
		}
		if(!state.getPlayers()[state.getTurn()].getHand().containstype(col)&&!state.getPlayers()[state.getTurn()].getHand().containstype('W')){
			System.out.print("YOu don't have that colour of card\n");
			return;
		}
		if(col=='P'&&state.getOldtour()=='P'){
			System.out.print("No running two purple tournaments in a row!\n");
			return;
		}
		state.startTour(col);
		state.setPlayersleft(state.getNumPlayers());
	}
	
	public int turnNum(){
		return state.getTurn();
	}
	
	public void printState(){
		state.printState();
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
	
	/**
	 * 
	 * @param aPlayer an int represent which player is requesting the gameState. 1 to numberOfPlayers. Use -1 for testing to view all private data
	 * @return a String representation of the gameState.
	 */
	public String getGameStateForPlayer(int aPlayer){
		return state.getGameStateForPlayer(aPlayer);
	}
}
