package gameEntities;

import java.util.*;

public class AI {
	int playernum;
	Player playerstate;
	GameBoard internalState;
	
	public AI(int plyr){
		internalState = new GameBoard();
		playernum=plyr;
	}
	
	
	
	public String handle(String in){
		internalState.setGameState("GAMESTATE|"+in);
		String args[]= in.split("\\|");
		String AIstate[]= args[1+playernum].split("\\$");
		//Checks if the AIplayer has drawn a card this turn, if not the returns the draw command.
		if(AIstate[1].equals("false") && internalState.getTurn()==playernum){
			return "DRAW";
		}
		if(internalState.highestDisplay()==playernum&&internalState.getCol()!='G'){
			return "ENDTURN";
		}
		if(internalState.highestDisplayG()==playernum&&internalState.getCol()=='G'){
			return "ENDTURN";
		}
		return "SOMETHING IS HORRIBLY WRONG!";
	}
}

interface strategy{
	public String makeMove(String in);
}
/*
public class normalPlay implements strategy{
	@Override
	public String makeMove(String in){
		
	}
}

public class DesperateGambit implements strategy{
	@Override
	public String makeMove(String in){
		
	}
}

public class savingCards implements strategy{
	@Override
	public String makeMove(String in){
		
	}
}*/