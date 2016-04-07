package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Util.config;
import gameEntities.Card;
import gameEntities.Player;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

public class PlayerPanel extends JPanel {

	private JLabel lblName;
	private JLabel lblToken;
	private JLabel lblDisplayVal;
	private JLabel lblShield;
	private JLabel lblStunned;
	private JLabel lblShieldIcon;
	private JLabel lblStunIcon;
	private JLabel lblHand;
	private JLabel lblDisplay;
	private JLabel lblWithdrawn;
	
	
	private JLabel[] lblTokens;
	
	private CardButton[] btnHand;
	private CardButton[] btnDisplay;
	
	private JPanel pnlTokens;
	private JPanel pnlInfo;
	private JPanel pnlCards;
	private JPanel pnlShield;
	private JPanel pnlStun;
	
	
	private int playerNum;
	private Player player;
	
	/**
	 * Create the panel.
	 */
	
	/**
	 * Should display the following
	 * Name
	 * Tokens
	 * Hand(maybe?)
	 * Display
	 * Display Value
	 * Shield
	 * Stunned
	 * Withdrawn
	 */
	public PlayerPanel(int pnNum, Player pl) {
		playerNum = pnNum;
		player = pl;
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[100px,left][100px,grow 50]", "[20px,top][100px,grow]"));
		
		pnlInfo = new JPanel();
		pnlInfo.setOpaque(false);
		FlowLayout fl_pnlInfo = (FlowLayout) pnlInfo.getLayout();
		fl_pnlInfo.setHgap(30);
		fl_pnlInfo.setAlignment(FlowLayout.LEFT);
		add(pnlInfo, "cell 0 0 2 1,alignx left,aligny center");
		
		lblName = new JLabel("Player " + playerNum);
		pnlInfo.add(lblName);
		
		pnlTokens = new JPanel();
		pnlTokens.setOpaque(false);
		pnlInfo.add(pnlTokens);
		
		lblToken = new JLabel("TOKENS");
		pnlTokens.add(lblToken);
		
		lblTokens = new JLabel[5];
		for (int x=0;x<5;x++){
			lblTokens[x] = new JLabel();
			lblTokens[x].setOpaque(true);
			lblTokens[x].setPreferredSize(new Dimension(10,10));
			if (player.getStones()[x]==0){
				lblTokens[x].setBackground(Color.black);
			}
			else{
				switch (x){
				case 0:
					lblTokens[x].setBackground(config.getColor('R'));
					break;
				case 1:
					lblTokens[x].setBackground(config.getColor('G'));
					break;
				case 2:
					lblTokens[x].setBackground(config.getColor('Y'));
					break;
				case 3:
					lblTokens[x].setBackground(config.getColor('B'));
					break;
				case 4:
					lblTokens[x].setBackground(config.getColor('P'));
					break;
				}
			}
			pnlTokens.add(lblTokens[x]);
		}
		
		lblDisplayVal = new JLabel("DISPLAY VAL: "+player.displayVal());
		pnlInfo.add(lblDisplayVal);
		
		
		
		pnlShield = new JPanel();
		pnlShield.setOpaque(false);
		pnlInfo.add(pnlShield);
		
		lblShield = new JLabel("Shield: ");
		pnlShield.add(lblShield);
		
		
		lblShieldIcon = new JLabel(CardFactory.getShieldIcon());
		lblShieldIcon.setVisible(player.isShielded());
		pnlShield.add(lblShieldIcon);
		
		
		
		
		pnlStun = new JPanel();
		pnlStun.setOpaque(false);
		pnlInfo.add(pnlStun);
		
		lblStunned = new JLabel("Stunned: ");
		pnlStun.add(lblStunned);
		

		lblStunIcon = new JLabel(CardFactory.getStunIcon());
		lblStunIcon.setVisible(player.isStunned());
		pnlStun.add(lblStunIcon);

		
		
		
		pnlCards = new JPanel();
		pnlCards.setOpaque(false);
		add(pnlCards, "cell 0 1,alignx left,aligny center");
		pnlCards.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		lblHand = new JLabel("Hand");
		pnlCards.add(lblHand);
		
		int x = 0;
		btnHand = new CardButton[player.getHand().getSize()];
		for (Card c: player.getHand().getHandStack()){
			btnHand[x] = new CardButton(c);
			btnHand[x].setPreferredSize(new Dimension(70,100));
			pnlCards.add(btnHand[x]);
			x++;
		}
		
		
		
		
//		pnlDisplay = new JPanel();
//		pnlDisplay.setOpaque(false);
//		FlowLayout fl_pnlDisplay = (FlowLayout) pnlDisplay.getLayout();
//		fl_pnlDisplay.setAlignment(FlowLayout.LEFT);
//		//add(pnlDisplay, "cell 1 1,alignx left,aligny center");
		
		lblDisplay = new JLabel("DISPLAY");
		pnlCards.add(lblDisplay);
		
		x=0;
		btnDisplay = new CardButton[player.displayNum()];
		for (Card c: player.getDisplay()){
			btnDisplay[x] = new CardButton(c);
			btnDisplay[x].setPreferredSize(new Dimension(70,100));
			pnlCards.add(btnDisplay[x]);
			x++;
		}
		
		lblWithdrawn = new JLabel("WITHDRAWN");
		lblWithdrawn.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblWithdrawn.setForeground(Color.WHITE);
		if (player.isWithdrawn()){
			pnlCards.add(lblWithdrawn);
		}
		
	}
	
