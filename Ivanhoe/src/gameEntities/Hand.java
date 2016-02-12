package gameEntities;
import java.util.*;
public class Hand {
	Stack<Card> hand;
	
	//TODO: change this to a constructor
	public void initialize(){
		hand= new Stack<Card>();
	}
	public void DrawCard(Deck Inplay){
		hand.push(Inplay.draw());
	}
	public void Discard(int i,Deck discardPile){
		discardPile.putInto(hand.get(i));
		hand.remove(i);
	}
	public void swap(int i, int j){
		
	}
}
