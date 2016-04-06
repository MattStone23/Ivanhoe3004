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

import Util.config;
import Util.timer;
import gameEntities.Card;
import gameEntities.GameBoard;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class GUIFrame extends JFrame {

	private JPanel contentPane;
	private JPanel pnlChat;
	private JPanel pnlHeader;
	private JPanel pnlMain;
	
	private JLabel lblCurrentPlayer;
	private JLabel lblDeck;
	private JLabel lblDiscard;
	
	private PlayerPanel[] playerPanels;
	
	private JTextField txtChat;
	private JTextArea txtChatArea;
	
	private GameBoard gameState;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameBoard state = new GameBoard(3);
					System.out.println(state.getGameStateForPlayer(1));
					state.setGameState("GAMESTATE|"+state.getGameStateForPlayer(1));
					GUIFrame frame = new GUIFrame(state);
					frame.setVisible(true);
					
					state.startTour('B');
					state.playCard(new Card(4,'B'), 0);
					state.getPlayers()[0].addStone('R');
					state.getPlayers()[0].addStone('B');
					state.getPlayers()[0].addStone('P');
					state.getPlayers()[1].addStone('G');
					state.getPlayers()[1].addStone('Y');
					state.withdraw(1);
					state.setGameState("GAMESTATE|"+state.getGameStateForPlayer(1));
					frame.setGameBoard(state);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIFrame(GameBoard gb) {
		gameState = gb;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1555, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setTitle("Ivanhoe");
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {1345, 200};
		gbl_contentPane.rowHeights = new int[] {50, 650};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		pnlChat = new JPanel();
		GridBagConstraints gbc_pnlChat = new GridBagConstraints();
		gbc_pnlChat.fill = GridBagConstraints.BOTH;
		gbc_pnlChat.gridheight = 2;
		gbc_pnlChat.anchor = GridBagConstraints.EAST;
		gbc_pnlChat.insets = new Insets(0, 0, 5, 5);
		gbc_pnlChat.gridx = 1;
		gbc_pnlChat.gridy = 0;
		contentPane.add(pnlChat, gbc_pnlChat);
		pnlChat.setLayout(new BorderLayout(0, 4));
		
		txtChatArea = new JTextArea();
		txtChatArea.setBackground(Color.BLACK);
		txtChatArea.setForeground(Color.WHITE);
		pnlChat.add(txtChatArea);
		
		txtChat = new JTextField();
		txtChat.setText("CHAT");
		txtChat.setForeground(Color.WHITE);
		txtChat.setBackground(Color.BLACK);
		pnlChat.add(txtChat, BorderLayout.SOUTH);
		txtChat.setColumns(10);
		
		pnlHeader = new JPanel();
		pnlHeader.setBackground(config.getColor(gameState.getCol()));
		pnlHeader.setLayout(null);
		GridBagConstraints gbc_pnlHeader = new GridBagConstraints();
		gbc_pnlHeader.fill = GridBagConstraints.BOTH;
		gbc_pnlHeader.anchor = GridBagConstraints.NORTH;
		gbc_pnlHeader.insets = new Insets(0, 0, 5, 5);
		gbc_pnlHeader.gridx = 0;
		gbc_pnlHeader.gridy = 0;
		contentPane.add(pnlHeader, gbc_pnlHeader);
		
		lblCurrentPlayer = new JLabel("Current Player: "+gameState.getTurn());
		lblCurrentPlayer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCurrentPlayer.setBounds(10, 11, 135, 33);
		pnlHeader.add(lblCurrentPlayer);
		
		lblDeck = new JLabel("DECK: "+ gameState.getDeckSize());
		lblDeck.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDeck.setBounds(194, 11, 92, 33);
		pnlHeader.add(lblDeck);
		
		lblDiscard = new JLabel("DISCARD: "+ gameState.getDiscardSize());
		lblDiscard.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDiscard.setBounds(328, 11, 109, 33);
		pnlHeader.add(lblDiscard);
		
		pnlMain = new JPanel();
		GridBagConstraints gbc_pnlMain = new GridBagConstraints();
		gbc_pnlMain.fill = GridBagConstraints.BOTH;
		gbc_pnlMain.gridx = 0;
		gbc_pnlMain.gridy = 1;
		contentPane.add(pnlMain, gbc_pnlMain);
		pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
		
		
		playerPanels = new PlayerPanel[gameState.getNumPlayers()];
		for (int x=0;x<gameState.getNumPlayers();x++){
			playerPanels[x] = new PlayerPanel(x,gameState.getPlayers()[x]);
			if (gameState.getTurn()==x){
				playerPanels[x].setBackground(Color.lightGray);
			}
			else if(gameState.getPlayers()[x].isWithdrawn()){
				playerPanels[x].setBackground(Color.darkGray);
			}
			else{
				playerPanels[x].setBackground(Color.gray);
			}
			pnlMain.add(playerPanels[x]);
		}
	}
	
	public void setGameBoard(GameBoard gb){
		gameState = gb;
		update();
		
		validate();
		repaint();
	}
	
	private void update(){
		//disable
		
		
		updateHeader();
		updatePlayers();
		
		
		//enable
	}
	
	private void updateHeader(){
		lblCurrentPlayer.setText("You are player X");
		lblDeck.setText("Deck: "+gameState.getDeckSize());
		lblDiscard.setText("Discard: "+gameState.getDiscardSize());
		pnlHeader.setBackground(config.getColor(gameState.getCol()));
	}
	
	public void updateChat(String message){
		txtChatArea.setText(txtChatArea.getText()+ "\n" + message);
	}
	
	private void updatePlayers(){
		while (pnlMain.getComponentCount()!=0){
			pnlMain.remove(0);
		}
		
		playerPanels = new PlayerPanel[gameState.getNumPlayers()];
		for (int x=0;x<gameState.getNumPlayers();x++){
			playerPanels[x] = new PlayerPanel(x,gameState.getPlayers()[x]);
			
			//paint backgrounds
			if (gameState.getTurn()==x){
				playerPanels[x].setBackground(Color.lightGray);
			}
			else if(gameState.getPlayers()[x].isWithdrawn()){
				playerPanels[x].setBackground(Color.darkGray);
			}
			else{
				playerPanels[x].setBackground(Color.gray);
			}
			
			//add panel
			pnlMain.add(playerPanels[x]);
		}
		
		for (int x=0;x<gameState.getNumPlayers();x++){
			playerPanels[x].repaint();
		}
			
		pnlMain.repaint();
	}
	

}
