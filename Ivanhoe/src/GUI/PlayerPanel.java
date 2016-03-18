package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import gameEntities.Card;
import java.awt.FlowLayout;

public class PlayerPanel extends JPanel {

	JLabel lblName;
	
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
	public PlayerPanel(int pnNum) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel_2);
		lblName = new JLabel("NAME " + pnNum);
		panel_2.add(lblName);
		
		JLabel lblTokens = new JLabel("TOKENS");
		panel_2.add(lblTokens);
		
		JLabel lblDisplayVal = new JLabel("DISPLAY VAL");
		panel_2.add(lblDisplayVal);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1);
		
		JLabel lblDisplay = new JLabel("DISPLAY");
		panel_1.add(lblDisplay);
		
		JLabel lblCard1 = new JLabel(CardFactory.getCard(new Card("A5")));
		panel_1.add(lblCard1);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		add(panel);
		
		JLabel lblHand = new JLabel("Hand");
		panel.add(lblHand);
		
	}
	
	public void displayCard(Card c){
		try {                
	          BufferedImage image = ImageIO.read(new File("0.png"));
	       } catch (IOException ex) {
	            System.out.println("exception :"+ex);
	       }
	}

}
