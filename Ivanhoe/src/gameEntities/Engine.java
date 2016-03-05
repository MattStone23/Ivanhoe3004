package gameEntities;

public class Engine {
	GameBoard state;
	//tracks which players turn it is
	
	
	
	public Engine(int players){
		state = new GameBoard( players);
		
	}
	
	
	//ends the turn and switches to the next valid player, then draws card for that player.
	public void endTurn(){
		do{
			state.setTurn((state.getTurn()+1)%state.getNumPlayers());
		}while(state.getPlayers()[state.getTurn()].isWithdrawn());
	}
	
	
	public void draw(){
		state.playerDraw(state.getTurn());
	}
	
	
	
	public void playCard( String[] in){
		Card c = new Card(in[1]);
		if(!state.getPlayers()[state.getTurn()].getHand().getHandStack().contains(c)){
			throw new IllegalArgumentException();
		}
		if(c.getColour()=='A'){
			playActionCard(in);
		}
		else{
			state.playCard(c, state.getTurn());
		}
	}
	//TODO make sure cards make it into the discard.
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
			if (in[3].charAt(0)=='G' || in[2].charAt(0)=='P'){
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
			
			//Break Lance: makes player indicated by in[3] discard all purple cards.
			//TODO: check if getter will return the object itself, or a copy of the object. could be double danger if it returns a copy. Currently assuming they will return the object itself.
		case 4:
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(ply != state.getTurn() && !(state.getPlayers()[ply].isWithdrawn())){
					state.getPlayers()[ply].getHand().DiscardCol('P', state.discard);
				}
			}
			break;
			
		case 5:
			//discards a specific card from the player indicated by arg[2]'s display
			state.getPlayers()[Integer.parseInt(in[2])].displayDisc( Integer.parseInt(in[3])  , state.discard);
			break;
			
			//Retreat: returns card from display to hand.
		case 6:
			state.returnCard(Integer.parseInt(in[2]), state.getTurn());
			break;
			
			
		case 7:
			//discards the last card in each players display.
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(ply != state.getTurn()){
					state.getPlayers()[ply].displayDisc(state.getPlayers()[ply].displayNum()-1, state.discard);
				}
			}
			break;
			
			// Discards all cards of a lowest value in all players displays
		case 8:
			int minval=10;
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(state.getPlayers()[ply].displayLowest()<minval)minval= state.getPlayers()[ply].displayLowest();
			}
			for(int ply = 0; ply<state.getNumPlayers();ply++){
				state.getPlayers()[ply].displayDiscVal(minval, state.discard);
			}
			break;
			// Discards all cards of a highest value in all players displays
		case 9:
			int maxval=0;
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(state.getPlayers()[ply].displayHighest()>maxval)maxval= state.getPlayers()[ply].displayHighest();
			}
			break;
			
			//Dishonour: removes all follower (white) cards from all players display
		case 10:
			for(int ply = 0; ply<state.getNumPlayers(); ply++){
				if(ply != state.getTurn() && !(state.getPlayers()[ply].isWithdrawn())){
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
	
	//boolean to check if player has a maiden card.
	public boolean withdraw(){
		state.withdraw(state.getTurn());

		/*if(state.getPlayers()[state.getTurn()].getHand().retHandStack().contains(new Card(6, 'W'))){
 		refs/remotes/origin/master
			return true;
		}
		return false;*/
		return state.getPlayers()[state.getTurn()].containsMaiden();
	}
	
	public void startTour(char col){
		state.startTour(col);
	}
	
	public int turnNum(){
		return state.getTurn();
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
