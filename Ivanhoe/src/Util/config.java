package Util;

public class config {
	public static int PORT = 3000;
	public static String IP = "fe80::edb4:37bc:e476:d64a";
	public static int MAX_CLIENTS = 5;
	public static boolean SEEDED = true;
	public static String BLACK_TEXT = (char)27 + "[30m";
	public static String RED_TEXT = (char)27 + "[31m";
	public static String GREEN_TEXT = (char)27 + "[32m";
	public static String YELLOW_TEXT = (char)27 + "[33m";
	public static String BLUE_TEXT = (char)27 + "[34m";
	public static String PURPLE_TEXT = (char)27 + "[35m";
	public static String DEFAULT_TEXT = (char)27 + "[0m";
	public static String[][] ACTIONS = 
		{{"Unhorse","The tournament color changes from purple to red, blue or yellow, as announced by the player."},
		{"Change Weapon","The tournament color changes from red, blue or yellow to a different one of these colors, as announced by the player."},
		{"Drop Weapon","The tournament color changes from red, blue or yellow to green"},
		{"Break Lance","Force one opponent to discard all purple cards from his display."},
		{"Dodge","Discard any one card from any one opponent’s display"},
		{"Retreat","Take any one card from your own display back into your hand."},
		{"OutManuever","All opponents must remove the last card played on their displays."},
		{"Charge","Identify the lowest value card throughout all displays. All players must discard all cards of this value from their displays."},
		{"Counter Charge"," Identify the highest value card throughout all displays. All players must discard all cards of this value from their displays."},
		{"Disgrace","Each player must remove all his supporters from his display"},
		{"Adapt","INVALID\nEach player may only keep one card of each value in his display. All other cards with the same value are discarded. Each player decides which of the matching-value cards he will discard"},
		{"Outwit","INVALID\nPlace one of your faceup cards in front of an opponent, and take one faceup card from this opponent and place it face up in front of yourself. This may include the SHIELD and STUNNED cards."},
		{"Shield","INVALID\nA player plays this card face up in front of himself, but separate from his display. As long as a player has the SHIELD card in front of him, all action cards have no effect on his display"},
		{"Stunned","INVALID\nPlace this card separately face up in front of any one opponent. As long as a player has the STUNNED card in front of him, he may add only one new card to his display each turn."},
		{"IVANHOE","INVALID\nThis is the only card a player can play outside of his turn. A player can play it at any time as long as he is still in the tournament. Use this card to cancel all effects of any one action card just played."},
		{"Riposte","INVALID\nTake the last card played on any one opponent’s display and add it to your own display."},
		{"KnockDown","INVALID\nDraw at random one card from any one opponent’s hand and add it to your hand, without revealing the card to other opponents."}
		};
	
}
