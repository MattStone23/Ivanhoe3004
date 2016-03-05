package gameEntities;
import java.util.*;
import Util.config;
public class GameBoard {
	Deck inPlay;
	Deck discard;
	Player[] players;
	int numPlayers;
	char tourney, oldtourney;
	
	public GameBoard(int numP){
		if( numP<2 || numP>5) throw new IllegalArgumentException();
		discard= new Deck();
		inPlay= new Deck();
		inPlay.DeckBuild();
		if(config.SEEDED) inPlay.seededShuffle();
		else inPlay.shuffle();
		numPlayers=numP;
		tourney='N';
		players= new Player[numP];
		for(int i = 0; i<numP; i++){
			players[i]= new Player();
			for( int j = 0; j<8; j++){
				playerDraw(i);
			}
		}
	}
	public void playerDraw(int plyr){
		players[plyr].displayVal();
		players[plyr].plyHand.DrawCard(inPlay);
		if(inPlay.remaining()==0){
			inPlay= discard;
			discard = new Deck();
			if(config.SEEDED) inPlay.seededShuffle();
			else inPlay.shuffle();
		}
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
		players[player].withdraw(discard);
	}
	
	public void setShield(int player){
		players[player].setShield();
	}
	public char getCol(){
		return tourney;
	}
	
	public void changeCol(char col){
		tourney=col;
	}
	

	public int highestDisplay(){
		int plyerHI=-1, dispHI=0;
		for(int i=0; i<numPlayers;i++){
			if(players[i].displayVal()>dispHI)
				dispHI=players[i].displayVal();
				plyerHI=i;
		}
		return plyerHI;
	}
	
	public boolean hasValidCard(int plyer){
		if(players[plyer].getHand().containstype('G')||players[plyer].getHand().containstype('Y')||players[plyer].getHand().containstype('R')||players[plyer].getHand().containstype('B')||players[plyer].getHand().containstype('W')){
			return true;
		}
		if(oldtourney!='P'&&players[plyer].getHand().containstype('P')){
			return true;
		}
		return false;
	}
	
	public int highestDisplayG(){
		int plyerHI=-1, dispHI=0;
		for(int i=0; i<numPlayers;i++){
			if(players[i].displayNum()>dispHI)
				dispHI=players[i].displayNum();
				plyerHI=i;
		}
		return plyerHI;
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
		oldtourney=tourney;
		tourney='N';
	}
	
	public void returnCard(int card, int player){
		players[player].moveCard(card);
	}
	
	public void playCard(Card type, int plynum){
		if(type.getColour()=='A'){
			players[plynum].discardType(type, discard);
		}
		else players[plynum].playCard(type);
	}
	
	
	public Deck getDeck(){
		return inPlay;
	}
	
	public Deck getDiscard(){
		return discard;
	}
	
	public Player[] getPlayers(){
		return players;
	}
}
