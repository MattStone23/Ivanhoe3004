package network;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import GUI.GUIFrame;
import Util.config;
import Util.logger;
import gameEntities.GameBoard;

public class Client {
	
	private String ipAddress;
	private int port;
	private Socket socket;
	private boolean messageRecieved;
	private DataOutputStream streamOut;
	private GameBoard gameState;
	private ClientThread clientThread;
	private String recentMessage;
	private logger networkLog;
	private int playerNum;
	private int playerID;
	private GUIFrame gui;
//	private int prompt;
	
	
	public Client(){
		port = config.PORT;
		ipAddress = config.IP;
		recentMessage = new String();
		gameState = null;
		
		connectToServer();
	}
	
	public Client(String ipAdd, int portNum){
		port = portNum;
		ipAddress = ipAdd;
		recentMessage = new String();
		gameState = null;
		
		connectToServer();
	}
	
	private void connectToServer(){
		System.out.println("CLIENT-----Establishing connection. Please wait ...");
	      try
	      {  socket = new Socket(InetAddress.getByName(ipAddress), port);
	         System.out.println("CLIENT-----Connected: " + socket);
	         networkLog = new logger(socket.getLocalPort()+"_clientLog");
	         start();
	      }
	      catch(UnknownHostException uhe)
	      {  System.err.println("Host unknown: " + uhe.getMessage()); }
	      catch(IOException ioe)
	      {  System.err.println("Unexpected exception: " + ioe.getMessage()); }
	}
	
	private void start() throws IOException {
	    streamOut = new DataOutputStream(socket.getOutputStream());
	    logger.println("Starting ClientThread...");
	    if (clientThread == null){
	    	clientThread = new ClientThread(this, socket);
	    }    
	}
	
	public void sendMessage(String message){
		try{
			networkLog.write("SENT: \t"+message);
			streamOut.writeUTF(message);
		}
		catch(IOException ioe){
			System.err.println("Unexpected exception: "+ ioe.getMessage());
		}
	}
	
	public void chat(String message){
		sendMessage("CHAT|"+message);
	}
	
	public void stop() {  
	      try { 
	    	  	if (streamOut != null) streamOut.close();

	    	  	if (socket != null) socket.close();
	    	  	
	    	  	if (networkLog != null) networkLog.close();

	    	  	this.networkLog = null;
	    	  	this.socket = null;
	    	  	this.streamOut = null;    	  
	      } catch(IOException ioe) {  
	    	  System.err.println("Error stopping client: "+ioe.getMessage());
	      }
	      clientThread.close();  
	   }
	
	public void handle(String message){
		networkLog.write("RECIEVED: \t"+message);
		String args[] = message.split("\\|");
		switch (args[0]){
		case "GAMESTATE":
			if (gameState==null){
				gameState=new GameBoard();
				System.out.println("Starting Game");
			}
			gameState.setGameState(message);
			
			if (gui == null){
				gui = new GUIFrame(gameState);
				gui.setVisible(true);
			}

			gameState.print();
			gui.setGameBoard(gameState);
			printClientData();
			break;
		case "CHAT":
			System.out.println(args[1]);
			recentMessage = args[1];
			messageRecieved = true;
			break;
		case "ACCEPT":
			playerNum = Integer.parseInt(args[1]);
			playerID = Integer.parseInt(args[2]);
			System.out.println("Connected to lobby");
			break;
		case "PROMPT":
			//TODO deal with this
			System.out.println("Need input");
			prompt(args);
			break;
		case "INVALID":
			System.err.println(args[1]);
			break;
		case "CLOSE":
			clientThread.close();
			break;
		case "REJECT":
			System.out.println("ERROR "+args[1]);
			playerNum = -1;
			playerID = -1;
			break;
		case "DESC":
			System.out.println(args[1]);
			break;
		default:
			System.err.println("UNRECOGNIZED RESPONSE FROM SERVER\n"+message);
		}
	}
	
	
	
	public synchronized String getMessage(){
		while (!messageRecieved){
			//loop
		}
		messageRecieved = false;
		return recentMessage;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public GameBoard getGameState() {
		return gameState;
	}
	public void printClientData(){
		//YOU ARE PLAYER X
		System.out.println("You are Player "+(playerNum-1));
		//VALID MOVES
		System.out.println("MOVES: "+gameState.getValidMoves(playerNum-1));
	}
	
	private void prompt(String[] args){
		if (1==Integer.parseInt(args[1])){
			//won purple tourn
			System.out.println("You won a purple tournament. Please reply with a colour to win. \"WINTOKEN|C\"");
//			prompt=1;
		}
		else if(2==Integer.parseInt(args[1])){
			if (Boolean.parseBoolean(args[2])){
				//LOST WITH MAIDEN
				System.out.println("You lost a tournament with a maiden out. Please reply with a colour to lose. \"LOSETOKEN|C\"");
//				prompt=1;
			}
			else{
				sendMessage("ENDTURN");
			}
		}
	}
	
	
}
