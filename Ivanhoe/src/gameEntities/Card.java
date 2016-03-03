package gameEntities;

public class Card{
	private char colour;
	private int value;
	public Card(int val, char col){
		switch(col){
		
		case'G':if( val!=1){//checks that values are 1 for green cards
				System.out.println(val);
				throw new IllegalArgumentException();
			}
		break;
		
		case'P':if( (val<3 && val>5)&& val!=7 ){//checks that values are between 3 and 5 or 7 for purple cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'R':if(val<3 && val>5){//checks that values are between 3 and 5 for red cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'B':if(val<2 && val>5){//checks that values are between 2 and 5 for blue cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'Y':if( val<2 && val>4 ){//checks that values are between 2 and 4 for yellow cards
				throw new IllegalArgumentException();
			}
		break;
		
		case'W':if( ( (val<2 && val>3)&& val!=6 ) ){//checks that values are 2, 3, or 6 for white cards
				throw new IllegalArgumentException();
			}
			break;
		case'A':if(val<1 && val>17){//checks that values are between 1 and 17 for action cards
				throw new IllegalArgumentException();
			}
		break;
		
		default: 
			throw new IllegalArgumentException();	
		}
		colour= col;
		value=val;
		//System.out.println("Made a "+colour+" card with the face value of "+ val );
	}
	public char getColour(){
		return colour;
	}
	public int getValue(){
		return value;
	}
	//TODO: Override hash code, just in case.
	public boolean equals(Object object){
		final Card comp = (Card)object;
		if((comp.value == this.value)&&(comp.colour==this.colour)){
			return true;
		}
		else{
			return false;
		}
	}
	public void print(){
		System.out.println("This is a "+colour+" with the value of "+value);
	}
	public String toString(){
		String r = colour + "" + value;
		return r;
	}
}
