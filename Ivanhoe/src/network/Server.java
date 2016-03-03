package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

import Util.config;
import Util.timer;

public class Server implements Runnable {
	
	private HashMap<Integer, ServerThread> clients = null;
	private ServerSocket server = null;
	private Thread       thread = null;
	private int clientCount = 0;
	private int port;
	private int maxClients;
	private int gameState;
	
	
	private static final int LOBBY = 0;
	private static final int RUNNING = 1;
	private static final int ENDED = 2;
	
	public Server(){
		port = config.PORT;
		clients = new HashMap<Integer, ServerThread>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	public Server(int portNum){
		port = portNum;
		clients = new HashMap<Integer, ServerThread>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	
	private void bindToPort(){
		try{
			System.out.println("SERVER----------Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);  
			server.setReuseAddress(true);
			start(); 
		}
		catch(IOException ioe){
			System.err.println("Can not bind to port " + port + ": " + ioe.getMessage()); 
		}
	}
	public void start(){
		if (thread == null){
			thread = new Thread(this); 
			thread.start();
			gameState = LOBBY;
			System.out.println("SERVER----------Server started: "+ server +": "+thread.getId());
		}
	}
	
	public void run(){
		while (thread != null){
			try{
				System.out.println("SERVER----------Waiting for a client ..."); 
				addThread(server.accept());
			}
			catch(IOException ioe){
					System.err.println("Server accept error: " + ioe);
			}
		}
	}
	
	private void addThread(Socket socket){

		//accept client		
		System.out.println("SERVER----------Accepting Client: " + socket);
		try	{
			ServerThread serverThread = new ServerThread(this, socket);
			serverThread.open(); 
			serverThread.start();  
			timer.wait(1);
			//Check if should refuse
			if (clientCount >= maxClients){
				//too many clients
				System.err.println("Client refused: maximum " + maxClients + " reached.");
				serverThread.send("REJECT|Full Lobby");
				serverThread.close();
				serverThread = null;
			}
			else if (gameState != LOBBY){
				//LOBBY NOT OPEN
				System.err.println("Client refused: Lobby not open.");
				serverThread.send("REJECT|Lobby Closed");
				serverThread.close();
				serverThread = null;
			}
			else{
				//Lobby is open and not full
				serverThread.send("ACCEPT");
				clients.put(serverThread.getID(), serverThread);
				clientCount++; 
			}
		}
		catch(IOException ioe){
			System.err.println("Error opening thread: " + ioe); 
		}
	}
	public synchronized void removeThread(int ID){
		if (clients.containsKey(ID)){
			ServerThread toTerm = clients.get(ID);
			System.out.println("SERVER----------Removing client thread " + ID);
			clients.remove(ID);
			clientCount--;
			
			toTerm.close();
			toTerm = null;
			
		}
	}
	public synchronized void handle(int clientNum, String message){
		//TODO handle stuff
		ServerThread from = clients.get(clientNum);
		String toMessage = "";
		String fromMessage = "";
		
		String[] args =  message.split("\\|");
		String command = args[0];
		System.out.println("SERVER----------COMMAND:\t\""+command+"\"");
		System.out.println("SERVER----------COMMAND:\t\""+args.length+"\"");
		//if quit message
		switch (command){
		case "QUIT":
			//shut down this client
			from.close();
			break;
		case "SHUTDOWN":
			//shutdown the server
			this.shutdown();
			break;
		case "STARTTOURN":
			//startTourn()
			toMessage = args[1]+" colour tournament started";
			fromMessage = toMessage;
			break;
		case "DRAW":
			//draw a card
			toMessage = clientNum + " drew a card";
			fromMessage = "DRAW|G|1";
			break;
		case "PLAY":
			//play a card
			toMessage = clientNum + " played a card:"+args[1]+"|"+args[2];
			fromMessage = toMessage;
			break;
		case "WITHDRAW":
			//withdraw from tournament
			break;
		case "ENDTURN":
			//end turn
			toMessage = clientNum + " ended their turn";
			fromMessage = "Turn Over";
			break;
		case "IVANHOE":
			//interrupt actioncard
			break;
		case "WINTOKEN":
			//select which token to win if purple tournament won
			break;
		case "STARTGAME":
			//start the game
			gameState = RUNNING;
			toMessage = "Game Starting";
			fromMessage = toMessage;
			break;
		case "CONNECT":
			toMessage = clientNum + "joined the lobby";
			fromMessage = toMessage;
			break;
		case "CHAT":
			//chat and testing functionality
			toMessage = clientNum+":\t"+args[1];
			fromMessage = "";
			break;
		default:
			//invalid input
			from.send("INVALID");
			break;
		}
		
		
		
		//update all clients
		for (ServerThread to : clients.values()){
			if (from.getID() != to.getID()){
				to.send(toMessage);
			}
			else {
				to.send(fromMessage);
			}
		}
	}
	
	/** Shutdown the server cleanly */
	public void shutdown() {
		Set<Integer> keys = clients.keySet();

		if (thread != null) {
			thread = null;
		}

		try {
			for (Integer key : keys) {
				//clients.get(key).send("CLOSE");
				clients.get(key).close();
				clients.put(key, null);
			}
			clients.clear();
			if (server!=null){
				server.close();
				server=null;
			}
		} catch (IOException e) {
			System.err.println("Unexpected Error while shutting down: "+e.getMessage());
		}
		System.out.println("SERVER----------Server Shutdown cleanly "+ server);
		
	}
}
