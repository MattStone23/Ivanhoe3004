package gameEntities;

public class AI {
	int playernum;
	Player playerstate;
	GameBoard tempstate;
	
	public AI(int plyr){
		playernum=plyr;
	}
	
	
	
	public String handle(String in){	
		String args[]= in.split("\\|");
		String AIstate[]= args[1+playernum].split("\\$");
		//Checks if the AIplayer has drawn a card this turn, if not the returns the draw command.
		if(AIstate[1].equals("false")){
			return "DRAW";
		}
		
		return "SOMETHING IS HORRIBLY WRONG!";
	}
}
