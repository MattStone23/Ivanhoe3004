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
	
	public void addCard(Card card){
		hand.push(card);
	}
	public void Discard(int i,Deck discardPile){
		discardPile.putInto(hand.get(i));
		hand.remove(i);
	}
	
	public void DiscardCol(char col, Deck discardPile){
		for(Card card :hand){
			if( card.getColour()== col){
				discardPile.putInto(card);
				hand.remove(card);
			}
		}
	}
	
	public void DiscardVal(int value, Deck discardPile){
		for(Card card :hand){
			if( card.getValue()== value){
				discardPile.putInto(card);
				hand.remove(card);
			}
		}
	}
	
	public boolean containstype(char t){
		for(Card card:hand){
			if(card.getColour()==t)
				return true;
		}
		return false;
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
	
	public Stack<Card> getHandStack(){
		return hand;
	}
}

