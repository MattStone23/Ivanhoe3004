package gameEntities;
import java.util.*;
public class GameBoard {
	Deck inPlay;
	Deck discard;
	Player[] players;
	int numPlayers;
	char tourney;
	
	public GameBoard(int numP){
		if( numP<2 || numP>5) throw new IllegalArgumentException();
		discard= new Deck();
		inPlay= new Deck();
		inPlay.DeckBuild();
		inPlay.shuffle();
		numPlayers=numP;
		players= new Player[numP];
		for(int i = 0; i<numP; i++){
			players[i]= new Player();
		}
	}
	public void playerDraw(int plyr){
		players[plyr].displayVal();
		players[plyr].plyHand.DrawCard(inPlay);
	}
	
	//starts a tournament, and makes it's colour the input colour
	public void startTour(char col){
		tourney=col;
	}
	
	public char getCol(){
		return tourney;
	}
	
	
	public void endTour(){
		int temp= 0;
		for(int i = 0 ; i<=numPlayers;i++){
			if(players[i].displayVal() > players[temp].displayVal()) temp= i;
			discard.putAll(players[i].display);
		}
		players[temp].addStone(tourney);
	}
	
	public void playCard(Card type, int plynum){
		players[plynum].playCard(type);
	}
}
