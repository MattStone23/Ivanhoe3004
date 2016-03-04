package gameEntities;

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
		do{
			turn= (turn+1)%numply;
		}while(state.getPlayers()[turn].isWithdrawn());
	}
	
	
	public void draw(){
		state.playerDraw(turn);
	}
	
	
	
	public void playCard( String[] in){
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
		state.withdraw(turn);

		/*if(state.getPlayers()[turn].getHand().retHandStack().contains(new Card(6, 'W'))){
 		refs/remotes/origin/master
			return true;
		}
		return false;*/
		return state.getPlayers()[turn].containsMaiden();
	}
	
	public void startTour(char col){
		state.startTour(col);
	}
	
	public int turnNum(){
		return turn;
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
	
	public String getGameStateForPlayer(int aPlayer){
		String gameState = state.getCol() + "~" +
							turn +"~"+
							state.getDeck().remaining()+"~"+
							state.getDiscard().remaining() +"~"+
							numply;
		
		for (int x=0;x<numply;x++){
			if (x == aPlayer){
				gameState = gameState + "|" + state.getPlayers()[x].getPrivateString();
			}
			else{
				gameState = gameState + "|" + state.getPlayers()[x].getPublicString();
			}
			
		}
		
		
		return null;
	}
}
