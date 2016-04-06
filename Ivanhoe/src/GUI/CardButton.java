package GUI;

import javax.swing.JButton;

import gameEntities.Card;

public class CardButton extends JButton {
	Card aCard;
	
	public CardButton(){
		super();
	}
	public CardButton(Card c){
		super(CardFactory.getCard(c));
		aCard = c;
	}
}
