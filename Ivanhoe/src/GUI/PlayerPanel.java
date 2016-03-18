package GUI;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

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
		add(panel_2);
		lblName = new JLabel("NAME " + pnNum);
		panel_2.add(lblName);
		
		JLabel lblTokens = new JLabel("TOKENS");
		panel_2.add(lblTokens);
		
		JLabel lblDisplayVal = new JLabel("DISPLAY VAL");
		panel_2.add(lblDisplayVal);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel lblDisplay = new JLabel("DISPLAY");
		panel_1.add(lblDisplay);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblHand = new JLabel("Hand");
		panel.add(lblHand);
		
	}

}