	public void setPlayer(int pn, Player p){
		player = p;
		playerNum = pn;
		update();
		
	}
	
	private void update(){
		//disable
		updateInfo();
		updateCards();
		this.repaint();
		//enable
	}
	
	private void updateCards(){
		pnlCards.removeAll();
		
		pnlCards.setOpaque(false);
		pnlCards.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		//update hand
		lblHand = new JLabel("Hand");
		pnlCards.add(lblHand);
		
		int x = 0;
		btnHand = new CardButton[player.getHand().getSize()];
		for (Card c: player.getHand().getHandStack()){
			btnHand[x] = new CardButton(c);
			btnHand[x].setPreferredSize(new Dimension(70,100));
			pnlCards.add(btnHand[x]);
			x++;
		}
		
		//update display
		lblDisplay = new JLabel("DISPLAY");
		pnlCards.add(lblDisplay);
		
		x=0;
		btnDisplay = new CardButton[player.displayNum()];
		for (Card c: player.getDisplay()){
			btnDisplay[x] = new CardButton(c);
			btnDisplay[x].setPreferredSize(new Dimension(70,100));
			pnlCards.add(btnDisplay[x]);
			x++;
		}
		
		//update withdrawn
		if (player.isWithdrawn()){
			pnlCards.add(lblWithdrawn);
		}
	}
	
	private void updateInfo(){
		lblName.setText("Player "+playerNum);
		updateTokens();
		lblDisplayVal.setText("Display Value: "+player.displayVal());
		lblShieldIcon.setVisible(player.isShielded());
		lblShieldIcon.setVisible(player.isStunned());
	}
	
	public void updateforGreenTourn(){
		lblDisplayVal.setText("Display Value: "+player.displayNum());
	}
	
	private void updateTokens(){
		for (int x=0;x<5;x++){
			if (player.getStones()[x]==0){
				lblTokens[x].setBackground(Color.black);
			}
			else{
				switch (x){
				case 0:
					lblTokens[x].setBackground(config.getColor('R'));
					break;
				case 1:
					lblTokens[x].setBackground(config.getColor('G'));
					break;
				case 2:
					lblTokens[x].setBackground(config.getColor('Y'));
					break;
				case 3:
					lblTokens[x].setBackground(config.getColor('B'));
					break;
				case 4:
					lblTokens[x].setBackground(config.getColor('P'));
					break;
				}
			}
		}
	}
	
	public CardButton[] getHand(){
		return btnHand;
	}

}
