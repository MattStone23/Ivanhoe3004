package gameEntities;
import java.util.*;
public class Player {
	Hand plyHand;
	Stack<Card> display;
	int colours[];
	boolean withdrawn;
	Card shield;
	Card stun;
	public Player(){
		plyHand = new Hand();
		display = new Stack<Card>();
		colours = new int[5];
		shield=null;
		stun=null;
		for(int i = 0; i< 5; i++){
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
	}
	
	public void setShield(){
		shield = new Card(13, 'A');
	}
	
	public void setStun(){
		stun= new Card(14, 'A');
	}
	
	public boolean isShielded(){
		if(shield == null) return false;
		return true;
	}
	
	public boolean isStunned(){
		if(stun == null) return false;
		return true;
	}
	
	public void withdraw(Deck discard){
		withdrawn= true;
		if(shield !=null) discard.putInto(shield);
		if(stun !=null) discard.putInto(stun);
		while(!display.isEmpty()){
			discard.putInto(display.pop());
		}
		shield= null;
		stun=null;
	}
	
	public void win(Deck discard){
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
		if(display.size()<=1)return;

		discard.putInto(display.get(card));
		display.remove(card);
	}
	
	public void displayDiscVal(int val, Deck discard){
		Stack<Card> temp= new Stack<Card>();
		for( Card card:display){
			if(card.getValue()==val&&display.size()!=1){

				discard.putInto(card);
			}
			else temp.push(card);
		}

		if(temp.isEmpty()){
			temp.push(display.elementAt(0));
			discard.CardRemove(display.elementAt(0));
		}

		display=temp;
	}
	
	public void displayDiscCol(char col, Deck discard){
		Stack<Card> temp= new Stack<Card>();
		for( Card card:display){

			if(card.getColour()==col&&display.size()!=1){


				discard.putInto(card);
			}
			else temp.push(card);
		}

		if(temp.isEmpty()){
			temp.push(display.elementAt(0));
			discard.CardRemove(display.elementAt(0));
		}

		display=temp;
	}
	
	public boolean isWinner(int numPlyr){
		int numStone=0;
		if(numPlyr>3){
			for(int c:colours){
				if(c>0)numStone++;
			}
			if(numStone>=4)return true;
		}
		else{
			for(int c:colours){
				if(c>0)numStone++;
			}
			if(numStone>=5)return true;
			
		}
		return false;
	}
	
	
	public void addStone(char type){
		switch (type){
		case 'R':
			if(colours[0]<1)colours[0]++;
			break;
		case 'G':
			if(colours[1]<1)colours[1]++;
			break;
		case 'Y':
			if(colours[2]<1)colours[2]++;
			break;
		case 'B':
			if(colours[3]<1)colours[3]++;
			break;
		case 'P':
			if(colours[4]<1)colours[4]++;
			break;
		default:
			throw new IllegalArgumentException();			
		}
			
	}
	
	//returns how many different types of tournaments a player has won.
	public int getNumcolours(){
		int numcol = 0;
		for(int i=0;i <5; i++){

			if(colours[i]>0) numcol++;
		}
		return numcol;
	}
	
	public int[] getStones(){
		return colours;
	}
	
	//removes a token of a selected colour in case of failing to win a tournament with a maiden in play.
	public void removeColour( char type){
		if(this.getNumcolours()==0){
			return;
		}
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
	
	//DANGER! Only use for the outwit action, not to be trifled with, as may lose a card. Uses -1 and -2 for shield and stun cards respectively
	public Card removeCard(int i){
		if(i==-1){
			shield=null;
			return new Card(13,'A');
		}
		if(i==-2){
			stun=null;
			return new Card(14, 'A');
		}
		return display.remove(i);
	}
	
	public void addCard( Card add){
		if(add.equals(new Card(13, 'A'))) shield= add;
		else if(add.equals(new Card(14, 'A'))) stun= add;
		else display.push(add);
	}
	
	public void discardType(Card type, Deck discard){
		for( int i =0; i<plyHand.getHandStack().size();i++){
			if(plyHand.getHandStack().get(i).equals(type)){
				plyHand.Discard(i, discard);
			}
		}
	}
	
	public Hand getHand(){
		return plyHand;
	}
	

	public String getPrivateString(){
		String r = Arrays.toString(colours) + "$" +
				withdrawn +"$"+
				plyHand.toString() +"$"+
				displayToString();
		return r;
	}
	public String getPublicString(){
		String r = Arrays.toString(colours) + "$" +
				withdrawn +"$"+
				"size"+plyHand.getHandStack().size() +"$"+
				displayToString();
		return r;
	}
	
	private String displayToString(){
		String r = "";
		if (display.size()==0){
			r="0";
		}
		for (Card c : display){
			r = r + c.toString()+",";
		}
		if (r.length()>2)
			r=r.substring(0, r.length()-1);
		return r;
	}
	
	private void setDisplay(String s){
		if (!"0".equals(s)){
			String cards[] = s.split(",");
			Stack<Card> newDisplay = new Stack<Card>();
			for (int x = 0; x < cards.length; x++){
				Card newCard = new Card(cards[x]);
				newDisplay.push(newCard);
			}
			display.clear();
			display = newDisplay;
		}
		else{
			display.clear();
		}
	}
	
	private void setColours(String s){
		s=s.substring(1, s.length()-1);
		String args[] = s.split(", ");
		for (int x=0;x<5;x++){
			colours[x] = Integer.parseInt(args[x]);
		}
	}
	
	public void setPlayer(String s){
		String args[] = s.split("\\$");
		setColours(args[0]);
		
		withdrawn = Boolean.parseBoolean(args[1]);
		
		//check public or private hand
		if (args[2].startsWith("size")){
			plyHand.setHand(Integer.parseInt(args[2].substring(4)));
		}
		else{
			plyHand.setHand(args[2]);
		}
		
		setDisplay(args[3]);
	}

	public void displayPrint(){
		for (Card card :display){
			card.print();
		}

	}	
	public Stack<Card> getDisplay(){
		return display;
	}
	public String[] getDisplayAsStringArray(){
		String[] cards = new String[display.size()];
		for (int x=0;x<cards.length;x++){
			cards[x] = display.get(x).toString();
		}
		return cards;
	}
}
