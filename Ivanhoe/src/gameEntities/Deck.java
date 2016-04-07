
package gameEntities;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import Util.config;
public class Deck {
	/*using stack here, as it give us a nice pop and push function, good for the deck and the discard pile
	 * also extends vector, meaning we can see all elements and change them(for shuffling, plus use clone
	 *  for when moving deck from discard pile to in play deck
	 */
	private Stack<Card> currentDeck= new Stack<Card>();
	//fills the deck with the appropriate number and style of cards
	public void DeckBuild(){
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(3,'P'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(4,'P'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(5,'P'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(7,'P'));
			}
			
			
			for(int f=0; f<6; f++){
				currentDeck.push(new Card(3,'R'));
			}
			for(int f=0; f<6; f++){
				currentDeck.push(new Card(4,'R'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(5,'R'));
			}
			
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(2,'B'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(3,'B'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(4,'B'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(5,'B'));
			}
			
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(2,'Y'));
			}
			for(int f=0; f<8; f++){
				currentDeck.push(new Card(3,'Y'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(4,'Y'));
			}
			
			for(int f=0; f<14; f++){
				currentDeck.push(new Card(1,'G'));
			}
			
			for(int f=0; f<8; f++){
				currentDeck.push(new Card(2,'W'));
			}
			for(int f=0; f<8; f++){
				currentDeck.push(new Card(3,'W'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(6,'W'));
			}
			
			
			for(int f=1; f<=15; f++){
				currentDeck.push(new Card(f,'A'));
			}
			currentDeck.push(new Card(16, 'A'));
			currentDeck.push(new Card(16, 'A'));
			currentDeck.push(new Card(16, 'A'));
			currentDeck.push(new Card(17, 'A'));
			currentDeck.push(new Card(17, 'A'));

	}
	
	public int remaining(){
		return currentDeck.size();
	}
	public Card draw(){
		return currentDeck.pop();
	}
	
	public void putInto(Card ACard){
		currentDeck.push(ACard);
	}
	public void putAll(Stack<Card> disc){
		while(!(disc.empty())){
			this.putInto(disc.pop());
		}
	}
	
	public Stack<Card> getCurrentDeck(){
		return currentDeck;
	}
	public void CardRemove(Card r){
		currentDeck.remove(r);
	}
	public boolean containsCard(Card aCard){
		if(currentDeck.contains(aCard)){
			return true;			
		}
		else return false;
	}
	
	public void shuffle(){
		Card temp;
		for(int i=0 ;i<=currentDeck.size()-1  ; i++){
			int rnd=(int) (Math.random()*(currentDeck.size()-i)) +i;
			temp= currentDeck.get(rnd);
			currentDeck.set(rnd, currentDeck.get(i));
			currentDeck.set(i, temp);			
		}
	}
	//provides a constant random deck
	public void seededShuffle(){
		Collections.shuffle(currentDeck, new Random(config.RANDOMSEED));
	}

}

