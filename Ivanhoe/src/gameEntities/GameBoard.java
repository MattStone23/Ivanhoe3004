package gameEntities;
 import java.util.*;
public class GameBoard {
	Deck inPlay;
	Deck discard;
	Player[] players;
	int numPlayers;
	
	public GameBoard(int numP){
		if( numP<2 || numP>5) throw new IllegalArgumentException();
		inPlay.DeckBuild();
		Deck discard= new Deck();
		Deck inPlay= new Deck();
		inPlay.DeckBuild();
		numPlayers=numP;
	}
	public void playerDraw(int plyr){
		players[plyr].plyHand.DrawCard(inPlay);
	}
	public void endTour(){
		for(int i = 0 ; i<=numPlayers;i++){
			discard.putAll(players[i].display);
		}
	}
	
	public void playCard(Card type, int plynum){
		players[plynum].playCard(type);
	}
}
