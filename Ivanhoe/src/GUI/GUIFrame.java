package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;

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
		setBounds(100, 100, 1255, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Ivanhoe");
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {1000, 200};
		gbl_contentPane.rowHeights = new int[] {100, 600};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel pnlChat = new JPanel();
		GridBagConstraints gbc_pnlChat = new GridBagConstraints();
		gbc_pnlChat.fill = GridBagConstraints.BOTH;
		gbc_pnlChat.gridheight = 2;
		gbc_pnlChat.anchor = GridBagConstraints.EAST;
		gbc_pnlChat.insets = new Insets(0, 0, 5, 5);
		gbc_pnlChat.gridx = 1;
		gbc_pnlChat.gridy = 0;
		contentPane.add(pnlChat, gbc_pnlChat);
		pnlChat.setLayout(new BorderLayout(0, 4));
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		pnlChat.add(textArea);
		
		txtHello = new JTextField();
		txtHello.setText("CHAT");
		txtHello.setForeground(Color.WHITE);
		txtHello.setBackground(Color.BLACK);
		pnlChat.add(txtHello, BorderLayout.SOUTH);
		txtHello.setColumns(10);
		
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Color.GREEN);
		pnlHeader.setLayout(null);
		GridBagConstraints gbc_pnlHeader = new GridBagConstraints();
		gbc_pnlHeader.fill = GridBagConstraints.BOTH;
		gbc_pnlHeader.anchor = GridBagConstraints.NORTH;
		gbc_pnlHeader.insets = new Insets(0, 0, 5, 5);
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
		
		JPanel pnlMain = new JPanel();
		GridBagConstraints gbc_pnlMain = new GridBagConstraints();
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
