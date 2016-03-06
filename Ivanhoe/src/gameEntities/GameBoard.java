package gameEntities;
import java.util.*;
import Util.config;
public class GameBoard {
	Deck inPlay;
	Deck discard;
	Player[] players;
	int numPlayers;
	char tourney, oldtourney;
	int turn;
	int playersleft;
	int deckSize;
	int discardSize;
	boolean hasDrawn;
	
	public GameBoard(int numP){
		if( numP<2 || numP>5) throw new IllegalArgumentException();
		discard= new Deck();
		inPlay= new Deck();
		inPlay.DeckBuild();
		if(config.SEEDED) inPlay.seededShuffle();
		else inPlay.shuffle();
		numPlayers=numP;
		turn =0;
		tourney = 'N';
		players= new Player[numP];
		for(int i = 0; i<numP; i++){
			players[i]= new Player();
			for( int j = 0; j<8; j++){
				playerDraw(i);
			}
		}
		hasDrawn=false;
		
	}
	public GameBoard(){
		inPlay = null;
		discard=null;
		turn = 0;
		players = null;
		numPlayers=0;
		tourney = 'N';
		hasDrawn=false;
		playersleft=0;
		deckSize=0;
		discardSize=0;
	}
	public Card playerDraw(int plyr){
		players[plyr].displayVal();
		Card c = players[plyr].plyHand.DrawCard(inPlay);
		hasDrawn=true;
		if(inPlay.remaining()==0){
			inPlay= discard;
			discard = new Deck();
			if(config.SEEDED) inPlay.seededShuffle();
			else inPlay.shuffle();
		}
		return c;
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
			if(players[i].displayVal()>dispHI){
				dispHI=players[i].displayVal();
				plyerHI=i;
			}
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
			if(players[i].displayNum()>dispHI){
				dispHI=players[i].displayNum();
				plyerHI=i;
			}
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
	
	public int getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int getPlayersleft() {
		return playersleft;
	}
	public void setPlayersleft(int playersleft) {
		this.playersleft = playersleft;
	}
	
	public int getDeckSize() {
		if (inPlay!=null)
			deckSize = inPlay.remaining();
		return deckSize;
	}
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	public int getDiscardSize() {
		if (discard!=null)
			discardSize = discard.remaining();
		return discardSize;
	}
	public void setDiscardSize(int discardSize) {
		this.discardSize = discardSize;
	}
	
	public void setGameState(String s){
		// Should recieve "GAMESTATE|localVariable|PlayerData|PlayerData|..."
		
		String args[] = s.split("\\|");
		setLocalState(args[1]);
		for (int x = 0; x<numPlayers;x++){
			if (players[x]==null)	players[x] = new Player();
			players[x].setPlayer(args[2+x]);
		}
	}
	
	private void setLocalState(String s){
		String args[] = s.split("~");
		tourney = args[0].charAt(0);
		this.setTurn(Integer.parseInt(args[1]));
		this.setDeckSize(Integer.parseInt(args[2]));
		this.setDiscardSize(Integer.parseInt(args[3]));
		this.setNumPlayers(Integer.parseInt(args[4]));
		if (players==null||players.length!=numPlayers)
			players = new Player[numPlayers];
		this.hasDrawn=Boolean.parseBoolean(args[5]);
	}
	
	/**
	 * 
	 * @param aPlayer an int represent which player is requesting the gameState. 1 to numberOfPlayers. Use -1 for testing to view all private data
	 * @return a String representation of the gameState.
	 */
	public String getGameStateForPlayer(int aPlayer){
		String gameState = this.getCol() + "~" +
							this.getTurn() +"~"+
							this.getDeckSize()+"~"+
							this.getDiscardSize() +"~"+
							this.getNumPlayers()+"~"+
							this.hasDrawn;
		
		for (int x=0;x<this.getNumPlayers();x++){
			if (x == aPlayer-1 || aPlayer == -1){
				gameState = gameState + "|" + this.getPlayers()[x].getPrivateString();
			}
			else{
				gameState = gameState + "|" + this.getPlayers()[x].getPublicString();
			}
			
		}
		
		
		return gameState;
	}
	
	public void printState(){
		System.out.print("Card remaining in Deck: "+this.getDeckSize()+"\t Cards in discard pile: "+this.getDiscardSize()+"\tPlayers left :"+this.getPlayersleft()+"\n-----------\n");
		for(int i = 0; i<this.numPlayers;i++){
			//System.out.print("Player "+i+" has ")
			System.out.print("Player "+i+"'s hand:\n");
			this.getPlayers()[i].getHand().display();
			System.out.print("\nPlayer "+i+"'s display:"+(this.getCol()=='G'?this.getPlayers()[i].displayNum():this.getPlayers()[i].displayVal())+"\n");
			if (this.getPlayers()[i].isWithdrawn())
				System.out.println("WITHDRAWN");
			else
				this.getPlayers()[i].displayPrint();
			System.out.print("\n");
		}
		System.out.print("TOURN COLOUR: "+this.getCol()+"\n\n");
		System.out.print("Player "+this.getTurn()+"'s turn:\tMOVES: "+getValidMoves(turn)+"\n\n\n");
		
	}
	
	public String getValidMoves(int player){
		if (!hasDrawn){
			return "DRAW";
		}
		if (tourney=='N'){
			return "STARTTOURN";
		}
		if (this.highestDisplay()==player){
			return "PLAY, WITHDRAW, ENDTURN";
		}
		else{
			return "PLAY, WITHDRAW";
		}
		
	}
	
	
	
}
