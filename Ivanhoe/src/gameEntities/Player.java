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
		colours = new int[5];
		shield=null;
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
	
	public void withdraw(Deck discard){
		withdrawn= true;
		if(shield !=null) discard.putInto(shield);
		while(!display.isEmpty()){
			discard.putInto(display.pop());
		}
		shield= null;
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
		discard.putInto(display.get(card));
		display.remove(card);
	}
	
	public void displayDiscVal(int val, Deck discard){
		Stack<Card> temp= new Stack<Card>();
		for( Card card:display){
			if(card.getValue()==val && card.getColour()!='A'){
				discard.putInto(card);
			}
			else temp.push(card);
		}
		display=temp;
	}
	
	public void displayDiscCol(char col, Deck discard){
		Stack<Card> temp= new Stack<Card>();
		for( Card card:display){
			if(card.getColour()==col){
				discard.putInto(card);
			}
			else temp.push(card);
		}
		display=temp;
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
}
