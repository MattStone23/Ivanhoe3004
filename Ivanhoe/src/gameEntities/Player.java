package gameEntities;
import java.util.*;
public class Player {
	Hand plyHand;
	Stack<Card> display;
	int colours[];
	boolean withdrawn;
	Card shield;
	public Player(){
		plyHand = new Hand();
		display = new Stack<Card>();
		colours = new int[4];
		shield= null;
		for(int i = 0; i< 4; i++){
			colours[i]=0;
		}
	}
	
	public int displayVal(){
		int out = 0 ;
		for(Card card : display){
			out += card.getValue();
		}
		return out;
	}
	
	public int displayLowest(){
		int temp= 10;
		for(int i= 0 ; i<display.size();i++){
			if(display.get(i).getValue()<temp) temp= display.get(i).getValue();
		}
		return temp;
	}
	
	public boolean containsMaiden(){
		return (display.contains(new Card(6, 'W')));
	}
	
	public int displayHighest(){
		int temp= 0;
		for(int i= 0 ; i<display.size();i++){
			if(display.get(i).getValue()>temp) temp= display.get(i).getValue();
		}
		return temp;
	}
	
	
	public void enterTour(){
		withdrawn=false;
		shield= null;
	}
	
	public void setShield(){
		shield = new Card(13, 'A');
	}
	
	public void withdraw(Deck discard){
		withdrawn= true;
		if(shield !=null) discard.putInto(shield);
		while(!display.isEmpty()){
			discard.putInto(display.pop());
		}
		shield= null;
	}
	
	public boolean isWithdrawn(){
		return withdrawn;
	}
	
	public int displayNum(){
		return display.size();
	}
	
	public void moveCard(int card){
		plyHand.addCard(display.get(card));
		display.remove(card);
	}
	
	public void displayDisc(int card, Deck discard){
		discard.putInto(display.get(card));
		display.remove(card);
	}
	
	public void displayDiscVal(int val, Deck discard){
		for( Card card:display){
			if(card.getValue()==val && card.getColour()!='A'){
				discard.putInto(card);
				display.remove(card);
			}
		}
	}
	public void addStone(char type){
		switch (type){
		case 'R':
			colours[0]++;
			break;
		case 'G':
			colours[1]++;
			break;
		case 'Y':
			colours[2]++;
			break;
		case 'B':
			colours[3]++;
			break;
		case 'P':
			colours[4]++;
			break;
		default:
			throw new IllegalArgumentException();			
		}
			
	}
	
	//returns how many different types of tournaments a player has won.
	public int getNumcolours(){
		int numcol = 0;
		for(int i=0;i <=4; i++){
			if(colours[i]>0) numcol++;
		}
		return numcol;
	}
	
	public int[] getStones(){
		return colours;
	}
	
	//removes a token of a selected colour in case of failing to win a tournament with a maiden in play.
	public void removeColour( char type){
		switch (type){
		case 'R':
			if(colours[0]<1) throw new IllegalArgumentException();
			colours[0]--;
			break;
		case 'G':
			if(colours[1]<1) throw new IllegalArgumentException();
			colours[1]--;
			break;
		case 'Y':
			if(colours[2]<1) throw new IllegalArgumentException();
			colours[2]--;
			break;
		case 'B':
			if(colours[3]<1) throw new IllegalArgumentException();
			colours[3]--;
			break;
		case 'P':
			if(colours[4]<1) throw new IllegalArgumentException();
			colours[4]--;
			break;
		default:
			throw new IllegalArgumentException();			
		}
	}
	
	public void playCard(Card type){
		display.push(plyHand.play(type));
	}
	public void discardType(Card type, Deck discard){
		for( int i =0; i<plyHand.retHandStack().size();i++){
			if(plyHand.retHandStack().get(i).equals(type)){
				plyHand.Discard(i, discard);
			}
		}
	}
	public Hand getHand(){
		return plyHand;
	}
	
	public void displayPrint(){
		for (Card card :display){
			card.print();
		}
	}
}
