package gameEntities;
import java.util.*;
public class Player {
	Hand plyHand;
	Stack<Card> display;
	int colours[];
	public Player(){
		plyHand = new Hand();
		display = new Stack<Card>();
		colours = new int[4];
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
	public void addStone(char type){
		switch (type){
		case 'r':
			colours[0]++;
			break;
		case 'g':
			colours[1]++;
			break;
		case 'y':
			colours[2]++;
			break;
		case 'b':
			colours[3]++;
			break;
		case 'p':
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
	
	//removes a token of a selected colour in case of failing to win a tournament with a maiden in play.
	public void removeColour( char type){
		switch (type){
		case 'r':
			if(colours[0]<1) throw new IllegalArgumentException();
			colours[0]--;
			break;
		case 'g':
			if(colours[1]<1) throw new IllegalArgumentException();
			colours[1]--;
			break;
		case 'y':
			if(colours[2]<1) throw new IllegalArgumentException();
			colours[2]--;
			break;
		case 'b':
			if(colours[3]<1) throw new IllegalArgumentException();
			colours[3]--;
			break;
		case 'p':
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
}
