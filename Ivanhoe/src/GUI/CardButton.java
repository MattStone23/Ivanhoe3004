package GUI;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gameEntities.Card;
import gameEntities.GameBoard;

public class CardButton extends JButton {
	private Card aCard;
	
	public CardButton(){
		super();
	}
	public CardButton(Card c){
		super(CardFactory.getCard(c));
		aCard = c;
	}
	
	public String handle(GameBoard state){
		if (aCard==null)
			return null;
		if (aCard.getColour()!='A'){
			return aCard.toString();
		}
		
		Object[] values;
		Object selected;
		Object[] values2;
		Object selected2;
		Object[] values3;
		Object selected3;
		switch (aCard.getValue()){
		case 1:
			//UNHORSE : select colour
			values = new Object[] {"RED","BLUE","YELLOW"};
			selected = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected!=null){
				return aCard.toString()+"|"+selected.toString().charAt(0);
			}
			break;
		case 2:
			//Change Weapon : select colour
			values = new Object[] {"RED","BLUE","YELLOW"};
			selected = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected!=null){
				return aCard.toString()+"|"+selected.toString().charAt(0);
			}
			break;
		case 3:
			//Drop Weapon : confirm
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 4:
			//BreakLance: opponent
			values = new Object[state.getNumPlayers()];
			for(int x=0;x<state.getNumPlayers();x++){
				values[x] = x;
			}
			selected = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected!=null){
				return aCard.toString()+"|"+selected.toString();
			}
			break;
			
			
		case 5:
			//Dodge: select player and display card
			//select opponent
			values = new Object[state.getNumPlayers()];
			for(int x=0;x<state.getNumPlayers();x++){
				values[x] = x;
			}
			selected = JOptionPane.showInputDialog(null, aCard.getDesc()+"\nPlease select which player", aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected==null){
				return null;
			}
			
			//select card
			values2 = state.getPlayers()[(int) selected].getDisplayAsStringArray();
			selected2 = JOptionPane.showInputDialog(null, aCard.getDesc()+"\nPlease select which card", aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values2, "0");
			if (selected2!=null){
				for (int x=0;x<values2.length;x++){
					if (selected2.equals(values2[x])){
						selected2 = x;
						break;
					}
				}
				return aCard.toString()+"|"+selected.toString()+"|"+selected2.toString();
			}
			break;
			
		case 6:
			//Retreat: select own display card
			values = state.getPlayers()[state.getTurn()].getDisplayAsStringArray();
			
			selected = JOptionPane.showInputDialog(null,aCard.getDesc(),aCard.getName(),JOptionPane.DEFAULT_OPTION,null,values,"0");
			if (selected!=null){
				for (int x=0;x<values.length;x++){
					if (selected.equals(values[x])){
						selected = x;
						break;
					}
				}
				return aCard.toString()+"|"+selected;
			}
			break;
		case 7:
			//Outmaneuver : confirm
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 8:
			//Charge : confirm
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 9:
			//Counter Charge : confirm
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 10:
			//Disgrace : confirm
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 11:
			//Adapt : confirm??
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 12:
			//Outwit : target player, own card, target card
			//opponent
			values = new Object[state.getNumPlayers()];
			for(int x=0;x<state.getNumPlayers();x++){
				values[x] = x;
			}
			selected = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected!=null){
				return aCard.toString()+"|"+selected.toString();
			}
			
			//own card
			values2 = state.getPlayers()[state.getTurn()].getDisplayAsStringArray();
			selected2 = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected2==null){
				return null;
			}
			for (int x=0;x<values2.length;x++){
				if (selected2.equals(values2[x])){
					selected2 = x;
					break;
				}
			}
			
			
			//target card
			values3 = state.getPlayers()[(int) selected].getDisplayAsStringArray();
			selected3 = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected3!=null){
				for (int x=0;x<values3.length;x++){
					if (selected3.equals(values3[x])){
						selected3 = x;
						break;
					}
				}
				return aCard.toString()+"|"+selected.toString()+"|"+selected2.toString()+"|"+selected3.toString();
			}
			
			break;
		case 13:
			//Shield : confirm
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 14:
			//Stunned : opponent
			values = new Object[state.getNumPlayers()];
			for(int x=0;x<state.getNumPlayers();x++){
				values[x] = x;
			}
			selected = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected!=null){
				return aCard.toString()+"|"+selected.toString();
			}
			break;
		case 15:
			//Ivanhoe : confirm
			if (JOptionPane.showConfirmDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
				return aCard.toString();
			}
			break;
		case 16:
			//Riposte : opponent
			values = new Object[state.getNumPlayers()];
			for(int x=0;x<state.getNumPlayers();x++){
				values[x] = x;
			}
			selected = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected!=null){
				return aCard.toString()+"|"+selected.toString();
			}
			break;
		case 17:
			//Knock Down : opponent
			values = new Object[state.getNumPlayers()];
			for(int x=0;x<state.getNumPlayers();x++){
				values[x] = x;
			}
			selected = JOptionPane.showInputDialog(null, aCard.getDesc(), aCard.getName(), JOptionPane.DEFAULT_OPTION, null, values, "0");
			if (selected!=null){
				return aCard.toString()+"|"+selected.toString();
			}
			break;
		}
		
		return null;
	}
}
