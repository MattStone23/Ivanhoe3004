package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import Util.config;
import Util.timer;
import gameEntities.Engine;

public class Server implements Runnable {
	
	private HashMap<Integer, ServerThread> clients = null;
	private HashMap<Integer, Integer> players = null;
	private ServerSocket server = null;
	private Thread       thread = null;
	private int clientCount = 0;
	private int port;
	private int maxClients;
	private int gameState;
	private Engine engine;
	
	
	private static final int LOBBY = 0;
	private static final int RUNNING = 1;
	private static final int ENDED = 2;
	
	public Server(){
		port = config.PORT;
		clients = new HashMap<Integer, ServerThread>();
		players = new HashMap<Integer, Integer>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	public Server(int portNum){
		port = portNum;
		clients = new HashMap<Integer, ServerThread>();
		players = new HashMap<Integer, Integer>();
		maxClients = config.MAX_CLIENTS;
		
		bindToPort();
	}
	
	private void bindToPort(){
		try{
			System.out.println("SERVER----------Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port, 10, InetAddress.getByName(config.IP));
			server.setReuseAddress(true);
			System.out.println("IA:\t"+server.getInetAddress());
			System.out.println("LH:\t"+InetAddress.getLocalHost());
			System.out.println("HA:\t"+InetAddress.getLocalHost().getHostAddress());
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
				players.put(serverThread.getID(), clientCount+1);
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
		System.out.println("SERVER----------#ofARGS:\t\""+args.length+"\"");
		//if quit message
		try{
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
			if (engine!=null){
				//TODO args check
				engine.startTour(args);
			}
			break;
		case "DRAW":
			//draw a card
			//TODO check for single draw only
			if (engine!=null){
				toMessage = engine.draw().toString();
				clients.get(clientNum).send("CHAT|You drew "+toMessage);
			}
			break;
		case "PLAY":
			//play a card
			if (engine!=null){
				engine.playCard(args);
			}
			break;
		case "WITHDRAW":
			//withdraw from tournament
			if (engine!=null){
				engine.withdraw();
			}
			break;
		case "ENDTURN":
			//end turn
			if (engine!=null){
				engine.endTurn();
			}
			break;
		case "IVANHOE":
			//interrupt actioncard
			break;
		case "WINTOKEN":
			//select which token to win if purple tournament won
			break;
		case "STARTGAME":
			//start the game
			try{
				engine = new Engine(clientCount);
				gameState = RUNNING;
			}
			catch (IllegalArgumentException iae){
				engine = null;
				broadCast("INVALID|Not Enough Players");
			}
			break;
		case "CONNECT":
			toMessage = "CHAT|Player "+players.get(clientNum)+"("+clientNum + ") joined the lobby";
			broadCast(toMessage);
			break;
		case "CHAT":
			toMessage = "CHAT|Player "+players.get(clientNum)+"("+clientNum + ") said:"+args[1];
			broadCast(toMessage);
			break;
		default:
			//invalid input
			from.send("INVALID|Improper command");
			break;
		}
		}
		catch (IllegalArgumentException iae){
			from.send("INVALID|Improper arguments");
		}
		
		
		
		//update all clients
		if (engine!=null)
			broadCastUpdate();
	}
	
	public void broadCastUpdate(){
		for (Integer to : clients.keySet()){
			clients.get(to).send("GAMESTATE|"+engine.getGameStateForPlayer(players.get(to)));
		}
	}
	
	public void broadCast(String message){
		for (Integer to : clients.keySet()){
			clients.get(to).send(message);
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
