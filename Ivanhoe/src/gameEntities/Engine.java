package gameEntities;

public class Engine {
	GameBoard state;
	//tracks which palyers turn it is
	int turn;
	int numply;
	int playersleft;
	
	public Engine(int players){
		state = new GameBoard( players);
		turn =0;
		numply=0;
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
		if(in[1]=="A"){
			playActionCard(in);
		}
		else{
			state.playCard(new Card(val, type ), turn);
		}
	}
	
	public void playActionCard(String[] in){
		int val;
		val = Integer.parseInt(in[2]);
		switch(val){
		case 1:
			if (state.getCol() != 'p'){
				throw new IllegalArgumentException();
			}
			if (in[3].charAt(0)=='g' || in[3].charAt(0)=='p'){
				throw new IllegalArgumentException();
			}
			state.changeCol(in[3].charAt(0));
			break;
		case 2:
			if (state.getCol()=='p' || state.getCol()== 'g'){
				throw new IllegalArgumentException();
			}
			if (in[3].charAt(0)=='g' || in[3].charAt(0)=='p'){
				throw new IllegalArgumentException();
			}
			state.changeCol(in[3].charAt(0));
			break;
		case 3:
			if (state.getCol()=='p' || state.getCol()== 'g'){
				throw new IllegalArgumentException();
			}
			state.changeCol('g');
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		case 7:
			
			break;
		case 8:
			
			break;
		case 9:
			
			break;
		case 10:
			
			break;
		case 11:
			
			break;
		case 12:
			
			break;
		case 13:
			
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
		
	}
	
	//boolean to check if player has a maiden card.
	public boolean withdraw(){
		state.withdraw(turn);
		if(state.getPlayers()[turn].getHand().retHandStack().contains(new Card(6, 'w'))){
			return true;
		}
		return false;
	}
	
	
	
	
	//returns the value of the display, using the number of cards if tournemant colour is green
	public int displayVal(int player){
		if(state.getCol()!='g'){
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
