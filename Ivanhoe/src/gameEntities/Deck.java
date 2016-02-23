
package gameEntities;
import java.util.*;
public class Deck {
	/*using stack here, as it give us a nice pop and push function, good for the deck and the discard pile
	 * also extends vector, meaning we can see all elements and change them(for shuffling, plus use clone
	 *  for when moving deck from discard pile to in play deck
	 */
	private Stack<Card> currentDeck= new Stack<Card>();
	//fills the deck with the appropriate number and style of cards
	public void DeckBuild(){
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(3,'p'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(4,'p'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(5,'p'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(7,'p'));
			}
			
			
			for(int f=0; f<6; f++){
				currentDeck.push(new Card(3,'r'));
			}
			for(int f=0; f<6; f++){
				currentDeck.push(new Card(4,'r'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(5,'r'));
			}
			
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(2,'b'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(3,'b'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(4,'b'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(5,'b'));
			}
			
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(2,'y'));
			}
			for(int f=0; f<8; f++){
				currentDeck.push(new Card(3,'y'));
			}
			for(int f=0; f<2; f++){
				currentDeck.push(new Card(4,'y'));
			}
			
			for(int f=0; f<14; f++){
				currentDeck.push(new Card(1,'g'));
			}
			
			for(int f=0; f<8; f++){
				currentDeck.push(new Card(2,'w'));
			}
			for(int f=0; f<8; f++){
				currentDeck.push(new Card(3,'w'));
			}
			for(int f=0; f<4; f++){
				currentDeck.push(new Card(6,'w'));
			}
			
			
			for(int f=1; f<=15; f++){
				currentDeck.push(new Card(f,'a'));
			}
			currentDeck.push(new Card(16, 'a'));
			currentDeck.push(new Card(16, 'a'));
			currentDeck.push(new Card(16, 'a'));
			currentDeck.push(new Card(17, 'a'));
			currentDeck.push(new Card(17, 'a'));

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
			int rnd=(int) (Math.random()*(110-i)) +i;
			temp= currentDeck.get(rnd);
			currentDeck.set(rnd, currentDeck.get(i));
			currentDeck.set(i, temp);			
		}
	}

}

