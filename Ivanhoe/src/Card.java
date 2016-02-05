
public class Card{
	private char colour;
	private int value;
	public Card(int val, char col){
		switch(col){
		
		case'g':if( val!=1){//checks that values are 1 for green cards
				System.out.println(val);
				throw new IllegalArgumentException();
			}
		break;
		
		case'p':if( (val<3 && val>5)&& val!=7 ){//checks that values are between 3 and 5 or 7 for purple cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'r':if(val<3 && val>5){//checks that values are between 3 and 5 for red cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'b':if(val<2 && val>5){//checks that values are between 2 and 5 for blue cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'y':if( val<2 && val>4 ){//checks that values are between 2 and 4 for yellow cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'w':if( ( (val<2 && val>3)&& val!=6 ) ){//checks that values are 2, 3, or 6 for white cards
				throw new IllegalArgumentException();
			}
			break;
		case'a':if(val<1 && val>17){//checks that values are between 1 and 17 for action cards
				throw new IllegalArgumentException();
			}
		break;
		
		default: 
			throw new IllegalArgumentException();	
		}
		colour= col;
		value=val;
		System.out.println("Made a "+colour+" card with the face value of "+ val );
	}
	public char getColour(){
		return colour;
	}
	public int getValue(){
		return value;
	}
}
