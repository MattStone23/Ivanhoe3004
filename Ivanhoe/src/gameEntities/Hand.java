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
		Stack<Card> temp= new Stack<Card>();
		for(Card card :hand){
			if( card.getColour()== col){
				discardPile.putInto(card);
			}
			else temp.push(card);
		}
		hand=temp;
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
	
	public String toString(){
		String r = "";
		for (Card c : hand){
			r = r + c.toString()+",";
		}
		if (r.length()>2)
			r=r.substring(0, r.length()-1);
		return r;
	}
	public void setHand(String s){
		String cards[] = s.split(",");
		Stack<Card> newHand = new Stack<Card>();
		for (int x = 0; x < cards.length; x++){
			Card newCard = new Card(cards[x]);
			newHand.push(newCard);
		}
		hand.clear();
		hand = newHand;
	}
	public void setHand(int numCards){
		hand.clear();
		for (int x=0;x<numCards;x++){
			hand.push(new Card());
		}
	}
}