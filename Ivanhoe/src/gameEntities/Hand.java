package gameEntities;
import java.util.*;
public class Hand {
	Stack<Card> hand;
	public Hand(){
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
	
	public void display(){
		for (Card card :hand){
			card.print();
		}
	}
	
	public Card play(Card type){
		if(hand.contains(type)){
			hand.remove(type);			
			return type;
		}
		throw new IllegalArgumentException();
	}
	
	public Stack<Card> retHandStack(){
		return hand;
	}
}

