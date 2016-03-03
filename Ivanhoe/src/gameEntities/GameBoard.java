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
		if(tourney == 'P' && col== 'P'){
			throw new IllegalArgumentException();
		}
		for(int i=0; i<numPlayers; i++){
			players[i].enterTour();
		}
		tourney=col;
	}
	
	public void withdraw(int player){
		players[player].withdraw();
	}
	
	public char getCol(){
		return tourney;
	}
	
	public void changeCol(char col){
		tourney=col;
	}
	
	public void endTour(int winner, char col){
		
		/* commented out because winning is decided by who doesn't withdraw, not hand size
		int temp= 0;
		for(int i = 0 ; i<=numPlayers;i++){
			if(players[i].displayVal() > players[temp].displayVal()) temp= i;
			discard.putAll(players[i].display);
		*/
		if(tourney != 'P'){
			players[winner].addStone(tourney);
		}
		else{
			players[winner].addStone(col);
		}
	}
	
	public void returnCard(int card, int player){
		players[player].moveCard(card);
	}
	
	public void playCard(Card type, int plynum){
		players[plynum].playCard(type);
	}
	
	
	public Deck getDeck(){
		return inPlay;
	}
	
	public Player[] getPlayers(){
		return players;
	}
}
