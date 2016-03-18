package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;

public class GUIFrame extends JFrame {

	private JPanel contentPane;
	private PlayerPanel[] playerPanels;
	private JTextField txtHello;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFrame frame = new GUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Ivanhoe");
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {300, 0};
		gbl_contentPane.rowHeights = new int[] {20, 300};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Color.GREEN);
		pnlHeader.setLayout(null);
		GridBagConstraints gbc_pnlHeader = new GridBagConstraints();
		gbc_pnlHeader.insets = new Insets(0, 0, 5, 5);
		gbc_pnlHeader.fill = GridBagConstraints.BOTH;
		gbc_pnlHeader.gridx = 0;
		gbc_pnlHeader.gridy = 0;
		contentPane.add(pnlHeader, gbc_pnlHeader);
		
		JLabel lblCurrentPlayer = new JLabel("Current Player");
		lblCurrentPlayer.setBounds(10, 11, 46, 14);
		pnlHeader.add(lblCurrentPlayer);
		
		JLabel lblDeck = new JLabel("DECK");
		lblDeck.setBounds(194, 11, 46, 14);
		pnlHeader.add(lblDeck);
		
		JLabel lblDiscard = new JLabel("DISCARD");
		lblDiscard.setBounds(328, 11, 46, 14);
		pnlHeader.add(lblDiscard);
		
		JPanel pnlChat = new JPanel();
		pnlChat.setLayout(null);
		GridBagConstraints gbc_pnlChat = new GridBagConstraints();
		gbc_pnlChat.gridheight = 2;
		gbc_pnlChat.insets = new Insets(0, 0, 5, 0);
		gbc_pnlChat.fill = GridBagConstraints.BOTH;
		gbc_pnlChat.gridx = 1;
		gbc_pnlChat.gridy = 0;
		contentPane.add(pnlChat, gbc_pnlChat);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setBounds(7, 7, 141, 426);
		pnlChat.add(textArea);
		
		txtHello = new JTextField();
		txtHello.setText("CHAT");
		txtHello.setForeground(Color.WHITE);
		txtHello.setBackground(Color.BLACK);
		txtHello.setBounds(7, 444, 141, 20);
		pnlChat.add(txtHello);
		txtHello.setColumns(10);
		
		JPanel pnlMain = new JPanel();
		GridBagConstraints gbc_pnlMain = new GridBagConstraints();
		gbc_pnlMain.insets = new Insets(0, 0, 0, 5);
		gbc_pnlMain.fill = GridBagConstraints.BOTH;
		gbc_pnlMain.gridx = 0;
		gbc_pnlMain.gridy = 1;
		contentPane.add(pnlMain, gbc_pnlMain);
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
		
		
		playerPanels = new PlayerPanel[5];
		for (int x=0;x<5;x++){
			playerPanels[x] = new PlayerPanel(x);
			pnlMain.add(playerPanels[x]);
		}
	}
}
