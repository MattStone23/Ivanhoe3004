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

import net.miginfocom.swing.MigLayout;

public class PlayerPanel extends JPanel {

	JLabel lblName;
	Player player;
	
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
		player = pl;
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[100px,left][100px,grow 50]", "[20px,top][100px,grow]"));
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setOpaque(false);
		FlowLayout fl_pnlInfo = (FlowLayout) pnlInfo.getLayout();
		fl_pnlInfo.setHgap(30);
		fl_pnlInfo.setAlignment(FlowLayout.LEFT);
		add(pnlInfo, "cell 0 0 2 1,alignx left,aligny center");
		lblName = new JLabel("NAME " + pnNum);
		pnlInfo.add(lblName);
		
		JPanel pnlTokens = new JPanel();
		pnlTokens.setOpaque(false);
		pnlInfo.add(pnlTokens);
		
		JLabel lblTokens = new JLabel("TOKENS");
		pnlTokens.add(lblTokens);
		
		for (int x=0;x<5;x++){
			JLabel lblStone = new JLabel();
			lblStone.setOpaque(true);
			lblStone.setPreferredSize(new Dimension(10,10));
			if (player.getStones()[x]==0){
				lblStone.setBackground(Color.black);
			}
			else{
				switch (x){
				case 0:
					lblStone.setBackground(config.getColor('R'));
					break;
				case 1:
					lblStone.setBackground(config.getColor('G'));
					break;
				case 2:
					lblStone.setBackground(config.getColor('Y'));
					break;
				case 3:
					lblStone.setBackground(config.getColor('B'));
					break;
				case 4:
					lblStone.setBackground(config.getColor('P'));
					break;
				}
			}
			pnlTokens.add(lblStone);
		}
		
		JLabel lblDisplayVal = new JLabel("DISPLAY VAL: "+player.displayVal());
		pnlInfo.add(lblDisplayVal);
		
		
		
		JPanel pnlShield = new JPanel();
		pnlShield.setOpaque(false);
		pnlInfo.add(pnlShield);
		
		JLabel lblShield = new JLabel("Shield: ");
		pnlShield.add(lblShield);
		
		if (player.isShielded()){
			JLabel lblShieldIcon = new JLabel(CardFactory.getShieldIcon());
			pnlShield.add(lblShieldIcon);
		}
		
		
		JPanel pnlStun = new JPanel();
		pnlStun.setOpaque(false);
		pnlInfo.add(pnlStun);
		
		JLabel lblStunned = new JLabel("Stunned: ");
		pnlStun.add(lblStunned);
		
		if (player.isStunned()){
			JLabel lblStunIcon = new JLabel(CardFactory.getStunIcon());
			pnlStun.add(lblStunIcon);
		}
		
		
		
		JPanel pnlHand = new JPanel();
		pnlHand.setOpaque(false);
		add(pnlHand, "cell 0 1,alignx left,aligny center");
		pnlHand.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblHand = new JLabel("Hand");
		pnlHand.add(lblHand);
		
		for (Card c: player.getHand().getHandStack()){
			JButton btnCard = new JButton(CardFactory.getCard(c));
			btnCard.setPreferredSize(new Dimension(70,100));
			pnlHand.add(btnCard);
		}
		
		
		
		
		JPanel pnlDisplay = new JPanel();
		pnlDisplay.setOpaque(false);
		FlowLayout fl_pnlDisplay = (FlowLayout) pnlDisplay.getLayout();
		fl_pnlDisplay.setAlignment(FlowLayout.LEFT);
		add(pnlDisplay, "cell 1 1,alignx left,aligny center");
		
		JLabel lblDisplay = new JLabel("DISPLAY");
		pnlDisplay.add(lblDisplay);
		
		for (Card c: player.getDisplay()){
			JButton btnCard = new JButton(CardFactory.getCard(c));
			btnCard.setPreferredSize(new Dimension(70,100));
			pnlDisplay.add(btnCard);
		}
		if (player.isWithdrawn()){
			JLabel lblWithdrawn = new JLabel("WITHDRAWN");
			lblWithdrawn.setFont(new Font("Tahoma", Font.BOLD, 26));
			lblWithdrawn.setForeground(Color.WHITE);
			pnlDisplay.add(lblWithdrawn);
		}
		
	}
	
	public void displayCard(Card c){
		try {                
	          BufferedImage image = ImageIO.read(new File("0.png"));
	       } catch (IOException ex) {
	            System.out.println("exception :"+ex);
	       }
	}

}
